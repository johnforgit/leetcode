class Solution(object):
    def champagneTower(self, poured, query_row, query_glass):
        A = [[0] * k for k in range(1, 102)]
        A[0][0] = poured
        for r in range(query_row + 1):
            for c in range(r+1):
                q = (A[r][c] - 1.0) / 2.0
                if q > 0:
                    A[r+1][c] += q
                    A[r+1][c+1] += q

        return min(1, A[query_row][query_glass])

# runtime - 47ms
class Solution:
    def champagneTower(self, poured, query_row, query_glass):
        query_glass = min(query_glass, query_row - query_glass)
        glass = [0.] * (query_glass + 1)
        glass[0] = float(poured)
        c = query_row - query_glass + 1
        z = -1

        for i in range(query_row):
            if (mid := i >> 1) >= query_glass:
                mid, i1 = query_glass, 0
            else:
                i1 = i & 1
            if (excess := max(glass[mid] - 1., 0.)):
                if i1 != 0:
                    glass[mid + 1] += excess
                glass[mid] = excess * .5
            else:
                return 0.
            for j in range(mid - 1, max(i - c, z), -1):
                if (excess := max(glass[j] - 1., 0.) * .5):
                    glass[j + 1] += excess
                    glass[j] = excess
                else:
                    z = j
                    break

        return min(1., glass[query_glass])



# runtime - 49ms
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        row = [poured*1.0]
        for i in range(query_row):
            new_row = [0]*(i+2)
            for j in range(len(row)):
                if row[j]>1: # if the glass holds more than 1, it will split into j th and j+1 th glass in the next row
                    new_row[j] += (row[j]-1)/2.0
                    new_row[j+1] += (row[j]-1)/2.0
            row = new_row
        row = [v if v<=1 else 1 for v in row]
        return row[query_glass]


# runtime - 51ms
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        prev_row = [poured]
        for row in range(1, query_row + 1):
            cur_row = [0] * (row + 1)
            for i in range(row):
                extra = prev_row[i] - 1
                if extra > 0:
                    cur_row[i] += extra * 0.5
                    cur_row[i + 1] += extra * 0.5
            prev_row = cur_row
        return min(1, prev_row[query_glass])


# runtime - 52ms
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        row=0
        col=0
        tower = [[0] * (query_row + 1) for _ in range(query_row + 1)] #initialization of 2D array
        tower[0][0]=poured
        for r in range(query_row):
            for c in range(r+1):
                if tower[r][c]>1: #overflow check
                    overflow=(tower[r][c]-1)/2
                    tower[r+1][c]+=overflow
                    tower[r+1][c+1]=overflow
                    tower[r][c]=1
        return min(1, tower[query_row][query_glass])


# runtime - 54ms
class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        
        dp = [poured]

        for _ in range(query_row):
            next_dp = [0] * (len(dp) + 1)

            for i in range(len(dp)):
                if dp[i] > 1:
                    h = (dp[i] - 1) / 2
                    next_dp[i] += h
                    next_dp[i + 1] += h

            
            dp = next_dp

        return min(1.0, dp[query_glass])
