class Solution {

    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode root, long min, long max) {

        // Empty tree is valid
        if (root == null) {
            return true;
        }

        // Current value must be strictly inside the range
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // Left subtree:
        // values must be smaller than root.val
        boolean left = check(root.left, min, root.val);

        // Right subtree:
        // values must be greater than root.val
        boolean right = check(root.right, root.val, max);

        return left && right;
    }
}