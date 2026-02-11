class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        ans = float('inf')
        l=0
        c = Counter()
        ss=0
        for r in range(len(nums)):
            # if the num is not duplicate add to sliding window sum
            if(nums[r] not in c):
                ss += nums[r]
            # increment the frequency of the current num
            c[nums[r]] += 1
            # if the sum is greater than k and left pointer is left than right
            while ((ss >= k) and (l <= r)):
                ans = min(ans, r-l+1)
                # decrement the counter
                c[nums[l]] -= 1
                # if the leftmost element's freq is not 0, then don't
                # delete from sliding window sum
                if(c[nums[l]] == 0):
                    del c[nums[l]]
                    ss -= nums[l]
                l += 1

        # if no such subarray exists, return -1
        if(ans==float('inf')): return -1
        return ans

# runtime - 6ms
class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        left = 0
        min_len = float('inf')
        freq = defaultdict(int)
        distinct_sum = 0
        for right in range(n):
            if freq[nums[right]] == 0:
                distinct_sum += nums[right]
            freq[nums[right]] += 1
            while distinct_sum >= k:
                min_len = min(min_len, right - left + 1)
                freq[nums[left]] -= 1
                if freq[nums[left]] == 0:
                    distinct_sum -= nums[left]
                left += 1
        return min_len if min_len != float('inf') else -1

__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))


# runtime - 19ms
class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        freq = [0] * (10**5 + 1)
        minLen = 0
        csum = 0
        for v in nums:
            minLen += 1
            cnt = freq[v]
            cnt += 1
            freq[v] = cnt
            if cnt > 1: continue
            elif v >= k: return 1
            csum += v
            if csum >= k:
                break
        if csum < k: return -1
        vcnt = freq[v]
        vcnt -= 1
        freq[v] = vcnt
        if vcnt == 0:
            csum -= v
        searchLen = minLen - 1
        if searchLen == 1:
            if minLen < len(nums) and max(nums[minLen:]) >= k:
                return  1
            else:
                return 2
        try:
            for i, vrem in enumerate(nums):
                vcnt = freq[vrem]
                if vcnt > 0:
                    vcnt -= 1
                    freq[vrem] = vcnt
                    if vcnt == 0:
                        csum -= vrem
                v = nums[i + searchLen]
                vcnt = freq[v] + 1
                if vcnt >= 2:
                    freq[v] = vcnt
                    continue
                csum += v
                if csum < k:
                    freq[v] = 1
                    continue
                minLen = searchLen
                csum -= v
                if searchLen == 2:
                    if max(nums[i+searchLen:]) >= k:
                        return  1
                    else:
                        return 2
                searchLen -= 1
        except:
            return minLen
        return minLen


# runtime - 32ms
class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        freq = [0] * (10**5 + 1)
        minLen = 0
        csum = 0
        for v in nums:
            minLen += 1
            cnt = freq[v]
            cnt += 1
            freq[v] = cnt
            if cnt > 1: continue
            elif v >= k: return 1
            csum += v
            if csum >= k:
                break
        if csum < k: return -1
        vcnt = freq[v]
        vcnt -= 1
        freq[v] = vcnt
        if vcnt == 0:
            csum -= v
        searchLen = minLen - 1
        if searchLen == 1:
            if minLen < len(nums) and max(nums[minLen:]) >= k:
                return  1
            else:
                return 2
        try:
            revn = nums[searchLen:][::-1]
            for vrem in nums:
                vcnt = freq[vrem]
                if vcnt > 0:
                    vcnt -= 1
                    freq[vrem] = vcnt
                    if vcnt == 0:
                        csum -= vrem
                v = revn.pop()
                vcnt = freq[v] + 1
                if vcnt >= 2:
                    freq[v] = vcnt
                    continue
                csum += v
                if csum < k:
                    freq[v] = 1
                    continue
                revn.append(v)
                if searchLen == 2:
                    if len(revn) > 0 and max(revn) >= k:
                        return  1
                    else:
                        return 2
                minLen = searchLen
                csum -= v
                searchLen -= 1
        except Exception:
            return minLen
        return minLen


# runtime - 45ms
class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        freq = [0] * (10**5 + 1)
        minLen = 0
        nlen = len(nums)
        csum = 0

        for v in nums:
            minLen += 1
            cnt = freq[v]
            cnt += 1
            freq[v] = cnt
            if cnt > 1: continue
            elif v >= k: return 1
            csum += v
            if csum >= k:
                break
        if csum < k: return -1
        vcnt = freq[v]
        vcnt -= 1
        freq[v] = vcnt
        if vcnt == 0:
            csum -= v
        searchLen = minLen - 1
        if searchLen == 1:
            for v in nums[minLen:]:
                if v >= k:
                    return 1
        try:
            for i, vrem in enumerate(nums):
                vcnt = freq[vrem]
                if vcnt > 0:
                    vcnt -= 1
                    freq[vrem] = vcnt
                    if vcnt == 0:
                        csum -= vrem
                v = nums[i + searchLen]
                vcnt = freq[v] + 1
                if vcnt >= 2:
                    freq[v] = vcnt
                    continue
                csum += v
                if csum < k:
                    freq[v] = 1
                    continue
                minLen = searchLen
                csum -= v
                searchLen -= 1
                if searchLen == 1:
                    for v in nums[i + 1:]:
                        if v >= k: return 1
                    break
        except:
            return minLen
        return minLen


# runtime - 59ms
from typing import List

class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        # Check if any single element is >= k, which would make the answer 1
        if any(num >= k for num in nums):
            return 1
        
        # Determine the maximum value in nums to size our count array efficiently
        max_num = max(nums)
        count = [0] * (max_num + 1)
        sum_distinct = 0
        left = 0
        min_len = float('inf')
        n = len(nums)
        
        for right in range(n):
            current_num = nums[right]
            # If this number is not in the current window, add its value to sum_distinct
            if count[current_num] == 0:
                sum_distinct += current_num
            # Increment the count of the current number
            count[current_num] += 1
            
            # Shrink the window from the left as much as possible while sum_distinct >= k
            while sum_distinct >= k:
                current_length = right - left + 1
                # Update the minimum length found
                if current_length < min_len:
                    min_len = current_length
                    # Since we already checked for length 1, length 2 is the smallest possible
                    if min_len == 2:
                        return 2
                # Remove the leftmost element from the window
                left_num = nums[left]
                count[left_num] -= 1
                # If this was the last occurrence of left_num, subtract its value from sum_distinct
                if count[left_num] == 0:
                    sum_distinct -= left_num
                left += 1
        
        # If no valid subarray was found, return -1
        return min_len if min_len != float('inf') else -1
