class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        if(len(nums)==3):
            return sum(nums)

        min1, min2 = 100, 100
        
        for i in range(1, len(nums)):
            if(nums[i] < min1):
                min2 = min1
                min1 = nums[i]
            elif(nums[i] < min2):
                min2 = nums[i]

        return nums[0] + min1 + min2


# runtime - 0ms
class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        first = nums[0]
        rest = nums[1:]
        
        rest.sort()
        
        return first + rest[0] + rest[1]

        
