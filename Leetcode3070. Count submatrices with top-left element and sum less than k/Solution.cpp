class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();
        vector<int> cols(m);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int rows = 0;
            for (int j = 0; j < m; j++) {
                cols[j] += grid[i][j];
                rows += cols[j];
                if (rows <= k) {
                    res++;
                }
            }
        }
        return res;
    }
};


// runtime - 0ms
class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();
        vector<int> cols(m);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int rows = 0;
            for (int j = 0; j < m; j++) {
                cols[j] += grid[i][j];
                rows += cols[j];
                if (rows <= k) {
                    res++;
                }
            }
        }
        return res;
    }
};


// runtime - 1ms
/*class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        
    }
};*/
class Solution {
public:
    static int countSubmatrices(vector<vector<int>>& grid, int k) {
        const int r=grid.size(), c=grid[0].size();
        int cnt=0, brCol=c;
        if (grid[0][0]>k) return 0;// early stop
        cnt++;
        for(int j=1; j<c; j++){
            int& x=grid[0][j];
            x+=grid[0][j-1];
            if(x>k)// no need for computing for the rest cols
                { brCol=j; break;}
            cnt++;
        }
        for(int i=1; i<r; i++){
            grid[i][0]+=grid[i-1][0];
            if (grid[i][0]>k) break;
            cnt++;
            for(int j=1; j<brCol; j++){
                int& x=grid[i][j];
                x+=grid[i-1][j]+grid[i][j-1]-grid[i-1][j-1];
                if (x>k){
                    brCol=j; break;
                }
                cnt++;
            }
        }
        return cnt;
    }
};


// runtime - 2ms
class Solution {
public:
    static int countSubmatrices(vector<vector<int>>& grid, int k) {
        const int r=grid.size(), c=grid[0].size();
        int cnt=0, brCol=c;
        if (grid[0][0]>k) return 0;// early stop
        cnt++;
        for(int j=1; j<c; j++){
            int& x=grid[0][j];
            x+=grid[0][j-1];
            if(x>k)// no need for computing for the rest cols
                { brCol=j; break;}
            cnt++;
        }
        for(int i=1; i<r; i++){
            grid[i][0]+=grid[i-1][0];
            if (grid[i][0]>k) break;
            cnt++;
            for(int j=1; j<brCol; j++){
                int& x=grid[i][j];
                x+=grid[i-1][j]+grid[i][j-1]-grid[i-1][j-1];
                if (x>k){
                    brCol=j; break;
                }
                cnt++;
            }
        }
        return cnt;
    }
};


// runtime - 3ms
class Solution {
public:
    int countSubmatrices(const vector<vector<int>>& grid, const int k) {
        int ans = 0;
        vector<int> rsums(grid[0].size(), 0);

        for (size_t r = 0; r < grid.size(); ++r) {
            int rows = 0;

            for (size_t c = 0; c < grid[r].size(); ++c) {
                rsums[c] += grid[r][c];
                rows += rsums[c];
                //rsum += grid[r][c];
                if (rows <= k) {
                    //cout << r << "x" << c << " " << rsums[c] << endl;
                    ++ans;
                } else {
                    //sum -= k;
                    //break;
                }
            }
        }

        return ans;        
    }
};


// runtime - 4ms
class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();
        vector<int> cols(m);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int rows = 0;
            for (int j = 0; j < m; j++) {
                cols[j] += grid[i][j];
                rows += cols[j];
                if (rows <= k) {
                    res++;
                }
            }
        }
        return res;
    }
};


// runtime - 5ms
class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();
        vector<int> cols(m);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int rows = 0;
            for (int j = 0; j < m; j++) {
                cols[j] += grid[i][j];
                rows += cols[j];
                if (rows <= k) {
                    res++;
                }
            }
        }
        return res;
    }
};

// runtime - 6ms
class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int ret = 0;
        int m = grid.size(), n = grid[0].size();
        {
            int tempSum = 0;
            int j=0;
            for (; j<n; ++j){
                grid[0][j] = (tempSum += grid[0][j]);
                if (grid[0][j]>k) break;
            }
            //cout << j << " ";
            ret += j;
        }
        for (int i=1; i<m; ++i){
            int tempSum = 0;
            int j=0;
            for (; j<n; ++j){
                grid[i][j] = (tempSum += grid[i][j]) + grid[i-1][j];
                if (grid[i][j]>k) break;
            }
            //cout << j << " ";
            ret += j;
        }
        return ret;
    }
};
