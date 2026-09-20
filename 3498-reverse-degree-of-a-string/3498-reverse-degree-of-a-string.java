class Solution {

    public int reverseDegree(String s) {

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Reverse alphabet position
            int reversePosition = 26 - (ch - 'a');

            // String position (1-indexed)
            int position = i + 1;

            // Add product
            sum += reversePosition * position;
        }

        return sum;
    }
}