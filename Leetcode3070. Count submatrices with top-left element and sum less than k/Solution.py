class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        n, m = len(grid), len(grid[0])
        cols = [0] * m
        res = 0

        for i in range(n):
            row_sum = 0
            for j in range(m):
                cols[j] += grid[i][j]
                row_sum += cols[j]
                if row_sum <= k:
                    res += 1

        return res


# runtime - 13ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        curWidth, curSum = len(grid[0]), sum(grid[0])
        while curSum > k:
            curWidth -= 1
            curSum -= grid[0][curWidth]
        result = curWidth
        for i in range(1, len(grid)):
            curSum += sum(grid[i][0:curWidth])
            while curSum > k:
                curWidth -= 1
                for j in range(0, i + 1):
                    curSum -= grid[j][curWidth]
            result += curWidth
        return result


# runtime - 19ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        
        sm = 0
        m = len(grid)
        n = len(grid[0])

        ans = 0
        c = n
        for r in range(m):
            sm += sum(grid[r][:c])
            while sm > k:
                c -= 1
                if c < 0:
                    return ans
                for rr in range(r+1):
                    sm -= grid[rr][c]
            ans += c
        return ans


# runtime - 24ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        if not len(grid) : return 0
        r,c=0,len(grid[0])
        ans=s=0
        for r,row in enumerate(grid):
            s+= sum(row[:c])
            while s>k and c>0:
                s-=sum ( grid[_][c-1] for _ in range(r+1))
                c-=1
            if s<=k:
                ans+=c
            else:
                break    
        return ans        



# runtime - 30ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        if not len(grid) : return 0
        r,c=0,len(grid[0])
        ans=s=0
        for r,row in enumerate(grid):
            s+= sum(row[:c])
            while s>k and c>0:
                s-=sum ( grid[_][c-1] for _ in range(r+1))
                c-=1
            if s>k:
                break
            ans+=c        
        return ans        


# runtime - 35ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        s = 0
        ans = 0
        n = len(grid)
        m = len(grid[0])
        right = m-1
        for i in range(n):
            for j in range(right+1):
                s += grid[i][j]
            while right >= 0 and s > k:
                for ii in range(i+1):
                    s -= grid[ii][right]
                right -= 1
            ans += right + 1
        return ans


# runtime - 41ms
class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        s = 0
        ans = 0
        n = len(grid)
        m = len(grid[0])
        right = m-1
        for i in range(n):
            for j in range(right+1):
                s += grid[i][j]
            while right >= 0 and s > k:
                for ii in range(i+1):
                    s -= grid[ii][right]
                right -= 1
            ans += right + 1
        return ans
