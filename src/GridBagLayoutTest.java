import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jungzl
 * @program Swing
 * @description 网格管理测试
 * @create 2018-08-22 04:57
 */
public class GridBagLayoutTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GridBagLayoutFrame frame = new GridBagLayoutFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame that uses a grid bag layout to arrange font selection components.
 */
class GridBagLayoutFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    private JComboBox face;
    private JComboBox size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JTextArea sample;
    
    public GridBagLayoutFrame() throws HeadlessException {
        setTitle("GridBagLayoutTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        ActionListener listener = new FontAction();
        
        //construct components
        
        JLabel faceLabel = new JLabel("Face: ");
        
        face = new JComboBox(new String[]{"Serif", "SanSerif", "Monospaced", "Dialog", "DialogInput"});
        face.addActionListener(listener);
        
        JLabel sizeLabel = new JLabel("Size: ");
        
        size = new JComboBox(new String[]{"8", "10", "12", "15", "18", "24", "36", "48"});
        size.addActionListener(listener);
        
        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        
        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);
        
        sample = new JTextArea();
        sample.setText("The quick brown fox jumps over the lazy dog");
        sample.setEditable(false);
        sample.setLineWrap(true);
        sample.setBorder(BorderFactory.createEtchedBorder());
        
        //add components to grid,using GBC convenience class
        
        add(faceLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(face, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
        add(sizeLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(size, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
        add(bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        add(italic, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        add(sample, new GBC(2, 0, 1, 4).setFill(GBC.BOTH).setWeight(100, 100));
    }
    
    /**
     * An action listener that changes the font of the sample text.
     */
    private class FontAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String fontFace = (String) face.getSelectedItem();
            int fontStyle = (bold.isSelected() ? Font.BOLD : 0) +
                    (italic.isSelected() ? Font.ITALIC : 0);
            int fontSize = Integer.parseInt((String) size.getSelectedItem());
            Font font = new Font(fontFace, fontStyle, fontSize);
            sample.setFont(font);
            sample.repaint();
        }
    }
}