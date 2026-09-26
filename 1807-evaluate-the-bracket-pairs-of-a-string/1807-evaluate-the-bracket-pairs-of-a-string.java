import java.util.*;

class Solution {

    public String evaluate(String s, List<List<String>> knowledge) {

        // Store knowledge in HashMap
        HashMap<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
            }

            // Bracket pair
            else {

                i++; // skip '('

                StringBuilder key = new StringBuilder();

                // Read key until ')'
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Get value from map
                String value = map.getOrDefault(
                    key.toString(),
                    "?"
                );

                ans.append(value);

                i++; // skip ')'
            }
        }

        return ans.toString();
    }
}