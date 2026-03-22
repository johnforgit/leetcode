class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x,
                                         int y, int k) {
        for (int i0 = x, i1 = x + k - 1; i0 < i1; ++i0, --i1) {
            for (int j = y; j < y + k; ++j) {
                swap(grid[i0][j], grid[i1][j]);
            }
        }
        return grid;
    }
};


// runtime - 0ms
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        for (int i = 0; i < k / 2; ++i) {
            for (int j = 0; j < k; ++j) {
                swap(grid[x + i][y + j], grid[x + k - 1 - i][y + j]);
            }
        }
        return grid;
    }
};



// runtime - 1ms
class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        int left_row=x , right_row = x+k-1;
        int n=grid.size() , m=grid[0].size();
        while(left_row < right_row && right_row<n){
            for(int col=y ; col<min(y+k , m)  ; col++){
                swap(grid[left_row][col] , grid[right_row][col]);
            }
            left_row++;
            right_row--;
        }
        return grid;
    }
};


// runtime - 2ms
class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        int y2 = y + k - 1;
        int x2 = x + k - 1;
        while (x < x2) {
            for (int i = y; i <= y2; ++i) {
                swap(grid[x][i], grid[x2][i]);
            }
            ++x; --x2;
        }
        return grid;
    }
};



// runtime - 3ms
class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        int top = x;
        int bottom = x + k - 1;

        while (top < bottom) {
            for (int j = y; j < y + k; ++j) {
                swap(grid[top][j], grid[bottom][j]);
            }
            top++;
            bottom--;
        }

        return grid;
    }
};


