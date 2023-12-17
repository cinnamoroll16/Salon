import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Service implements ActionListener, ButtonClickListener {
    private static JButton btnOrder;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static ButtonClickListener buttonClickListener;
    private static JComboBox<String> servicesComboBox;
    private static JComboBox<String> subcategoriesComboBox;
    private static JLabel priceLabel, descriptionLabel;

    private static final String[] SERVICE_TYPES = {"Haircut", "Styling", "Color Services", "Texture Services"};
    private static final String[][] SUBCATEGORIES = {{"Signature Haircut(men)", "Signature Haircut(women)", "Creative Design Cut", "Children's Cut (12 and up)"},
            {"Blowout", "Updo/Event Styling", "Braiding"},
            {"Root Touch-Up", "Full Color", "Highlight/Lowlights", "Balayage/Ombre"},
            {"Perms", "Keratin Treatment", "Deep Conditioning"}};
    private static final double[][] PRICES = {
            {50.0, 35.0, 60.0, 25.0},
            {50.0, 75.0, 30.0},
            {65.0, 90.0, 100.0, 120.0},
            {60.0, 90.0, 25.0}
        };
    public static void main(String[] args) {
        Service service = new Service();
        OrderPreview orderPreview = new OrderPreview(); // Create an instance of OrderPreview
        orderPreview.initializeUI();
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

        JLabel lbTitle = new JLabel("STRANDVOGUE");
        lbTitle.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 20));
        lbTitle.setBounds(310, 10, 300, 20);
        panel.add(lbTitle);

        JLabel lblTitle1 = new JLabel("Services MENU:");
        lblTitle1.setFont(new Font("SERIF", Font.BOLD, 15));
        lblTitle1.setBounds(80, 50, 300, 100);
        panel.add(lblTitle1);

        servicesComboBox = new JComboBox<>(SERVICE_TYPES);
        servicesComboBox.setFont(new Font("SERIF", Font.PLAIN, 16));
        servicesComboBox.setBounds(80, 120, 200, 30);
        servicesComboBox.addActionListener(this);
        panel.add(servicesComboBox);

        subcategoriesComboBox = new JComboBox<>();
        subcategoriesComboBox.setFont(new Font("SERIF", Font.PLAIN, 16));
        subcategoriesComboBox.setBounds(290, 120, 200, 30);
        subcategoriesComboBox.addActionListener(this);
        subcategoriesComboBox.setVisible(false);
        panel.add(subcategoriesComboBox);

        priceLabel = new JLabel("Price: ");
        priceLabel.setFont(new Font("SERIF", Font.PLAIN, 16));
        priceLabel.setBounds(80, 180, 200, 30);
        panel.add(priceLabel);

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("SERIF", Font.PLAIN, 16));
        descriptionLabel.setBounds(80, 210, 800, 30);
        panel.add(descriptionLabel);

        btnOrder = new JButton("ORDER NOW");
        btnOrder.setFont(new Font("SERIF", Font.PLAIN, 16));
        btnOrder.setBounds(280, 350, 200, 35);
        btnOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedService = (String) subcategoriesComboBox.getSelectedItem();
                    int selectedIndex = servicesComboBox.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < PRICES.length) {
                        int subcategoryIndex = subcategoriesComboBox.getSelectedIndex();
                        double price = PRICES[selectedIndex][subcategoryIndex];
                        String serviceInfo = "Service Type: " + SERVICE_TYPES[selectedIndex] +
                            "\nSelected Service: " + selectedService +
                            "\nPrice: $" + price;
                        JOptionPane.showMessageDialog(frame, serviceInfo, "Order Now", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            });
        btnOrder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(btnOrder);

        frame.setVisible(true);

        panelAdd = new JPanel();
        panelAdd.setLayout(new BorderLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "SERVICES"));
        panelAdd.setBounds(25, 30, 730, 380);
        panelAdd.setBackground(Color.WHITE);
        panel.add(panelAdd);

    }
    private void saveSelectedServicesToFile(List<String> selectedServices) {
        try (FileWriter writer = new FileWriter("selected_services.txt")) {
            for (String service : selectedServices) {
                writer.write(service + "\n");
            }
            JOptionPane.showMessageDialog(frame, "Selected services saved to file.", "Save Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving selected services to file.", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == servicesComboBox) {
            updateSubcategories();
        } else if (e.getSource() == subcategoriesComboBox) {
            updatePrice();
        }
    }

    private void updateSubcategories() {
        int selectedIndex = servicesComboBox.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < SUBCATEGORIES.length) {
            String[] subcategories = SUBCATEGORIES[selectedIndex];
            subcategoriesComboBox.removeAllItems();
            for (String subcategory : subcategories) {
                subcategoriesComboBox.addItem(subcategory);
            }
            subcategoriesComboBox.setVisible(true);
            updatePrice();
        } else {
            subcategoriesComboBox.setVisible(false);
        }
    }

    private void updatePrice() {
        int selectedIndex = servicesComboBox.getSelectedIndex();
        int subcategoryIndex = subcategoriesComboBox.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < PRICES.length && subcategoryIndex >= 0) {
            double price = PRICES[selectedIndex][subcategoryIndex];
            String description = DESCRIPTIONS[selectedIndex][subcategoryIndex];

            priceLabel.setText("Price: $" + price);
            descriptionLabel.setText("Description: " + description);
        }
    }

    // Description for each service and subcategory
    private static final String[][] DESCRIPTIONS = {
            {"Classic men's haircut with signature styling.",
                "Classic women's haircut with signature styling.",
                "Unique and creative haircut design.",
                "Gentle haircut for children aged 12 and up."},
            {"Professional blowout styling service.",
                "Updo or special event styling.",
                "Various braiding styles."},
            {"Touch-up the roots of your hair color.",
                "Full hair coloring service.",
                "Highlights, lowlights, or both.",
                "Balayage or ombre hair coloring."},
            {"Perm for curly or wavy hair.",
                "Keratin treatment for smooth, frizz-free hair.",
                "Deep conditioning for improved hair health."}
        };

    @Override
    public void onButtonClick() {
        saveSelectedServicesToFile(selectedServices);
        System.exit(0);
    }
}
