import java.util.*;

class Solution {

    static class State {
        long score;
        int count;
        int[] ids;

        State(long score, int count, int[] ids) {
            this.score = score;
            this.count = count;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }

            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[3], b[3]);
        });

        // next[i] = first interval whose left > arr[i][1]
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, i);
        }

        /*
         * dp[i][k]
         * = best answer from i onwards
         *   using at most k intervals
         */
        State[][] dp = new State[n + 1][5];

        // IMPORTANT:
        // Initialize EVERY DP cell.
        // This prevents NullPointerException.
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(
                    0,
                    0,
                    new int[0]
                );
            }
        }

        // Fill DP
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // -----------------------
                // OPTION 1: SKIP
                // -----------------------
                State skip = dp[i + 1][k];

                // -----------------------
                // OPTION 2: TAKE
                // -----------------------
                State after = dp[next[i]][k - 1];

                int newCount = after.count + 1;

                int[] takeIds = new int[newCount];

                // Current interval's original index
                takeIds[0] = arr[i][3];

                // Add indices from future intervals
                for (int j = 0; j < after.count; j++) {
                    takeIds[j + 1] = after.ids[j];
                }

                // Sort indices for lexicographical comparison
                Arrays.sort(takeIds);

                State take = new State(
                    arr[i][2] + after.score,
                    newCount,
                    takeIds
                );

                // Choose better one
                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].ids;
    }

    // Binary search:
    // first interval with left > current right
    private int findNext(int[][] arr, int index) {

        int target = arr[index][1];

        int low = index + 1;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid][0] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Compare two states
    private State better(State a, State b) {

        // Safety check
        if (a == null) return b;
        if (b == null) return a;

        // 1. Maximum score
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // 2. Lexicographically smaller indices
        int min = Math.min(a.count, b.count);

        for (int i = 0; i < min; i++) {

            if (a.ids[i] != b.ids[i]) {
                return a.ids[i] < b.ids[i] ? a : b;
            }
        }

        // If one is prefix of the other,
        // shorter array is lexicographically smaller
        if (a.count != b.count) {
            return a.count < b.count ? a : b;
        }

        return a;
    }
}