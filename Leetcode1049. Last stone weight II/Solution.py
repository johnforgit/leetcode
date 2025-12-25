class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        stone_sum = sum(stones)
        target = ceil(stone_sum/2)

        def dfs(i, total):
            if total >= target or i==len(stones):
                return abs(total-(stone_sum - total))
            if(i, total) in dp:
                return dp[(i, total)]
            dp[(i, total)] = min(dfs(i+1, total), 
                                    dfs(i+1, total+stones[i]))
            return dp[(i, total)]

        dp={}
        return dfs(0, 0)

# runtime - 2 ms
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        total  = sum(stones)
        stones.sort()
        target = total//2
        dp = [False] * (target + 1)
        dp[0] = True
        
        for s in stones:
            for i in range(target, s - 1, -1):
                if dp[i - s]:
                    dp[i] = True
        
        for i in range(target,-1,-1):
            if dp[i]:
                return total - 2 * i
        return -1

# runtime - 3 ms
from typing import List

class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        total = sum(stones)
        target = total // 2

        possible = {0}
        for w in stones:
            new = set()
            for s in possible:
                if s + w <= target:
                    new.add(s + w)
            possible |= new

        best = max(possible)
        return total - 2 * best

# runtime - 4 ms. Dynamic Programming
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        # dp[i] is whether we can form a sum with the given stones at that point
        total = sum(stones)
        target = total // 2
        dp = [False] * (target + 1)
        dp[0] = True
        for stone in stones:
            for i in range(target, stone - 1, -1):
                dp[i] = dp[i] or dp[i - stone]
        for i in range(target, -1, -1):
            if dp[i]:
                return total - 2 * i

