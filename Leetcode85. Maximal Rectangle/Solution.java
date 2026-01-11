class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] mat = new int[rows][cols];

        // convert char to int
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                mat[i][j] = matrix[i][j] - '0';
            }
        }

        // row - wise prefix widths
        for(int i=0; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                if(mat[i][j]==1) {
                    mat[i][j] += mat[i][j-1];
                }
            }
        }

        int res=0;
        // fix each column
        for(int j=0; j<cols; j++) {
            for(int i=0; i<rows; i++) {
                int width = mat[i][j];
                if(width == 0) continue;

                // expand downwards
                int cur_width = width;
                for(int k=i; k<rows && mat[k][j]>0; k++) {
                    cur_width = Math.min(cur_width, mat[k][j]);
                    int height = k-i+1;
                    res = Math.max(res, cur_width*height);
                }

                // expand upwards
                cur_width = width;
                for(int k=i; k>=0 && mat[k][j]>0; k--) {
                    cur_width = Math.min(cur_width, mat[k][j]);
                    int height = i-k+1;
                    res = Math.max(res, cur_width * height);
                }
            }
        }

        return res;
    }
}
