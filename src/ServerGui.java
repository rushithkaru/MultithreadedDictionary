
import javax.swing.*;
import java.awt.*;

public class ServerGui implements Runnable {
    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel jLabel;
    private String text = null;
    private String recentAction = null;


    public ServerGui(){
        initialize();
        frame.setVisible(true);

    }

    public void initialize(){
        frame = new JFrame();
        frame.setTitle("Server window");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        textArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 5, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 3;
        frame.getContentPane().add(textArea, gbc_textArea);
        textArea.setText("No connections yet");

        jLabel = new JLabel("Updates will appear here");
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.insets = new Insets(5,5,5,0);
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        frame.getContentPane().add(jLabel,gbcLabel);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setRecentAction(String recentAction) {
        this.recentAction = recentAction;
    }

    public String getRecentAction() {
        return recentAction;
    }

    @Override
    public void run() {
        while (true) {
            if (text != null) {
                textArea.setText(text);
                jLabel.setText(recentAction);
            }
            else {
                textArea.setText("No update");
                //System.out.println("svs");
            }

        }
    }
}
