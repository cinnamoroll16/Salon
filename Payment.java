import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Payment implements ActionListener {
    private static JTextField txtGcash, nameTextField, referenceNoTextField;
    private static JButton confirmButton, cancelButton;
    private static JCheckBox confirmationCheckBox;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.initializeUI();
    }

    public void initializeUI() {
        frame = new JFrame("Payment");
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
        // ID No.
        JLabel label = new JLabel("GCASH");
        label.setFont(new Font("SERIF", Font.PLAIN, 25));
        label.setBounds(150, 110, 100, 20);
        panel.add(label);
        // Reserved for
        JLabel lblName = new JLabel("NAME:");
        lblName.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblName.setBounds(230, 190, 100, 20);
        panel.add(lblName);
        nameTextField = new JTextField();
        nameTextField.setBounds(300, 185, 300, 35); // Adjusted width
        nameTextField.setColumns(10);
        panel.add(nameTextField);
        // Address
        JLabel lblReferenceNo = new JLabel("REFERENCE NO.:");
        lblReferenceNo.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblReferenceNo.setBounds(145, 220, 150, 30);
        panel.add(lblReferenceNo);
        referenceNoTextField = new JTextField();
        referenceNoTextField.setBounds(300, 220, 300, 35); // Adjusted width
        referenceNoTextField.setColumns(10);
        panel.add(referenceNoTextField);
        
        confirmationCheckBox = new JCheckBox("I hereby confirm that the information is true");
        confirmationCheckBox.setFont(new Font("SERIF", Font.PLAIN, 12));
        confirmationCheckBox.setBounds(280, 260, 350, 20);
        confirmationCheckBox.setBackground(Color.WHITE); 
        confirmationCheckBox.setFocusPainted(false); 
        panel.add(confirmationCheckBox);
        
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        confirmButton.setBounds(240, 340, 125, 30);
        confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
        panel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        cancelButton.setBounds(450, 340, 125, 30);
        cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle cancel button click in OnlineBooking
                }
            });
        panel.add(cancelButton);
        
        frame.setVisible(true);
        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "PAYMENT"));
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