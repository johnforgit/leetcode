# using matrix rotation
class Solution:
    def findRotation(
        self, mat: List[List[int]], target: List[List[int]]
    ) -> bool:
        n = len(mat)
        # at most 4 rotations
        for k in range(4):
            # rotation operation
            for i in range(n // 2):
                for j in range((n + 1) // 2):
                    (
                        mat[i][j],
                        mat[n - 1 - j][i],
                        mat[n - 1 - i][n - 1 - j],
                        mat[j][n - 1 - i],
                    ) = (
                        mat[n - 1 - j][i],
                        mat[n - 1 - i][n - 1 - j],
                        mat[j][n - 1 - i],
                        mat[i][j],
                    )

            if mat == target:
                return True
        return False
		
# runtime - 0ms

# matrix rotation
class Solution:
    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        for x in range(4):
            if mat == target: return True
            mat = [list(r) for r in zip(*mat[::-1])]
        return False

# using bit masking		
class Solution:
    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        n = len(mat)
        m = 0b1111

        for i in range(n):
            for j in range(n):
                if mat[i][j] != target[i][j]: m &= 0b1110
                if mat[i][j] != target[j][n - 1 - i]: m &= 0b1101
                if mat[i][j] != target[n - 1 - i][n - 1 - j]: m &= 0b1011
                if mat[i][j] != target[n - 1 - j][i]: m &= 0b0111
                if m == 0: return False

        return m != 0
