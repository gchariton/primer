import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.miginfocom.layout.Grid;

public class AboutWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Declare current JFrame to be used belown in actionPerformed method
    private JFrame aboutFrame;

    public void createAboutWindow(String appName) {

        // Set About window
        aboutFrame = new JFrame("About " + appName);
        aboutFrame.setSize(400, 150); // Window dimensions
        aboutFrame.setResizable(false); // Fixed dimensions
        aboutFrame.setLocationRelativeTo(null); // Center of the screen

        // Set window icon
        ImageIcon icon = new ImageIcon(getClass().getResource("images/appicon.png"));
        aboutFrame.setIconImage(icon.getImage());

        // Set my logo
        JLabel aboutLogoLabel = new JLabel(new ImageIcon("images/chgeorge.png"));

        // Set About text
        JLabel aboutTextLabel = new JLabel();
        aboutTextLabel.setText("<html><b>" + appName + "</b><br />Developed by George Charitonidis."
                + "<br /> Contact: chgeorge@gmail.com</html>");

        JPanel panel = new JPanel(new MigLayout("wrap 2", "10 [] 10 [] 10"));

        panel.add(aboutLogoLabel);
        panel.add(aboutTextLabel);

        aboutFrame.add(panel, BorderLayout.CENTER);
        aboutFrame.pack();

        // Final touches
        aboutFrame.setLayout(null);
        aboutFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if ("OK".equals(e.getActionCommand())) {
            aboutFrame.dispose();
        }
    }
}