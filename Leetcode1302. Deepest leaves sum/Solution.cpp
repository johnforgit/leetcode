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
    int deepestLeavesSum(TreeNode* root) {
        queue<pair<TreeNode*, int>> q;
        q.push({root, 0});

        int maxDepth = 0;
        int res = 0;

        while (!q.empty()) {
            auto [node, depth] = q.front();
            q.pop();

            if (depth > maxDepth) {
                maxDepth = depth;
                res = node->val;
            } else if (depth == maxDepth) {
                res += node->val;
            }

            if (node->left)
                q.push({node->left, depth + 1});
            if (node->right)
                q.push({node->right, depth + 1});
        }

        return res;
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
    int maxlevel=0;
    unordered_map<int,int> mp;
    void dfs(TreeNode *root,int level){
        if(!root) return;
        mp[level]+=root->val;
        maxlevel=max(maxlevel,level);
        dfs(root->left,level+1);
        dfs(root->right,level+1);
    }
    int deepestLeavesSum(TreeNode* root) {
        dfs(root,0);
        return mp[maxlevel];
    }
};


// runtime - 1ms
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
    int height(TreeNode * root) {
        if(!root) {
            
            return 0;
        }
        return 1 + max(height(root->left), height(root->right));
    }
    int sum(TreeNode * root, int h) {
        if(!root)  {
            return 0;
        }
        if(!root->left  and !root->right) {
            if(h == 1) {
                return root->val;
            }
            return 0;
        }
        int l = sum(root->left, h - 1);
        int r = sum(root->right, h - 1);
        return l + r;
    }
    int deepestLeavesSum(TreeNode* root) {
        int h = height(root);
        return sum(root, h);
        
    }
};

// runtime - 2ms
class Solution {
public:
    int deepestLeavesSum(TreeNode* root) 
    {
        queue<TreeNode*> q;
        q.push(root);
        int ans = 0;
        
        while (!q.empty()) 
        {
            int s = q.size();
            ans = 0;
            while (s--) {
                TreeNode* cur = q.front();
                q.pop();
                ans += cur->val;
                if (cur->left) q.push(cur->left);
                if (cur->right) q.push(cur->right);
            }
        }
        return ans;
    }
};


// runtime - 3ms
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
    int deepestLeavesSum(TreeNode* root) {
        if(root == NULL){
            return 0;
        }
        queue<TreeNode*> q;
        int sum = 0;
        q.push(root);
        while(!q.empty()){
            int sz = q.size();
            sum = 0;
            while(sz-- && !q.empty()){
                TreeNode* u = q.front();
                q.pop();
                sum += u -> val;
                if(u -> left){
                    q.push(u -> left);
                }
                if(u -> right){
                    q.push(u -> right);
                }
            }
        }
        return sum;
    }
};


// runtime - 4ms
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
    int deepSum(TreeNode*root,int h){
        if(!root) return 0;
        else if(h==1){
            return root->val;
        }
        int left=deepSum(root->left,h-1),right=deepSum(root->right,h-1);
        return left+right;
    }
    int height(TreeNode*root){
        if(!root) return 0;
        return 1+max({height(root->left),height(root->right)});
    }
    int deepestLeavesSum(TreeNode* root) {
        int depth=height(root);
        return deepSum(root,depth);
    }
};
