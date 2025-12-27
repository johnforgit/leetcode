class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int rows=grid.size();
        int cols = grid[0].size();
        int max_area = 0;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]==1) { // if the cell is land
                    int area = dfs(grid, i, j);
                    max_area = max(max_area, area);
                }
            }
        }
        return max_area;
    }

    int dfs(vector<vector<int>>& grid, int i, int j) {
        // boundary and water check
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[0].size() || grid[i][j] == 0)
            return 0;

        // mark as visited
        grid[i][j] = 0;

        int cur_area = 1;
        // explore all 4 directions
        cur_area += dfs(grid, i + 1, j);
        cur_area += dfs(grid, i - 1, j);
        cur_area += dfs(grid, i, j + 1);
        cur_area += dfs(grid, i, j - 1);

        return cur_area;
    }
};

// runtime - 0 ms

class Solution {
public:

    int dfs(vector<vector<int>>& grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.size() || j >=grid[0].size() || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return (1 + dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1));
    }

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[0].size(); j++) {
                maxArea = max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }
};


class Solution {
    bool check(vector<vector<int>>& g, int x, int y)  {
        return (x >= 0 && x < g.size() && y >= 0 && y < g[x].size() && g[x][y] == 1);
    }
    int dfs(vector<vector<int>>& grid, int x, int y) {
        if (!check(grid,x,y))
            return 0;
        grid[x][y] = 0;
        int ret = 1;
        ret += dfs(grid, x + 1, y);
        ret += dfs(grid, x - 1, y);
        ret += dfs(grid, x, y + 1);
        ret += dfs(grid, x, y - 1);
        return ret;
    }
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int max = 0;
        for (size_t i = 0; i < grid.size(); i++) {
            for (size_t j = 0; j < grid[i].size(); j++) {
                if (grid[i][j] == 1) {
                    max = std::max(dfs(grid, i, j), max);
                }
            }
        }
        return max;
    }
};
