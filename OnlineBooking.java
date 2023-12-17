import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineBooking implements ActionListener {
    private static JTextField txtIdNo, reservedForTextField, contactNumberTextField, dateTextField,
    timeTextField;
    private static JButton confirmButton, cancelButton;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static JMenuItem homeServiceMenuItem;
    
    // public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
                    // OnlineBooking onlineBooking = new OnlineBooking();
                    // onlineBooking.initializeUI();
            // });
    // }
    public void initializeUI() {
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu navigateMenu = new JMenu("Option");
        menuBar.add(navigateMenu);

        homeServiceMenuItem = new JMenuItem("Home Service");
        navigateMenu.add(homeServiceMenuItem);
        homeServiceMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showHomeServiceInterface();
                }
            });

        JPanel homeServicePanel = new JPanel(new BorderLayout());
        homeServicePanel.setBorder(BorderFactory.createTitledBorder("ONLINE BOOKING"));
        JLabel lbTitle = new JLabel("STRANDVOGUE");
        lbTitle.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        lbTitle.setBounds(310, 10, 300, 20);
        panel.add(lbTitle);
        // ID No.
        JLabel lblIdNo = new JLabel("ID No.");
        lblIdNo.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblIdNo.setBounds(250, 110, 70, 20);
        panel.add(lblIdNo);
        txtIdNo = new JTextField();
        txtIdNo.setBounds(250, 130, 300, 25); // Adjusted width
        txtIdNo.setColumns(10);
        panel.add(txtIdNo);
        // Reserved for
        JLabel lblReservedFor = new JLabel("Reserved for");
        lblReservedFor.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblReservedFor.setBounds(250, 160, 100, 20);
        panel.add(lblReservedFor);
        reservedForTextField = new JTextField();
        reservedForTextField.setBounds(250, 180, 300, 25); // Adjusted width
        reservedForTextField.setColumns(10);
        panel.add(reservedForTextField);
        // Contact Number
        JLabel lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblContactNumber.setBounds(250, 210, 120, 20); // Adjusted width
        panel.add(lblContactNumber);
        contactNumberTextField = new JTextField();
        contactNumberTextField.setBounds(250, 230, 300, 25); // Adjusted width
        contactNumberTextField.setColumns(10);
        panel.add(contactNumberTextField);
        // Date
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblDate.setBounds(250, 260, 100, 20);
        panel.add(lblDate);
        dateTextField = new JTextField("MM/DD/YY");
        dateTextField.setBounds(250, 280, 130, 25); // Adjusted width
        dateTextField.setColumns(6);
        panel.add(dateTextField);
        // Time
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblTime.setBounds(400, 260, 100, 20);
        panel.add(lblTime);
        timeTextField = new JTextField("00:00");
        timeTextField.setBounds(400, 280, 130, 25); // Adjusted width
        timeTextField.setColumns(6);
        panel.add(timeTextField);
        // Confirm and Cancel buttons
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        confirmButton.setBounds(240, 340, 125, 30);
        confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleConfirmButtonClick();
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
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "ONLINE BOOKING"));
        panelAdd.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);
    }

    private static void showHomeServiceInterface() {
        frame.setVisible(false);
        HomeService homeService = new HomeService();
        homeService.initializeUI();
    }

    private void handleConfirmButtonClick() {
        // Validate input fields
        if (isFieldEmpty(txtIdNo, "ID No.") || isFieldEmpty(reservedForTextField, "Reserved for")
        || isFieldEmpty(contactNumberTextField, "Contact Number")
        || isFieldEmpty(dateTextField, "Date") || isFieldEmpty(timeTextField, "Time")) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Process the confirmation logic
            // For demonstration, just showing a message
            JOptionPane.showMessageDialog(frame, "Book!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showServiceMenu();
        }
    }

    private void showServiceMenu() {
        frame.setVisible(false);
        ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.initializeUI();
    }

    private boolean isFieldEmpty(JTextField textField, String fieldName) {
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, fieldName + " cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeServiceMenuItem) {
            showHomeServiceInterface();
        }
        // Handle button click events if needed
    }
}