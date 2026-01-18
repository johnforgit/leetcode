class Solution {
public:
    int largestMagicSquare(vector<vector<int>>& grid) {
        int totalRows = grid.size();
        int totalCols = grid[0].size();

        // Prefix sum arrays
        vector<vector<int>> rowPrefixSum(totalRows, vector<int>(totalCols + 1, 0));
        vector<vector<int>> colPrefixSum(totalRows + 1, vector<int>(totalCols, 0));
        vector<vector<int>> mainDiagPrefix(totalRows + 1, vector<int>(totalCols + 1, 0));
        vector<vector<int>> antiDiagPrefix(totalRows + 1, vector<int>(totalCols + 2, 0));

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

        int maxPossibleSize = min(totalRows, totalCols);

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

private:
    bool isMagicSquare(
        int startRow,
        int startCol,
        int size,
        vector<vector<int>>& rowPrefixSum,
        vector<vector<int>>& colPrefixSum,
        vector<vector<int>>& mainDiagPrefix,
        vector<vector<int>>& antiDiagPrefix) {

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
};
