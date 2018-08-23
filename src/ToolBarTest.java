import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Jungzl
 * @program Swing
 * @description 工具栏测试
 * @create 2018-08-22 03:04
 */
public class ToolBarTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToolBarFrame frame = new ToolBarFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame with a toolbar and menu for color changes.
 */
class ToolBarFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 600;

    private JPanel panel;

    public ToolBarFrame() throws HeadlessException {
        setTitle("ToolBarTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //add a panel for color change

        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        //set up actions

        Action blueAction = new ColorAction("Blue", new ImageIcon("E:\\pokemonBalls\\dive-ball.png"), Color.BLUE);
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("E:\\pokemonBalls\\fast-ball.png"), Color.YELLOW);
        Action redAction = new ColorAction("Red", new ImageIcon("E:\\pokemonBalls\\cherish-ball.png"), Color.RED);

        Action exitAction = new AbstractAction("Exit", new ImageIcon("F:\\UI\\material-design-icons-master\\action\\ios\\ic_exit_to_app.imageset\\ic_exit_to_app.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");

        //populate tool bar
        JToolBar bar = new JToolBar();
        bar.add(blueAction);
        bar.add(yellowAction);
        bar.add(redAction);
        bar.addSeparator();
        bar.add(exitAction);
        add(bar, BorderLayout.NORTH);

        //populate menu
        JMenu menu = new JMenu("Color");
        menu.add(blueAction);
        menu.add(yellowAction);
        menu.add(redAction);
        menu.add(exitAction);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    /**
     * The color action sets the background of the frame to a given color.
     */
    class ColorAction extends AbstractAction {

        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, name + " background");
            putValue("Color", c);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("Color");
            panel.setBackground(c);
        }
    }
}