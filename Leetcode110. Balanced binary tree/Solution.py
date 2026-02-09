# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        return (self.dfsHeight(root) != -1)

    def dfsHeight(self, node:TreeNode) -> int:
        if not node:
            return 0

        leftHeight = self.dfsHeight(node.left)
        if(leftHeight==-1):
            return -1

        rightHeight = self.dfsHeight(node.right)
        if(rightHeight==-1):
            return -1

        if(abs(leftHeight - rightHeight) > 1):
            return -1

        return 1 + max(leftHeight, rightHeight)

# runtime - 0ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:

        def help(cur,h):
            if not cur: return 0
            
            curL = help(cur.left, h)
            curR = help(cur.right,h)

            if curL == -1 or curR == -1: return -1

            if abs(curL - curR) > 1: return -1
            return 1 + max(curL, curR)

        return True if help(root, 0) != -1 else False

        
