import java.math.BigInteger;
import java.util.List;

public class Main {
    public static String inputInteger = null;

    public static void main(String[] args) {
        inputInteger = args[0];
        BigInteger BI = null;
        List<BigInteger> resultList = null;

        // Setting up GUI
        new PrimerWindow().createPrimerWindow();

        try {
            BI = new BigInteger(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid integer.");
            System.out.println("Program will now exit.");
            System.exit(0);
        }

        resultList = new PrimeFactors().findPrimeFactors(BI);

        if (resultList.size() == 1) {
            System.out.println("Given number is a prime.");
        } else {
            System.out.print("Given number's prime factors are: ");
            System.out.println(resultList);
        }
    }
}