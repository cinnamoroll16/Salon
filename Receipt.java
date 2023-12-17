import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receipt implements ActionListener {
    private static JTextField txtGcash, nameTextField, referenceNoTextField;
    private static JButton confirmButton, cancelButton;
    private static JCheckBox confirmationCheckBox;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.initializeUI();
    }
    public void initializeUI() {
        frame = new JFrame("RECEIPT");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel lblTitle1 = new JLabel("STRANDVOGUE");
        lblTitle1.setFont(new Font("SERIF", Font.PLAIN, 20));
        lblTitle1.setBounds(310, 10, 300, 20);
        panel.add(lblTitle1);

        //display the name,date,time,address
        //display the type of service inputted
        // display the 
        
        
        
        frame.setVisible(true);
        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Receipt"));
        panelAdd.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events if needed
    }
}