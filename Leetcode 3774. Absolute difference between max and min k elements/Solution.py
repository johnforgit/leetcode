class Solution:
    def absDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        s1 = s2 = 0
        for i in range(k):
            s1 += nums[i]
            s2 += nums[len(nums) - 1 - i]
        return abs(s1 - s2)
