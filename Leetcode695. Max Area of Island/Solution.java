class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid==null || grid.length==0)
            return 0;
        int maxArea=0;
        int rows=grid.length;
        int cols = grid[0].length;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]==1) {
                    int area = dfs(grid, i, j);
                    maxArea=Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int row, int col) {
        if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0) {
            return 0;
        }
        grid[row][col]=0; // mark the cell as visited
        int area=1;
        // explore in the 4 directions
        area += dfs(grid, row+1, col);
        area += dfs(grid, row-1, col);
        area += dfs(grid, row, col+1);
        area += dfs(grid, row, col-1);
        return area;
    }
}
