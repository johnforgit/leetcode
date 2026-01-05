class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))   # remove duplicates
        m = len(nums)

        ans = n
        left = 0

        for right in range(m):
            while nums[right] - nums[left] >= n:
                left += 1
            window_size = right - left + 1
            ans = min(ans, n - window_size)

        return ans

# runtime - 70 ms
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        A = sorted(set(nums))
        i = 0
        for j in range(len(A)):
            if A[i] < A[j] - n + 1:
                i += 1
        return n - (j - i + 1)
