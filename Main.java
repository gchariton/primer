import java.math.BigInteger;
import java.util.List;

public class Main {
    public static String inputInteger = null;

    public static void main(String[] args) {
        inputInteger = args[0];
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

        // The list of the prime factors of the given integer
        resultList = new PrimeFactors().findPrimeFactors(BI);

        // Export results
        if (resultList.size() == 1) { // In this case, the given integer is a prime itself
            System.out.println("Given number is a prime.");
        } else {
            System.out.print("Given number's prime factors are: ");
            System.out.println(resultList);
        }
    }
}