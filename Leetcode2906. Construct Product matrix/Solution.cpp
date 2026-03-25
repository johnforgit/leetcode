class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        const int MOD = 12345;
        int n = grid.size(), m = grid[0].size();
        vector<vector<int>> p(n, vector<int>(m));

        long long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = suffix;
                suffix = suffix * grid[i][j] % MOD;
            }
        }

        long long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = p[i][j] * prefix % MOD;
                prefix = prefix * grid[i][j] % MOD;
            }
        }

        return p;
    }
};


// runtime - 11ms
class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int m= grid.size();
        int n= grid[0].size();
        int mod= 12345;
        long store=1;
        vector<vector<int>> ans(m,vector<int>(n,1));
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ans[i][j]= store *ans[i][j]% mod;
                store =store * grid[i][j] % mod;
            }
        }
        store=1;
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                ans[i][j]= store *ans[i][j]% mod;
                store =store * grid[i][j] % mod;
            }
        }
        return ans;
        
    }
};



// runtime - 13ms
class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size();
        long pre = 1, suf = 1, mod = 12345;
        vector<vector<int>> A(n, vector<int>(m, 1));
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
            {
                A[i][j] = pre * A[i][j] % mod;
                A[n - i - 1][m - j - 1] = suf * A[n - i - 1][m - j - 1] % mod;
                pre = pre * grid[i][j] % mod;
                suf = suf * grid[n - i - 1][m - j - 1] % mod;
            }
        return A;
    }
};

// runtime - 15ms
class Solution {
public:
    int mod = 12345;
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> p(m, vector<int>(n));
        long prod = 1;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                p[i][j] = prod;
                prod = (prod * grid[i][j]) % mod;
            }
            // cout<<endl;
        }
        prod = 1;
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                p[i][j] = (p[i][j] * prod) % mod;
                prod = (prod * grid[i][j]) % mod;
                // cout<<p[i][j]<<",";
            }
            // cout<<endl;
        }
        return p;
    }
};


// runtime - 17ms
class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int n = grid.size() , m = grid[0].size();
        vector<vector<int>> ans(n , vector<int>(m , 1));

        long long left = 1 , right = 1;
        const int MOD = 12345;

        for(int i =0 ; i<n ; ++i){
            for(int j = 0 ; j<m ; ++j){
                ans[i][j] = (ans[i][j] *left)%MOD;
                left = (left * grid[i][j])%MOD;
            }
        }

        for(int i = n-1 ; i>=0 ; --i){
            for(int j = m-1 ; j>=0 ; --j){
                ans[i][j] = (ans[i][j] * right)%MOD;
                right = (right * grid[i][j])%MOD;
            }
        }

        return ans;
    }
};


// runtime - 19ms
class Solution {
public:
    const int MOD = 12345;
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();

        vector<int> store;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                store.push_back(grid[i][j] % MOD);
            }
        }

        vector<int> pref = store, suf = store;

        for (int i = 1; i < n * m; i++) {
            pref[i] = (1LL * pref[i - 1] * pref[i]) % MOD;
            suf[n*m - i - 1] = (1LL * suf[n*m - i] * suf[n*m - i - 1]) % MOD;
        }
        vector<vector<int>> ans(n, vector<int>(m));

        int i = 1;
        ans[0][0] = suf[1];
        ans[n - 1][m - 1] = pref[n * m - 2];
        while (i < n * m - 1) {
            int r = i / m;
            int c = i % m;
            ans[r][c] = (1LL * pref[i - 1] * suf[i + 1]) % MOD;
            i++;
        }
        return ans;
    }
};
