class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        int maxDepth = 0;
        int res = 0;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            TreeNode node = cur.node;
            int depth = cur.depth;

            if (depth > maxDepth) {
                maxDepth = depth;
                res = node.val;
            } else if (depth == maxDepth) {
                res += node.val;
            }

            if (node.left != null)
                q.offer(new Pair(node.left, depth + 1));
            if (node.right != null)
                q.offer(new Pair(node.right, depth + 1));
        }

        return res;
    }

    // Helper class for queue
    static class Pair {
        TreeNode node;
        int depth;
        Pair(TreeNode n, int d) {
            node = n;
            depth = d;
        }
    }
}



// runtime - 1ms
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
    
   private int maxLevel = 0;
    private int sum = 0;
    
    public int deepestLeavesSum(TreeNode root) {
        if(root == null) return 0;
        calculateSumAtLevel(root,0);
        return sum;
        
    }
    
    private void calculateSumAtLevel(TreeNode root,int level){
        
       if(root == null) return;
        if(level > maxLevel){
            sum = 0;
            maxLevel = level;
        }
        if(level == maxLevel){
            sum = sum + root.val;
        }
        calculateSumAtLevel(root.left,level+1);
        calculateSumAtLevel(root.right,level+1);
    }
}


// runtime - 2ms
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
    private int maxDepthOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(maxDepthOfTree(root.left), maxDepthOfTree(root.right));
    }
    private int deepestLeavesSum(TreeNode root, int currDepth, int maxDepth){
        if(root == null){
            return 0;
        }

        // at max depth node will be garunteed to be leaf...
        if(currDepth == maxDepth){
            return root.val;
        }
        return deepestLeavesSum(root.left, currDepth + 1, maxDepth) + deepestLeavesSum(root.right, currDepth + 1, maxDepth);
               
    }
    public int deepestLeavesSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        int maxDepth = maxDepthOfTree(root);
        return deepestLeavesSum(root, 1, maxDepth);
    }   
}


// runtime - 3ms
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
    public int deepestLeavesSum(TreeNode root) {
        int depth = findDepth(root);
        System.out.println(depth);
        return findSum(root, depth);
    }
    int findDepth(TreeNode root){
        if(root == null) {
            return 0;
        }
        return Math.max(findDepth(root.left), findDepth(root.right)) + 1;
    }
    
    int findSum(TreeNode root,int depth){
        if(root == null){
            return 0;
        }
        if(depth == 1) {
            return root.val;
        }
        return findSum(root.left, depth-1) + findSum(root.right, depth-1);
        
    }
}


// runtime - 5ms
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
    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(depth(root.left),depth(root.right))+1;
    }
    public int deepestLeavesSum(TreeNode root) { 
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return root.val;
        }
        if(depth(root.left)==depth(root.right)){
            return deepestLeavesSum(root.left)+deepestLeavesSum(root.right);
        }
        if(depth(root.left)>depth(root.right)){
            return deepestLeavesSum(root.left);
        }
        return deepestLeavesSum(root.right);
    }
}
