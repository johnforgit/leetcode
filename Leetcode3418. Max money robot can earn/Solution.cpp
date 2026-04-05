class Solution {
public:
    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size(), n = coins[0].size();
        vector memo(m, vector(n, array<int, 3>{INT_MIN, INT_MIN, INT_MIN}));

        function<int(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (i >= m || j >= n) {
                return INT_MIN;
            }

            int x = coins[i][j];
            // arrive at the destination
            if (i == m - 1 && j == n - 1) {
                return k > 0 ? max(0, x) : x;
            }

            int res = memo[i][j][k];
            if (res != INT_MIN) {
                return res;
            }
            // not neutralize
            res = max(dfs(i + 1, j, k), dfs(i, j + 1, k)) + x;
            if (k > 0 && x < 0) {
                // neutralize
                res = max({res, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)});
            }
            return memo[i][j][k] = res;
        };

        return dfs(0, 0, 2);
    }
};



// runtime - 4ms
class Solution {
public:
    int maximumAmount(vector<vector<int>>& g) {
        int dp[500][3];
        const int h = g.size(), w = g[0].size();

        fill_n(dp[0], 3 * w, -500'000);
        fill_n(dp[0], 3, 0);

        for (int y = 0; y != h; ++y) {
            {
                constexpr int x = 0;
                int top[]{dp[x][0], dp[x][1], dp[x][2]};
                int v = g[y][x], v0 = max(v, 0);
                dp[x][0] = v + top[0];
                dp[x][1] = max(v + top[1], v0 + top[0]);
                dp[x][2] = max(v + top[2], v0 + top[1]);
            }

            for (int x = 1; x != w; ++x) {
                int top[]{dp[x][0], dp[x][1], dp[x][2]};
                int v = g[y][x], v0 = max(v, 0);
                dp[x][0] = max(v + dp[x - 1][0], v + top[0]);
                dp[x][1] = max(max(v + dp[x - 1][1], v + top[1]),
                               max(v0 + dp[x - 1][0], v0 + top[0]));
                dp[x][2] = max(max(v + dp[x - 1][2], v + top[2]),
                               max(v0 + dp[x - 1][1], v0 + top[1]));
            }
        }

        return dp[w - 1][2];
    }
};



// runtime - 14ms
static int dp[500][3];

class Solution {
public:
    static int maximumAmount(vector<vector<int>>& coins) {
        const int r=coins.size(), c=coins[0].size();
        // Base case
        int x=coins[0][0];
        dp[0][0]=x;          
        dp[0][1]=dp[0][2]=max(0, x);  

        for(int j=1; j<c; j++){// 0th row
            x=coins[0][j];
            for (int k=2; k>0; k--){
                dp[j][k]=max(x+dp[j-1][k], dp[j-1][k-1]);
            }
            dp[j][0]=x+dp[j-1][0];// k=0
        }

        for (int i=1; i<r; i++) {
            // 0th col
            x=coins[i][0];
            for (int k=2; k>0; k--){
                dp[0][k]=max(x+dp[0][k], dp[0][k-1]);
            }
            dp[0][0]=x+dp[0][0];// k=0
            
            for (int j=1; j<c; j++) {
                x=coins[i][j];
                for (int k=2; k>0; k--) {
                    dp[j][k]=max(max(x+dp[j-1][k], dp[j-1][k-1]), max(x+dp[j][k], dp[j][k-1]));
                }
                dp[j][0]=x+max(dp[j][0], dp[j-1][0]);// k=0

            }
        }
        return dp[c-1][2];
    }
};
auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();



// runtime - 63ms
#include <vector>
#include <algorithm>

using namespace std;

// Fast I/O optimization
auto speedup = []() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    long long maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size();
        int n = coins[0].size();
        
        // Using a flat array instead of vector of vectors to keep memory contiguous
        // dp[2][n][3] uses a "rolling row" technique
        long long dp[2][501][3];
        long long INF = 1e16;

        // Initialize only what we need
        for(int j = 0; j < n; ++j) {
            for(int k = 0; k < 3; ++k) dp[0][j][k] = dp[1][j][k] = -INF;
        }

        // Base Case
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) dp[0][0][1] = 0;

        for (int i = 0; i < m; ++i) {
            int curr = i % 2;
            int prev = (i + 1) % 2;
            
            // If i > 0, we need to reset the current row before filling
            if (i > 0) {
                for(int j = 0; j < n; ++j) {
                    dp[curr][j][0] = dp[curr][j][1] = dp[curr][j][2] = -INF;
                }
            }

            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 3; ++k) {
                    if (i == 0 && j == 0) continue;

                    long long bestPrev = -INF;
                    // Check Above (from previous row)
                    if (i > 0) bestPrev = max(bestPrev, dp[prev][j][k]);
                    // Check Left (from current row)
                    if (j > 0) bestPrev = max(bestPrev, dp[curr][j-1][k]);

                    // Option 1: Normal move
                    if (bestPrev != -INF) {
                        dp[curr][j][k] = max(dp[curr][j][k], bestPrev + coins[i][j]);
                    }

                    // Option 2: Neutralize move
                    if (k > 0 && coins[i][j] < 0) {
                        long long bestPrevK = -INF;
                        if (i > 0) bestPrevK = max(bestPrevK, dp[prev][j][k-1]);
                        if (j > 0) bestPrevK = max(bestPrevK, dp[curr][j-1][k-1]);
                        
                        if (bestPrevK != -INF) {
                            dp[curr][j][k] = max(dp[curr][j][k], bestPrevK);
                        }
                    }
                }
            }
        }

        int lastRow = (m - 1) % 2;
        return max({dp[lastRow][n-1][0], dp[lastRow][n-1][1], dp[lastRow][n-1][2]});
    }
};
