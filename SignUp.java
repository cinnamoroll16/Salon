import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SignUp implements ActionListener {
    private static JTextField txtIdNo, txtUsername, txtEmail, txtLocation, txtPhoneNumber, txtPassword;
    private static JButton btnSubmit, btnCancel;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static SalonManagement salonManagement;

    private static final String FILE_PATH = "userdata.txt";

    public SignUp(SalonManagement salonManagement) {
        this.salonManagement = salonManagement;
    }

    // public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
                    // SignUp signUp = new SignUp(new SalonManagement());
                    // signUp.initializeUI();
            // });
    // }

    public void initializeUI() {
        frame = new JFrame("SIGN UP");
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

        JLabel lblIdNo = new JLabel("ID No.");
        lblIdNo.setFont(new Font("SERIF", Font.PLAIN, 14));
        lblIdNo.setBounds(220, 80, 70, 60);
        panel.add(lblIdNo);

        txtIdNo = new JTextField(String.valueOf(getLastUsedId() + 1));
        txtIdNo.setEditable(false);
        txtIdNo.setBounds(265, 95, 40, 30);
        panel.add(txtIdNo);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblUsername.setBounds(220, 100, 100, 80);
        panel.add(lblUsername);
        txtUsername = new JTextField();
        txtUsername.setBounds(300, 130, 300, 30);
        txtUsername.setColumns(10);
        panel.add(txtUsername);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblEmail.setBounds(250, 135, 100, 80);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(300, 160, 300, 30);
        txtEmail.setColumns(10);
        panel.add(txtEmail);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblLocation.setBounds(230, 165, 80, 80);
        panel.add(lblLocation);
        txtLocation = new JTextField();
        txtLocation.setBounds(300, 190, 300, 30);
        txtEmail.setColumns(10);
        panel.add(txtLocation);

        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblPhoneNumber.setBounds(183, 195, 300, 80);
        panel.add(lblPhoneNumber);
        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(300, 220, 300, 30);
        txtPhoneNumber.setColumns(10);
        panel.add(txtPhoneNumber);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SERIF", Font.PLAIN, 18));
        lblPassword.setBounds(220, 225, 100, 80);
        panel.add(lblPassword);
        txtPassword = new JTextField();
        txtPassword.setBounds(300, 250, 300, 30);
        txtPassword.setColumns(10);
        panel.add(txtPassword);

        btnSubmit = new JButton("Confirm");
        btnSubmit.setFont(new Font("SERIF", Font.PLAIN, 14));
        btnSubmit.setBounds(260, 340, 125, 30);
        btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (validateFields()) {
                        saveUserData();
                        showSignInInterface();
                    }
                }
            });
        panel.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("SERIF", Font.PLAIN, 14));
        btnCancel.setBounds(470, 340, 125, 30);
        btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    if (salonManagement != null) {
                        salonManagement.initializeUI();
                    }
                }
            });
        panel.add(btnCancel);

        panel.setVisible(true);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Sign Up"));
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

    private void showSignInInterface() {
        frame.setVisible(false);
        SignIn signIn = new SignIn();
        signIn.initializeUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            frame.setVisible(false);
            if (salonManagement != null) {
                salonManagement.initializeUI();
            }
        }
    }

    private boolean validateFields() {
        if (txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtLocation.getText().isEmpty() ||
        txtPhoneNumber.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private int getLastUsedId() {
        int lastId = 100; // Set the default starting value

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    lastId = Integer.parseInt(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    private void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            int newId = getLastUsedId() + 1;
            writer.write(newId + "," + txtUsername.getText() + "," + txtEmail.getText() + ","
                + txtLocation.getText() + "," + txtPhoneNumber.getText() + "," + txtPassword.getText());
            writer.newLine();

            // Update the displayed ID
            txtIdNo.setText(String.valueOf(newId));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
