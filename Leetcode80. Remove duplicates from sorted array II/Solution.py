class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        '''
        j=1
        count=1
        n=len(nums)
        for i in range(1, n):
            if nums[i]==nums[i-1]:
                count += 1
            else:
                count = 1

            if count <= 2:
                nums[j] = nums[i]
                j += 1
        
        return j
        '''
        l,r=0,0
        while r<len(nums):
            count=1
            while r+1<len(nums) and nums[r]==nums[r+1]:
                r += 1
                count += 1

            for i in range(min(2, count)):
                nums[l]=nums[r]
                l += 1
            r += 1
        
        return l
