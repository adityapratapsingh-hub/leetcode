
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // Build suffix array
        int[] sa = buildSuffixArray(nums);

        // Build LCP array
        int[] lcp = buildLCP(nums, sa);

        int ans = n;

        for (int i = 0; i < n; i++) {
            int maxLCP = lcp[i];

            if (i + 1 < n) {
                maxLCP = Math.max(maxLCP, lcp[i + 1]);
            }

            int len = maxLCP + 1;

            if (len <= n - sa[i]) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int[] a = new int[n + 1];

        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        int N = n + 1;
        int[] p = new int[N];
        int[] c = new int[N];

        int alphabet = 100002;
        int[] cnt = new int[Math.max(alphabet, N)];

        for (int x : a) cnt[x]++;
        for (int i = 1; i < cnt.length; i++)
            cnt[i] += cnt[i - 1];

        for (int i = N - 1; i >= 0; i--)
            p[--cnt[a[i]]] = i;

        c[p[0]] = 0;
        int classes = 1;

        for (int i = 1; i < N; i++) {
            if (a[p[i]] != a[p[i - 1]])
                classes++;
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        for (int h = 0; (1 << h) < N; h++) {
            int shift = 1 << h;

            for (int i = 0; i < N; i++) {
                pn[i] = (p[i] - shift + N) % N;
            }

            Arrays.fill(cnt, 0, classes, 0);

            for (int i = 0; i < N; i++)
                cnt[c[pn[i]]]++;

            for (int i = 1; i < classes; i++)
                cnt[i] += cnt[i - 1];

            for (int i = N - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                int cur = p[i];
                int prev = p[i - 1];

                if (c[cur] != c[prev] ||
                    c[(cur + shift) % N] !=
                    c[(prev + shift) % N]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;
        }

        return Arrays.copyOfRange(p, 1, N);
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;

        int k = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + k < n && j + k < n &&
                   nums[i + k] == nums[j + k]) {
                k++;
            }

            lcp[r] = k;

            if (k > 0) k--;
        }

        return lcp;
    }
}
