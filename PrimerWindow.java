import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import net.miginfocom.layout.Grid;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private List<BigInteger> resultList;
    private JFrame frame;

    void createPrimerWindow() {

        // Set the Look 'n' Feel
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
        frame.setLocationRelativeTo(null); // Center of the screen

        // Set window icon
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

        exitItem = new JMenuItem("Exit");
        actionsMenu.add(exitItem);
        exitItem.addActionListener(this);

        // Build the Help menu item
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);

        // PANELS **********************************************************

        // Main Panel
        JPanel mainPanel = new JPanel(new MigLayout("wrap 1", "10 [grow] 10", "10 [] 5 [] 10 [] 10"));

        // Input Panel
        JPanel inPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "[grow] [grow]"));

        TitledBorder inBorder = new TitledBorder("Insert number in this area");
        inBorder.setTitlePosition(TitledBorder.TOP);
        inBorder.setTitleJustification(TitledBorder.LEFT);
        inPanel.setBorder(inBorder);

        // Text area to insert the number
        JTextArea textArea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(textArea);
        inPanel.add(scrollPane, "cell 0 0, grow");

        // Calculation button
        JButton calcButton = new JButton("Calculate!");
        inPanel.add(calcButton, "cell 0 1, growx");
        calcButton.addActionListener(this);

        // Output Panel
        JPanel outPanel = new JPanel(new MigLayout("wrap 1", "", "[]"));

        // Concatenate panels
        mainPanel.add(inPanel, "span, grow");
        mainPanel.add(outPanel);

        // Final touches
        frame.add(mainPanel, BorderLayout.CENTER);
        // frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        } else if ("About".equals(e.getActionCommand())) {
            new AboutWindow().createAboutWindow("Primer", "v2019.05");
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