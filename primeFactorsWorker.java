import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class PrimeFactorsWorker extends SwingWorker<List<BigInteger>, Integer> {

    private List<BigInteger> primeFactorsList;
    private BigInteger bi;
    private BigInteger biUnchanged;
    private JButton calcButton;
    private JLabel resultsLabel;
    private JProgressBar progressBar;
    private String copyResult;

    // Constructor
    public PrimeFactorsWorker(BigInteger bi, JButton calcButton, JLabel resultsLabel, JProgressBar progressBar,
            String copyResult) {

        this.biUnchanged = new BigInteger(bi.toString());
        this.bi = bi;
        this.calcButton = calcButton;
        this.resultsLabel = resultsLabel;
        this.progressBar = progressBar;
        this.copyResult = copyResult;
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

    }

    private static void stopIfInterrupted() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Interrupted by user while calculting");
        }
    }

    @Override
    protected void done() {

        try {

            if (!isCancelled()) { // Check if process was canceled by user

                // Reset progress bar
                progressBar.setVisible(false);

                // Reset calculation button
                calcButton.setBackground(null);
                calcButton.setBackground(new JButton().getBackground());
                calcButton.setText(null);
                calcButton.setText("Calculate!");

                // Export results
                if (get().size() == 1) { // Given number is a prime itself
                    resultsLabel.setText(null);
                    resultsLabel.setText("Given number is a prime itself.");

                    // Message for clipboard
                    copyResult = "Given number:\n" + biUnchanged + "\n\n" + "This is a prime number!";
                    this.setCopyResult(copyResult);

                } else { // In this case the prime factors of the given number are printed
                    resultsLabel.setText(null);
                    resultsLabel.setText("<html>Given number's prime factors are: <b>" + get() + "</b></html>");

                    // Message for clipboard
                    copyResult = "Given number:\n" + biUnchanged + "\n\nPrime factors:\n" + get();
                    this.setCopyResult(copyResult);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception in primeFactorsWorker.done()" + e);
        }

    }

    public String getCopyResult() {
        return this.copyResult;
    }

    public void setCopyResult(String msg) {
        this.copyResult = msg;
    }
}