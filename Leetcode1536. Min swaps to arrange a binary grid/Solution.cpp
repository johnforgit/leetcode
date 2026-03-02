class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        //count of zeros from right to left until any one (1) is occured
        vector<int> zeroCount(n,0);
        for(int i = 0; i < n; i++){
            for(int j = n-1; j > 0 && grid[i][j] == 0; j--){
                zeroCount[i]++;
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            int j = i;
            // find the nearest index which has at least n-1-i zeros
            while(j < n && zeroCount[j] < n-i-1){
                j++;
            }
            if(j == n) return -1;
            ans += j-i;
            // perform swaps and bring the row at index j to index i
            while(j > i){
                swap(zeroCount[j], zeroCount[j-1]);
                j--;
            }
        }

        return ans;
    }
};


// runtime - 0ms
class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> zeros(n);
        for (int i = 0; i < n; ++i) {
            int cnt = 0, j = n-1;
            while (j >= 0 && grid[i][j] == 0) {
                cnt++;
                j--;
            }
            zeros[i] = cnt;
        }
        int swaps = 0;
        for (int i = 0; i < n; ++i) {
            int need = n - 1 - i;
            int j = i;
            while (j < n && zeros[j] < need) j++;
            if (j == n) return -1;
            while (j > i) {
                swap(zeros[j], zeros[j-1]);
                swaps++;
                j--;
            }
        }
        return swaps;
    }
};



// runtime - 1ms
class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> pos(n, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    pos[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = i; j < n; ++j) {
                if (pos[j] <= i) {
                    ans += j - i;
                    k = j;
                    break;
                }
            }
            if (~k) {
                for (int j = k; j > i; --j) {
                    swap(pos[j], pos[j - 1]);
                }
            } else {
                return -1;
            }
        }
        return ans;
    }
};



// runtime - 2ms
class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size(), swaps = 0;
        vector<int> r_one(n);
        for (int i{}; i < n; i++) {
            for (int j{}; j < n; j++) {
                if (grid[i][j] == 1) {
                    r_one[i] = j;
                }
            }
        }

        for (int i{}; i<n; ++i) {
            int k {i + 1};
            while (r_one[i] > i) {
                if (k >= n) return -1;
                swap(r_one[i], r_one[k++]);
                ++swaps;
            }
        }
        return swaps;
    }
};
