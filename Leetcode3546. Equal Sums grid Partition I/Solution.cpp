class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        long long total = 0;
        
        for (auto &row : grid)
            for (int x : row)
                total += x;
        
        if (total % 2) return false;
        
        long long target = total / 2, sum = 0;
        
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++)
                sum += grid[i][j];
            if (sum == target) return true;
        }
        
        sum = 0;
        
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++)
                sum += grid[i][j];
            if (sum == target) return true;
        }
        
        return false;
    }
};


// runtime - 0ms
class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        long long total = 0;

        // total sum
        for (auto &row : grid) {
            for (auto &val : row) {
                total += val;
            }
        }

        // if odd → impossible
        if (total % 2 != 0) return false;

        long long target = total / 2;

        // 🔹 check horizontal cut
        long long curr = 0;
        for (int i = 0; i < m - 1; i++) {  // m-1 because last row not allowed
            for (int j = 0; j < n; j++) {
                curr += grid[i][j];
            }
            if (curr == target) return true;
        }

        // 🔹 check vertical cut
        curr = 0;
        for (int j = 0; j < n - 1; j++) {  // n-1 because last column not allowed
            for (int i = 0; i < m; i++) {
                curr += grid[i][j];
            }
            if (curr == target) return true;
        }

        return false;
    }
};



// runtime - 1ms
class Solution {
public:

    bool canPartitionGrid(vector<vector<int>>& grid) {
        int M = grid.size();
        int N = grid[0].size();
        long long totalSum = 0;
        vector<long long> rowSum(M, 0);
        vector<long long> colSum(N, 0);
        for(int x = 0; x < M; x++)
        {
            for(int y = 0; y < N; y++)
            {
                totalSum += grid[x][y];
            }
        }
            if(totalSum % 2 != 0)
        {
            return false;
        }
        long long halfSum = totalSum / 2;
        long long currentRow = 0;

        for(int x = 0; x < M; x++)
        {
            for(int y = 0; y < N; y++)
            {
                currentRow += grid[x][y];
                totalSum += grid[x][y];
            }
            if(currentRow == halfSum)
            {
                return true;
            }
            rowSum[x] = currentRow;
        }
                // We can't partition an odd total sum into two separate parts
        long long currentCol = 0;
        for(int y = 0; y < N; y++)
        {
            for(int x = 0; x < M; x++)
            {
                currentCol += grid[x][y];
                if(currentCol == halfSum)
                {
                    return true;
                }
            }

            colSum[y] = (currentCol);
        }


        for(const auto& sum : rowSum)
        {
            if (sum == halfSum)
            {
                return true;
            }
        }
        for(const auto& sum : colSum)
        {
            if (sum == halfSum)
            {
                return true;
            }
        }
        return false;

    }
};



// runtime - 2ms
class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        
        int n = grid.size();
        int m = grid[0].size();

        vector<long long> row(n,0), col(m,0);

        // compute row and column sums
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }

        // check horizontal cut
        long long total = 0;
        for(auto x : row) total += x;

        long long prefix = 0;

        for(int i=0;i<n-1;i++){
            prefix += row[i];
            if(prefix == total - prefix)
                return true;
        }

        // check vertical cut
        total = 0;
        for(auto x : col) total += x;

        prefix = 0;

        for(int i=0;i<m-1;i++){
            prefix += col[i];
            if(prefix == total - prefix)
                return true;
        }

        return false;
    }
};



// runtime - 3ms
class Solution {
public:

    bool canPartitionGrid(vector<vector<int>>& grid) {
        long long sum = 0;
        int n = grid.size();
        int m = grid[0].size();
        for(int i=0;i<n;i++)
            for(int j = 0;j<m;j++) sum += grid[i][j];
        
        if(sum %2) return false;
        long long curr = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                curr += grid[i][j];
            }
            if(sum/2 == curr) return true;
        }
        //
        curr = 0;
        for(int j=0;j<m;j++){
            for(int i=0;i<n;i++){
                curr += grid[i][j];
            }
            if(sum/2 == curr) return true;
        }
        return false;
    }
};
