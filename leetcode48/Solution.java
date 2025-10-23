class Solution {
    public void rotate(int[][] matrix) {
        
        // first transform the matrix to its transpose
        int rows=matrix.length;
        int cols=matrix.length;
        for(int i = 0;i < rows;i++) {
            for(int j = i+1;j < cols;j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }

        // now reverse each row in the matrix
        for(int i = 0;i < rows;i++) {
            int left=0;
            int right=rows-1;
            while(left<right) {
                int temp=matrix[i][left];
                matrix[i][left]=matrix[i][right];
                matrix[i][right]=temp;
                left++;
                right--;
            }
        }
    }
}

public void rotate(int[][] mat) {
        int n = mat.length;
        for(int i = 0;i < (n+1)/2;i++) {
            for(int j = 0;j < n/2;j++) {
                int temp = mat[n-1-j][i];
                mat[n-1-j][i] = mat[n-1-i][n-1-j];
                mat[n-1-i][n-1-j] = mat[j][n-1-i];
                mat[j][n-1-i] = mat[i][j];
                mat[i][j] = temp;
            }
        }
    }
