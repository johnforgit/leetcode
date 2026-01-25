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
class Solution {
public:
    int max_sum = INT_MIN;
    int maxPathSum(TreeNode* root) {
        maxGain(root);
        return max_sum;
    }

    int maxGain(TreeNode* node) {
        if(node == nullptr)
            return 0;

        int leftGain = max(maxGain(node->left), 0);
        int rightGain = max(maxGain(node->right), 0);

        int priceNewPath = leftGain + node->val + rightGain;

        max_sum = max(priceNewPath, max_sum);

        return node->val + max(leftGain, rightGain);
    }
};


// runtime - 0ms
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
class Solution {
public:

    int result=INT_MIN;
    int helper(TreeNode* node){
       if(node == nullptr) return 0;

       int left = max(0,helper(node->left));
       int right = max(0,helper(node->right));

       int curr_max = left+right+node->val;
       result = max(result,curr_max);

       return max(left,right)+node->val;
    }
    int maxPathSum(TreeNode* root) {
        helper(root);

        return result;
    }
};
