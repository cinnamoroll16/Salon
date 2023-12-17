import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SalonManagement implements ActionListener, ButtonClickListener {
    private static JButton btnExit,btnHomeService,btnOnlineReservation;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static ButtonClickListener buttonClickListener;

    public static void main(String[] args) {
        SalonManagement salonManagement = new SalonManagement();
        salonManagement.initializeUI();
    }

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
        homeServicePanel.setBorder(BorderFactory.createTitledBorder(""));

        JLabel lblTitle1 = new JLabel("STRAND");
        lblTitle1.setFont(new Font("SERIF", Font.BOLD, 70));
        lblTitle1.setBounds(250, 90, 300, 100);
        panel.add(lblTitle1);

        JLabel lblTitle2 = new JLabel("VOGUE");
        lblTitle2.setFont(new Font("SERIF", Font.BOLD, 70));
        lblTitle2.setBounds(273, 150, 300, 100);
        panel.add(lblTitle2);

        JLabel lbTitle = new JLabel("STRANDVOGUE");
        lbTitle.setFont(new Font("SERIF", Font.PLAIN, 20));
        lbTitle.setBounds(310, 10, 300, 20);
        panel.add(lbTitle);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        JMenuItem signInMenuItem = new JMenuItem("Go to Sign-In");
        helpMenu.add(signInMenuItem);
        signInMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSignInInterface();
                }
            });
        JMenuItem homeServiceMenuItem = new JMenuItem("Home Service");
        helpMenu.add(homeServiceMenuItem);
        homeServiceMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showHelpDialog("Home Service", "This service allows our salon professionals to provide their expertise at your home.");

                }
            });
        JMenuItem onlineBookingMenuItem = new JMenuItem("Online Booking");
        helpMenu.add(onlineBookingMenuItem);
        onlineBookingMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showHelpDialog("Online Booking", "Our online booking system allows you to schedule appointments conveniently from your device.");
                }
            });

        btnHomeService = new JButton("Home-Service");
        btnHomeService.setFont(new Font("SERIF", Font.PLAIN, 15));
        btnHomeService.setBounds(150, 300, 150, 35);
        btnHomeService.setBackground(Color.WHITE);
        btnHomeService.setForeground(Color.BLACK);
        btnHomeService.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        btnHomeService.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSignUpInterface();
                }
            });
        panel.add(btnHomeService);

        btnOnlineReservation = new JButton("Online-Reservation");
        btnOnlineReservation.setFont(new Font("SERIF", Font.PLAIN, 15));
        btnOnlineReservation.setBounds(450, 300, 200, 35);
        btnOnlineReservation.setBackground(Color.WHITE);
        btnOnlineReservation.setForeground(Color.BLACK);
        btnOnlineReservation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        btnOnlineReservation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSignUpInterface();
                }
            });
        panel.add(btnOnlineReservation);

        btnExit = new JButton("EXIT");
        btnExit.setFont(new Font("SERIF", Font.PLAIN, 16));
        btnExit.setBounds(280, 350, 200, 35);
        btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
        btnExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(btnExit);

        frame.setVisible(true);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());

        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), ""));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);
    }

    private void showHelpDialog(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showSignUpInterface() {
        frame.setVisible(false);
        SignUp signUp = new SignUp(this);
        signUp.initializeUI();
    }

    private void showSignInInterface() {
        frame.setVisible(false);
        SignIn signIn = new SignIn();
        signIn.initializeUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == btnHomeService || e.getSource() == btnOnlineReservation) {
            showSignUpInterface();
        }
    }

    @Override
    public void onButtonClick() {
        System.exit(0);
    }
}