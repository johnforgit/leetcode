class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        rows=len(grid)
        cols=len(grid[0])
        num_islands=0

        def dfs(cur_row, cur_col):
            directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]
            for i_off, j_off in directions:
                r, c = cur_row+i_off, cur_col+j_off
                if r>=0 and r<rows and c>=0 and c<cols and grid[r][c]=='1':
                    grid[r][c]='2'
                    dfs(r, c)


        for row in range(rows):
            for col in range(cols):
                if grid[row][col] == '1':
                    num_islands += 1 # if a cell is one increment the no of islands
                    grid[row][col] = '2' # mark the existing cell
                    dfs(row, col) # perform dfs from that cell position
        
        return num_islands
        
