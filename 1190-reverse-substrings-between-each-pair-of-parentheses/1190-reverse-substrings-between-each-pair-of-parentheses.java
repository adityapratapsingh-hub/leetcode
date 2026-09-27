import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save current string
                stack.push(current);
                current = new StringBuilder();

            } else if (ch == ')') {
                // Reverse current substring
                current.reverse();

                // Get previous string
                StringBuilder previous = stack.pop();

                // Join reversed substring with previous
                previous.append(current);

                current = previous;

            } else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}