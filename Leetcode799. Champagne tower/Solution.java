class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] A = new double[102][102];
        A[0][0] = (double) poured;
        for (int r = 0; r <= query_row; ++r) {
            for (int c = 0; c <= r; ++c) {
                double q = (A[r][c] - 1.0) / 2.0;
                if (q > 0) {
                    A[r+1][c] += q;
                    A[r+1][c+1] += q;
                }
            }
        }

        return Math.min(1, A[query_row][query_glass]);
    }
}

// runtime - 2ms
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] pyramid = new double[query_row + 2][query_row + 2];
        pyramid[0][0] = (double)poured;
        for(int i = 0; i <= query_row; i++){
            for(int j = 0; j <= i; j++){
                if(pyramid[i][j] > 1.0){
                    double remain = pyramid[i][j] - 1.0;
                    pyramid[i + 1][j] += remain / 2;
                    pyramid[i + 1][j + 1] += remain / 2;
                    pyramid[i][j] = 1.0;
                }
            }
        }

        return pyramid[query_row][query_glass];
    }
}


// runtime - 1ms
class Solution {
    public double champagneTower(int poured, int row, int col) {
        double[] dp = new double[col + 2];
        dp[0] = poured;
        for(int i = 0; i < row; i++) {
            for(int j = Math.min(i, col); j >= 0; j--) {
                if(dp[j] > 1) {
                    double val = (dp[j] - 1) / 2.0;
                    dp[j] = val;
                    dp[j + 1] += val;
                }else dp[j] = 0;
            }
        }
        return Math.min(1, dp[col]);
    }
}
