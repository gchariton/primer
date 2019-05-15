import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    void createPrimerWindow() {

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
        JFrame frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
        frame.setResizable(false); // Fixed dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);

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

        // Final touches
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        } else if ("About".equals(e.getActionCommand())) {
            System.out.println("in");
            new AboutWindow().createAboutWindow("Primer");
        }
    }
}