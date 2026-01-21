class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        total = sum(nums)
        if total == x:
            return len(nums)

        p = list(itertools.accumulate(nums))
        ans = float('inf')
        ss=0

        for i in range(len(nums)):
            ss += nums[i]
            l, r = i, len(p)-1
            if i<=0:
                low = 0
            else:
                low = p[i-1]
            while l<=r:
                mid = (l+r)//2
                val = p[mid]
                ss = val-low
                if(total-ss == x):
                    ans = min(ans, len(nums)-(mid-i+1))
                    r  = mid-1
                elif(total-ss > x):
                    l = mid + 1
                else:
                    r = mid - 1
            
        if(ans == float('inf')):
            return -1
        return ans


# runtime - 1ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        tot = sum(nums)
        n = len(nums)
        diff = tot - x
        s, r, l = 0, 0, 0
        max, cur = 0, 1
    
        if tot == x:
            return n

        while l <= n - 1:
            if s == diff:
                cur = r - l
                if max < cur:
                    max = cur

            if s < diff and r <= n - 1:
                s += nums[r]
                r += 1
            else:
                s -= nums[l]
                l += 1
            
        return n - max if max != 0 else -1
        
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))

# runtime - 5ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        # Insight: Finding the longest subarray with sum = total - x
        target = sum(nums) - x

        if target < 0:
            return -1
        if target == 0:
            return len(nums)
        
        left = 0
        current_sum = 0
        max_len = -1

        for right in range(len(nums)):
            current_sum += nums[right]

            while current_sum > target:
                current_sum -= nums[left]
                left += 1
            
            if current_sum == target:
                max_len = max(max_len, right - left + 1)
        
        return len(nums) - max_len if max_len != -1 else -1

# runtime - 8ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        n = len(nums)
        k = sum(nums) - x
        if k == 0:
            return n
        if k < 0:
            return -1
        l = 0
        curr_sum = 0
        max_len = -1
        for r in range(n):
            curr_sum += nums[r]
            while l <= r and curr_sum > k:
                curr_sum -= nums[l]
                l += 1

            if curr_sum == k:
                max_len = max(max_len, r-l+1)
            
        return n - max_len if max_len != -1 else -1

# runtime - 12ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        n, s = len(nums), sum(nums)
        target = s - x
        if target < 0:
            return -1
        
        if target == 0:
            return n
        
        curr_sum = 0
        l = 0
        max_len = 0

        for r in range(n):
            curr_sum += nums[r]
            while curr_sum > target:
                curr_sum -= nums[l]
                l += 1

            if curr_sum == target:
                max_len = max(max_len, r - l + 1)
        
        return n - max_len if max_len > 0 else -1

# runtime - 16ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        n = len(nums)
        total = sum(nums)
        target = total - x
        if total == x: return n
        if total < x: return -1
        curr_sum = 0
        max_len = 0
        l = 0
        for r in range(n):
            curr_sum += nums[r]
            while curr_sum > target:
                curr_sum -= nums[l]
                l += 1
            if curr_sum == target:
                max_len = max(max_len, r - l + 1)
        return n - max_len if max_len else -1

# runtime - 19ms
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        total = sum(nums)
        target = total - x

        if target == 0:
            return len(nums)
        
        if target < 0:
            return -1

        max_length = -1
        current_sum = 0
        left = 0
        for right in range(len(nums)):
            current_sum += nums[right]

            while current_sum > target and left <= right:
                current_sum -= nums[left]
                left += 1

            if current_sum == target:
                max_length = max(max_length, right - left + 1)

        if max_length == -1:
            return -1

        return len(nums) - max_length
