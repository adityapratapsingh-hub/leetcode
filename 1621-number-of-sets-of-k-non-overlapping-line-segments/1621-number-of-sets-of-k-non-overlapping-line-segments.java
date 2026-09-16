class Solution {
    static final long MOD = 1000000007;

    public int numberOfSets(int n, int k) {

        long[][] dp = new long[n + k][2 * k + 1];

        // dp[i][j] = C(i, j)
        for (int i = 0; i < n + k; i++) {
            dp[i][0] = 1;

            for (int j = 1; j <= 2 * k && j <= i; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
            }
        }

        return (int) dp[n + k - 1][2 * k];
    }
}