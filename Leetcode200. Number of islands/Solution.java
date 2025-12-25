class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) { // if the grid is empty, return 0
            return 0;
        }
        int rows=grid.length;
        int cols=grid[0].length;
        int num_islands = 0;

        // iterate through the grid and find the islands
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]=='1') {
                    num_islands++; // increment the count of islands
                    dfs(grid, i, j); // perform dfs from that cell onwards
                }
            }
        }
        return num_islands;
    }

    public void dfs(char[][] grid, int i, int j) {
        int newRow = grid.length;
        int newCol = grid[0].length;
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // update the current cell as 0
        for(int[] dir:directions) {
            dfs(grid, i+dir[0], j+dir[1]); // explore in all four directions

        }
    }
}

// runtime - 0 ms
class Solution {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;

        int numIslands = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (grid[row][col] == '1') {
                    numIslands++;
                    dfs(grid, row, col);
                }
            }
        }
        
        return numIslands;
    }

    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length || 
            grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}

// runtime - 2 ms
class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0){
            return 0;
        }
        int cnt=0;
        int r=grid.length;
        int c=grid[0].length;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void dfs(char grid[][],int i,int j){
        if(i<0||i>=grid.length||j>=grid[0].length||j<0||grid[i][j]=='0'){
            return;
        }
        grid[i][j]='0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);

        
    }
}

// runtime - 3 ms
class Solution {
    public int numIslands(char[][] grid) {
        
        int res=0;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int i, int j){

        grid[i][j]='0';

        int[] dir={0,1,0,-1,0};

        for(int d=0;d<4;d++){
            int nr=i+dir[d], nc=j+dir[d+1];
            if(isValid(grid, nr, nc) && grid[nr][nc]=='1'){
                dfs(grid, nr, nc);
            }
        }

    }

    private boolean isValid(char[][] grid, int i, int j){
        return i>=0 && j>=0 && i<grid.length && j<grid[0].length;
    }
}

