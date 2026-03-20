class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> res(m - k + 1, vector<int>(n - k + 1));
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                vector<int> kgrid;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        kgrid.push_back(grid[x][y]);
                    }
                }
                int kmin = INT_MAX;
                sort(kgrid.begin(), kgrid.end());
                for (int t = 1; t < kgrid.size(); t++) {
                    if (kgrid[t] == kgrid[t - 1]) {
                        continue;
                    }
                    kmin = min(kmin, kgrid[t] - kgrid[t - 1]);
                }
                if (kmin != INT_MAX) {
                    res[i][j] = kmin;
                }
            }
        }
        return res;
    }
};


// runtime - 3ms
class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m-k+1, vector<int>(n-k+1, 0));
        for(int i=0; i<=m-k; i++){
            for(int j=0; j<=n-k; j++){
                vector<int> temp;
                for(int r=i; r<i+k; r++){
                    for(int c=j; c<j+k; c++){
                        temp.push_back(grid[r][c]);
                    }
                }
                sort(temp.begin(), temp.end());
                int diff = INT_MAX;
                for(int x=1; x<temp.size(); x++){
                    if(temp[x] != temp[x-1]) diff = min(diff, abs(temp[x] - temp[x-1]));
                }
                ans[i][j] = (diff == INT_MAX) ? 0 : diff;
            }
        }
        return ans;
    }
};


// runtime - 4ms
class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int v) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>>ans(m-v+1, vector<int>(n-v+1, 0));
        for(int i = 0; i<=m-v; i++){
            for(int j = 0; j<=n-v; j++){
                vector<int>temp;
                for(int k = i; k<i+v; k++){
                    for(int l = j; l<j+v; l++){
                        temp.push_back(grid[k][l]);
                    }
                }
                int diff = INT_MAX;
                sort(temp.begin(), temp.end());
                for(int m = 1; m<temp.size(); m++){
                    if(temp[m] != temp[m-1]) diff = min(diff, temp[m]-temp[m-1]);
                }
                ans[i][j] = (diff==INT_MAX)?0:diff;
            }
        }
        return ans;
    }
};



// runtime - 7ms
class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> ans(m-k+1,vector<int>(n-k+1));

        for(int i=0;i<=m-k;i++){
            for(int j=0;j<=n-k;j++){
                vector<int> temp;

                for(int r = i;r<i+k;r++){
                    for(int c = j;c<j+k;c++){
                        temp.push_back(grid[r][c]);
                    }
                }
                sort(begin(temp),end(temp));
                int diff = INT_MAX;
                for(int x=0;x<temp.size()-1;x++){
                    if(temp[x]!=temp[x+1]){
                        diff = min(diff,abs(temp[x]-temp[x+1]));
                    }
                }
                if(diff==INT_MAX) ans[i][j]=0;
                else ans[i][j]=diff;
            }
        }
        return ans;
    }
};



// runtime - 8ms
class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> res(m - k + 1, vector<int>(n - k + 1));
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                vector<int> kgrid;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        kgrid.push_back(grid[x][y]);
                    }
                }
                int kmin = INT_MAX;
                sort(kgrid.begin(), kgrid.end());
                for (int t = 1; t < kgrid.size(); t++) {
                    if (kgrid[t] == kgrid[t - 1]) {
                        continue;
                    }
                    kmin = min(kmin, kgrid[t] - kgrid[t - 1]);
                }
                if (kmin != INT_MAX) {
                    res[i][j] = kmin;
                }
            }
        }
        return res;
    }
};
