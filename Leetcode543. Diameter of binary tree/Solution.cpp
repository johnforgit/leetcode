/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

using namespace std;
class Solution {
public:

    int res = 0;

    int diameterOfBinaryTree(TreeNode* root) {
        dfs(root);
        return res;
    }

    int dfs(TreeNode* node) {
        if(node==nullptr)
            return 0;

        // find the heights of the left and right subtree
        int left = dfs(node->left);
        int right = dfs(node->right);
        res = max(res, left+right);
        return 1+max(left, right);
    }
};
