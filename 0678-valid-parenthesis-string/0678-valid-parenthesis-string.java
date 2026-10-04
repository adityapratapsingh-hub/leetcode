class Solution {
    public boolean checkValidString(String s) {

        int min = 0;
        int max = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                min++;
                max++;
            }

            else if (ch == ')') {
                min--;
                max--;
            }

            else { // '*'

                // '*' can be ')' or '(' or empty
                min--;
                max++;
            }

            // Even the maximum possible balance is negative
            if (max < 0) {
                return false;
            }

            // Minimum balance cannot be negative
            min = Math.max(min, 0);
        }

        // If balance 0 is possible, string is valid
        return min == 0;
    }
}