/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    public int dfsHeight(TreeNode node) {
        if(node==null)
            return 0;

        // find the heights of the left and right subtree
        // if any of them is -1, don't check the other tree
        int leftHeight = dfsHeight(node.left);
        if(leftHeight==-1)
            return -1;

        int rightHeight = dfsHeight(node.right);
        if(rightHeight==-1)
            return -1;

        if(Math.abs(leftHeight-rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
