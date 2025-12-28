class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        rows=len(grid)
        cols=len(grid[0])
        num_negatives=0
        
        i=rows-1
        j=0

        while i>=0 and j<cols:
            if(grid[i][j] < 0):
                num_negatives += cols-j
                i -= 1
            else:
                j += 1
        
        return num_negatives
