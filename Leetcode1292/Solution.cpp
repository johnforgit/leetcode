// optimized solution with enumeration

class Solution {
public:
    int getRect(const vector<vector<int>>& P, int x1, int y1, int x2, int y2) {
        return P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1];
    }

    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> P(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                P[i][j] = P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1] +
                          mat[i - 1][j - 1];
            }
        }

        int r = min(m, n), ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int c = ans + 1; c <= r; ++c) {
                    if (i + c - 1 <= m && j + c - 1 <= n &&
                        getRect(P, i, j, i + c - 1, j + c - 1) <= threshold) {
                        ++ans;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }
};

// normal solution using prefix sum and binary search
class Solution {
public:
    int getRect(const vector<vector<int>>& P, int x1, int y1, int x2, int y2) {
        return P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1];
    }

    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> P(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                P[i][j] = P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1] +
                          mat[i - 1][j - 1];
            }
        }

        int l = 1, r = min(m, n), ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            bool find = false;
            for (int i = 1; i <= m - mid + 1; ++i) {
                for (int j = 1; j <= n - mid + 1; ++j) {
                    if (getRect(P, i, j, i + mid - 1, j + mid - 1) <=
                        threshold) {
                        find = true;
                    }
                }
            }
            if (find) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
};

// runtime - 1ms
class Solution {
public:

    void debug(const vector<vector<int>>& mat)
    {
        for (const vector<int>& row: mat)
        {
            for (const int& col: row)
            {
                cout << col << " ";
            }
            cout << endl;         
        }
    }

    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        vector<vector<int>> prefix_sum(mat.size() + 1, vector<int>(mat[0].size() + 1, 0));

        for (int ii = 1; ii <= mat.size(); ++ii)
        {
            for (int jj = 1; jj <= mat[0].size(); ++jj)
            {
                prefix_sum[ii][jj] = mat[ii-1][jj-1] +  prefix_sum[ii-1][jj] + prefix_sum[ii][jj - 1] -  prefix_sum[ii-1][jj-1];
            }
        }
        
        int max_side = 0;
        for (int ii = 1; ii <= mat.size(); ++ii)
        {
            for (int jj = 1; jj <= mat[0].size(); ++jj)
            {
                if (max_side < ii && max_side < jj)
                {
                    int k = max_side + 1;
                    int curr_sum = prefix_sum[ii][jj] + prefix_sum[ii-k][jj-k] - prefix_sum[ii-k][jj] - prefix_sum[ii][jj-k];
                    if (curr_sum <= threshold)
                    {
                        max_side = k; 
                    } 
                }
            }    
        }

        return max_side;
    }
};


// runtime - 5ms
class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int rows = mat.size();
        int cols = mat[0].size();

        vector<vector<int>> prefix(rows, vector<int>(cols, 0));

        // Build prefix sum
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefix[i][j] = mat[i][j]
                             + (i > 0 ? prefix[i - 1][j] : 0)
                             + (j > 0 ? prefix[i][j - 1] : 0)
                             - (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }

        // Helper lambda to get sum of square
        auto sumSquare = [&](int i, int j, int r2, int c2) {
            int sum = prefix[r2][c2];
                    if (i > 0) sum -= prefix[i - 1][c2];
                    if (j > 0) sum -= prefix[r2][j - 1];
                    if (i > 0 && j > 0) sum += prefix[i - 1][j - 1];
            
            return sum;
        };

        int best = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = best; k < min(rows - i, cols - j); k++) { //offset to find bottom right cell
                    //bottom right cell (r2, c2)
                    int r2 = i + k;
                    int c2 = j + k;

                    int sum = sumSquare(i, j, r2, c2);

                    if (sum <= threshold) {
                        best = k + 1; //(offset + 1) gives the side of square
                    } else {
                        break; //because sum will increase only. Better move to next cell
                    }
                }
            }
        }

        return best;
    }
};
