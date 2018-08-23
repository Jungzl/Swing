import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Jungzl
 * @program Swing
 * @description 滑块测试
 * @create 2018-08-21 05:11
 */
public class SliderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SliderFrame frame = new SliderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


/**
 * A frame with many sliders and a text field to show slider values.
 */
class SliderFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 330;
    public static final int DEFAULT_HEIGHT = 480;

    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener listener;

    public SliderFrame() throws HeadlessException {
        setTitle("SliderTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //common listener for all sliders
        listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //update text text field when the slider value changes
                JSlider source = (JSlider) e.getSource();
                textField.setText("" + source.getValue());
            }
        };

        //add a plain slider

        JSlider slider = new JSlider();
        addSlide(slider, "Plain");

        //add a slider with major and minor ticks

        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider, "Ticks");

        //add a slider that snaps to ticks

        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider, "Snap to ticks");

        //add a slider with no track

        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(false);
        addSlide(slider, "No track");

        //add an inverted slider

        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlide(slider, "Inverted");

        //add a slider with numeric labels
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider, "Labels");

        //add a slider with alphabetic labels

        slider = new JSlider();
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        Dictionary<Integer, Component> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));

        slider.setLabelTable(labelTable);
        addSlide(slider, "Custom labels");

        //add a slider with icon labels

        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        labelTable = new Hashtable<>();

        //add card images
        labelTable.put(0, new JLabel(new ImageIcon("E:\\pokemonBalls\\cherish-ball.png")));
        labelTable.put(20, new JLabel(new ImageIcon("E:\\pokemonBalls\\dive-ball.png")));
        labelTable.put(40, new JLabel(new ImageIcon("E:\\pokemonBalls\\heal-ball.png")));
        labelTable.put(60, new JLabel(new ImageIcon("E:\\pokemonBalls\\net-ball.png")));
        labelTable.put(80, new JLabel(new ImageIcon("E:\\pokemonBalls\\master-ball.png")));
        labelTable.put(100, new JLabel(new ImageIcon("E:\\pokemonBalls\\quick-ball.png")));

        slider.setLabelTable(labelTable);
        addSlide(slider, "Icon labels");

        //add the text field that displays the slider value

        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
    }

    /**
     * Adds a slider to the slider panel and hooks up the listener
     *
     * @param s           the slider
     * @param description the slider description
     */
    public void addSlide(JSlider s, String description) {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        sliderPanel.add(panel);
    }
}