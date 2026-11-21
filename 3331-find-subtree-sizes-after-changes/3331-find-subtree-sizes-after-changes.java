
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] oldLast = new int[n];

        Arrays.fill(last, -1);

        // Iterative DFS on original tree
        int[] stack = new int[2 * n + 5];
        int top = 0;

        stack[top++] = 1; // Enter node 0

        while (top > 0) {
            int event = stack[--top];

            if (event > 0) {
                int node = event - 1;
                int ch = s.charAt(node) - 'a';

                oldLast[node] = last[ch];

                // Keep original parent if no matching ancestor
                newParent[node] = (oldLast[node] == -1)
                        ? parent[node]
                        : oldLast[node];

                last[ch] = node;

                stack[top++] = -(node + 1);

                for (int i = adj[node].size() - 1; i >= 0; i--) {
                    stack[top++] = adj[node].get(i) + 1;
                }

            } else {
                int node = -event - 1;
                int ch = s.charAt(node) - 'a';

                last[ch] = oldLast[node];
            }
        }

        // Build modified tree
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] order = new int[n];
        top = 0;
        int count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[count++] = node;

            for (int child : tree[node]) {
                stack[top++] = child;
            }
        }

        // Bottom-up calculation
        for (int i = count - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
