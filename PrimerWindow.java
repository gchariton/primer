import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private List<BigInteger> resultList;
    private JFrame frame;

    void createPrimerWindow() {

        // Playing around with the Look 'n' Feel stuff
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception e) {
            System.out.println("Problem loading the GUI Look 'n' Feel.");
        }

        // Set main application window
        frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
        frame.setResizable(false); // Fixed dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);

        // Set window icon
        ImageIcon icon = new ImageIcon(getClass().getResource("images/appicon.png"));
        frame.setIconImage(icon.getImage());

        // Declare the menu bar and its items
        JMenuBar menuBar;

        JMenu actionsMenu;
        JMenuItem exitItem;

        JMenu helpMenu;
        JMenuItem aboutItem;

        // Set the main menu bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Build the Actions menu item
        actionsMenu = new JMenu("Actions");
        menuBar.add(actionsMenu);

        exitItem = new JMenuItem("Exit");
        actionsMenu.add(exitItem);
        exitItem.addActionListener(this);

        // Build the Help menu item
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);

        // Add the integer input field and submit button
        JLabel enterIntegerLabel = new JLabel();
        frame.add(enterIntegerLabel);
        enterIntegerLabel.setBounds(15, 10, 780, 25);
        enterIntegerLabel.setText("<html><b>Enter or paste the integer inside this field:</b></html>");

        textField = new JTextField();
        frame.add(textField);
        textField.setBounds(10, 40, 600, 30);

        JButton calcButton = new JButton("Calculate!");
        frame.add(calcButton);
        calcButton.setBounds(620, 40, 100, 30);
        calcButton.addActionListener(this);

        // Final touches
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        } else if ("About".equals(e.getActionCommand())) {
            new AboutWindow().createAboutWindow("Primer");
        } else if ("Calculate!".equals(e.getActionCommand())) {
            resultList = new PrimeFactors().findPrimeFactors(new BigInteger(textField.getText()));

            // Results area
            JLabel resultsLabel = new JLabel();
            frame.add(resultsLabel);
            resultsLabel.setBounds(300, 15, 600, 200);

            // Export results
            if (resultList.size() == 1) { // In this case, the given integer is a prime itself
                resultsLabel.setText("Given number is a prime.");

            } else {
                System.out.print("Given number's prime factors are: ");
                System.out.println(resultList);
            }
        }
    }
}