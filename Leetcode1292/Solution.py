# optimized solution with optimization and enumeration
class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        P = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                P[i][j] = (
                    P[i - 1][j]
                    + P[i][j - 1]
                    - P[i - 1][j - 1]
                    + mat[i - 1][j - 1]
                )

        def getRect(x1, y1, x2, y2):
            return P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1]

        r, ans = min(m, n), 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                for c in range(ans + 1, r + 1):
                    if (
                        i + c - 1 <= m
                        and j + c - 1 <= n
                        and getRect(i, j, i + c - 1, j + c - 1) <= threshold
                    ):
                        ans += 1
                    else:
                        break
        return ans

class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        P = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                P[i][j] = (
                    P[i - 1][j]
                    + P[i][j - 1]
                    - P[i - 1][j - 1]
                    + mat[i - 1][j - 1]
                )

        def getRect(x1, y1, x2, y2):
            return P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1]

        l, r, ans = 1, min(m, n), 0
        while l <= r:
            mid = (l + r) // 2
            find = any(
                getRect(i, j, i + mid - 1, j + mid - 1) <= threshold
                for i in range(1, m - mid + 2)
                for j in range(1, n - mid + 2)
            )
            if find:
                ans = mid
                l = mid + 1
            else:
                r = mid - 1
        return ans

class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        side = 0
        
        # prefix sum matrix
        prefix_sum = [[0]*(n+1) for _ in range(m+1)]

        # build the prefix sums
        for i in range(1, m+1):
            for j in range(1, n+1):
                prefix_sum[i][j] = (mat[i-1][j-1] + prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1])

        # maximum possible square size
        max_side = min(m, n)

        # try square from largest to smallest
        while max_side > 0:
            for start_row in range(m-max_side + 1):
                for start_col in range(n - max_side + 1):
                    square_sum = (
                        prefix_sum[start_row + max_side][start_col + max_side]
                        - prefix_sum[start_row][start_col + max_side]
                        - prefix_sum[start_row + max_side][start_col]
                        + prefix_sum[start_row][start_col]
                    )
                    if(square_sum <= threshold):
                        return max_side
            max_side -= 1

        return 0


# runtime - 102ms
from typing import List

class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        # build prefix sum matrix
        pre = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m):
            row_sum = 0
            for j in range(n):
                row_sum += mat[i][j]
                pre[i + 1][j + 1] = pre[i][j + 1] + row_sum

        def square_exists(k: int) -> bool:
            # check if any k x k square has sum <= threshold
            for i in range(k, m + 1):
                for j in range(k, n + 1):
                    s = (
                        pre[i][j]
                        - pre[i - k][j]
                        - pre[i][j - k]
                        + pre[i - k][j - k]
                    )
                    if s <= threshold:
                        return True
            return False

        ans = 0
        # grow side length while feasible
        for k in range(1, min(m, n) + 1):
            if square_exists(k):
                ans = k
            else:
                break
        return ans


# runtime - 158ms
class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        prefix = [[0] * (len(mat[0])+1)]
        for row in mat:
            prefix.append([0]+row)
            for idx in range(len(row)):
                prefix[-1][idx+1] -=prefix[-2][idx] 
                prefix[-1][idx+1] +=prefix[-1][idx] 
                prefix[-1][idx+1] +=prefix[-2][idx+1] 

        result = 0
        for r in range(len(prefix)-1):
            for c in range(len(prefix[0])-1):
                end_r = r + result+1
                end_c = c+ result+1
                while end_r < len(prefix) and end_c < len(prefix[0]) and prefix[end_r][end_c] - prefix[r][end_c] - prefix[end_r][c] + prefix[r][c] <=threshold:
                    end_r+=1
                    end_c+=1
                    result +=1
        return result  

# runtime - 214ms
class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])

        prefix = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(m):
            for j in range(n):
                prefix[i + 1][j + 1] = (
                    prefix[i][j + 1]
                    + prefix[i + 1][j]
                    - prefix[i][j]
                    + mat[i][j]
                )

        left, right = 1, min(m, n)
        ans = 0

        while left <= right:
            mid = (left + right) // 2
            isValid = False

            for i in range(mid, m + 1):
                for j in range(mid, n + 1):
                    total = (
                        prefix[i][j]
                        - prefix[i - mid][j]
                        - prefix[i][j - mid]
                        + prefix[i - mid][j - mid]
                    )
                    if total <= threshold:
                        isValid = True
                        break
                if isValid:
                    break

            if isValid:
                ans = mid
                left = mid + 1
            else:
                right = mid - 1

        return ans

# runtime - 270ms
class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])

        # =========================
        # Step 1: 构建二维前缀和
        # =========================
        prefix = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(m):
            for j in range(n):
                prefix[i + 1][j + 1] = (
                    prefix[i][j + 1]
                    + prefix[i + 1][j]
                    - prefix[i][j]
                    + mat[i][j]
                )

        # 计算以 (i, j) 为左上角，边长为 k 的正方形和
        def square_sum(i: int, j: int, k: int) -> int:
            return (
                prefix[i + k][j + k]
                - prefix[i][j + k]
                - prefix[i + k][j]
                + prefix[i][j]
            )

        # =========================
        # Step 2: 二分正方形边长
        # =========================
        left, right = 0, min(m, n)

        while left < right:
            mid = (left + right + 1) // 2
            found = False

            for i in range(m - mid + 1):
                for j in range(n - mid + 1):
                    if square_sum(i, j, mid) <= threshold:
                        found = True
                        break
                if found:
                    break

            if found:
                left = mid
            else:
                right = mid - 1

        return left



# runtime - 326ms
class Solution:
    def check(self, mat, bound, threshold, rows, cols):
        startRow, startCol = bound, bound
        for i in range(startRow, rows):
            for j in range(startCol, cols):
                val = mat[i][j]
                if i - bound - 1 >= 0:
                    val -= mat[i-bound-1][j]
                if j - bound - 1 >= 0:
                    val -= mat[i][j-bound-1]
                if i - bound - 1 >= 0 and j - bound - 1 >= 0:
                    val += mat[i-bound-1][j-bound-1]
                
                if val <= threshold:
                    return True

        return False

    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        rows, cols = len(mat), len(mat[0])
        for i in range(1, cols):
            mat[0][i] += mat[0][i-1]
        for i in range(1, rows):
            mat[i][0] += mat[i-1][0]
        for i in range(1, rows):
            for j in range(1, cols):
                mat[i][j] += (mat[i-1][j] + mat[i][j-1] - mat[i-1][j-1])
    
        left, right = 0, max(rows, cols)+1
        while left < right:
            mid = (left + right) // 2
            maxLength = min(rows, cols)
            if self.check(mat, mid, threshold, rows, cols):
                left = mid + 1
            else:
                right = mid
        
        return left
