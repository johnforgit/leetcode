class Solution {

    public int largestMagicSquare(int[][] grid) {
        int totalRows = grid.length;
        int totalCols = grid[0].length;

        // Prefix sum arrays
        int[][] rowPrefixSum = new int[totalRows][totalCols + 1];
        int[][] colPrefixSum = new int[totalRows + 1][totalCols];
        int[][] mainDiagPrefix = new int[totalRows + 1][totalCols + 1];
        int[][] antiDiagPrefix = new int[totalRows + 1][totalCols + 2];

        // Build prefix sums
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                rowPrefixSum[row][col + 1] =
                        rowPrefixSum[row][col] + grid[row][col];

                colPrefixSum[row + 1][col] =
                        colPrefixSum[row][col] + grid[row][col];

                mainDiagPrefix[row + 1][col + 1] =
                        mainDiagPrefix[row][col] + grid[row][col];

                antiDiagPrefix[row + 1][col] =
                        antiDiagPrefix[row][col + 1] + grid[row][col];
            }
        }

        int maxPossibleSize = Math.min(totalRows, totalCols);

        // Try larger squares first
        for (int size = maxPossibleSize; size >= 1; size--) {
            for (int row = 0; row + size <= totalRows; row++) {
                for (int col = 0; col + size <= totalCols; col++) {
                    if (isMagicSquare(row, col, size,
                            rowPrefixSum, colPrefixSum,
                            mainDiagPrefix, antiDiagPrefix)) {
                        return size;
                    }
                }
            }
        }

        return 1;
    }

    private boolean isMagicSquare(
            int startRow,
            int startCol,
            int size,
            int[][] rowPrefixSum,
            int[][] colPrefixSum,
            int[][] mainDiagPrefix,
            int[][] antiDiagPrefix) {

        int targetSum =
                rowPrefixSum[startRow][startCol + size]
              - rowPrefixSum[startRow][startCol];

        // Check all rows
        for (int r = startRow; r < startRow + size; r++) {
            int rowSum =
                    rowPrefixSum[r][startCol + size]
                  - rowPrefixSum[r][startCol];
            if (rowSum != targetSum) {
                return false;
            }
        }

        // Check all columns
        for (int c = startCol; c < startCol + size; c++) {
            int colSum =
                    colPrefixSum[startRow + size][c]
                  - colPrefixSum[startRow][c];
            if (colSum != targetSum) {
                return false;
            }
        }

        // Check main diagonal
        int mainDiagSum =
                mainDiagPrefix[startRow + size][startCol + size]
              - mainDiagPrefix[startRow][startCol];
        if (mainDiagSum != targetSum) {
            return false;
        }

        // Check anti-diagonal
        int antiDiagSum =
                antiDiagPrefix[startRow + size][startCol]
              - antiDiagPrefix[startRow][startCol + size];
        if (antiDiagSum != targetSum) {
            return false;
        }

        return true;
    }
}


// runtime - 4ms
class Solution {
    boolean exist(int[][] grid, int[][] rowSum, int[][] colSum, int size) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        int rowMax = rowSize - size;
        int colMax = colSize - size;

        for ( int row = 0; row <= rowMax; row++ ) {
            for ( int col = 0; col <= colMax; col++ ) {
                int sum = rowSum[row][col+size] - rowSum[row][col];
// System.out.printf("size=%d, row=%d, col=%d, sum=%d\n", size, row, col, sum);
                boolean match = true;
                for ( int ii = 0; match && ii < size; ii++ ) {
                    int sum1 = (rowSum[row+ii][col+size] - rowSum[row+ii][col]);
                    int sum2 = (colSum[row+size][col+ii] - colSum[row][col+ii]);
// System.out.printf("size=%d, row=%d, col=%d, ii=%d, sum1=%d, sum2=%d\n", size, row, col, ii, sum1, sum2);
                    match = sum1 == sum && sum2 == sum;
                }
                
                if ( match ) {
                    int sum1 = 0, sum2 = 0;
                    for ( int ii = 0; ii < size; ii++ ) {
                        sum1 += grid[row+ii][col+ii];
                        sum2 += grid[row+ii][col+size-1-ii];
                    }
// System.out.printf("sum1=%d, sum2=%d\n", sum1, sum2);
                    if ( sum1 == sum && sum2 == sum ) return true;
                }
            }
        }
        return false;
    }
    public int largestMagicSquare(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        int[][] rowSum = new int[rowSize][colSize+1];
        int[][] colSum = new int[rowSize+1][colSize];

        for ( int row = 0; row < rowSize; row++ ) {
            for ( int col = 0; col < colSize; col++ ) {
                rowSum[row][col+1] = rowSum[row][col] + grid[row][col];
                colSum[row+1][col] = colSum[row][col] + grid[row][col];
            }
        }

        for ( int size = Math.min(rowSize, colSize); size > 1; size-- ) {
            if ( exist(grid, rowSum, colSum, size) ) return size;
        }
        return 1;
    }
}

// runtime - 5ms
class Solution {
    public int largestMagicSquare(int[][] grid) {
          int m = grid.length, n = grid[0].length;

        // Row prefix sums
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }

        int maxSize = Math.min(m, n);

        // Try largest k first
        for (int k = maxSize; k >= 2; k--) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {
                    if (isMagic(grid, rowSum, colSum, i, j, k)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private boolean isMagic(int[][] grid, int[][] rowSum, int[][] colSum,
                            int r, int c, int k) {

        int target = rowSum[r][c + k] - rowSum[r][c];

        // Check all rows
        for (int i = r; i < r + k; i++) {
            if (rowSum[i][c + k] - rowSum[i][c] != target) {
                return false;
            }
        }

        // Check all columns
        for (int j = c; j < c + k; j++) {
            if (colSum[r + k][j] - colSum[r][j] != target) {
                return false;
            }
        }

        // Main diagonal
        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + k - 1 - i];
        }

        return diag1 == target && diag2 == target;
    }
}

//runtime - 7ms
class Solution {

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // prefix sum of each row
        int[][] rowsum = new int[m][n];
        for (int i = 0; i < m; ++i) {
            rowsum[i][0] = grid[i][0];
            for (int j = 1; j < n; ++j) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i][j];
            }
        }
        // prefix sum of each column
        int[][] colsum = new int[m][n];
        for (int j = 0; j < n; ++j) {
            colsum[0][j] = grid[0][j];
            for (int i = 1; i < m; ++i) {
                colsum[i][j] = colsum[i - 1][j] + grid[i][j];
            }
        }

        // enumerate edge lengths from largest to smallest
        for (int edge = Math.min(m, n); edge >= 2; --edge) {
            // enumerate the top-left corner position (i,j) of the square
            for (int i = 0; i + edge <= m; ++i) {
                for (int j = 0; j + edge <= n; ++j) {
                    // the value for each row, column, and diagonal should be calculated (using the first row as a sample)
                    int stdsum =
                        rowsum[i][j + edge - 1] -
                        (j > 0 ? rowsum[i][j - 1] : 0);
                    boolean check = true;
                    // enumerate each row and directly compute the sum using prefix sums
                    for (int ii = i + 1; ii < i + edge; ++ii) {
                        if (
                            rowsum[ii][j + edge - 1] -
                                (j > 0 ? rowsum[ii][j - 1] : 0) !=
                            stdsum
                        ) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    // enumerate each column and directly calculate the sum using prefix sums
                    for (int jj = j; jj < j + edge; ++jj) {
                        if (
                            colsum[i + edge - 1][jj] -
                                (i > 0 ? colsum[i - 1][jj] : 0) !=
                            stdsum
                        ) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    // 两条对角线的和
                    int d1 = 0;
                    int d2 = 0;
                    for (int k = 0; k < edge; ++k) {
                        d1 += grid[i + k][j + k];
                        d2 += grid[i + k][j + edge - 1 - k];
                    }
                    if (d1 == stdsum && d2 == stdsum) {
                        return edge;
                    }
                }
            }
        }
        return 1;
    }
}
