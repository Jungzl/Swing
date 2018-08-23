import javax.swing.*;
import java.awt.*;

/**
 * @author Jungzl
 * @program Swing
 * @description 尺寸化框架
 * @create 2018-08-15 01:03
 */
public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SizedFrame frame = new SizedFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame(){
        //get screen dimensions

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenHeight=screenSize.height;
        int screenWidth=screenSize.width;

        //set frame width,height and let platform pick screen location

        setSize(screenWidth/2,screenHeight/2);
        setLocationByPlatform(true);

        //set frame icon and title
        Image img=kit.getImage("E:\\杂项\\神奇宝贝球像素图\\神奇宝贝球\\cherish-ball.png");
        setIconImage(img);
        setTitle("SizedFrame");
    }
}