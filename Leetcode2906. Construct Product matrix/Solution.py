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
