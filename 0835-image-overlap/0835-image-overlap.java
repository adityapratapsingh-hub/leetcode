class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Try every possible translation
        for (int dr = -(n - 1); dr <= n - 1; dr++) {
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                // Check every cell
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {

                        int nr = r + dr;
                        int nc = c + dc;

                        // Make sure translated cell is inside the matrix
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            if (img1[r][c] == 1 && img2[nr][nc] == 1) {
                                overlap++;
                            }
                        }
                    }
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}
