import java.math.BigInteger;
import java.util.List;

public class Main {
    public static String inputInteger = null;

    public static void main(String[] args) {
        BigInteger BI = null;
        List<BigInteger> resultList = null;

        // Setting up main window GUI
        new PrimerWindow().createPrimerWindow();

        // Insert the integer to check
        try {
            BI = new BigInteger(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid integer.");
            System.out.println("Program will now exit.");
            System.exit(0);
        }
    }
}