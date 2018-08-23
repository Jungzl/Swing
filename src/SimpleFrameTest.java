import javax.swing.*;

/**
 * @author Jungzl
 * @program Swing
 * @description 简单的空白框架
 * @create 2018-08-15 00:48
 */
public class SimpleFrameTest {
    public static void main(String[] args) {
        SimpleFrame frame = new SimpleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class SimpleFrame extends JFrame{

    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=300;

    public SimpleFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}