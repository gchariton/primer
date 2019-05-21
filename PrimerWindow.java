import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
import javax.swing.JProgressBar;
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
    private JProgressBar progressBar;
    private String copyResult = "";
    private SwingWorker primeWorker;

    // CONSTANT fields
    private final String UI_STYLE = "Windows";
    private final String APP_NAME = "Primer";
    private final String VERSION = "v2019.05";

    void createPrimerWindow() {

        // Set the Look 'n' Feel
        new LookFeel().setUIStyle(UI_STYLE); // Global UI style
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
        JMenuItem copyItem;
        JMenuItem exitItem;

        JMenu helpMenu;
        JMenuItem aboutItem;

        // Set the main menu bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Build the Actions menu item
        actionsMenu = new JMenu("Actions");
        menuBar.add(actionsMenu);

        copyItem = new JMenuItem("Copy");
        actionsMenu.add(copyItem);
        copyItem.addActionListener(this);

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
        JPanel mainPanel = new JPanel(new MigLayout("wrap 1", "10 [grow] 10", "10 [] 10 [] 10"));

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

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setSize(0, 3);
        progressBar.setVisible(false);
        outPanel.add(progressBar, "growx");

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

        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
        case ("Copy"): {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(copyResult);
            clipboard.setContents(strSel, null);

            break;
        }

        case ("Exit"): {
            System.exit(0);
            break;
        }

        case ("About"): {
            new AboutWindow().createAboutWindow(APP_NAME, VERSION);
            break;
        }

        case ("Calculate!"): {
            if (!(new CheckNumber().isInteger(textField.getText()))) { // Check if number is in a correct format or not
                resultsLabel.setText(null);
                resultsLabel.setText("The number you entered is not a valid integer.");
            } else if ((new BigInteger(textField.getText())).compareTo((new BigInteger("2"))) == -1) {
                // Check if given number is greater than 1
                resultsLabel.setText(null);
                resultsLabel.setText("Given number must be greater than 1.");
            } else {

                resultsLabel.setText(null);
                resultsLabel.setText("Calculating...");
                progressBar.setVisible(true);
                calcButton.setText(null);
                calcButton.setBackground(new Color(255, 230, 230));
                calcButton.setText("Click here to cancel process");

                primeWorker = new primeFactorsWorker(new BigInteger(textField.getText()), calcButton, resultsLabel,
                        progressBar, copyResult);
                primeWorker.execute();

            }
            break;
        }

        case ("Click here to cancel process"): {

            primeWorker.cancel(true);

            // Reset progress bar
            progressBar.setVisible(false);

            // Reset calculation button
            calcButton.setBackground(null);
            calcButton.setBackground(new JButton().getBackground());
            calcButton.setText(null);
            calcButton.setText("Calculate!");

            // Reset results label
            resultsLabel.setText(null);

            break;
        }
        }
    }
}