import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Declare current JFrame to be used belown in actionPerformed method
    private JFrame aboutFrame;

    public void createAboutWindow(String appName) {

        // Set About window
        aboutFrame = new JFrame("About " + appName);
        aboutFrame.setSize(300, 180); // Window dimensions
        aboutFrame.setResizable(false); // Fixed dimensions
        aboutFrame.setLocationRelativeTo(null); // Center of the screen

        // Set my logo
        JLabel aboutLogo = new JLabel(new ImageIcon("images/chgeorge.png"));
        aboutFrame.add(aboutLogo);
        aboutLogo.setBounds(10, 10, 58, 58);

        // Set About text
        JLabel aboutTextLabel = new JLabel();
        aboutFrame.add(aboutTextLabel);
        aboutTextLabel.setBounds(78, 10, 212, 58);
        aboutTextLabel.setText("<html><b>" + appName + "</b><br />Developed by George Charitonidis."
                + "<br /> Contact: chgeorge@gmail.com");

        // Set OK button
        JButton okButton = new JButton("OK");
        aboutFrame.add(okButton);
        okButton.setBounds(125, 105, 50, 25);
        okButton.addActionListener(this);

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