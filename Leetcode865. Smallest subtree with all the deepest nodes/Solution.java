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

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    public Result dfs(TreeNode node) {
        if(node==null)
            return new Result(null, 0);
        Result L = dfs(node.left);
        Result R = dfs(node.right);

        if(L.dist > R.dist)
            return new Result(L.node, L.dist+1);
        if(L.dist < R.dist)
            return new Result(R.node, R.dist+1);

        return new Result(node, L.dist+1);
    }
}

class Result {
    TreeNode node;
    int dist;
    Result(TreeNode n, int d) {
        node = n;
        dist = d;
    }
}

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

    Map<TreeNode, Integer> depth;
    int max_depth;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth = new HashMap<>();
        depth.put(null, -1);

        // find the depth of each node in the tree
        dfs(root, null);
        max_depth = -1;
        for(Integer d:depth.values())
            max_depth = Math.max(max_depth, d);
        return answer(root);
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if(node != null) {
            depth.put(node, depth.get(parent)+1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public TreeNode answer(TreeNode node) {
        if(node==null || depth.get(node) == max_depth) {
            return node;
        }
        TreeNode L = answer(node.left);
        TreeNode R = answer(node.right);

        if(L != null && R != null)
            return node;
        if(L != null)
            return L;
        if(R != null)
            return R;
        return null;
    }
}


// runtime - 0ms
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
class pair{
    int ht;
    TreeNode node;
    public pair(int h, TreeNode n){
        this.ht= h;
        this.node= n;
    }
}
class Solution {
    public pair helper(TreeNode root){

        if(root==null){
            return new pair(0,null);
        }
        pair left= helper(root.left);
        pair right= helper(root.right);

        int ansht= (Math.max(left.ht,right.ht))+1;
        if(left.ht==right.ht){
            return new pair(ansht,root);
        }
        else if(left.ht > right.ht){
            return new pair(ansht, left.node);
        }
        else{
            return new pair(ansht,right.node);
        }

    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        pair res= helper(root);
        return res.node;
        
    }
}

