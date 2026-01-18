// solution using bfs
class FindElements {
    unordered_set<int> seen;

public:
    FindElements(TreeNode* root) { bfs(root); }

    bool find(int target) { return seen.find(target) != seen.end(); }

private:
    void bfs(TreeNode* root) {
        queue<TreeNode*> queue;
        root->val = 0;
        queue.push(root);

        while (!queue.empty()) {
            TreeNode* currentNode = queue.front();
            queue.pop();
            // visit currentNode by adding its recovered value to the set
            seen.insert(currentNode->val);
            if (currentNode->left) {
                currentNode->left->val = currentNode->val * 2 + 1;
                queue.push(currentNode->left);
            }
            if (currentNode->right) {
                currentNode->right->val = currentNode->val * 2 + 2;
                queue.push(currentNode->right);
            }
        }
    }
};

// solution using dfs
class FindElements {
public:
    FindElements(TreeNode* root) { dfs(root, 0); }

    bool find(int target) { return seen.count(target) > 0; }

private:
    unordered_set<int> seen;

    void dfs(TreeNode* currentNode, int currentValue) {
        if (!currentNode) return;
        // visit current node by adding its value to seen
        seen.insert(currentValue);
        dfs(currentNode->left, currentValue * 2 + 1);
        dfs(currentNode->right, currentValue * 2 + 2);
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
class FindElements {
public:
    /*
    If root.val start with 1, equation of left and right become:
        left.val = 2*x
        left.right = 2*x + 1
    So, if represent values in binary, left value shifts parent value by 1 and add 0 to the end
    while right value shifts parent value by 1 and add 1 to the end
    So, for each target, we add 1, then find the first '1' in binary from the left
    Ignore the first '1' as that is the root
    Then move from left to right, if meet 0, mean move from parent to left node
    otherwise, meet 1 then move from parent to right node
    */
    TreeNode* root;

    FindElements(TreeNode* root) {
        this->root = root;
    }
    
    // O(lgn)
    bool find(int target) {
        target++;
        int k = 30;
        while ((target & (1 << k)) == 0) k--;
        k--; // ignore the root
        TreeNode* node = root;
        while (k >= 0) {
            if ((target & (1 << k)) == 0) node = node->left;
            else node = node->right;
            
            if (node == NULL)
                return false;
            k--;
        }
        return true;
    }
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */

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
#include <unordered_map>
#include <unordered_set>
class FindElements {
public:
    FindElements(TreeNode* root) {

		this->root = root;
		this->root->val = 0;
		populate(root, 0);
    }
    
    bool find(int target) {
		return value_set.count(target) > 0;
    }

private:
	TreeNode* root;
	std::unordered_set<int> value_set = {0};

	void populate(TreeNode* root, int value) {

		int new_val; 								// the new value to be computed
		if (root->right) {
			new_val = value * 2 + 2;
			value_set.emplace(new_val);			// Store the value in a hash map
			root->right->val = new_val;
			populate(root->right, new_val);
		}

		if (root->left) {
			new_val = value * 2 + 1;
			value_set.emplace(new_val);
			root->left->val = new_val;
			populate(root->left, new_val);
		}

	}

	bool dfs(TreeNode* root, int target) {
		if (not root) 
			return false;

		if (root->val == target)
			return true;

		return (dfs(root->right, target) || dfs(root->left, target));
	}
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */

// runtime - 2ms
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
class FindElements {
public:
    FindElements(TreeNode* root) {
        std::queue<TreeNode*> q;

        q.push(root);

        root->val = 0;
        _values.insert(0);

        while (!q.empty()) {
            auto sz = q.size();

            while (sz--) {
                auto* node = q.front();

                q.pop();

                int parentVal = node->val;

                if (node->left) {
                    int val = (parentVal * 2) + 1;

                    _values.insert(val);

                    node->left->val = val;
                    q.push(node->left);
                }

                if (node->right) {
                    int val = (parentVal * 2) + 2;

                    _values.insert(val);

                    node->right->val = val;
                    q.push(node->right);
                }
            }
        }
    }
    
    bool find(int target) {
        return _values.find(target) != _values.end();
    }

private:

    std::unordered_set<int> _values;
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */
