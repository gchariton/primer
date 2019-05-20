import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import net.miginfocom.swing.MigLayout;
import net.miginfocom.layout.Grid;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JFrame frame;
    private JButton calcButton;
    private JLabel resultsLabel;
    private SwingWorker primeWorker;

    void createPrimerWindow() {

        // Set the Look 'n' Feel
        new LookFeel().setUIStyle("Windows"); // Global UI style
        new LookFeel().setUIFont(new FontUIResource("Tahoma", 0, 13)); // Global Font style

        // Set main application window
        frame = new JFrame("Primer");
        frame.setSize(500, 300); // Window dimensions
        frame.setResizable(false); // Fixed size
        frame.setLocationRelativeTo(null); // Center of the screen

        // Set application icon
        ImageIcon icon = new ImageIcon(getClass().getResource("images/appicon.png"));
        frame.setIconImage(icon.getImage());

        // MENU **********************************************************
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

        exitItem = new JMenuItem("Exit", new ImageIcon(PrimerWindow.class.getResource("/images/icons/exit.png")));
        actionsMenu.add(exitItem);
        exitItem.addActionListener(this);

        // Build the Help menu item
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        aboutItem = new JMenuItem("About", new ImageIcon(PrimerWindow.class.getResource("/images/icons/about.png")));
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);

        // PANELS **********************************************************

        // Main Panel
        JPanel mainPanel = new JPanel(new MigLayout("wrap 1", "10 [grow] 10", "10 [] 5 [] 10 [] 10"));

        // Input Panel
        JPanel inPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "[grow] [grow]"));

        TitledBorder inBorder = new TitledBorder("Insert number to analyze");
        inBorder.setTitlePosition(TitledBorder.TOP);
        inBorder.setTitleJustification(TitledBorder.LEFT);
        inPanel.setBorder(inBorder);

        // Text area to insert the number
        textField = new JTextField();
        inPanel.add(textField, "cell 0 0, grow");

        // Calculation button
        calcButton = new JButton("Calculate!");
        inPanel.add(calcButton, "cell 0 1, growx");
        calcButton.addActionListener(this);
        frame.getRootPane().setDefaultButton(calcButton); // Calculate when hitting the Enter key

        // Output Panel
        JPanel outPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "[grow]"));

        TitledBorder outBorder = new TitledBorder("Results area");
        outBorder.setTitlePosition(TitledBorder.TOP);
        outBorder.setTitleJustification(TitledBorder.LEFT);
        outPanel.setBorder(outBorder);

        // Results label
        resultsLabel = new JLabel();
        outPanel.add(resultsLabel, "push, grow");

        // Concatenate panels
        mainPanel.add(inPanel, "grow");
        mainPanel.add(outPanel, "grow");

        // Final touches
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Center of screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        } else if ("About".equals(e.getActionCommand())) {
            new AboutWindow().createAboutWindow("Primer", "v2019.05");
        } else if ("Calculate!".equals(e.getActionCommand())) {

            if (!isInteger(textField.getText())) { // Check if number is in a correct format or not
                resultsLabel.setText(null);
                resultsLabel.setText("The number you entered is not a valid integer.");
            } else if ((new BigInteger(textField.getText())).compareTo((new BigInteger("2"))) == -1) {
                // Check if given number is greater than 1
                resultsLabel.setText(null);
                resultsLabel.setText("Given number must be greater than 1.");
            } else {

                resultsLabel.setText(null);
                resultsLabel.setText("Calculating...");
                calcButton.setText(null);
                calcButton.setText("Click here to cancel process");

                primeWorker = new primeFactorsWorker(new BigInteger(textField.getText()), calcButton, resultsLabel);
                primeWorker.execute();

            }
        } else if ("Click here to cancel process".equals(e.getActionCommand())) {
            primeWorker.cancel(true);

            // Reset calculation button
            calcButton.setText(null);
            calcButton.setText("Calculate!");

            // Reset results label
            resultsLabel.setText(null);
        }
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}