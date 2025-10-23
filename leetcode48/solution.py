class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        for i in range(len(matrix)):
            for j in range(i, len(matrix)):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
        for row in matrix:
            row.reverse()

class Solution:
    def rotate(self, matrix:List[List[int]]) -> None:
        n=len(matrix)
        A=matrix

        # Find the transpose of the given matrix
        for i in range(n):
            for j in range(i+1, n):
                A[i][j], A[j][i] = A[j][i], A[i][j]

        # Find the reflection of the matrix
        for i in range(n):
            for j in range(n//2):
                A[i][j], A[i][n-j-1] = A[i][n-j-1], A[i][j]

class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        Do not return anything, modify matrix in-place instead.
        n = len(matrix)
        for i in range((n+1)//2): # traverse the rows
            for j in range(n//2): # traverse the columns
                temp = matrix[n - 1 - j][i]
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j]
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i]
                matrix[j][n-1-i] = matrix[i][j]
                matrix[i][j] = temp

