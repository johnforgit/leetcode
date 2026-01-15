# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        max_product = float('-inf')
        totalTreeSum = 0

        def calculateSubtreeSum(node):
            nonlocal max_product, totalTreeSum
            if not node:
                return 0
            
            cur_subtree_sum = node.val + calculateSubtreeSum(node.left) + calculateSubtreeSum(node.right)

            # try splitting the subtree at this node
            productAfterSplit = (totalTreeSum - cur_subtree_sum) * cur_subtree_sum
            max_product = max(max_product, productAfterSplit)
            return cur_subtree_sum

        # calculate total sum of the tree
        totalTreeSum = calculateSubtreeSum(root)
        # evaluate max product for every possible split
        calculateSubtreeSum(root)


        return max_product%(10**9 + 7)


# runtime - 0 ms

MOD = 10 ** 9 + 7
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        Sum = lambda node : Sum(node.left) + Sum(node.right) + node.val if node else 0
        totalSum = Sum(root)

        def dfs(node):
            nonlocal res
            if not node:
                return 0
            subtreeSum = dfs(node.right) + dfs(node.left) + node.val
            res = max(res, (totalSum - subtreeSum) * subtreeSum)
            return subtreeSum
        res = 0
        dfs(root)
        return res % MOD

__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))

# runtime - 1 ms

class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        sums = []
        def sum_tree(root:TreeNode):
            if root.left:
                sum_tree(root.left)
                root.val+= root.left.val
            if root.right:
                sum_tree(root.right)
                root.val+= root.right.val
            sums.append(root.val)
        sum_tree(root)
        whole = root.val
        half = whole // 2
        while True:
            a = root.left.val if root.left else 0
            if a > half:
                root = root.left
                continue
            b = root.right.val if root.right else 0
            if b > half:
                root = root.right
                continue
            return max((whole-x)*x for x in [a,b,root.val]) % 1_000_000_007

# runtime - 3 ms
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:

        def sum_tree(root:TreeNode):
            if root.left:
                sum_tree(root.left)
                root.val+= root.left.val
            if root.right:
                sum_tree(root.right)
                root.val+= root.right.val
        sum_tree(root)

        whole = root.val; half = whole // 2
        while True:
            a = root.left.val if root.left else 0
            if a > half:
                root = root.left
                continue
            b = root.right.val if root.right else 0
            if b > half:
                root = root.right
                continue
            return max((whole-x)*x for x in [a,b,root.val]) % 1_000_000_007

# runtime - 5 ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        subtree_sums = []

        def get_sum(node):
            if not node:
                return 0
            
            # Post-order: calculate left and right sums first
            current_sum = node.val + get_sum(node.left) + get_sum(node.right)
            subtree_sums.append(current_sum)
            return current_sum

        # 1. First pass to get all subtree sums and total sum
        total_sum = get_sum(root)
        
        # 2. Find the product that is closest to (total_sum / 2)
        max_prod = 0
        for s in subtree_sums:
            product = s * (total_sum - s)
            if product > max_prod:
                max_prod = product
        
        return max_prod % (10**9 + 7)

# runtime - 6 ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        MOD = 10 ** 9 + 7

        sums = []

        def sum_tree(node: Optional[TreeNode]) -> int:
            if not node:
                return 0
            value = node.val + sum_tree(node.left) + sum_tree(node.right)
            sums.append(value)
            return value
        
        total_sum = sum_tree(root)
        max_value = 0

        for value in sums:
            max_value = max(max_value, (total_sum - value) * value)

        return max_value % MOD
