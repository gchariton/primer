import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AboutWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    public void createAboutWindow(String appName) {

        // Set About window
        JFrame frame = new JFrame("About " + appName);
        frame.setSize(300, 200); // Window dimensions
        frame.setResizable(false); // Fixed dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);

        // Set OK button
        JButton okButton = new JButton("OK");
        frame.add(okButton);
        okButton.setVisible(true);

        // Final touches
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if ("OK".equals(e.getActionCommand())) {
            dispose();
        }
    }
}