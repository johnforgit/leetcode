class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                // check for consecutive 1s appearing from second row onwards
                if(matrix[i][j] != 0 && i>0) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }

            int[] curRow = matrix[i].clone();
            Arrays.sort(curRow);

            for(int row=0; row<n; row++) {
                ans = Math.max(ans, curRow[row] * (n-row));
            }
        }

        return ans;
    }
}


// runtime - 5ms
class Solution {

    // Frequency array used for counting sort of heights
    static int[] freq = new int[100000];

    public int largestSubmatrix(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxArea = 0;

        // Handle first row (height = 1s only)
        for (int val : matrix[0]) {
            if (val == 1) maxArea++;
        }

        if (rows == 1) return maxArea;

        // Special case: single column
        if (cols == 1) {
            for (int i = 1; i < rows; i++) {
                int val = matrix[i][0];

                // If val == 1, add previous height
                val += (-val & matrix[i - 1][0]);

                matrix[i][0] = val;
                maxArea = Math.max(maxArea, val);
            }
            return maxArea;
        }

        // Process each row
        for (int i = 1; i < rows; i++) {

            // Build heights (in-place)
            for (int j = 0; j < cols; j++) {
                int val = matrix[i][j];
                val += (-val & matrix[i - 1][j]);
                matrix[i][j] = val;
            }

            // Find min and max heights in this row
            int minHeight = i + 1;
            int maxHeight = 0;

            for (int h : matrix[i]) {
                minHeight = Math.min(minHeight, h);
                maxHeight = Math.max(maxHeight, h);
            }

            // Reset frequency array for used range
            for (int k = 0; k <= maxHeight - minHeight; k++) {
                freq[k] = 0;
            }

            // Count frequencies of heights
            for (int h : matrix[i]) {
                freq[h - minHeight]++;
            }

            int width = 0;

            // Traverse heights from largest to smallest
            for (int h = maxHeight - minHeight; width < cols; h--) {
                if (freq[h] > 0) {
                    width += freq[h];
                    maxArea = Math.max(maxArea, width * (h + minHeight));
                }
            }
        }

        return maxArea;
    }
}


// runtime - 6ms
class Solution {
    public static int help(int[] arr){
        int min=Integer.MAX_VALUE;
        int max=0;
        for(int i:arr){
            min=Math.min(min,i);
            max=Math.max(max,i);
        }
        int[] ans=new int[max-min+1];
        for(int i:arr){
            ans[i-min]++;
        }
        int temp=max*ans[max-min];
        for(int i=ans.length-2;i>=0;i--){
            ans[i]+=ans[i+1];
            temp=Math.max(temp,ans[i]*(i+min));
        }
        return temp;
    }
    public int largestSubmatrix(int[][] matrix) {
        int[] sum=new int[matrix[0].length];
        int ans=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==1) sum[j]+=1;
                else sum[j]=0;
            }
            ans=Math.max(ans,help(sum));
        }
        return ans;
    }
}


// runtime - 7ms
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int width = matrix[0].length;
        int result = 0;
        int[] counts = new int[width];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = i;
        }
        int[] roll = new int[width];
        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < width; j++) {
                int pos = counts[j];
                if (row[pos] == 0) {
                    roll[pos] = 0;
                } else {
                    ++roll[pos];
                }
            }
            int p = 0;
            for (int p1 = 0; p1 < counts.length; p1++) {
                int pos = counts[p1];
                if (roll[pos] > 0) {
                    counts[p1] = counts[p];
                    counts[p++] = pos;
                }
            }
           // System.out.println(Arrays.toString(roll) + Arrays.toString(counts));
            for (int j = 0; j < counts.length; j++) {
                int pos = counts[j];
                int c = roll[pos];
                if (c == 0) break;
                if ((j + 1) * c > result) result = (j+1) * c;
                
            }
        }

        return result;
    }
}


// runtime - 8ms
class Solution {

    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] height = new int[n];
        int result = 0;

        int[] sorted = null;
        for (int i = 0; i < m; i++) {

            boolean f = false;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    height[j]++;
                } else if (height[j] != 0) {
                    height[j] = 0;
                    f = true;
                }
            }
            
            if (m < n * 2) {
                int[] cnt = new int[m + 1];
                int mh = 0;
                for (int h : height) {
                    cnt[h]++;
                    if (h > mh) {
                        mh = h;
                    }
                }
                int width = 0;
                for (int h = mh; h > 0; h--) {
                    if (cnt[h] > 0) {
                        width += cnt[h];
                        result = Math.max(result, h * width);
                    }
                }
            } else {
                if (f) {
                    Arrays.sort(sorted);
                    for (int j = n - 1; j >= 0; j--) {
                        int h = sorted[j];
                        int width = n - j;
                        result = Math.max(result, h * width);
                    }
                }
                sorted = height.clone();
            }
        }

        if (sorted != null) {
            Arrays.sort(sorted);
            for (int j = n - 1; j >= 0; j--) {
                int h = sorted[j];
                int width = n - j;
                result = Math.max(result, h * width);
            }
        }

        return result;
    }
}


