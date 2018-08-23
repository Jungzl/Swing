import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jungzl
 * @program Swing
 * @description 文本框组件测试
 * @create 2018-08-19 01:51
 */
public class TextComponentTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final TextComponentFrame frame = new TextComponentFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame with sample text component.
 */
class TextComponentFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;

    public TextComponentFrame() throws HeadlessException {
        setTitle("TextComponentTest");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        final JTextField textField=new JTextField();
        final JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(new JLabel("User name: ",SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password: ",SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel,BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(8, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane,BorderLayout.CENTER);

        //add button to append text into the text area

        JPanel southPanel = new JPanel();

        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("User name: "+textField.getText()+"\nPassword: "+new String(passwordField.getPassword())+"\n");
            }
        });

        add(southPanel,BorderLayout.SOUTH);

        //add a text area with scrollbars

    }
}