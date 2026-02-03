# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        queue = deque([(root, 0)])
        res, max_depth = 0,0
        while queue:
            for _ in range(len(queue)):
                cur_node, cur_depth = queue.popleft()
                if(cur_depth > max_depth):
                    max_depth = cur_depth
                    res = cur_node.val
                else:
                    res += cur_node.val
                if(cur_node.left):
                    queue.append((cur_node.left, cur_depth+1))
                if(cur_node.right):
                    queue.append((cur_node.right, cur_depth+1))

        return res


# runtime - 0ms
class Solution:
    def deepestLeavesSum(self, root: TreeNode) -> int:
        next_level = deque([root,])
        
        while next_level:
            # prepare for the next level
            curr_level = next_level
            next_level = deque()
            
            for node in curr_level:
                # add child nodes of the current level
                # in the queue for the next level
                if node.left:
                    next_level.append(node.left)
                if node.right:
                    next_level.append(node.right)
        
        return sum([node.val for node in curr_level])


# runtime - 1ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        ans = []
        queue = deque([root])
        
        while queue:
            curr = 0
            nodes_current = len(queue)
            
            for _ in range(nodes_current):
                node = queue.popleft()
                curr += node.val
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            ans.append(curr)
        return ans[-1]            


# runtime - 2ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
      def levelOrder(root):
        if not root:
            return []

        result = []
        q = deque([root])

        while q:
            level = []
            size = len(q)

            for _ in range(size):
                node = q.popleft()
                level.append(node.val)

                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

            result.append(level)

        return result
      ans=levelOrder(root)
      return sum(ans[-1])


# runtime - 3ms
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        maxLevel = [0]
        sumValue = [0]
        self.dfs(root, 0, maxLevel, sumValue)
        return sumValue[0]

    def dfs(self, root: TreeNode, level: int, maxLevel: list, sumValue: list):
        if root is None:
            return
        if root.left is None and root.right is None:
            if level > maxLevel[0]:
                sumValue[0] = 0
                sumValue[0] += root.val
                maxLevel[0] = level
            elif level == maxLevel[0]:
                sumValue[0] += root.val
        self.dfs(root.left, level + 1, maxLevel, sumValue)
        self.dfs(root.right, level + 1, maxLevel, sumValue)

# runtime - 4ms
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:

        q = deque([root])

        while q:
            ans = 0
            qSize = len(q)
            for _ in range(qSize):
                node = q.popleft()
                ans += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

        return ans 
        
        
        
