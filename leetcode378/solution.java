class Solution {
    int rows, cols;
    public int kthSmallest(int[][] matrix, int k) {
        rows = matrix.length;
        cols = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[rows-1][cols-1];
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

    int countLessOrEqual(int matrix[][], int x)
    {
        int count=0;
        int j=cols-1; // start with the rightmost column
        for(int i=0; i<rows; i++) {
            while(j>=0 && matrix[i][j] > x)
    // decrease column until matrix[r][c] <= x
                --j;
            count += (j+1);
        }
        return count;
    }
}
