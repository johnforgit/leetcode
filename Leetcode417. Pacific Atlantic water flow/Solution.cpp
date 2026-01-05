#include <vector>
#include <queue>

using namespace std;

class Solution {
public:
    int m, n;
    vector<vector<int>> heights;
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};

    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        this->heights = heights;
        m = heights.size();
        n = heights[0].size();

        queue<pair<int,int>> pQue, aQue;
        vector<vector<bool>> pSeen(m, vector<bool>(n, false));
        vector<vector<bool>> aSeen(m, vector<bool>(n, false));

        // Pacific (top row)
        for (int j = 0; j < n; j++) {
            pQue.push({0, j});
            pSeen[0][j] = true;
        }
        // Pacific (left column)
        for (int i = 1; i < m; i++) {
            pQue.push({i, 0});
            pSeen[i][0] = true;
        }

        // Atlantic (right column)
        for (int i = 0; i < m; i++) {
            aQue.push({i, n - 1});
            aSeen[i][n - 1] = true;
        }
        // Atlantic (bottom row)
        for (int j = 0; j < n - 1; j++) {
            aQue.push({m - 1, j});
            aSeen[m - 1][j] = true;
        }

        bfs(pQue, pSeen);
        bfs(aQue, aSeen);

        vector<vector<int>> result;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pSeen[i][j] && aSeen[i][j]) {
                    result.push_back({i, j});
                }
            }
        }
        return result;
    }

private:
    void bfs(queue<pair<int,int>>& q, vector<vector<bool>>& seen) {
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();

            for (auto& d : dirs) {
                int r = i + d[0];
                int c = j + d[1];

                if (r >= 0 && r < m && c >= 0 && c < n &&
                    !seen[r][c] &&
                    heights[r][c] >= heights[i][j]) {
                    
                    seen[r][c] = true;
                    q.push({r, c});
                }
            }
        }
    }
};

// runtime - 1 ms
class Solution {
public:
    int n, m;
    vector<vector<int>> dir{{1,0},{-1,0},{0,1},{0,-1}};

    void dfs(int i, int j, vector<vector<bool>>& ocean,
             vector<vector<int>>& heights) {
        ocean[i][j] = true;

        for (auto& d : dir) {
            int ni = i + d[0], nj = j + d[1];
            if (ni < 0 || nj < 0 || ni >= n || nj >= m)
                continue;
            if (ocean[ni][nj])
                continue;
            if (heights[ni][nj] < heights[i][j])
                continue;

            dfs(ni, nj, ocean, heights);
        }
    }

    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        n = heights.size();
        m = heights[0].size();

        vector<vector<bool>> pac(n, vector<bool>(m, false));
        vector<vector<bool>> atl(n, vector<bool>(m, false));

        // Pacific: top row + left column
        for (int i = 0; i < n; i++)
            dfs(i, 0, pac, heights);
        for (int j = 0; j < m; j++)
            dfs(0, j, pac, heights);

        // Atlantic: bottom row + right column
        for (int i = 0; i < n; i++)
            dfs(i, m-1, atl, heights);
        for (int j = 0; j < m; j++)
            dfs(n-1, j, atl, heights);

        vector<vector<int>> res;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pac[i][j] && atl[i][j])
                    res.push_back({i, j});
            }
        }
        return res;
    }
};
