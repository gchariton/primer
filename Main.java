import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static long hops = 0;

    public static void main(String[] args) {
        BigInteger BI = null;
        List<BigInteger> resultList = null;

        // while (args[0] == null || args[0].matches("[0-9]+")) {
        // System.out.println("Please enter a valid integer number.");
        // }

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