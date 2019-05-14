import java.util.LinkedList;
import java.util.List;
import java.math.BigInteger;

public class PrimeFactors {

    // Checks input and calculates prime factors
    public List<BigInteger> findPrimeFactors(BigInteger n) {
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
