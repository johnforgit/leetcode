class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        rows=len(grid)
        cols=len(grid[0])
        perimeter=0
        for i in range(rows):
            for j in range(cols):
                # check if it's land
                if grid[i][j]==1:
                    perimeter += 4
                    # if so find it's neighbours are land or water
                    for i_off, j_off in [(0,1),(1,0),(0,-1),(-1,0)]:
                        r, c = i+i_off, j+j_off
                        if 0 <= r < rows and 0 <= c < cols and grid[r][c]:
                            perimeter -= 1
        return perimeter


# runtime - 22ms
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        perimeter = 0

        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == 1:
                    perimeter += 4

                    if i > 0 and grid[i-1][j] == 1:
                        perimeter -= 2
                    if j > 0 and grid[i][j-1] == 1:
                        perimeter -= 2

        return perimeter

class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        rows = len(grid)
        cols = len(grid[0])
        
        result = 0
        
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    result += 4
                    
                    if r > 0 and grid[r-1][c] == 1:
                        result -= 2
                        
                    if c > 0 and grid[r][c-1] == 1:
                        result -= 2
        
        return result

# runtime - 17ms
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        perimeter = 0
        rows, cols = len(grid), len(grid[0])

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    perimeter += 4
                    if r > 0 and grid[r - 1][c] == 1:
                        perimeter -= 2
                    if c > 0 and grid[r][c - 1] == 1:
                        perimeter -= 2

        return perimeter  

# runtime - 11ms
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        
        peri = 0

        for i in range(len(grid)):
            for j,e in enumerate(grid[i]):
                if e:
                    peri+=4
                    if j>0 and grid[i][j-1]:
                        peri-=2
                    if i>0 and grid[i-1][j]:
                        peri-=2
        
        return peri

# runtime - 6ms
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        ans = 0
        r,c = len(grid), len(grid[0])

        for i in range(r):
            for j in range(c):
                if grid[i][j] == 1:
                    ans += 4
                    if i > 0 and grid[i-1][j] == 1:
                        ans -= 2
                    if j > 0 and grid[i][j-1] == 1:
                        ans -=2
        return ans


# neetcode solution
class Solution:
    def islandPerimeter(class, grid:List[List[int]]) -> int:
        visit = set()

        def dfs(i, j):
            if i>=len(grid) or j>=len(grid[0]) or i<0 or j<0 or grid[i][j] == 0:
                return 1
            if(i, j) in visit:
                return 0

            # add the row and column to the visited set
            visit.add((i,j))
            perim = dfs(i, j+1)
            perim += dfs(i, j-1)
            perim += dfs(i+1, j)
            perim += dfs(i-1, j)
            return perim

        for i in range(len(grid)):
            for j in range(len(grid[0]):
                if grid[i][j]:
                    return dfs(i, j);
