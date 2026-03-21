class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = len(matrix)
        n = len(matrix[0])
        res = 0

        for i in range(1, m):
            for j in range(n):
                if matrix[i][j] == 1:
                    matrix[i][j] += matrix[i - 1][j]

        for i in range(m):
            matrix[i].sort(reverse=True)
            for j in range(n):
                res = max(res, matrix[i][j] * (j + 1))

        return res

class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = len(matrix)
        n = len(matrix[0])
        ans = 0

        for row in range(m):
            for col in range(n):
                if matrix[row][col] != 0 and row > 0:
                    matrix[row][col] += matrix[row - 1][col]

            curr_row = sorted(matrix[row], reverse=True)
            for i in range(n):
                ans = max(ans, curr_row[i] * (i + 1))

        return ans

class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = len(matrix)
        n = len(matrix[0])
        res = 0

        for i in range(1, m):
            for j in range(n):
                if matrix[i][j] == 1:
                    matrix[i][j] += matrix[i - 1][j]

        for i in range(m):
            matrix[i].sort(reverse=True)
            for j in range(n):
                res = max(res, matrix[i][j] * (j + 1))

        return res



# runtime - 42ms
import numpy as np
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = np.array(matrix, dtype=int)
        rows, cols = m.shape
        row_idx = np.arange(rows)[:, None]

        heights = row_idx - np.maximum.accumulate(
            np.where(m == 0, row_idx, -1),
            axis=0,
        )

        heights = np.sort(heights, axis=1)[:, ::-1]
        widths = np.arange(1, cols + 1)

        return int(np.max(heights * widths))


# runtime - 44ms
import numpy as np
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = np.array(matrix, dtype=int)
        rows, cols = m.shape

        r = np.arange(rows)[:, None]

        # last row index containing 0 in each column up to current row
        last_zero = np.maximum.accumulate(
            np.where(m == 0, r, -1),
            axis=0
        )

        # consecutive-1 heights
        h = r - last_zero
        h[m == 0] = 0

        # sort each row descending
        h = np.sort(h, axis=1)[:, ::-1]

        # area for each possible width
        widths = np.arange(1, cols + 1)
        return int(np.max(h * widths))



# runtime - 46ms
import numpy as np
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        m = np.array(matrix, dtype=int)
        rows, cols = m.shape
        row_idx = np.arange(rows)[:, None]

        heights = row_idx - np.maximum.accumulate(
            np.where(m == 0, row_idx, -1),
            axis=0,
        )

        heights = np.sort(heights, axis=1)[:, ::-1]
        widths = np.arange(1, cols + 1)

        return int(np.max(heights * widths))


# runtime - 48ms
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        maxA = 0
        m = len(matrix)
        n = len(matrix[0])
        dic = {}
        for i in range(m-2,-1,-1):
            for j in range(n):
                if matrix[i][j] == 1:
                    matrix[i][j]+=matrix[i+1][j]
        for i in range(m):
            matrix[i].sort(reverse = True)
            for j in range(n):
                area = matrix[i][j]*(j+1)
                if area > maxA:
                    maxA = area
            if maxA >= n*(m-i-1):
                return maxA
        
        return maxA
