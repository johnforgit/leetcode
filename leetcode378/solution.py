class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        
        def countLessOrEqual(x):
            count=0
            j=n-1
            for i in range(m):
                while j >= 0 and matrix[i][j] > x:
                    j -= 1
                count += j+1
            return count

        left, right = matrix[0][0], matrix[-1][-1]
        ans=-1
        while left <= right:
            mid = (left + right) //2
            if countLessOrEqual(mid) >= k:
                ans = mid
                right=mid-1
            else:
                left=mid+1
        return ans

# using a min heap

class sol:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        minHeap = []
        for i in range(min(k, m)):
            heappush(minHeap,(matrix[i][0], r, 0))
        
        ans=-1
        for i in range(k):
            ans, r, c = heappop(minHeap)
            if c+1 < n:
                heappush(minHeap, (matrix[i][c+1], r, c+1))
        return ans
