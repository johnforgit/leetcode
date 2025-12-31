class Solution:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        size = len(nums2)
        nums1.sort()
        nums2.sort()
        ans = float('inf')
        for c in combinations(nums1, size):
            diff=[]
            for i in range(len(c)):
                diff.append(nums2[i]-c[i])
            if(len(set(diff)) == 1):
                ans = min(ans, diff[0])
        
        return ans


# runtime - 35 ms
class Solution:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        def check(x: int):
            cnt = 0
            i, j = 0, 0
            while i < len(nums1) and j < len(nums2):
                if nums2[j] - nums1[i] == x:
                    i += 1
                    j += 1
                else:
                    i += 1
                    cnt += 1
            return cnt <= 2
        nums1.sort()
        nums2.sort()
        res = inf
        for i in range(3):
            x = nums2[0] - nums1[i]
            if check(x):
                res = min(res, x)
        return res
