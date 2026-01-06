class Solution:
    def maxMatrixSum(self, matrix: List[List[int]]) -> int:
        n=len(matrix)
        totalSum = 0
        negative=0
        min_abs_val = float("inf")
        for row in matrix:
            for val in row:
                totalSum += abs(val)
                if val < 0:
                    negative += 1
                min_abs_val = min(min_abs_val, abs(val))

        # adjust if the count of negative nos is odd
        if negative%2 != 0:
            totalSum -= 2*min_abs_val

        return totalSum
