
'''
class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        res=[]
        for num in nums:
            og = num
            candidate = -1
            for j in range(1, og):
                if(j | (j+1)) == og:
                    candidate = j
                    break
            res.append(candidate)
        return res
'''
class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        for i in range(len(nums)):
            res = -1
            d = 1
            while (nums[i] & d) != 0:
                res = nums[i] - d
                d <<= 1
            nums[i] = res
        return nums

# runtime - 1 ms
class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        for i,j in enumerate(nums):
            if j==2:
                nums[i]=-1
            else:
                nums[i]=((((j+1)^j)+1)>>2)^j
        return nums

# runtime - 2ms
class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []
        for p in nums:
            if p == 2:
                ans.append(-1)
                continue

            # count trailing ones in p
            t = 0
            x = p
            while x & 1:
                t += 1
                x >>= 1

            ans.append(p - (1 << (t - 1)))
        return ans
