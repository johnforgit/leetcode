'''
Permutation cycles approach
---------------------------
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)
        digitSum = [0] * n
        for i, v in enumerate(nums):
            s = 0
            while v > 0:
                s += v % 10
                v //= 10
            digitSum[i] = s

        indices = list(range(n))
        indices.sort(key=lambda i: (digitSum[i], nums[i]))

        targetPos = [0] * n
        for pos, idx in enumerate(indices):
            targetPos[idx] = pos

        visited = [False] * n
        cycles = 0
        for i in range(n):
            if not visited[i]:
                cycles += 1
                j = i
                while not visited[j]:
                    visited[j] = True
                    j = targetPos[j]

        return n - cycles

Union find approach
-------------------
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)
        digitSum = [0] * n
        for i, v in enumerate(nums):
            s = 0
            while v > 0:
                s += v % 10
                v //= 10
            digitSum[i] = s

        indices = list(range(n))
        indices.sort(key=lambda i: (digitSum[i], nums[i]))

        targetPos = [0] * n
        for pos, idx in enumerate(indices):
            targetPos[idx] = pos

        parent = list(range(n))
        components = n

        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]

        for i in range(n):
            ri = find(i)
            rj = find(targetPos[i])
            if ri != rj:
                parent[ri] = rj
                components -= 1

        return n - components
'''


# runtime - 274ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            curr = num
            digit_sum = 0
            while curr:
                digit_sum += curr % 10
                curr //= 10
            nums[i] = (digit_sum, num, i)
        nums.sort()
        nums = [num[2] for num in nums]
        n = len(nums)
        visited = [False] * n
        cycle = 0

        for i, num in enumerate(nums):
            if visited[i]:
                continue
            
            cycle += 1
            j = i
            while not visited[j]:
                visited[j] = True
                j = nums[j]
        print(n, cycle)
            
        return n - cycle



# runtime - 310ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)

        def ds(x):
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        order = sorted(range(n), key=lambda i: (ds(nums[i]), nums[i]))
        seen = [False] * n
        cycles = 0
        for i in range(n):
            if seen[i]:
                continue
            cycles += 1
            j = i
            while not seen[j]:
                seen[j] = True
                j = order[j]
        return n - cycles


# runtime - 328ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        # Sorting by (digit sum, value) fixes where each element must end up; a permutation
        # made of c cycles needs len - c swaps.
        n = len(nums)
        order = sorted(range(n), key=lambda i: (sum(map(int, str(nums[i]))), nums[i]))
        seen = [False] * n
        cycles = 0
        for i in range(n):
            if seen[i]:
                continue
            cycles += 1
            j = i
            while not seen[j]:
                seen[j] = True
                j = order[j]
        return n - cycles


# runtime -346ms
def sumOfDigits(num):
    sd = 0
    while num > 0:
        sd += num % 10
        num //= 10
    return sd

class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        sortable = [(sumOfDigits(num), num, i) for i, num in enumerate(nums)]
        sortable.sort()
        seen = [False] * len(nums)
        nCycles = 0
        #print(sortable)
        for i in range(len(nums)):
            if seen[i]:
                continue
            current = i
            while not seen[current]:
                seen[current] = True
                current = sortable[current][2]
            nCycles += 1
        return len(nums) - nCycles


# runtime - 382ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            curr = num
            digit_sum = 0
            while curr:
                digit_sum += curr % 10
                curr //= 10
            nums[i] = (digit_sum, num, i)
        nums.sort()
        nums = [num[2] for num in nums]
        n = len(nums)
        visited = [False] * n
        cycle = 0

        for i, num in enumerate(nums):
            if visited[i]:
                continue
            
            cycle += 1
            j = i
            while not visited[j]:
                visited[j] = True
                j = nums[j]
            
        return n - cycle


# runtime - 400ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        def digit_sum(num):
            total=0
            while num:
                total+=num%10
                num//=10
            return total
        n=len(nums)
        swap=0
        order=sorted(range(n),key=lambda i:(digit_sum(nums[i]),nums[i]))
        for curr_idx,target_idx in enumerate(order):
            while curr_idx!=target_idx:
                order[target_idx],target_idx=target_idx,order[target_idx]
                swap+=1
        return swap


# runtime - 436ms
class Solution:

  def minSwaps(self, nums: list[int]) -> int:
    n = len(nums)

    def digit_sum(x: int) -> int:
      s = 0
      while x > 0:
        s += x % 10
        x //= 10
      return s

    # 1. Pair each number with its original index and sorting key: (digit_sum(x), x)
    # indexed_nums[i] = (digit_sum, value, original_index)
    indexed_nums = [(digit_sum(x), x, i) for i, x in enumerate(nums)]

    # 2. Sort to get the target permutation
    indexed_nums.sort()

    # 3. Build target index mapping: target_pos[original_index] = sorted_index
    target_pos = [0] * n
    for target_idx, (_, _, orig_idx) in enumerate(indexed_nums):
      target_pos[orig_idx] = target_idx

    # 4. Count disjoint cycles
    visited = [False] * n
    num_cycles = 0

    for i in range(n):
      if not visited[i]:
        num_cycles += 1
        curr = i
        while not visited[curr]:
          visited[curr] = True
          curr = target_pos[curr]

    # Total minimum swaps = n - num_cycles
    return n - num_cycles


# runtime - 454ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            curr = num
            digit_sum = 0
            while curr:
                digit_sum += curr % 10
                curr //= 10
            nums[i] = (digit_sum, num, i)
        nums.sort()
        nums = [num[2] for num in nums]
        ans = n = len(nums)
        visited = [False] * n

        for i, num in enumerate(nums):
            if visited[i]:
                continue
            
            ans -= 1
            j = i
            while not visited[j]:
                visited[j] = True
                j = nums[j]
            
        return ans


# runtime - 887ms
class Solution:
    def digitSum(self, x):
        s = 0

        while x > 0:
            s += x % 10
            x //= 10

        return s

    def minSwaps(self, nums):
        n = len(nums)

        if n <= 1:
            return 0

        arr = nums[:]

        arr.sort(key=lambda x: (self.digitSum(x), x))

        mp = {}

        for i in range(n):
            mp[nums[i]] = i

        ans = 0

        for i in range(n):
            if nums[i] == arr[i]:
                continue

            correctVal = arr[i]
            correctIndex = mp[correctVal]

            curVal = nums[i]

            nums[i], nums[correctIndex] = nums[correctIndex], nums[i]

            ans += 1

            mp[curVal] = correctIndex
            mp[correctVal] = i

        return ans


# runtime - 905ms
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        n = len(nums)
        target = sorted(nums, key= lambda x: (sum(map(int, str(x))), x))
        pos = {v: i for i, v in enumerate(nums)}
        ans = 0
        for i in range(n):
            if target[i] != nums[i]:
                # update positions
                j = pos[target[i]]
                pos[target[i]] = i
                pos[nums[i]] = j 
                # do actual swap
                nums[i], nums[j] = nums[j], nums[i]
                ans+=1
        return ans


