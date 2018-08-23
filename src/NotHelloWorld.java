import javax.swing.*;
import java.awt.*;

/**
 * @author Jungzl
 * @program Swing
 * @description 不是你好世界
 * @create 2018-08-15 01:58
 */
public class NotHelloWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NotHelloWorldFrame frame = new NotHelloWorldFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame that contains a message panel
 */
class NotHelloWorldFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    public NotHelloWorldFrame() {
        setTitle("NotHelloWorld");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //add panel to frame
        NotHelloWorldPanel panel = new NotHelloWorldPanel();
        add(panel);
    }
}

/**
 * A panel that displays a message.
 */
class NotHelloWorldPanel extends JPanel {
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 75;

    @Override
    public void paintComponent(Graphics g) {
        g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
    }
}