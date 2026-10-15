class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        MOD = 10**9+7
        cnt = [0]*30

        # Count the number of 1 bits at each position
        for num in nums:
            for i in range(30):
                if(num & (1<<i)):
                    cnt[i] += 1

        res = 0
        for _ in range(k):
            cur = 0
            
            # construct the current number from the most significant bit to the last significant bit

            for j in range(29, -1, -1):
                if(cnt[j] > 0):
                    cur |= 1 << j
                    cnt[j] -= 1
            res = (res+cur * cur)%MOD

        return res


# RUNTIME - 619ms
class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        maxBits = max(nums).bit_length()
        masks = [1 << pos  for pos in range(maxBits)]

        # For each bit position, get the count of 1-bits at 
        # that position in across all of nums[].
        counts = [sum(num & mask  for num in nums) >> pos  for pos, mask in enumerate(masks)]

        # Build the k values to be squared from the counts of 
        # 1-bits available at each bit position.  Build the 
        # biggest possible values while distributing those 
        # 1-bits, by distributing those 1-bits into all the 
        # lowest indexed k values as possible.  Therefore, 
        # for any index into the k values, the next higher 
        # index will have the same or fewer bits, or more 
        # mathematically:
        #   kVals[i].bit_count() >= kVals[i + 1].bit_count()
        #
        k = min(k, max(counts))
        kVals = [0] * k
        for pos, mask in enumerate(masks):
            for i in range(min(k, counts[pos])):
                kVals[i] |= mask

        # Sum the squares of the k values that were built.
        return sum((val * val) % 1_000_000_007  for val in kVals) % 1_000_000_007


#--------------------------------------------------------
# class Solution:
#     def maxSum(self, nums: List[int], k: int) -> int:
#         maxBits = max(nums).bit_length()
#         counts = [0] * maxBits
#         masks = [1 << pos  for pos in range(maxBits)]

#         for pos, mask in enumerate(masks):
#             counts[pos] = sum(num & mask  for num in nums) >> pos

#         k = min(k, max(counts))
#         kVals = [0] * k
#         for pos, mask in enumerate(masks):
#             for i in range(min(k, counts[pos])):
#                 kVals[i] |= mask

#         return sum((val * val) % 1_000_000_007  for val in kVals) % 1_000_000_007


#--------------------------------------------------------
# class Solution:
#     def maxSum(self, nums: List[int], k: int) -> int:
#         maxBits = max(nums).bit_length()
#         counts = [0] * maxBits
#         masks = [1 << pos  for pos in range(maxBits)]

#         for num in nums:
#             for pos, mask in enumerate(masks):
#                 if num & mask:
#                     counts[pos] += 1

#         result = 0
#         for _ in range(k):
#             num = 0
#             for pos in range(maxBits):
#                 if counts[pos]:
#                     counts[pos] -= 1
#                     num |= (1 << pos)
#             result = (result + num * num) % 1_000_000_007
#         return result



#--------------------------------------------------------



# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# RUNTIME - 53ms
import numpy as np

class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        a = np.array(nums, dtype=np.uint32)
        cnt = [int(np.count_nonzero(a & (1 << b))) for b in range(30)]

        pairs = sorted(((min(c, k), 1 << b) for b, c in enumerate(cnt) if c), reverse=True)
        pairs.append((0, 0))
        ans = v = 0
        for j in range(len(pairs) - 1):
            c, bit = pairs[j]
            v |= bit
            ans += (c - pairs[j + 1][0]) * v * v
        return ans % 1_000_000_007
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# RUNTIME - 74ms
import numpy as np

class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        a = np.array(nums, dtype=np.uint32)
        cnt = [int(np.count_nonzero(a & (1 << b))) for b in range(30)]

        pairs = sorted(((min(c, k), 1 << b) for b, c in enumerate(cnt) if c), reverse=True)
        pairs.append((0, 0))
        ans = v = 0
        for j in range(len(pairs) - 1):
            c, bit = pairs[j]
            v |= bit
            ans += (c - pairs[j + 1][0]) * v * v
        return ans % 1_000_000_007
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# RUNTIME - 95ms
import numpy as np


class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        arr = np.array(nums, dtype=np.int64)
        bits = np.sum((arr[:, None] >> np.arange(31, -1, -1)) & 1, axis=0)
        r = np.arange(k, dtype=np.int64)[:, None]
        matrix = (r < bits).astype(np.int64)
        powers = 1 << np.arange(31, -1, -1)
        vals = matrix.dot(powers)
        mod = 10**9 + 7
        vals_mod = vals % mod
        return int(np.sum(vals_mod * vals_mod, dtype=object) % mod)
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# RUNTIME - 116ms
import numpy as np

class Solution:
    def maxSum(self, nums, k):
        MOD = 1_000_000_007
        arr = np.asarray(nums, dtype=np.int64)

        bit_counts = np.array(
            [(arr >> bit & 1).sum() for bit in range(30)],
            dtype=np.int64
        )

        results = np.zeros(k, dtype=np.int64)

        for bit, count in enumerate(bit_counts):
            n = min(k, int(count))
            if n:
                results[:n] |= 1 << bit

        results *= results
        results %= MOD

        return int(results.sum(dtype=np.int64) % MOD)
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
