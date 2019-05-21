public class CheckNumber {

    /**
     * Checks if a given string is an nteger or not.
     * 
     * @param str The string to check.
     * 
     * @return Returns true if given string is a valid integer, otherwise false.
     */
    public boolean isInteger(String str) {
        if (str == null || str.length() == 0) {
            return false;
        } else {
            int numLength = str.length();

            int i = 0;

            if (str.charAt(0) == '-') {
                if (numLength == 1) {
                    return false;
                }
                i = 1;
            }

            for (; i < numLength; i++) {
                char c = str.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }

            return true;
        }

    }
}