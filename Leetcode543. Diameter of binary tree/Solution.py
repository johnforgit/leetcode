# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        res = 0

        # returns the height
        def dfs(node:TreeNode) -> int:
            if(node is None):
                return 0

            left = dfs(node.left)
            right = dfs(node.right)

            nonlocal res
            res = max(res, left+right)
            return 1 + max(left, right)

        dfs(root)
        return res
        
