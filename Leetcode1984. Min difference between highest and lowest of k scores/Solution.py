class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        return min(nums[i+k-1]-nums[i] for i in range(len(nums)-k+1))


# runtime - 0ms
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        if k==1:
            return 0
        nums.sort()
        min_dif=float('inf')
        for i in range(len(nums)-k+1):
            dif=nums[i+k-1]-nums[i]
            min_dif=min(min_dif,dif)
        return min_dif

# runtime - 1ms
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        
        nums.sort()
        l, r = 0, 0
        res = float("inf")
        while r < len(nums):
            if r-l+1 == k:
                res = min(res, nums[r]-nums[l])
                l += 1
            r += 1
        return res

# runtime - 2ms
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        c=[]
        if k==1:
            return 0
        nums.sort()
        for i in range(0,len(nums)-k+1):
                c.append(abs(nums[i+k-1]-nums[i]))
        return min(c)

# runtime - 3ms
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        c=[]
        nums.sort()
        for i in range(0,len(nums)-k+1):
                c.append(abs(nums[i+k-1]-nums[i]))
        if len(c)==0:
            return 0
        else:
            return min(c)
