class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = 0;
        
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] != 0 && row > 0) {
                    matrix[row][col] += matrix[row - 1][col];
                }
            }
            
            vector<int> currRow = matrix[row];
            sort(currRow.begin(), currRow.end(), greater());
            for (int i = 0; i < n; i++) {
                ans = max(ans, currRow[i] * (i + 1));
            }
        }
        
        return ans;
    }
};

class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        auto m = matrix.size(), n = matrix[0].size();
        int res = 0;

        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 1)
                    matrix[i][j] += matrix[i - 1][j];

        for (int i = 0; i < m; i++) {
            sort(matrix[i].rbegin(), matrix[i].rend());
            for (int j = 0; j < n; j++)
                res = max(res, matrix[i][j] * (j + 1));
        }

        return res;
    }
};

class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        vector<int> prevRow = vector(n, 0);
        int ans = 0;
        
        for (int row = 0; row < m; row++) {
            vector<int> currRow = matrix[row];
            for (int col = 0; col < n; col++) {
                if (currRow[col] != 0) {
                    currRow[col] += prevRow[col];
                }
            }
            
            vector<int> sortedRow = currRow;
            sort(sortedRow.begin(), sortedRow.end(), greater());
            for (int i = 0; i < n; i++) {
                ans = max(ans, sortedRow[i] * (i + 1));
            }
            
            prevRow = currRow;
        }

        return ans;
    }
};


// runtime - 0ms
int freq[100000];
class Solution {
public:  
    static int largestSubmatrix(vector<vector<int>>& matrix) {
        const int m=matrix.size(), n=matrix[0].size();
        int area=count(matrix[0].begin(), matrix[0].end(), 1);
        if (m==1) return area;
        if (n==1){
            for(int i=1; i<m; i++){
                int& x=matrix[i][0];
                x+=-x & matrix[i-1][0];
                area=max(area, x);
            }
            return area;
        }
        
        for(int i=1; i<m; i++){
            for(int j=0;  j<n; j++){
                int& x=matrix[i][j];
                x+= -x & matrix[i-1][j];
            }
            const auto& row=matrix[i];
            int minH=i+1, maxH=0;
            for(int x: row){
                minH=min(minH, x);
                maxH=max(maxH, x);
            }
            memset(freq, 0, sizeof(int)*(maxH-minH+1));
            for(int x: row){
                freq[x-minH]++;
            }
            int acc=0;
            for(int x=maxH-minH; acc<n; x--){
                if (freq[x]>0){
                    acc+=freq[x];
                    area=max(area, acc*(x+minH));
                }
            }  
        }
        return area;
    }
};
auto init = []()
{ 
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();



// runtime - 1ms
class Solution {
public:  
    static int largestSubmatrix(vector<vector<int>>& matrix) {
        const int m=matrix.size(), n=matrix[0].size();
        int area=count(matrix[0].begin(), matrix[0].end(), 1);
        if (m==1) return area;
        if (n==1){
            for(int i=1; i<m; i++){
                matrix[i][0]+=matrix[i][0]*matrix[i-1][0];
                area=max(area, matrix[i][0]);
            }
            return area;
        }
        
        for(int i=1; i<m; i++){
            for(int j=0;  j<n; j++){
                matrix[i][j]+=matrix[i][j]*matrix[i-1][j];
            }
            const auto& row=matrix[i];
            int minH=i+1, maxH=0;
            for(int x: row){
                minH=min(minH, x);
                maxH=max(maxH, x);
            }
            vector<int> freq(maxH-minH+1, 0);
            for(int x: row){
                freq[x-minH]++;
            }
            int acc=0;
            for(int x=maxH-minH; acc<n; x--){
                if (freq[x]>0){
                    acc+=freq[x];
                    area=max(area, acc*(x+minH));
                }
            }  
        }
        return area;
    }
};
auto init = []()
{ 
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();



// runtime - 2ms
class Solution {
public:  
    static int largestSubmatrix(vector<vector<int>>& matrix) {
        const int m=matrix.size(), n=matrix[0].size();
        int area=count(matrix[0].begin(), matrix[0].end(), 1);
        if (m==1) return area;
        if (n==1){
            for(int i=1; i<m; i++){
                matrix[i][0]+=matrix[i][0]*matrix[i-1][0];
                area=max(area, matrix[i][0]);
            }
            return area;
        }
        
        for(int i=1; i<m; i++){
            for(int j=0;  j<n; j++){
                matrix[i][j]+=matrix[i][j]*matrix[i-1][j];
            }
            const auto& row=matrix[i];
            int minH=i+1, maxH=0;
            for(int x: row){
                minH=min(minH, x);
                maxH=max(maxH, x);
            }
            vector<int> freq(maxH-minH+1, 0);
            for(int x: row){
                freq[x-minH]++;
            }
            int acc=0;
            for(int x=maxH-minH; acc<n; x--){
                if (freq[x]>0){
                    acc+=freq[x];
                    area=max(area, acc*(x+minH));
                }
            }  
        }
        return area;
    }
};
auto init = []()
{ 
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();


// runtime - 3ms
class Solution {
public:
  int largestSubmatrix(vector<vector<int>>& matrix) {
    int m = matrix.size(), n = matrix[0].size();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i != 0 && matrix[i][j] == 1) {
          matrix[i][j] += matrix[i - 1][j];
        }
      }
    }

    int res = 0;
    for (int i = 0; i < m; i++) {
      sort(matrix[i].begin(), matrix[i].end(), greater<int>());
      for (int j = 0; j < n; j++) {
        res = max(res, matrix[i][j] * (j + 1));
      }
    }

    return res;
  }
};


// runtime - 4ms
class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        for (int j = 0; j < n; ++j) {
            for (int i = 1; i < m; ++i) {
                matrix[i][j] += matrix[i][j] == 0 ? 0 : matrix[i-1][j];
            }
        }
     
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            ranges::sort(matrix[i]);
            int last = -1;
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == last) {
                    continue;
                    last == matrix[i][j];
                }
                ans = max(ans, matrix[i][j] * (n - j));
            }
        }
        return ans;
    }
};
