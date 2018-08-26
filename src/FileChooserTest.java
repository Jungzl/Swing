import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * @author Jungzl
 * @program Swing
 * @description 文件选择测试
 * @create 2018-08-27 04:28
 */
public class FileChooserTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
            WebLookAndFeel.globalControlFont  = new FontUIResource("宋体",0, 12);
            ImageViewerFrame frame = new ImageViewerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * A frame that has a menu for loading an image and a display area for the loaded image.
 */
class ImageViewerFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private JFileChooser chooser;

    public ImageViewerFrame() throws HeadlessException {
        setTitle("FileChooserTest");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //set up menu bar
        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu=new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem=new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem=new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(e ->
        {
           System.exit(0);
        });

        //use a label to display the images
        label=new JLabel();
        add(label);

        //set up file chooser
        chooser=new JFileChooser();

        //accept all images files ending with .jpg, .jpeg, .png, .gof
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files","jpg","jpeg","png","gif"
        );
        chooser.setFileFilter(filter);

        chooser.setAccessory(new ImagePreviewer(chooser));

        chooser.setFileView(new FileIconView(filter,new ImageIcon("F:\\UI\\material-design-icons-master\\image\\1x_web\\ic_color_lens_black_24dp.png")));
    }

    /**
     * This is the listener for the File->Open menu item.
     */
    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            chooser.setCurrentDirectory(new File("."));

            //show file chooser dialog
            int result =chooser.showOpenDialog(ImageViewerFrame.this);

            //if image file accepted, set it as icon of the label
            if (result==JFileChooser.APPROVE_OPTION) {
                String name=chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
            }
        }
    }

}

/**
 * A file that displays an icon for all files that match a file filter.
 */
class FileIconView extends FileView {
    private FileFilter filter;
    private Icon icon;

    /**
     * Constructs a FileIconView
     *
     * @param filter a file filter--all files that this filter accepts will be
     *               shown with the icon.
     * @param icon   --the icon shown with all accepted files.
     */
    public FileIconView(FileFilter filter, Icon icon) {
        this.filter = filter;
        this.icon = icon;
    }

    @Override
    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f)) {
            return icon;
        }
        else {
            return null;
        }
    }
}

/**
 * A file chooser accessory that previews images.
 */
class ImagePreviewer extends JLabel {
    /**
     * Constructs an ImagePreviewer.
     *
     * @param chooser the file chooser whose property changes trigger
     *                an image change in this previewer
     */
    public ImagePreviewer(JFileChooser chooser) {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createEtchedBorder());

        chooser.addPropertyChangeListener(evt ->
        {
            if (evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
                //the user has selected a new file
                File f = (File) evt.getNewValue();
                if (f == null) {
                    setIcon(null);
                    return;
                }

                //read the image into an icon
                ImageIcon icon = new ImageIcon(f.getPath());

                //if the icon is too large to fit, scale it
                if (icon.getIconWidth() > getWidth()) {
                    icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                }

                setIcon(icon);
            }
        });
    }
}