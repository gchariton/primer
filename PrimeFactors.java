import java.util.LinkedList;
import java.util.List;

import java.math.BigInteger;

public class PrimeFactors {

    // Checks input and calculates prime factors
    public List<BigInteger> findPrimeFactors(BigInteger n) {
        BigInteger two = BigInteger.valueOf(2);
        List<BigInteger> primeFactorsList = new LinkedList<BigInteger>();

        while (n.mod(two).equals(BigInteger.ZERO)) {
            primeFactorsList.add(two);
            n = n.divide(two);
        }

        if (n.compareTo(BigInteger.ONE) > 0) {
            BigInteger f = BigInteger.valueOf(3);

            while (f.multiply(f).compareTo(n) <= 0) {
                if (n.mod(f).equals(BigInteger.ZERO)) {
                    primeFactorsList.add(f);
                    n = n.divide(f);
                } else {
                    f = f.add(two);
                }
            }

            primeFactorsList.add(n);
        }

        return primeFactorsList;
    }
}
