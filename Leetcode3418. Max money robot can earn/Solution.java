class Solution {

    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] memo = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }

        return dfs(coins, memo, 0, 0, 2);
    }

    private int dfs(int[][] coins, int[][][] memo, int i, int j, int k) {
        int m = coins.length;
        int n = coins[0].length;
        if (i >= m || j >= n) {
            return Integer.MIN_VALUE;
        }

        int x = coins[i][j];
        // arrive at the destination
        if (i == m - 1 && j == n - 1) {
            return k > 0 ? Math.max(0, x) : x;
        }

        if (memo[i][j][k] != Integer.MIN_VALUE) {
            return memo[i][j][k];
        }

        // not neutralize
        int res =
            Math.max(
                dfs(coins, memo, i + 1, j, k),
                dfs(coins, memo, i, j + 1, k)
            ) +
            x;

        if (k > 0 && x < 0) {
            // neutralize
            res = Math.max(
                res,
                Math.max(
                    dfs(coins, memo, i + 1, j, k - 1),
                    dfs(coins, memo, i, j + 1, k - 1)
                )
            );
        }

        memo[i][j][k] = res;
        return res;
    }
}



// runtime - 8ms
class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int[] dp0 = new int[n], dp1 = new int[n], dp2 = new int[n];
        var c0 = coins[0];
        int p0 = 0, p1 = 0, p2 = 0;
        for (int j = 0; j < n; ++j) {
            int val = c0[j],  max0 = p0, max1 = p1, max2 = p2;
            dp0[j] = p0 = max0 + val;
            dp1[j] = p1 = Math.max(max0, max1 + val);
            dp2[j] = p2 = Math.max(max1, max2 + val);
        }
        for (int i = 1; i < m; ++i) {
            p0 = Integer.MIN_VALUE; p1 = p0; p2 = p1;
            var c = coins[i];
            for (int j = 0; j < n; ++j) {
                int val = c[j], max0 = Math.max(dp0[j], p0), max1 = Math.max(dp1[j], p1), max2 = Math.max(dp2[j], p2);
                dp0[j] = p0 = max0 + val;
                dp1[j] = p1 = Math.max(max0, max1 + val);
                dp2[j] = p2 = Math.max(max1, max2 + val);
            }
        }
        return dp2[n - 1];
    }
}


// runtime - 10ms
class Solution {
    public int maximumAmount(int[][] coins) {
        int m=coins.length,
            n=coins[0].length;
        
        int minVal=-1000000;
        int[][][] dp= new int[3][m+1][n+1];

        for(int i=0;i<=m;i++){
            dp[0][i][0]=minVal;
            dp[1][i][0]=minVal;
            dp[2][i][0]=minVal;
        }
        for(int j=0;j<=m;j++){
            dp[0][0][j]=minVal;
            dp[1][0][j]=minVal;
            dp[2][0][j]=minVal;
        }
        dp[0][0][1]=0;
        dp[0][1][0]=0;

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                int min0=Math.max(dp[0][i-1][j],    dp[0][i][j-1]);
                int min1=Math.max(dp[1][i-1][j],    dp[1][i][j-1]);
                int min2=Math.max(dp[2][i-1][j],    dp[2][i][j-1]);
                int curr=coins[i-1][j-1];
                dp[0][i][j]=curr+min0;
                dp[1][i][j]=Math.max(min0,min1+curr);
                dp[2][i][j]=Math.max(min1,min2+curr);
            }
        }
        return Math.max(dp[0][m][n],Math.max(dp[1][m][n],dp[2][m][n]));
    }
}


// runtime - 106ms
class Solution {
    public int maximumAmount(int[][] coins) {
        int n = coins.length;
        int m = coins[0].length;
        int[][][] dp = new int[n][m][3];

        for (int[][] row : dp) {
            for (int[] col : row) {
                java.util.Arrays.fill(col, (int)-1e9);
            }
        }

        dp[0][0][1] = 0;
        dp[0][0][2] = 0;
        dp[0][0][0] = coins[0][0];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < 3; k++) {
                    if (i > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k] + coins[i][j]);
                    if (i > 0 && k > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1]);
                    if (j > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k] + coins[i][j]);
                    if (j > 0 && k > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k - 1]);
                }

        return Math.max(dp[n - 1][m - 1][0], Math.max(dp[n - 1][m - 1][1], dp[n - 1][m - 1][2]));
    }
}
