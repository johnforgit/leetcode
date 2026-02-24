class Solution:
    def sumRootToLeaf(self, root):
        
        def dfs(node, current):
            if not node:
                return 0
            
            # Build binary number
            current = current * 2 + node.val
            
            # If leaf node
            if not node.left and not node.right:
                return current
            
            # Return sum of left and right subtree
            return dfs(node.left, current) + dfs(node.right, current)
        
        return dfs(root, 0)

# runtime - 0ms
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def sumRootToLeaf(self, root):
        res = 0

        def dfs(node,num):
            if not node:
                return
            nonlocal res
            num += str(node.val)
            if not node.left and not node.right:
                res += int(num,2)
            
            dfs(node.left, num)
            dfs(node.right, num)
        dfs(root, '')

        return res



# runtime - 1ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.path = ""
        self.res = 0

    def sumRootToLeaf(self, root: Optional[TreeNode]) -> int:
        self.dfs(root)
        return self.res

    def dfs(self, root):   
        if root is None:
            return 

        self.path += str(root.val)
        
        if root.left is None and root.right is None:
            self.res += int(self.path, 2)

        self.dfs(root.left)
        self.dfs(root.right)

        self.path = self.path[:-1]

# runtime - 2ms
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def sumRootToLeaf(self, root):
        nums = []

        def dfs(node,num):
            if not node:
                return
            num += str(node.val)
            if not node.left and not node.right:
                nums.append(num)
            
            dfs(node.left, num)
            dfs(node.right, num)
        dfs(root, '')

        res = 0

        for n in nums:
            res += int(n,2)
        return res

        
