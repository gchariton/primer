import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    void createPrimerWindow() {

        // Set main application window
        JFrame frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
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

        // Final touches
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if ("Exit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
}