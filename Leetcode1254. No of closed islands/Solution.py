class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        ans=0
        def dfs(x, y):
            if x<0 or x>=len(grid) or y<0 or y>=len(grid[0]) or grid[x][y]!=0:
                return
            grid[x][y] = 2
            dfs(x, y+1)
            dfs(x+1, y)
            dfs(x-1, y)
            dfs(x, y-1)

        for x in range(len(grid)):
            dfs(x, 0)
            dfs(x, len(grid[0])-1)
        for y in range(len(grid[0])):
            dfs(0, y)
            dfs(len(grid)-1, y)
        for x in range(len(grid)):
            for y in range(len(grid[0])):
                if(grid[x][y]==0):
                    dfs(x, y)
                    ans += 1
            
        return ans

# runtime - 3ms
class Solution:
    def closedIsland(self, grid):
        m, n = len(grid), len(grid[0])
        
        def dfs(r, c):
            if r < 0 or c < 0 or r >= m or c >= n:
                return
            if grid[r][c] == 1:
                return
            grid[r][c] = 1   # mark visited as water
            dfs(r+1, c)
            dfs(r-1, c)
            dfs(r, c+1)
            dfs(r, c-1)

        # Step 1: Remove land connected to the boundary
        for r in range(m):
            dfs(r, 0)
            dfs(r, n-1)
        for c in range(n):
            dfs(0, c)
            dfs(m-1, c)

        # Step 2: Count closed islands
        count = 0
        for r in range(m):
            for c in range(n):
                if grid[r][c] == 0:
                    count += 1
                    dfs(r, c)

        return count

# runtime - 4ms
class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])

        def dfs(r, c):
            if r < 0 or c < 0 or r >= rows or c >= cols:
                return
            if grid[r][c] == 1:
                return

            grid[r][c] = 1  # mark as water
            dfs(r-1, c)
            dfs(r+1, c)
            dfs(r, c-1)
            dfs(r, c+1)

        # STEP 1: Remove boundary-connected land
        for r in range(rows):
            if grid[r][0] == 0:
                dfs(r, 0)
            if grid[r][cols-1] == 0:
                dfs(r, cols-1)

        for c in range(cols):
            if grid[0][c] == 0:
                dfs(0, c)
            if grid[rows-1][c] == 0:
                dfs(rows-1, c)

        # STEP 2: Count remaining islands
        count = 0
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 0:
                    dfs(r, c)
                    count += 1

        return count
