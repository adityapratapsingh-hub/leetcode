import java.util.*;

class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(result, new StringBuilder(), 0, 0, n);

        return result;
    }

    private void backtrack(List<String> result,
                            StringBuilder current,
                            int open,
                            int close,
                            int n) {

        // If length becomes 2*n, we have a complete answer
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // We can add '(' if we still have opening brackets
        if (open < n) {
            current.append('(');

            backtrack(result, current, open + 1, close, n);

            current.deleteCharAt(current.length() - 1);
        }

        // We can add ')' only if there is an unmatched '('
        if (close < open) {
            current.append(')');

            backtrack(result, current, open, close + 1, n);

            current.deleteCharAt(current.length() - 1);
        }
    }
}