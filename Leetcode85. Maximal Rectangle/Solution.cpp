class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return 0;

        int M = matrix.size();
        int N = matrix[0].size();

        // convert char to int (in-place)
        vector<vector<int>> mat(M, vector<int>(N));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = matrix[i][j] - '0';
            }
        }

        // row-wise prefix widths
        for (int i = 0; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] += mat[i][j - 1];
                }
            }
        }

        int Ans = 0;

        // fix each column
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < M; i++) {
                int width = mat[i][j];
                if (width == 0) continue;

                // expand downward
                int currWidth = width;
                for (int k = i; k < M && mat[k][j] > 0; k++) {
                    currWidth = min(currWidth, mat[k][j]);
                    int height = k - i + 1;
                    Ans = max(Ans, currWidth * height);
                }

                // expand upward
                currWidth = width;
                for (int k = i; k >= 0 && mat[k][j] > 0; k--) {
                    currWidth = min(currWidth, mat[k][j]);
                    int height = i - k + 1;
                    Ans = max(Ans, currWidth * height);
                }
            }
        }

        return Ans;
    }
};

// runtime - 0 ms
class Solution {
public:
    int lhisto(vector<int>
          &arr){
        int mx=0;
        stack<int>st;//index store
        int n=arr.size();
        for(int i=0;i<n;i++){
            while(!st.empty()&&arr[st.top()]>arr[i]){
                int nse=i;
                int elm=st.top();//compute
                st.pop();
                int pse=st.empty()?-1:st.top();
                mx=max(mx,arr[elm]*(nse-pse-1));
            }
            st.push(i);
        }
        while(!st.empty()){
            int nse=n;
            int elm=st.top();
            st.pop();
            int pse=st.empty()?-1:st.top();
            mx=max(mx,arr[elm]*(nse-pse-1));
        }
        return mx;
        
    }
    int maximalRectangle(vector<vector<char>>& matrix) {
        if(matrix.empty()){
            return 0;
        }
        int n=matrix.size();//row
        int m=matrix[0].size();//col
        
        vector<vector<int>>psum(n, vector<int>(m));
        for(int j=0;j<m;j++){
            int sum=0;
            for(int i=0;i<n;i++){
                if(matrix[i][j]=='1'){
                    sum+=1;
                }
                
                if(matrix[i][j]=='0'){
                    sum=0;
                }
                psum[i][j]=sum;
            }
            
            
        }
        int mx=0;
        
        for(int i=0;i<n;i++){
            mx=max(mx,lhisto(psum[i]));
        }
        return mx;
    }
};

// runtime - 1 ms
class Solution {
public:
    int largestRectangleArea(vector<int>& h) {
        int n = h.size();
        vector<int> st;
        int ans = 0;
        int i = 0;

        while(i <= n){
            int cur;
            if(i == n) cur = 0;
            else cur = h[i];

            if(st.empty() || cur >= h[st.back()]){
                st.push_back(i);
                i++;
            }else{
                int top = st.back();
                st.pop_back();
                int width;
                if(st.empty()) width = i;
                else width = i - st.back() - 1;
                int area = h[top] * width;
                if(area > ans) ans = area;
            }
        }
        return ans;
    }

    int maximalRectangle(vector<vector<char>>& matrix) {
        if(matrix.size() == 0) return 0;
        int n = matrix.size();
        int m = matrix[0].size();

        vector<int> heights(m, 0);
        int ans = 0;

        int i = 0;
        while(i < n){
            int j = 0;
            while(j < m){
                if(matrix[i][j] == '1'){
                    heights[j] = heights[j] + 1;
                }else{
                    heights[j] = 0;
                }
                j++;
            }
            int cur = largestRectangleArea(heights);
            if(cur > ans) ans = cur;
            i++;
        }
        return ans;
    }
};

// runtime - 2 ms
class Solution {
public:
    int helper(vector<int>& heights) {
        stack<int> st;
        vector<int> v1(heights.size());
        vector<int> v2(heights.size());
        for(int i=0; i<heights.size(); i++){
            while(!st.empty() && heights[i]<=heights[st.top()]) st.pop();
            if(!st.empty()) v1[i]=st.top();
            else v1[i]=-1;
            st.push(i);
        }
        while(!st.empty()) st.pop();
        for(int i=heights.size()-1; i>=0; i--){
            while(!st.empty() && heights[i]<=heights[st.top()]) st.pop();
            if(!st.empty()) v2[i]=st.top();
            else v2[i]=heights.size();
            st.push(i);
        }
        int mx=0;
        for(int i=0; i<heights.size(); i++){
            mx=max(mx,heights[i]*(v2[i]-v1[i]-1));
        }
        return mx;
    }
    int maximalRectangle(vector<vector<char>>& grid) {
        vector<vector<int>> matrix(grid.size(),vector<int>(grid[0].size()));
        for(int i=0; i<matrix.size(); i++){
            for(int j=0; j<matrix[0].size(); j++){
                matrix[i][j]=grid[i][j]-'0';
            }
        }
        for(int i=0; i<matrix.size(); i++){
            for(int j=0; j<matrix[0].size(); j++){
                matrix[i][j]=(matrix[i][j]==0 || i-1<0) ? matrix[i][j] : matrix[i-1][j]+matrix[i][j];
            }
        }
        int mx=0;
        for(int i=0; i<matrix.size(); i++){
            mx=max(mx,helper(matrix[i]));
        }
        return mx;
    }
};

// runtime - 3 ms
class Solution {
public:
    int maxRect(vector<int>& mat) {
        int n = mat.size();
        stack<pair<int, int>> st;
        int maxi = 0;

        for (int i = 0; i < n; i++) {
            while (!st.empty() && st.top().second > mat[i]) {
                int next = i - 1;
                int val = st.top().second;
                st.pop();
                int prev = st.empty() ? -1 : st.top().first;
                maxi = max(maxi, ((next - prev) * val));
            }
            st.push({i, mat[i]});
        }
        while (!st.empty()) {
            int next = n - 1;
            int val = st.top().second;
            st.pop();
            int prev = st.empty() ? -1 : st.top().first;
            maxi = max(maxi, ((next - prev) * val));
        }
        return maxi;
    }
    int maximalRectangle(vector<vector<char>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        vector<vector<int>> histMat(n, vector<int>(m, 0));
        for (int i = 0; i < m; i++) {
            histMat[0][i] = matrix[0][i] - '0';
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') {
                    histMat[i][j] = 0;
                    continue;
                }
                histMat[i][j] = histMat[i - 1][j] + (matrix[i][j] - '0');
            }
        }
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            int val = maxRect(histMat[i]);
            maxi = max(maxi, val);
        }
        return maxi;
    }
};

// runtime - 4 ms
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if(matrix.empty()) return 0;
        int m=matrix.size(), n=matrix[0].size(), ans=0;
        vector<int> h(n,0);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                h[j]=matrix[i][j]=='1'?h[j]+1:0;
            stack<int> st;
            h.push_back(0);
            for(int k=0;k<h.size();k++){
                while(!st.empty() && h[st.top()]>h[k]){
                    int ht=h[st.top()]; st.pop();
                    int w=st.empty()?k:k-st.top()-1;
                    ans=max(ans,ht*w);
                }
                st.push(k);
            }
            h.pop_back();
        }
        return ans;
    }
};

// runtime - 5 ms
class Solution {
public:
    int largestRectangleArea(vector<int> &heights) {
        
        int n = heights.size(); // Size of array
        
        // Stack 
        stack<int> st;
        
        // To store largest area
        int largestArea = 0;
        
        // To store current area
        int area;
        
        /* To store the indices of next 
        and previous smaller elements */
        int nse, pse;
        
        // Traverse on the array
        for(int i=0; i < n; i++) {
            
            /* Pop the elements in the stack until 
            the stack is not empty and the top 
            elements is not the smaller element */
            while(!st.empty() && 
                  heights[st.top()] >= heights[i]){
                      
                // Get the index of top of stack
                int ind = st.top(); 
                st.pop();
                
                /* Update the index of 
                previous smaller element */
                pse = st.empty() ? -1 : st.top();
                
                /* Next smaller element index for 
                the popped element is current index */
                nse = i;
                
                // Calculate the area of the popped element
                area = heights[ind] * (nse-pse-1);
                
                // Update the maximum area
                largestArea = max(largestArea, area);
            }
            
            // Push the current index in stack
            st.push(i);
        }
        
        // For elements that are not popped from stack
        while(!st.empty()) {
            
            // NSE for such elements is size of array
            nse = n;
            
            // Get the index of top of stack
            int ind = st.top(); 
            st.pop();
            
            // Update the previous smaller element
            pse = st.empty() ? -1 : st.top();
            
            // Calculate the area of the popped element
            area = heights[ind] * (nse-pse-1);
            
            // Update the maximum area
            largestArea = max(largestArea, area);
        }
        
        // Return largest area found
        return largestArea;
    }
    int maximalRectangle(vector<vector<char>>& matrix) {
        int m=matrix[0].size();
        int n=matrix.size();
        vector<vector<int>> pref(n,vector<int>(m,0));
        for(int j=0;j<m;j++){
            int sum=0;
            for(int i=0;i<n;i++){
                sum+=matrix[i][j]-'0';
                if((matrix[i][j]-'0')==0){
                    sum=0;
                }
                pref[i][j]=sum;
            }
        }
        int maxa=0;
        for(int i=0;i<n;i++){
            maxa=max(maxa,largestRectangleArea(pref[i]));
        }
        return maxa;
    }
};

// runtime - 6 ms
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& mat) {
        int m = mat.size(), n = mat[0].size(), mx = 0;
        vector<int> h(n, 0);

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++)
                h[c] = (mat[r][c] == '1') ? h[c] + 1 : 0;

            stack<int> st;
            for(int i = 0; i <= n; i++) {
                int curr = (i == n ? 0 : h[i]);
                while(!st.empty() && curr < h[st.top()]) {
                    int height = h[st.top()];
                    st.pop();
                    int width = st.empty() ? i : i - st.top() - 1;
                    mx = max(mx, height * width);
                }
                st.push(i);
            }
        }
        return mx;
    }
};

// runtime - 7 ms
class Solution {
public:

    int solve(vector<int>& rec){
        stack<int>st;
        int ans = 0;
        for(int i=0;i<=rec.size();i++){
            while(!st.empty() && (i==rec.size() || rec[st.top()] >= rec[i])){
                int height = rec[st.top()];
                st.pop();

                int width = 0;
                if(st.empty())width = i;
                else width = i-st.top()-1;

                ans = max(ans,height*width);
            }
            st.push(i);
        }
        return ans;
    }

    int maximalRectangle(vector<vector<char>>& matrix) {
        if (matrix.empty()) return 0;
        int n = matrix.size(),m=matrix[0].size();
        vector<int>rec(m,0);

        int ans = INT_MIN;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='0')rec[j]=0;
                else rec[j]++;
            }
            ans = max(ans,solve(rec));
        }
        return ans;
    }
};

// runtime - 8 ms
class Solution {
public:
    int maxHistogram(vector<int>& heights) {
        stack<int> st;
        int ans = 0;
        int n = heights.size();

        for (int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);

            while (!st.empty() && h < heights[st.top()]) {
                int height = heights[st.top()];
                st.pop();

                int left = st.empty() ? -1 : st.top();
                int width = i - left - 1;

                ans = max(ans, height * width);
            }
            st.push(i);
        }
        return ans;
    }

    int maximalRectangle(vector<vector<char>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        vector<int> base(m, 0);
        int ans = 0;
        for(int i = 0; i < n; i++){
            // add the level
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == '0'){
                    base[j] = 0;
                }
                else{
                    base[j] += 1;
                }
            }

            ans = max(ans, maxHistogram(base));
        }
        return ans;
    }
};
