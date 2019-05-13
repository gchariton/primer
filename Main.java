import java.math.BigInteger;
import java.util.LinkedList;

public class Main {

    static long hops = 0;

    public static void main(String[] args) {

        LinkedList<BigInteger> resultList = null;

        BigInteger BI = new BigInteger(args[0]);

        resultList = findPrimeFactors(BI);

        if (resultList.size() == 1) {
            System.out.println("Given number is a prime.");
        } else {
            System.out.print("Number's prime factors are: ");
            System.out.println(resultList);
        }
    }

    private static LinkedList<BigInteger> findPrimeFactors(BigInteger n) {
        BigInteger two = BigInteger.valueOf(2);
        LinkedList<BigInteger> fs = new LinkedList<>();

        if (n.compareTo(two) < 0) {
            throw new IllegalArgumentException("must be greater than one");
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