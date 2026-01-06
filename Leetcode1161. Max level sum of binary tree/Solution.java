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
/*
class Solution {
    static {
    Runtime.getRuntime().gc();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (FileWriter writer = new FileWriter("display_runtime.txt")) {
            writer.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }));
}

    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        int maxLevel = -1;
        int maxSum = Integer.MIN_VALUE;

        Queue<TreeNode> levels = new ArrayDeque<>();
        levels.add(root);
        int level =1;
        while (!levels.isEmpty()) {
            int levelSize = levels.size();
            int sum = 0;
            for (int i=0;i<levelSize;i++) {
                TreeNode node = levels.remove();
                sum += node.val;
                if (node.left != null) levels.add(node.left);
                if (node.right != null) levels.add(node.right);
            }
            if (maxSum < sum) {
                maxSum = sum;
                maxLevel = level;
            }
            level++;
        }
        
        return maxLevel;
    }
}
*/
class Solution {
    public void dfs(TreeNode node, int level, List<Integer> sumOfNodesAtLevel) {
        if (node == null) {
            return;
        }

        if (sumOfNodesAtLevel.size() == level) {
            sumOfNodesAtLevel.add(node.val);
        } else {
            sumOfNodesAtLevel.set(level, sumOfNodesAtLevel.get(level) + node.val);
        }

        dfs(node.left, level + 1, sumOfNodesAtLevel);
        dfs(node.right, level + 1, sumOfNodesAtLevel);
    }

    public int maxLevelSum(TreeNode root) {
        List<Integer> sumOfNodesAtLevel = new ArrayList<>();
        dfs(root, 0, sumOfNodesAtLevel);

        int maxSum = Integer.MIN_VALUE;
        int ans = 0;

        for (int i = 0; i < sumOfNodesAtLevel.size(); i++) {
            if (maxSum < sumOfNodesAtLevel.get(i)) {
                maxSum = sumOfNodesAtLevel.get(i);
                ans = i + 1;
            }
        }

        return ans;
    }
}
