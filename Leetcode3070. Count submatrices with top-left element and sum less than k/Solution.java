class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] cols = new int[n];
        int res=0;

        for(int i=0; i<m; i++) {
            int rows=0;
            for(int j=0; j<n; j++) {
                cols[j] += grid[i][j];
                rows += cols[j];
                if(rows <= k) {
                    res ++;
                }
            }
        }

        return res;
    }
}


// runtime - 2ms
class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid[0].length;
        int[] subSum = new int[n];
        int result = 0;
        for (int[] row : grid) {
            for (int j = 0, rowSum = 0; j < n; j++) {
                rowSum += row[j];
                subSum[j] += rowSum;
                if (subSum[j] > k) {
                    break;
                } 
                result++;
            }
        }
        return result;
    }
}
