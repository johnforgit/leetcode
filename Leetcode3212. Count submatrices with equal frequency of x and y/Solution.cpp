int Sum[2][1001];
int seenX[2][1001];
class Solution {
public:
    static int numberOfSubmatrices(vector<vector<char>>& grid) {
        const int r=grid.size(), c=grid[0].size();
        memset(Sum[0], 0, (c+1)*sizeof(int));
        memset(Sum[1], 0, (c+1)*sizeof(int));
        memset(seenX[0], 0, (c+1)*sizeof(int));
        memset(seenX[1], 0, (c+1)*sizeof(int));
        int cnt=0;
        for(int i=0; i<r; i++){
            bool iEven=(i&1)==0, prv=!iEven;
            for(int j=0; j<c; j++){
                const char c=grid[i][j];
                bool isX=c=='X', isY=c=='Y';
                Sum[iEven][j+1]=isX-isY+Sum[iEven][j]+Sum[prv][j+1]-Sum[prv][j];
                seenX[iEven][j+1]=isX+seenX[iEven][j]+seenX[prv][j+1]-seenX[prv][j];
                cnt+=((Sum[iEven][j+1]==0) & (seenX[iEven][j+1]>0));
            }
        }
        return cnt;
    }
};



// runtime - 12ms
class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& A) {
        int n = A.size(), m = A[0].size(), res = 0;
        vector<pair<int,int>> dp(m); // Create a DP array to store (hasX, diffCount) for each column
        
        // Iterate through each row
        for(auto& vec : A) {
            int sum = 0, fl = 0; // Initialize sum and flag for the current row
            
            // Iterate through each column in the current row
            for(int i = 0; i < m; i++) {
                if(vec[i] == 'X') {
                    fl = 1;  // Set flag if 'X' is encountered
                    sum++;   // Increment sum for 'X'
                } else if(vec[i] == 'Y') {
                    sum--;   // Decrement sum for 'Y'
                }
                
                dp[i].first |= fl;  // Update dp[i].first to indicate the presence of 'X' up to the current column
                dp[i].second += sum; // Update dp[i].second to maintain the cumulative difference
                
                // Check if the current submatrix meets the criteria
                if(dp[i].first and dp[i].second == 0) {
                    res++; // Increment the result counter if conditions are met
                }
            }
        }
        return res; // Return the count of valid submatrices
    }
};


// runtime - 14ms
class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& A) {
        int n = A.size(), m = A[0].size(), res = 0;
        vector<pair<int,int>> dp(m); // Create a DP array to store (hasX, diffCount) for each column
        
        // Iterate through each row
        for(auto& vec : A) {
            int sum = 0, fl = 0; // Initialize sum and flag for the current row
            
            // Iterate through each column in the current row
            for(int i = 0; i < m; i++) {
                if(vec[i] == 'X') {
                    fl = 1;  // Set flag if 'X' is encountered
                    sum++;   // Increment sum for 'X'
                } else if(vec[i] == 'Y') {
                    sum--;   // Decrement sum for 'Y'
                }
                
                dp[i].first |= fl;  // Update dp[i].first to indicate the presence of 'X' up to the current column
                dp[i].second += sum; // Update dp[i].second to maintain the cumulative difference
                
                // Check if the current submatrix meets the criteria
                if(dp[i].first and dp[i].second == 0) {
                    res++; // Increment the result counter if conditions are met
                }
            }
        }
        return res; // Return the count of valid submatrices
    }
};


// runtime - 15ms
class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& grid) {
        const int rows = grid.size();
        const int cols = grid[0].size();
        std::vector<int> xCount(cols);
        std::vector<int> yCount(cols);
        auto count = 0;
        for (auto row = 0; row < rows; ++row) {
            auto rowX = 0;
            auto rowY = 0;
            for (auto col = 0; col < cols; ++col) {
                rowX += grid[row][col] == 'X';
                rowY += grid[row][col] == 'Y';
                xCount[col] += rowX;
                yCount[col] += rowY;
                if (xCount[col] && xCount[col] == yCount[col]) ++count;
            }
        }
        return count;
    }
};
