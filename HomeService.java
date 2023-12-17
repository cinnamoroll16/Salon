import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HomeService implements ActionListener {
    private static JTextField txtIdNo, reservedForTextField, addressTextField, contactNumberTextField,
    dateTextField, timeTextField;
    private static JButton confirmButton, cancelButton;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    
    // public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
                    // HomeService homeService = new HomeService();
                    // homeService.initializeUI();
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
        panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 6)); // Adjusted border thickness
        frame.add(panel);

        // Create a JMenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create a JMenu
        JMenu navigateMenu = new JMenu("Navigate");
        menuBar.add(navigateMenu);

        // Create a JMenuItem for directing to OnlineBooking class
        JMenuItem onlineBookingMenuItem = new JMenuItem("Go to Online Booking");
        navigateMenu.add(onlineBookingMenuItem);
        onlineBookingMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle menu item click to go to the SignIn class
                    frame.dispose(); // Close the current frame
                    OnlineBooking onlineBooking = new OnlineBooking();
                    onlineBooking.initializeUI();
                }
            });

        JPanel homeServicePanel = new JPanel(new BorderLayout());
        homeServicePanel.setBorder(BorderFactory.createTitledBorder("HOME SERVICE"));

        // Labels and TextFields
        // Labels and TextFields
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
        // Address
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblAddress.setBounds(250, 210, 100, 20);
        panel.add(lblAddress);
        addressTextField = new JTextField();
        addressTextField.setBounds(250, 230, 300, 25); // Adjusted width
        addressTextField.setColumns(10);
        panel.add(addressTextField);
        // Contact Number
        JLabel lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblContactNumber.setBounds(250, 260, 100, 20);
        panel.add(lblContactNumber);
        contactNumberTextField = new JTextField();
        contactNumberTextField.setBounds(250, 280, 300, 25); // Adjusted width
        contactNumberTextField.setColumns(10);
        panel.add(contactNumberTextField);
        // Date
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblDate.setBounds(250, 310, 100, 20);
        panel.add(lblDate);
        dateTextField = new JTextField("MM/DD/YY");
        dateTextField.setBounds(250, 330, 120, 25); // Adjusted width
        dateTextField.setColumns(6); // Set the number of columns for the text field
        panel.add(dateTextField);
        // Time
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        lblTime.setBounds(430, 310, 100, 20);
        panel.add(lblTime);
        timeTextField = new JTextField("00:00");
        timeTextField.setBounds(430, 330, 120, 25); // Adjusted width
        timeTextField.setColumns(6); // Set the number of columns for the text field
        panel.add(timeTextField);
        // Confirm and Cancel buttons
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        confirmButton.setBounds(240, 380, 125, 30);
        confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleConfirmButtonClick();
                }
            });
        panel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        cancelButton.setBounds(450, 380, 125, 30);
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

        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "HOME SERVICE"));
        panelAdd.setBounds(25, 50, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            if (areFieldsEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Handle Confirm button click
                    storeInformation();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error storing information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == cancelButton) {
            // Handle Cancel button click
            panel.setVisible(true);
            panelAdd.setVisible(false);
        }
    }

    private void handleConfirmButtonClick() {
        // Validate input fields
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Process the confirmation logic
            // For demonstration, just showing a message
            JOptionPane.showMessageDialog(frame, "Book!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showServiceMenu();
        }
    }

    private boolean areFieldsEmpty() {
        return txtIdNo.getText().trim().isEmpty() ||
        reservedForTextField.getText().trim().isEmpty() ||
        addressTextField.getText().trim().isEmpty() ||
        contactNumberTextField.getText().trim().isEmpty() ||
        dateTextField.getText().trim().isEmpty() ||
        timeTextField.getText().trim().isEmpty();
    }

    private void storeInformation() throws IOException {
        String idNo = txtIdNo.getText();
        String reservedFor = reservedForTextField.getText();
        String address = addressTextField.getText();
        String contactNumber = contactNumberTextField.getText();
        String date = dateTextField.getText();
        String time = timeTextField.getText();

        String filePath = "path/to/home_service_data.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(idNo + "," + reservedFor + "," + address + "," + contactNumber + "," + date + "," + time);
            writer.newLine();
            writer.flush();
            clearInputFields();
            JOptionPane.showMessageDialog(frame, "Information stored successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showServiceMenu() {
        frame.setVisible(false);
        ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.initializeUI();
    }

    private void clearInputFields() {
        txtIdNo.setText("");
        reservedForTextField.setText("");
        addressTextField.setText("");
        contactNumberTextField.setText("");
        dateTextField.setText("MM/DD/YY");
        timeTextField.setText("00:00");
    }

}
