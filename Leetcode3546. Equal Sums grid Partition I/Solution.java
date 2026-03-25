class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;
        
        for (int[] row : grid)
            for (int x : row)
                total += x;
        
        if ((total & 1) == 1) return false;
        
        long target = total / 2, sum = 0;
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++)
                sum += grid[i][j];
            if (sum == target) return true;
        }
        
        sum = 0;
        
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++)
                sum += grid[i][j];
            if (sum == target) return true;
        }
        
        return false;
    }
}


// runtime - 3ms
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        final long[] rowSum = new long[grid.length];
        long sum = 0;
        for (int i = 0; i < rowSum.length; ++i) {
            for (final int n : grid[i]) {
                rowSum[i] += n;
            }
            sum += rowSum[i];
        }
        if ((sum % 2) != 0) {
            return false;
        }
        sum /= 2;
        long total = 0;
        for (int i = 0; i < rowSum.length - 1 && total < sum; ++i) {
            total += rowSum[i];
        }
        if (total == sum) {
            return true;
        }
        total = 0;
        for (int j = 0; j < grid[0].length - 1 && total < sum; ++j) {
            for (int i = 0; i < grid.length; ++i) {
                total += grid[i][j];
            }
        }
        return total == sum;
    }
}
