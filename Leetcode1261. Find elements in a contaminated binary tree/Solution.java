// solution using dfs
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
class FindElements {
    HashSet<Integer> data;
    public FindElements(TreeNode root) {
        data = new HashSet<>();
        dfs(root, 0);
    }
    
    public boolean find(int target) {
        return data.contains(target);
    }

    public void dfs(TreeNode currentNode, int currentValue) {
        if(currentNode == null)
            return;
        
        data.add(currentValue);
        dfs(currentNode.left, (currentValue*2)+1);
        dfs(currentNode.right, (currentValue*2)+2);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */

// solution using bfs
class FindElements {

    HashSet<Integer> seen;

    public FindElements(TreeNode root) {
        seen = new HashSet<>();
        bfs(root);
    }

    public boolean find(int target) {
        return seen.contains(target);
    }

    private void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        root.val = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.remove();
            // visit currentNode by adding its recovered value to the set
            seen.add(currentNode.val);
            if (currentNode.left != null) {
                currentNode.left.val = currentNode.val * 2 + 1;
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                currentNode.right.val = currentNode.val * 2 + 2;
                queue.add(currentNode.right);
            }
        }
    }
}

// runtime - 19 ms
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
class FindElements {
    BitSet set = new BitSet();

    public FindElements(TreeNode root) {
        root.val = 0;
        set.set(0);
        recover(root);
    }
    public void recover(TreeNode root){
        if(root.left!=null){
            root.left.val = (2*root.val)+1;
            set.set(root.left.val);
            recover(root.left);
        }
        if(root.right!=null){
            root.right.val = (2*root.val)+2;
            set.set(root.right.val);
            recover(root.right);
        }
    }
    
    public boolean find(int target) {
        return set.get(target);

    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
