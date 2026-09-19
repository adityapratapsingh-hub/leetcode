class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        // Nearest x-coordinate of rectangle
        int closestX = Math.max(x1, Math.min(xCenter, x2));

        // Nearest y-coordinate of rectangle
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Difference between circle center and nearest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        // Compare squared distance with squared radius
        return dx * dx + dy * dy <= radius * radius;
    }
}