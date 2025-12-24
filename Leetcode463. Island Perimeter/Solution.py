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


