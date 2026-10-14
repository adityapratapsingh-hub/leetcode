
class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        while (sum >= 9) {
            ans.append('9');
            sum -= 9;
            num--;
        }

        if (num > 0 && sum > 0) {
            ans.append((char) ('0' + sum));
            num--;
        }

        while (num > 0) {
            ans.append('0');
            num--;
        }

        return ans.toString();
    }
}
