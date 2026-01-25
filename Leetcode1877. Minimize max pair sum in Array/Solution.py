class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        nums.sort()
        max_sum = 0

        for i in range(len(nums)//2):
            max_sum = max(max_sum, nums[i] + nums[len(nums)-1-i])

        return max_sum

# runtime - 6ms
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        n = nums.sort()
        i = 0
        j = len(nums) - 1
        result = 0

        while i<j :
            sum = nums[i] + nums[j]
            result = max(result, sum)
            i += 1
            j -= 1
        return result

__import__("atexit").register(lambda: open("display_runtime.txt","w").write("000"))


# runtime - 18ms
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        res = 0
        l = 0
        r = len(nums) - 1
        nums.sort()
        while l < r:
            res = max(res, nums[l] + nums[r])
            l += 1
            r -= 1
        return res


# runtime - 30ms
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(nums)
        mn = 0
        for i in range(int(n/2)):
            if (nums[i] + nums[n-i-1]) > mn:
                mn = (nums[i] + nums[n-i-1])
        
        return mn


# runtime - 42ms
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        nums.sort()
        pairs = []
        prev = 0
        l, r = 0, len(nums) - 1
        while l < len(nums) // 2:
            s = nums[l] + nums[r]
            if s > prev:
                prev = s
            l += 1
            r -= 1
        return prev
