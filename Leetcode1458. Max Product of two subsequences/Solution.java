class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        if (m > n) {
            return maxDotProduct(nums2, nums1);
        }
        
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1000000000); 
        
        for (int i = 1; i <= n; i++) {
            int prev_diag = -1000000000;
            
            for (int j = 1; j <= m; j++) {
                int curr_product = nums1[i-1] * nums2[j-1];
                int temp = dp[j];
                
                int option1 = curr_product;
                int option2 = curr_product + prev_diag;
                int option3 = dp[j];
                int option4 = dp[j-1];
                
                dp[j] = Math.max(Math.max(option1, option2), Math.max(option3, option4));
                
                prev_diag = temp;
            }
        }
        
        return dp[m];
    }
}

// runtime - 7 ms
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int[][] DP = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= m; j++){
                DP[i][j] = Integer.MIN_VALUE / 2;
            }
        }
        
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                int prod = nums1[i - 1] * nums2[j - 1];

                DP[i][j] = Math.max(DP[i - 1][j], Math.max(DP[i][j - 1], Math.max(prod, prod + DP[i - 1][j - 1])));
            }
        }    

        return DP[n][m];
    }
}

// runtime - 8 ms
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length; 
        int m = nums2.length;
        int dp[][] = new int[n+1][m+1];
        for(int i=0; i<n+1; i++){
            for(int j=0; j<m+1; j++){
                if(i==0 || j == 0){
                    dp[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        for(int i=1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                int product =  nums1[i-1] * nums2[j-1];
                int takeTwo = Math.max(product, dp[i-1][j-1] == Integer.MIN_VALUE ? product : product + dp[i-1][j-1]);

                int maxSide = Math.max(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.max(takeTwo, maxSide);
            }
        }
         return dp[n][m];
    }
}
