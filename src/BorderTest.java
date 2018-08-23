import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jungzl
 * @program Swing
 * @description 边框测试
 * @create 2018-08-21 03:19
 */
public class BorderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final BorderFrame frame = new BorderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame with radio buttons to pick a border style.
 */
class BorderFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;

    private JPanel demoPanel;
    private JPanel buttonPanel;
    private ButtonGroup group;

    public BorderFrame() throws HeadlessException {
        setTitle("BorderTest");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        demoPanel=new JPanel();
        buttonPanel=new JPanel();
        group=new ButtonGroup();

        //凹斜面
        addRadioButton("Lowered bevel",BorderFactory.createLoweredBevelBorder());
        //凸斜面
        addRadioButton("Raised bevel",BorderFactory.createRaisedBevelBorder());
        //蚀刻
        addRadioButton("Etched",BorderFactory.createEtchedBorder());
        //直线
        addRadioButton("Line",BorderFactory.createLineBorder(Color.BLACK));
        //磨砂(不光滑)
        addRadioButton("Matte",BorderFactory.createMatteBorder(3,3,3,3,Color.BLACK));
        //空
        addRadioButton("Empty",BorderFactory.createEmptyBorder());

        Border etched=BorderFactory.createEtchedBorder();
        Border titled=BorderFactory.createTitledBorder(etched,"Border types");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2,1));
        add(buttonPanel);
        add(demoPanel);
    }

    public void addRadioButton(String buttonName, final Border b){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demoPanel.setBorder(b);
            }
        });
        group.add(button);
        buttonPanel.add(button);
    }
}