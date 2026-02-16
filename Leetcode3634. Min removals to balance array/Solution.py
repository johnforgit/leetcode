class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        n = len(nums)
        mx = 0
        nums.sort()

        l = 0
        for r in range(n):
            while((l < r) and (nums[l]*k < nums[r])):
                l += 1
            mx = max(mx, r-l+1)

        return n-mx


# runtime - 49ms
class Solution:
    def minRemoval(self, a: List[int], k: int) -> int:
        a.sort()
        l = 0
        for x in a:
            if x > k*a[l]:
                l += 1
        return l

# runtime - 54ms
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if n == 1:
            return 0
        nums.sort()
        def bi_search(r, v):
            l = 0
            while l < r:
                mid = l + (r - l)//2
                if nums[mid]*k >= v:
                    r = mid
                else:
                    l = mid + 1
            return l

        l, r = n - 2, n - 1
            
        ans = n - 1
        while l >= 0:
            if nums[l] * k >= nums[r]:
                l = bi_search(l, nums[r])
                ans = min(ans, l + n - r - 1)
            else:
                r -= 1
            l -= 1
        return ans
            

# runtime - 59ms
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        # Sort the array so that increasing elements can be checked from left to right.
        # This makes it possible to keep a "current minimum index" and compare against later values.
        nums.sort()

        # result counts how many elements we effectively "remove" from the beginning
        # by advancing the index of the current minimum element we want to keep.
        # (Per instructions: do NOT rename 'result'.)
        result = 0

        # current_limit is the maximum value allowed for the current window,
        # computed as nums[result] * k.
        # Any value greater than this would violate the condition:
        #   nums[result] * k >= value
        current_limit = nums[result] * k

        # If even the largest number already satisfies the condition with the smallest number,
        # then the whole array is valid and no removals are needed.
        if current_limit >= nums[-1]:
            return 0

        # Walk through the sorted numbers.
        # Whenever we find a number that is too large for the current_limit,
        # we "remove" one more smallest element by moving result forward,
        # and recompute the limit using the new smallest kept element.
        for current_value in nums:
            # If the current value breaks the condition with the current minimum kept element...
            if current_value > current_limit:
                # ...we advance the minimum index (equivalent to removing one smallest element).
                result += 1

                # Update the limit based on the new minimum kept element.
                current_limit = nums[result] * k

        # After the scan, result is how many smallest elements were skipped/removed.
        return result

# runtime - 64ms
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        nums.sort()
        l = 0
        for x in nums:
            if x > k*nums[l]:
                l += 1
        return l
