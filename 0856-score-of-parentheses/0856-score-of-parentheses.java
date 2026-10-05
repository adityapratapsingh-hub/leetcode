import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        // Base score
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Start a new group
                stack.push(0);
            } 
            else {
                // Score inside current parentheses
                int inside = stack.pop();

                // "()" = 1
                // "(A)" = 2 * A
                int score = Math.max(2 * inside, 1);

                // Add score to previous level
                int previous = stack.pop();
                stack.push(previous + score);
            }
        }

        return stack.peek();
    }
}