class Solution {
    public int[] maxDepthAfterSplit(String seq) {

        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {

                depth++;

                // Odd depth -> group 1
                if (depth % 2 == 1) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }

            } else {

                // Closing bracket belongs to the same group
                // as its corresponding opening bracket
                if (depth % 2 == 1) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }

                depth--;
            }
        }

        return answer;
    }
}