import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderPreview extends JFrame implements ActionListener {
    private JButton addButton, confirmButton, cancelButton;
    private JFrame frame;
    private JPanel panel, panelAdd;
    private JLabel orderDetailsLabel;
    private List<String> orderDetailsList = new ArrayList<>();
    private Service service;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderPreview orderPreview = new OrderPreview();
            orderPreview.initializeUI();
        });
    }

    public void initializeUI() {
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        service = new Service();
        service.initializeUI();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JLabel lbTitle = new JLabel("STRANDVOGUE");
        lbTitle.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        lbTitle.setBounds(310, 10, 300, 20);
        panel.add(lbTitle);

        JLabel lblService = new JLabel("SERVICE");
        lblService.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        lblService.setBounds(200, 110, 70, 20);
        panel.add(lblService);

        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        lblPrice.setBounds(500, 110, 70, 20);
        panel.add(lblPrice);

        addButton = new JButton("Add Order");
        addButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        addButton.setBounds(166, 340, 125, 30);
        addButton.addActionListener(this);
        panel.add(addButton);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        confirmButton.setBounds(332, 340, 125, 30);
        confirmButton.addActionListener(this);
        panel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
        cancelButton.setBounds(500, 340, 125, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancel button click in OnlineBooking
            }
        });
        panel.add(cancelButton);

        orderDetailsLabel = new JLabel("Order Details:");
        orderDetailsLabel.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
        orderDetailsLabel.setBounds(200, 160, 400, 20);
        panel.add(orderDetailsLabel);

        frame.setVisible(true);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "SERVICES"));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Get selected services from the Service class
            List<String> selectedServices = service.getSelectedServices();

            // Add order details when the "Add Order" button is clicked
            StringBuilder allOrderDetails = new StringBuilder("Order Details:\n");
            for (String orderDetails : orderDetailsList) {
                allOrderDetails.append(orderDetails).append("\n\n");
            }

            allOrderDetails.append("Selected Services:\n");
            for (String selectedService : selectedServices) {
                allOrderDetails.append(selectedService).append("\n");
            }

            orderDetailsLabel.setText(allOrderDetails.toString());
        } else if (e.getSource() == confirmButton) {
            // Add functionality to confirm orders
        }
    }
}
