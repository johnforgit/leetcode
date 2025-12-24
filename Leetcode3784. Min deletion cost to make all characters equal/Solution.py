class Solution:
    def minCost(self, s: str, cost: List[int]) -> int:
        d=defaultdict(list)
        for i,c in enumerate(s):
            d[c].append(cost[i])
        total_cost = sum(cost)
        ans = float('inf')
        for k in d:
            curr_sum = sum(d[k])
            diff=total_cost - curr_sum
            ans = min(ans, diff)
        return ans
        
