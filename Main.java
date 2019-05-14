import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static String inputInteger = null;

    public static void main(String[] args) {
        inputInteger = args[0];
        BigInteger BI = null;
        List<BigInteger> resultList = null;

        // Setting up GUI
        new Main().createWindowPane();

        try {
            BI = new BigInteger(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid integer.");
            System.out.println("Program will now exit.");
            System.exit(0);
        }

        resultList = findPrimeFactors(BI);

        if (resultList.size() == 1) {
            System.out.println("Given number is a prime.");
        } else {
            System.out.print("Given number's prime factors are: ");
            System.out.println(resultList);
        }
    }

    private void createWindowPane() {

        // Set main application window
        JFrame frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("outside");
        if ("Exit".equals(e.getActionCommand())) {
            System.out.println("inside");
            System.exit(0);
        }
    }

    // Checks input and calculates prime factors
    private static List<BigInteger> findPrimeFactors(BigInteger n) {
        BigInteger two = BigInteger.valueOf(2);
        List<BigInteger> fs = new LinkedList<BigInteger>();

        if (n.compareTo(two) < 0) {
            throw new IllegalArgumentException("Given integer must be greater than 1.");
        }

        while (n.mod(two).equals(BigInteger.ZERO)) {
            fs.add(two);
            n = n.divide(two);
        }

        if (n.compareTo(BigInteger.ONE) > 0) {
            BigInteger f = BigInteger.valueOf(3);

            while (f.multiply(f).compareTo(n) <= 0) {
                if (n.mod(f).equals(BigInteger.ZERO)) {
                    fs.add(f);
                    n = n.divide(f);
                } else {
                    f = f.add(two);
                }

            }

            fs.add(n);
        }

        return fs;
    }
}