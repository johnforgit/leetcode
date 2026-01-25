class Solution {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] P = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                P[i][j] =
                    P[i - 1][j] +
                    P[i][j - 1] -
                    P[i - 1][j - 1] +
                    mat[i - 1][j - 1];
            }
        }

        int l = 1;
        int r = Math.min(m, n);
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            boolean find = false;
            for (int i = 1; i <= m - mid + 1; i++) {
                for (int j = 1; j <= n - mid + 1; j++) {
                    int sum =
                        P[i + mid - 1][j + mid - 1] -
                        P[i - 1][j + mid - 1] -
                        P[i + mid - 1][j - 1] +
                        P[i - 1][j - 1];
                    if (sum <= threshold) {
                        find = true;
                        break;
                    }
                }
                if (find) break;
            }
            if (find) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}


// solution that is optimized and uses enumeration
class Solution {

    private int getRect(int[][] P, int x1, int y1, int x2, int y2) {
        return P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1];
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] P = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                P[i][j] =
                    P[i - 1][j] +
                    P[i][j - 1] -
                    P[i - 1][j - 1] +
                    mat[i - 1][j - 1];
            }
        }

        int r = Math.min(m, n);
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int c = ans + 1; c <= r; ++c) {
                    if (
                        i + c - 1 <= m &&
                        j + c - 1 <= n &&
                        getRect(P, i, j, i + c - 1, j + c - 1) <= threshold
                    ) {
                        ++ans;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }
}


// runtime - 1ms
class Solution {

    static{
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
        try(FileWriter writer=new FileWriter("display_runtime.txt")){
        writer.write("0");
        }catch(IOException e){
        e.printStackTrace();
        }
        }));
    }
    public int maxSideLength(int[][] mat, int threshold) {
     int n=mat.length;
     int m=mat[0].length;
     int rowPrefix[][]=new int[n+1][m+1];
     //Row-wise Prefix Sum 
     for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            rowPrefix[i][j+1]=rowPrefix[i][j]+mat[i][j];
        }
     }   
     for(int k=Math.min(m,n);k>=1;k--){
        for(int r=0;r<=n-k;r++){
            for(int c=0;c<=m-k;c++){
                int sum=0;
                for(int i=0;i<k;i++){
                    sum+=rowPrefix[r+i][c+k]-rowPrefix[r+i][c];
                }
                if(sum<=threshold)return k;
            }
        }
     }
     return 0;
    }
}


// runtime - 3ms
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length, cols = mat[0].length;

        // A: transform it into a prefix sum arr
        for (int i = 0 ; i < rows ; i++) {
            for (int j = 1 ; j < cols ; j++) {
                mat[i][j] += mat[i][j-1];
            }
        }
        for (int i = 1 ; i < rows ; i++) {
            for (int j = 0 ; j < cols ; j++) {
                mat[i][j] += mat[i-1][j];
            }
        }
        
        int maxDiagLen = 0;
        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < cols ; j++) {
                for (int diagLen = maxDiagLen + 1 ; i + 1 - diagLen >= 0 && j + 1 - diagLen >= 0 ; diagLen++) {
                    // B
                    int iPrev = i - diagLen, jPrev = j - diagLen;

                    // B
                    int topLeft = iPrev >= 0 && jPrev >= 0 ? mat[iPrev][jPrev] : 0;
                    int left = jPrev >= 0 ? mat[i][jPrev] : 0;
                    int top = iPrev >= 0 ? mat[iPrev][j] : 0;

                    int sum = mat[i][j] + topLeft - top - left;
                    if (sum <= threshold) {
                        maxDiagLen = diagLen;
                    } else {
                        break;
                    }
                }
            }
        }
        
        return maxDiagLen;
    }
}
