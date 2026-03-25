class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];

        // compute the suffix product
        long suffix = 1;
        for(int i=n-1; i>=0; i--) {
            for(int j=m-1; j>=0; j--) {
                p[i][j] = (int)suffix;
                suffix = (suffix * grid[i][j]) % MOD;
            }
        }

        // compute the prefix product
        long prefix = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                p[i][j] = (int) (((long) p[i][j]*prefix) % MOD);
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }

        return p;
    }
}


// runtime - 6ms
class Solution {
    int MOD = 12345;

    public int[][] constructProductMatrix(int[][] mat) {
        int[] arr = new int[mat.length * mat[0].length];
        int ind = 0;
        for(int i[] : mat)
            for(int j : i)
                arr[ind++] = j % MOD;

        int[] pre = new int[ind];
        int[] suf = new int[ind];
        
        pre[0] = 1;
        suf[ind - 1] = 1;
        for(int i = 1, j = ind - 2; i < ind && j >= 0; i++, j--){
            pre[i] = (pre[i - 1] * arr[i - 1]) % MOD;
            suf[j] = (suf[j + 1] * arr[j + 1]) % MOD;
        }

        for(int i = 0; i < ind; i++)
            mat[i / mat[0].length][i % mat[0].length] = (pre[i] * suf[i]) % MOD;

        return mat;
    }
}


// runtime - 7ms
class Solution {
    int MOD = 12345;

    public int[][] constructProductMatrix(int[][] mat) {
        int[] arr = new int[mat.length * mat[0].length];
        int ind = 0;
        for(int i[] : mat)
            for(int j : i)
                arr[ind++] = j % MOD;

        int[] pre = new int[ind];
        int[] suf = new int[ind];
        
        pre[0] = 1;
        suf[ind - 1] = 1;
        for(int i = 1, j = ind - 2; i < ind && j >= 0; i++, j--){
            pre[i] = (pre[i - 1] * arr[i - 1]) % MOD;
            suf[j] = (suf[j + 1] * arr[j + 1]) % MOD;
        }

        for(int i = 0; i < ind; i++)
            mat[i / mat[0].length][i % mat[0].length] = (pre[i] * suf[i]) % MOD;

        return mat;
    }
}


// runtime - 8ms
class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m*n;
        int mod = 12345;

        int[] prefix = new int[size];
        int[] suffix = new int[size];
        int[] flat = new int[size];

        int index = 0;
        for(int[] row : grid){
            for(int val : row){
                flat[index++] = val % mod;
            }
        }

        prefix[0] = 1;
        for(int i=1;i<size;i++){
            prefix[i] = (prefix[i-1]*flat[i-1]) % mod;
        }

        suffix[size-1] = 1;
        for(int i= size -2;i>=0;i--){
            suffix[i] = (suffix[i+1] * flat[i+1]) % mod;
        }

        for(int i=0;i<size;i++){
            int val = (prefix[i] * suffix[i]) % mod;
            grid[i/n][i%n] = val;
        }

        return grid;

    }
}
