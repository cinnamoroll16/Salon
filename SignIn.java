import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SignIn implements ActionListener {
    private static JTextField txtUsername, txtPhoneNumber, txtPassword, TitledBorder;
    private static JButton btnSubmit, btnCancel;
    private static JFrame frame;
    private static JPanel panel, panelAdd;

    // public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
                    // SignIn signIn = new SignIn();
                    // signIn.initializeUI();
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

        JPanel homeServicePanel = new JPanel(new BorderLayout());
        homeServicePanel.setBorder(BorderFactory.createTitledBorder("Sign In"));
        JLabel lblTitle1 = new JLabel("STRANDVOGUE");
        lblTitle1.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        lblTitle1.setBounds(310, 10, 300, 20);
        panel.add(lblTitle1);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblUsername.setBounds(220, 100, 100, 80);
        panel.add(lblUsername);
        txtUsername = new JTextField();
        txtUsername.setBounds(300, 130, 300, 30);
        txtUsername.setColumns(10);
        panel.add(txtUsername);
        
        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblPhoneNumber.setBounds(185, 138, 300, 80);
        panel.add(lblPhoneNumber);
        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(300, 165, 300, 30);
        txtPhoneNumber.setColumns(10);
        panel.add(txtPhoneNumber);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblPassword.setBounds(225, 173, 80, 80);
        panel.add(lblPassword);
        txtPassword = new JTextField();
        txtPassword.setBounds(300, 200, 300, 30);
        txtPassword.setColumns(10);
        panel.add(txtPassword);

        btnSubmit = new JButton("Confirm");
        btnSubmit.setFont(new Font("SERIF", Font.PLAIN, 14));
        btnSubmit.setBounds(260, 320, 125, 30);
        btnSubmit.addActionListener(this);
        panel.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("SERIF", Font.PLAIN, 14));
        btnCancel.setBounds(470, 320, 125, 30);
        btnCancel.addActionListener(this);
        panel.add(btnCancel);

        frame.setVisible(true);

        panel.setVisible(true);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Log In"));
        panelAdd.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);

        btnCancel.addActionListener(e -> {
                    panel.setVisible(true);
                    panelAdd.setVisible(false);
            });

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String username = txtUsername.getText().trim();
            String phoneNumber = txtPhoneNumber.getText().trim();
            String password = txtPassword.getText().trim();

            if (username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (validateCredentials(username, phoneNumber, password)) {
                    showOnlineBookingInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            frame.setVisible(false);
            SalonManagement salonManagement = new SalonManagement();
            salonManagement.initializeUI();
        }
    }

    private boolean validateCredentials(String username, String phoneNumber, String password) {
        boolean validCredentials = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String storedUsername = parts[1].trim();
                    String storedPhoneNumber = parts[4].trim();
                    String storedPassword = parts[5].trim();

                    if (username.equals(storedUsername) && phoneNumber.equals(storedPhoneNumber) && password.equals(storedPassword)) {
                        validCredentials = true;
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return validCredentials;
    }

    private void showOnlineBookingInterface() {
        frame.setVisible(false);
        OnlineBooking onlineBooking = new OnlineBooking();
        onlineBooking.initializeUI();
    }
}
