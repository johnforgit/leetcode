class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        count = Counter(nums)
        nums = sorted(list(set(nums)))
        earn1, earn2 = 0,0

        for i in range(len(nums)):
            curEarn = nums[i]*count[nums[i]]

            if i>0 and nums[i]==nums[i-1]+1:
                temp=earn2
                earn2=max(curEarn+earn1, earn2)
                earn1=temp
            else:
                temp = earn2
                earn2 = curEarn + earn2
                earn1 = temp
        
        return earn2

# runtime - 0 ms
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        if not nums:
            return 0
        
        max_val = max(nums)
        points = [0] * (max_val+1)

        for num in nums:
            points[num] += num
        
        prev2 = 0
        prev1 = 0

        for point in points:
            current = max(prev1, prev2+point)
            prev2 = prev1
            prev1 = current
        
        return prev1

# runtime - 1 ms using dynamic programming
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        if not nums:
            return 0

        freq = [0] * (max(nums) + 1)
        for num in nums:
            freq[num] += num

        n = len(freq)
        dp = [0] * n
        dp[1] = freq[1]
        for i in range(2, n):
            dp[i] = max(freq[i] + dp[i-2], dp[i-1])

        return dp[n-1]
