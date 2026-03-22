// runtime - 0ms
class Solution {
public:
    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size(), m = mat[0].size();
        
        for (int k = 0; k < 4; k++) {
            if (mat == target) return true;
            for (int i = 0; i < n; i++)
                for (int j = i; j < m; j++)
                    swap(mat[i][j], mat[j][i]);
            
            reverse(mat.begin(), mat.end());
        }
        
        return false;
    }
};

class Solution {
public:
    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size();
        int m = 0b1111;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) m &= 0b1110;
                if (mat[i][j] != target[j][n - 1 - i]) m &= 0b1101;
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) m &= 0b1011;
                if (mat[i][j] != target[n - 1 - j][i]) m &= 0b0111;
                if (m == 0) return false;
            }
        }

        return m != 0;
    }
};

class Solution {
public:
    int temp;
    bool flag;

    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size();
        // at most 4 rotations
        for (int k = 0; k < 4; ++k) {
            // rotation operation
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < (n + 1) / 2; ++j) {
                    temp = mat[i][j];
                    mat[i][j] = mat[n - 1 - j][i];
                    mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];
                    mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];
                    mat[j][n - 1 - i] = temp;
                }
            }

            if (mat == target) {
                return true;
            }
        }
        return false;
    }
};
