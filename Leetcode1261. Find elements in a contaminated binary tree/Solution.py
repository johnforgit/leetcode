# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class FindElements:

    def __init__(self, root: Optional[TreeNode]):
        self.root = root
        self.root.val = 0
        self.data = set()
        self.recover_tree(self.root)

    def find(self, target: int) -> bool:
        return True if target in self.data else False

    def recover_tree(self, root):
        if not root:
            return 
        self.data.add(root.val)
        if root.right:
            root.right.val = (2*root.val) + 2
            self.recover_tree(root.right)
        if root.left:
            root.left.val = (2*root.val) + 1
            self.recover_tree(root.left)



# Your FindElements object will be instantiated and called as such:
# obj = FindElements(root)
# param_1 = obj.find(target)
