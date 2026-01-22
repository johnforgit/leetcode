class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        count = 0

        while len(nums) > 1:
            isAscending = True
            minSum = float("inf")
            targetIndex = -1

            for i in range(len(nums) - 1):
                pair_sum = nums[i] + nums[i + 1]

                if nums[i] > nums[i + 1]:
                    isAscending = False

                if pair_sum < minSum:
                    minSum = pair_sum
                    targetIndex = i

            if isAscending:
                break

            count += 1
            nums[targetIndex] = minSum
            nums.pop(targetIndex + 1)

        return count


# runtime - 1ms
class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        c=0
        i=0
        n=len(nums)
        while i<n-1:
            if(nums[i]>nums[i+1]):
                mini=math.inf
                index=0
                for j in range(n-1):
                    if(mini>(nums[j]+nums[j+1])):
                        mini=nums[j]+nums[j+1]
                        index=j
                nums[index]=mini
                nums.pop(index+1)
                c=c+1
                n=n-1
                i=0
            else:
                i=i+1
        return c

# runtime - 3ms
class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        def nondecreasing(a):
            for i in range(1, len(a)):
                if a[i] < a[i - 1]:
                    return False
            return True

        ops = 0
        while not nondecreasing(nums):
            min_sum = float("inf")
            idx = 0
            for i in range(len(nums) - 1):
                s = nums[i] + nums[i + 1]
                if s < min_sum:          # leftmost tie kept automatically
                    min_sum = s
                    idx = i
            nums[idx] = nums[idx] + nums[idx + 1]
            nums.pop(idx + 1)
            ops += 1
        return ops
