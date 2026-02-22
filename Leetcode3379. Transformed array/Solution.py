class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        res = [0] * len(nums)

        for i in range(len(nums)):
            index = (nums[i] + i) % len(nums)
            res[i] = nums[index]

        return res

# runtime - 27ms
class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        result = [0] * n

        for idx in range(n):
            destIdx = (idx + nums[idx]) % n
            if destIdx < 0:
                destIdx += n
            result[idx] = nums[destIdx]


        return result


# runtime - 35ms
class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        out = [0] * n
        for i in range(n):
            x = (i + nums[i])%n
            out[i] = nums[x]
        return out


# runtime - 38ms
class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        return [nums[(i + x) % n] for i, x in enumerate(nums)]
        
