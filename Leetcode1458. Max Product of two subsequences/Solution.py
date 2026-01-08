
class Solution:
    def maxDotProduct(self, nums1: List[int], nums2: List[int]) -> int:
        n, m = len(nums1), len(nums2)
        dp = [[0] *m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                product = nums1[i] * nums2[j]

                # 1. start fresh with this pair
                dp[i][j] = product

                # 2. Add to the existing product
                if i>0 and j>0:
                    dp[i][j] = max(dp[i][j], dp[i-1][j-1] + product)

                # 3. skip nums1[i]
                if i>0:
                    dp[i][j] = max(dp[i][j], dp[i-1][j])
                # 4. skip nums2[j]
                if j>0:
                    dp[i][j] = max(dp[i][j], dp[i][j-1])

        return dp[n-1][m-1]

# runtime 
class Solution:
    def maxDotProduct(self, nums1: List[int], nums2: List[int]) -> int:
        if nums1[0]>nums2[0]:
            nums2,nums1=nums1,nums2
        if max(nums1)<0 and min(nums2)>0:
            return max(nums1)*min(nums2)
        m,n=len(nums1),len(nums2)
        d=[0]*(n+1)
        for i in range(m):
            for j in range(n-1,-1,-1):
                v=nums1[i]*nums2[j]+d[j]
                if v>d[j+1]:
                    d[j+1]=v
            for j in range(n):
                if d[j+1]<d[j]:
                    d[j+1]=d[j]
        
        #print(d,d[j+1])
        return d[j+1]
