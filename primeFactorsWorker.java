import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class primeFactorsWorker extends SwingWorker<List<BigInteger>, Integer> {

    private List<BigInteger> primeFactorsList;
    private BigInteger bi;
    private JButton cancelButton;
    private JLabel msgLabel;

    // Constructor
    public primeFactorsWorker(BigInteger bi, JButton cancelButton, JLabel msgLabel) {
        this.bi = bi;
        this.cancelButton = cancelButton;
        this.msgLabel = msgLabel;
    }

    @Override
    protected List<BigInteger> doInBackground() throws Exception {
        BigInteger two = BigInteger.valueOf(2);
        primeFactorsList = new LinkedList<BigInteger>();

        while (bi.mod(two).equals(BigInteger.ZERO)) {
            primeFactorsList.add(two);
            bi = bi.divide(two);
        }

        if (bi.compareTo(BigInteger.ONE) > 0) {
            BigInteger bif = BigInteger.valueOf(3);

            while (bif.multiply(bif).compareTo(bi) <= 0) {
                if (bi.mod(bif).equals(BigInteger.ZERO)) {
                    primeFactorsList.add(bif);
                    bi = bi.divide(bif);
                } else {
                    bif = bif.add(two);
                }
            }

            primeFactorsList.add(bi);
        }

        return primeFactorsList;
    }

    @Override
    protected void process(List<Integer> progress) {
        for (final Integer prg : progress) {
            cancelButton.setText("<html><b>Click here to cancel process</b></html>");
            msgLabel.setText("<html>Please wait...<br />Calculating... " + prg + "%</html>");
        }
    }

    private static void stopIfInterrupted() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Interrupted by user while calculting");
        }
    }

    @Override
    protected void done() {

        try {

            if (get().size() == 1) { // Given number is a prime itself
                PrimerWindow.resultsLabel.setText(null);
                PrimerWindow.resultsLabel.setText("Given number is a prime itself.");
            } else { // In this case the prime factors of the given number are printed
                PrimerWindow.resultsLabel.setText(null);
                PrimerWindow.resultsLabel
                        .setText("<html>Given number's prime factors are: <b>" + get() + "</b></html>");
            }

        } catch (Exception e) {

        }

    }

}