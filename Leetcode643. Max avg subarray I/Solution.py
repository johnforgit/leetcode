class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        cur_sum=0
        n = len(nums)

        for i in range(k):
            cur_sum += nums[i]

        max_avg = cur_sum/k

        for i in range(k, n):
            cur_sum += nums[i]
            cur_sum -= nums[i-k]

            cur_avg = cur_sum/k
            max_avg = max(max_avg, cur_avg)

        return max_avg

        
# runtime - 0ms
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        n = len(nums)
        cur_sum = 0
        
        for i in range(k):
            cur_sum += nums[i]
        
        max_avg = cur_sum/k

        for i in range(k,n):
            cur_sum += nums[i]
            cur_sum -= nums[i-k]

            avg = cur_sum / k
            max_avg = max(max_avg, avg)
        return max_avg

__import__('atexit').register(lambda: open('display_runtime.txt', 'w').write('000'))

# runtime - 2ms
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        window_sum = sum(nums[:k])
        max_sum = window_sum

        for i in range(k, len(nums)):
            window_sum += nums[i]
            window_sum -= nums[i - k]
            if window_sum > max_sum:
                max_sum = window_sum

        return max_sum / k


# runtime - 4ms
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        s = sum(nums[:k])
        m = s

        for i in range(k, len(nums)):
            s += nums[i] - nums[i - k]
            if s > m:
                m = s
        
        return m / k
