import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jungzl
 * @program Swing
 * @description 复选框
 * @create 2018-08-19 02:45
 */
public class CheckBoxTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final CheckBoxFrame frame = new CheckBoxFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame with a sample text label and check boxes for selecting font attributes.
 */
class CheckBoxFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    private JLabel label;
    private JCheckBox bold;
    private JCheckBox italic;

    private static final int FONT_SIZE = 12;

    public CheckBoxFrame() throws HeadlessException {
        setTitle("CheckBoxTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //add the sample text label

        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE));
        add(label, BorderLayout.CENTER);

        //this listeners sets the font attribute of the label to the check box state
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = 0;
                if (bold.isSelected()) {
                    mode += Font.BOLD;
                }
                if (italic.isSelected()) {
                    mode += Font.ITALIC;
                }
                label.setFont(new Font("Serif", mode, FONT_SIZE));
            }
        };

        //add the check boxes

        JPanel buttonPanel = new JPanel();

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        buttonPanel.add(bold);

        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);
        buttonPanel.add(italic);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
}