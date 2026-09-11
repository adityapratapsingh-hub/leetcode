class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Case 1: both null
        if (p == null && q == null) {
            return true;
        }

        // Case 2: one is null
        if (p == null || q == null) {
            return false;
        }

        // Case 3: values not equal
        if (p.val != q.val) {
            return false;
        }

        // Check left and right subtree
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}