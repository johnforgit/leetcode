// runtime - 0ms

// using bit masking
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        int m = 0b1111;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) m &= 0b1110;
                if (mat[i][j] != target[j][n - 1 - i]) m &= 0b1101;
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) m &= 0b1011;
                if (mat[i][j] != target[n - 1 - j][i]) m &= 0b0111;

                if (m == 0)
                    return false;
            }
        }

        return m != 0;
    }
}

// using matrix rotation
import java.util.*;

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        int m = mat[0].length;

        for (int k = 0; k < 4; k++) {
            if (areEqual(mat, target)) return true;

            // Transpose matrix
            for (int i = 0; i < n; i++) {
                for (int j = i; j < m; j++) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[j][i];
                    mat[j][i] = temp;
                }
            }

            // Reverse rows (equivalent to reverse(mat.begin(), mat.end()))
            for (int i = 0; i < n / 2; i++) {
                int[] temp = mat[i];
                mat[i] = mat[n - 1 - i];
                mat[n - 1 - i] = temp;
            }
        }

        return false;
    }

    private boolean areEqual(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
