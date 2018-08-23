import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

/**
 * @author Jungzl
 * @program Swing
 * @description 对话框测试
 * @create 2018-08-24 05:12
 */
public class OptionDialogTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OptionDialogFrame frame = new OptionDialogFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A panel with radio buttons inside a titled border.
 */
class ButtonPanel extends JPanel {
    private ButtonGroup group;
    
    public ButtonPanel(String title, String... options) {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        group = new ButtonGroup();
        
        //make one radio button for each option
        for (String option : options) {
            JRadioButton b = new JRadioButton(option);
            b.setActionCommand(option);
            add(b);
            group.add(b);
            b.setSelected(option.equals(options[0]));
        }
    }
    
    /**
     * Gets the currently selected option
     *
     * @return the label of the currently selected radio button.
     */
    public String getSelection() {
        return group.getSelection().getActionCommand();
    }
}

/**
 * A frame that contains settings for selecting various option dialogs.
 */
class OptionDialogFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    
    private ButtonPanel typePanel;
    private ButtonPanel messagePanel;
    private ButtonPanel messageTypePanel;
    private ButtonPanel optionTypePanel;
    private ButtonPanel optionsPanel;
    private ButtonPanel inputPanel;
    
    private String messageString = "Message";
    private Icon messageIcon = new ImageIcon("E:\\pokemonBalls\\dive-ball.png");
    private Object messageObject = new Date();
    private Component messageComponent = new SampleComponent();
    
    public OptionDialogFrame() throws HeadlessException {
        setTitle("OptionDialogTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 3));
        
        typePanel = new ButtonPanel("Type",
                "Message",
                "Confirm",
                "Option",
                "Input");
        messageTypePanel = new ButtonPanel("Message Type",
                "ERROR_MESSAGE",
                "INFORMATION_MESSAGE",
                "WARNING_MESSAGE",
                "QUESTION_MESSAGE",
                "PLAIN_MESSAGE");
        messagePanel = new ButtonPanel("Message",
                "String",
                "Icon",
                "Component",
                "Other",
                "Object[]");
        optionTypePanel = new ButtonPanel("Confirm",
                "DEFAULT_OPTION",
                "YES_NO_OPTION",
                "YES_NO_CANCEL_OPTION",
                "OK_CANCEL_OPTION");
        optionsPanel = new ButtonPanel("Option",
                "String[]",
                "Icon[]",
                "Object[]");
        inputPanel = new ButtonPanel("Input",
                "Text field",
                "Combo box");
        
        gridPanel.add(typePanel);
        gridPanel.add(messageTypePanel);
        gridPanel.add(messagePanel);
        gridPanel.add(optionTypePanel);
        gridPanel.add(optionsPanel);
        gridPanel.add(inputPanel);
        
        //add a panel with a Show button
        
        JPanel showPanel = new JPanel();
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ShowAction());
        showPanel.add(showButton);
        
        add(gridPanel, BorderLayout.CENTER);
        add(showPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Gets the currently selected message.
     *
     * @return a string,icon,component or object array,depending on the Message panel selection
     */
    public Object getMessage() {
        String s = messagePanel.getSelection();
        if (s.equals("String")) {
            return messageString;
        }
        else if (s.equals("Icon")) {
            return messageIcon;
        }
        else if (s.equals("Component")) {
            return messageComponent;
        }
        else if (s.equals("Object[]")) {
            return new Object[]{messageString, messageIcon, messageComponent, messageObject};
        }
        else if (s.equals("Other")) {
            return messageObject;
        }
        else {
            return null;
        }
    }
    
    /**
     * Gets the currently selected options.
     *
     * @return an array of strings,icons or objects, depending on the Option panel selection
     */
    public Object[] getOptions() {
        String s = optionsPanel.getSelection();
        if (s.equals("String[]")) {
            return new String[]{"Yellow", "Blue", "Red"};
        }
        else if (s.equals("Icon[]")) {
            return new Icon[]{new ImageIcon(".\\src\\pokemonBalls\\poke-ball.png"),
                    new ImageIcon(".\\src\\pokemonBalls\\dive-ball.png"),
                    new ImageIcon(".\\src\\pokemonBalls\\cherish-ball.png")};
        }
        else if (s.equals("Object[]")) {
            return new Object[]{messageString, messageIcon, messageComponent, messageObject};
        }
        else {
            return null;
        }
    }
    
    /**
     * Gets the selected message or option type
     *
     * @param panel the Message Type or Confirm panel
     * @return the selected XXX_MESSAGE orXXX_OPTION construct from the JOptionPane class
     */
    public int getType(ButtonPanel panel) {
        String s = panel.getSelection();
        try {
            return JOptionPane.class.getField(s).getInt(null);
        }
        catch (Exception e) {
            return -1;
        }
    }
    
    /**
     * The action listener for the Show button shows a Confirm,Input,Message
     * or Option dialog depending on the Type panel selection.
     */
    private class ShowAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (typePanel.getSelection().equals("Confirm")) {
                JOptionPane.showConfirmDialog(OptionDialogFrame.this, getMessage(),
                        "Title", getType(optionTypePanel), getType(messageTypePanel));
            }
            else if (typePanel.getSelection().equals("Input")) {
                if (inputPanel.getSelection().equals("Text field")) {
                    JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(),
                            "Title", getType(messageTypePanel));
                }
                else {
                    JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(),
                            "Title", getType(messageTypePanel), null,
                            new String[]{"Yellow", "Blue", "Red"}, "Blue");
                }
            }
            else if (typePanel.getSelection().equals("Message")) {
                JOptionPane.showMessageDialog(OptionDialogFrame.this, getMessage(),
                        "Title", getType(messageTypePanel));
            }
            else if (typePanel.getSelection().equals("Option")) {
                JOptionPane.showOptionDialog(OptionDialogFrame.this, getMessage(),
                        "Title", getType(optionTypePanel), getType(messageTypePanel),
                        null, getOptions(), getOptions()[0]);
            }
        }
    }
}

class SampleComponent extends JComponent {
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
        g2.setPaint(Color.YELLOW);
        g2.fill(rect);
        g2.setPaint(Color.BLUE);
        g2.draw(rect);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(10, 10);
    }
}