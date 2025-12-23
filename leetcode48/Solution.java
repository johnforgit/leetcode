class Solution {
    public void rotate(int[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        
        // transpose the matrix
        for(int i=0; i<rows; i++) {
            for(int j=i+1; j<cols; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]=temp;
            }
        }

        // reverse each rows in the matrix
        for(int i=0; i<rows; i++) {
            int L=0, R=rows-1;
            while(L<R) {
                int temp=matrix[i][L];
                matrix[i][L]=matrix[i][R];
                matrix[i][R]=temp;
                L++; R--;
            }
        }

        /**
        another way to reverse the matrix
        for(int i=0; i<n; i++) {
            for(int j=0; j<n/2; j++) {
                int temp = matrix[i][j]
                matrix[i][j] = matrix[i][n-j-1]
                matrix[i][n-j-1] = matrix[i][j]
            }
        }
         */
    }

  public void rotate(int[][] matrix) {
        int n=matrix.length;
        // flip the matrix top to bottom
        for(int i=0, j=n-1; i<j; j--,i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }

        // find the transpose of the matrix
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
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
}
