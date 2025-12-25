class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        max_area=0
        rows=len(grid)
        cols = len(grid[0])
        visited=set()

        for i in range(rows):
            for j in range(cols):
                # if a cell is 1 and is not visited in dfs
                if grid[i][j] and (i, j) not in visited:
                    area=0
                    stack=[(i,j)]
                    visited.add((i,j))
                
                    while stack:
                        new_i, new_j = stack.pop()
                        area += 1
                        for i_off, j_off in [(0, -1), (-1, 0), (0, 1), (1, 0)]:
                            r,c = new_i+i_off, new_j+j_off
                            if r>=0 and r<rows and c>=0 and c<cols and grid[r][c] and (r,c) not in visited:
                                visited.add((r,c))
                                stack.append((r,c))
                    max_area=max(max_area, area)
        
        return max_area
