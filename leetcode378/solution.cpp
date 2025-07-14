class Solution {
public:
    int rows, cols;
    int kthSmallest(vector<vector<int>>& matrix, int k) {
        rows=matrix.size();
        cols=matrix[0].size();
        int left=matrix[0][0];
        int right=matrix[rows-1][cols-1];
        int ans=-1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(countLessOrEqual(matrix, mid) >= k) {
                ans = mid;
                right = mid-1;
            } else
                left = mid+1;
        }
        return ans;
    }

    int countLessOrEqual(vector<vector<int>>& matrix, int x) 
    {
        int count=0;
        int j=cols-1;
        for(int i=0; i<rows; i++) {
            while(j>=0 && matrix[i][j] > x)
                --j;
            count += j+1;
        }
        return count;
    }
};
