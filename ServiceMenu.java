import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceMenu implements ActionListener, ButtonClickListener {
    private JButton btnOrder;
    private JFrame frame;
    private JPanel panel, panelAdd;
    private ButtonClickListener buttonClickListener;

    // public static void main(String[] args) {
        // ServiceMenu service = new ServiceMenu();
        // service.initializeUI();
    // }

    public void initializeUI() {
        frame = new JFrame("Service Menu");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        int center = SwingConstants.CENTER;

        panel = new JPanel(new BorderLayout()); // Use BorderLayout
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel lbTitle = new JLabel("STRANDVOGUE", center);
        lbTitle.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        panel.add(lbTitle, BorderLayout.NORTH);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "SERVICES MENU"));
        panelAdd.setBackground(Color.WHITE);

        // Add spaces (padding)
        panelAdd.add(Box.createRigidArea(new Dimension(5, 5)), BorderLayout.NORTH);
        panelAdd.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);
        panelAdd.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);

        // Create service boxes
        JPanel serviceBoxes = createServiceBoxes();
        panelAdd.add(serviceBoxes, BorderLayout.CENTER);

        // Add padding between service boxes and ORDER button
        panelAdd.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        // Add ORDER button to the SOUTH of panelAdd
        btnOrder = new JButton("ORDER");
        btnOrder.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        btnOrder.addActionListener(this);
        btnOrder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnOrder.setPreferredSize(new Dimension(90, 35)); // Set preferred size
        panelAdd.add(btnOrder, BorderLayout.SOUTH);

        // Add spaces (padding) to the frame
        panel.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);
        panel.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);

        panel.add(panelAdd, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createServiceBoxes() {
        JPanel serviceBoxes = new JPanel(new GridLayout(2, 2, 10, 10)); // 2x2 grid with spacing
        serviceBoxes.setBackground(Color.WHITE);

        // Customize each service box
        serviceBoxes.add(createServiceBox("HAIRCUTS", "1. SIGNATURE HAIRCUT 2. CREATIVE DESIGN CUT\n\n3. CHILDREN'S CUT (12 and up)", "men- $50 women- $35\n-$60 and up\n-$25"));
        serviceBoxes.add(createServiceBox("STYLING", "1. BLOWOUT\n2. UPDO/EVENT STYLING\n3. BRAIDING. BLOWOUT\n", "- $50\n- $75 and up\n- $30 and up"));
        serviceBoxes.add(createServiceBox("COLOR SERVICES", "1. ROOT TOUCH-UP\n2. FULL COLOR\n3. HIGHLIGHTS/LOWLIGHTS\n4. BALAYAGE/OMBRE", "-$65 and up\n- $90 and up\n- $100 and up\n- $120 and up"));
        serviceBoxes.add(createServiceBox("TEXTURE SERVICES", "1. PERMS\n2. KERATIN TREATMENT\n3. DEEP CONDITIONING", "-$80 and up\n-$90 and up\n-$25"));

        return serviceBoxes;
    }

    private JPanel createServiceBox(String serviceName, String serviceDescription, String servicePrice) {
        JPanel serviceBox = new JPanel();
        serviceBox.setLayout(new BoxLayout(serviceBox, BoxLayout.Y_AXIS));
        serviceBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel serviceNameLabel = new JLabel("<html><u>" + serviceName + "</u></html>");
        serviceNameLabel.setFont(new Font("SERIF", Font.BOLD, 15));
        serviceNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel serviceDescriptionLabel = new JLabel("<html><ol>" + serviceDescription + "</ol></html>");
        serviceDescriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel servicePriceLabel = new JLabel("<html><ol>" + servicePrice + "</ol></html>");
        servicePriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        serviceBox.add(serviceNameLabel);
        serviceBox.add(serviceDescriptionLabel);
        serviceBox.add(servicePriceLabel);

        return serviceBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOrder) {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClick();
            }
        }
    }

    @Override
    public void onButtonClick() {
        System.exit(0);
    }
}
