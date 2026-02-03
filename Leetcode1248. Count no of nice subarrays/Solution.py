class Solution:
    def numberOfSubarrays(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k>n:
            return 0

        def atMost(k):
            left = res = k_count = 0
            for right in range(n):
                if(nums[right]%2 == 1):
                    k_count += 1
                while (k_count > k):
                    if(nums[left]%2 == 1):
                        k_count -= 1
                    left += 1
                res += right-left+1
            return res

        return atMost(k) - atMost(k-1)
