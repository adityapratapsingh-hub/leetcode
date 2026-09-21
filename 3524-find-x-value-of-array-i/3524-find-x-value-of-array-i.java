class Solution {

    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            // 1. Start a new subarray
            int remainder = num % k;

            next[remainder]++;

            // 2. Extend previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {

                    int newRemainder = (int) ((long) r * num % k);

                    next[newRemainder] += dp[r];
                }
            }

            // 3. Add counts to result
            for (int r = 0; r < k; r++) {

                result[r] += next[r];
            }

            // 4. Update dp
            dp = next;
        }

        return result;
    }
}