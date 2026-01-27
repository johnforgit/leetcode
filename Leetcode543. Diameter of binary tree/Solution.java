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
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    // returns height
    public int dfs(TreeNode node) {
        if(node==null)
            return 0;

        // get the heights of the left and right subtree
        int left = dfs(node.left);
        int right = dfs(node.right);
        res = Math.max(res, left+right);
        return 1 + Math.max(left, right);
    }
}
