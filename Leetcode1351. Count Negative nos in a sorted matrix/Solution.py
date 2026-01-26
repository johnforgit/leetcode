class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        index = len(grid[0])-1
        res = 0
        for row in grid:
            while(index >= 0 and row[index]<0):
                index -= 1
            res += (len(grid[0])-1) - index
        return res

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
