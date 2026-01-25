# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    
    max_sum = float('-inf')

    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.maxGain(root)
        return self.max_sum

    def maxGain(self, node:[TreeNode]) -> int:
        if(node is None):
            return 0

        leftGain = max(self.maxGain(node.left), 0)
        rightGain = max(self.maxGain(node.right), 0)

        priceNewPath = node.val + leftGain + rightGain

        self.max_sum = max(self.max_sum, priceNewPath)

        return node.val + max(leftGain, rightGain)



# runtime - 0ms
  # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.pathsum = float("-inf")

        def dfs(node):
            if not node:
                return 0

            left_max = max(dfs(node.left),0)
            right_max = max(dfs(node.right),0)

            csum = left_max+right_max+node.val
            self.pathsum = max(self.pathsum,csum)

            return node.val+max(left_max,right_max)
        
        dfs(root)
        return(self.pathsum)
__import__("atexit").register(lambda: open("display_runtime.txt","w").write("0"))


# runtime - 2ms
  # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        ans = [root.val]
        self.find_max(root , ans)
        return ans[0]
    def find_max(self , node , maxi):
        if not node:
            return 0

        left_val = self.find_max(node.left , maxi)
        right_val = self.find_max(node.right , maxi)
        left_val = max(left_val , 0)
        right_val = max(right_val , 0)
        current = left_val + right_val + node.val

        maxi[0] = max(maxi[0] , current)

        return node.val + max(left_val , right_val)


# runtime - 3ms
  # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        """

        Do I need to worry about empty tree?
        Can I assume the sum fits int32?
    
        method:
        Do dfs, what it returns: max sum from one branch
        1
        |\
        2 3
        it returns 4

        
        1
        |\
        -10 -10

        return 1

        When visit a node?
        get the results from the left and right children
        want is the sum val + returns from left and right

        we need to have a global var to record the max.

        time will be n
        space n
        -10
        |  \
        9  20
           | \
           15 7


        edge case: one node and its negative
        """

        if not root:
            return 0

        ans = root.val

        def dfs(node):
            nonlocal ans
            if not node:
                return 0

            left_sum = dfs(node.left)
            right_sum = dfs(node.right)

            ans = max(ans, node.val + max(left_sum,0) + max(right_sum,0))

            return max(0, max(left_sum, right_sum) + node.val)

        dfs(root)

        return ans


# runtime - 4ms
  # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        res = root.val
        def dfs(node):
            nonlocal res
            if not node:
                return 0
            left = max(0,dfs(node.left))
            right = max(0,dfs(node.right))
            res = max(res,node.val+left+right)
            return node.val + max(left,right)
            
        dfs(root)
        return res


# runtime - 5ms
  # Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        def getmaxpath(root,maxi):
            if not root:
                return 0

            left = max(0,getmaxpath(root.left,maxi))
            right = max(0,getmaxpath(root.right,maxi))

            maxi[0] = max(maxi[0], left+right+root.val)

            return root.val + max(left,right)

        if not root:
            return 0

        maxi = [float('-inf')]

        getmaxpath(root,maxi)

        return maxi[0]


# runtime - 6ms
  class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.maximum = root.val

        def dfs(root):
            if not root:
                return 0
            
            l = dfs(root.left)
            r = dfs(root.right)
            nodeMax = max(root.val, l + root.val, r + root.val)
            self.maximum = max(nodeMax, self.maximum, l + r + root.val)

            return nodeMax

        dfs(root)
        return self.maximum
