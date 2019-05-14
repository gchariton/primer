import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class Main {

    public static String inputInteger = null;

    public static void main(String[] args) {
        inputInteger = args[0];
        BigInteger BI = null;
        List<BigInteger> resultList = null;

        // Setting up GUI
        createWindowPane();

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

    private static void createWindowPane() {

        // Set main application window
        JFrame frame = new JFrame("Primer");
        frame.setSize(800, 600); // Window dimensions
        frame.setLocationRelativeTo(null); // Center of the screen
        frame.setLayout(null);
        frame.setVisible(true);

        // Set the main menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Set the Actions menu item
        JMenu actionsMenu = new JMenu("Actions");

        // Set the Help menu item
        JMenu helpMenu = new JMenu("Help");

        // Add menu items to the menu bar
        menuBar.add(actionsMenu);
        menuBar.add(helpMenu);

    }

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