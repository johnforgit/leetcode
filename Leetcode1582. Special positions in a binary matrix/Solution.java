// solution by precomputing the no of 1s

class Solution {
    public int numSpecial(int[][] mat) {
        int res = 0;
        int rows=mat.length, cols = mat[0].length;
        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];

        // pre compute the no of 1s
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(mat[i][j]==1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(mat[i][j] == 1) {
                    if(rowCount[i] == 1 && colCount[j] == 1) 
                        res++;
                }
            }
        }

        return res;
    }
}
