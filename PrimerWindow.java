import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class PrimerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    void createPrimerWindow() {

        // Set main application window
        JFrame frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);

        // Set the main menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Build the Actions menu item
        JMenu actionsMenu = new JMenu("Actions");
        JMenu exitItem = new JMenu("Exit");
        actionsMenu.add(exitItem);
        exitItem.addActionListener(this);

        // Build the Help menu item
        JMenu helpMenu = new JMenu("Help");

        // Add menu items to the menu bar
        menuBar.add(actionsMenu);
        menuBar.add(helpMenu);

        // Final touches
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("outside");
        if ("Exit".equals(e.getActionCommand())) {
            System.out.println("inside");
            System.exit(0);
        }
    }
}