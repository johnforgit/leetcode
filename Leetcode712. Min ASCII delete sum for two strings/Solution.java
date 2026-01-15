class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(s1.charAt(i) == s2.charAt(j)) // if both characters match
                    dp[i+1][j+1] = dp[i][j] + s1.charAt(i);
                else // skip the characters from each string
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
            }
        }

        int total = 0;
        for(char c:s1.toCharArray())
            total += c;
        for(char c:s2.toCharArray())
            total += c;

        // keep these substrings
        return total - 2*dp[m][n];
    }
}

// runtime - 7 ms
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        char[] a = s1.toCharArray(); char[] b = s2.toCharArray();
        int n = a.length; int m = b.length;
        int[][] dp = new int[n + 1][m + 1];
        int total = 0;
        for(char c: a) total += (int) c;
        for(char c: b) total += (int) c;
        for(int i =  n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(a[i] == b[j]){
                    dp[i][j] += dp[i + 1][j + 1] + (int) a[i];
                } else {
                    dp[i][j] = Math.max(dp[i +1][j], dp[i][j + 1]);
                }

            }
        }
        return -dp[0][0] * 2 + total;
    }
}

// runtime - 8 ms
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        char[] ct1 = s1.toCharArray();
        char[] ct2 = s2.toCharArray();
        int[][] dp = new int[ct1.length + 1][ct2.length + 1];

        for (int i = 1; i <= ct1.length; i++) dp[i][0] = (int) ct1[i - 1] + dp[i-1][0];
        for (int j = 1; j <= ct2.length; j++) dp[0][j] = (int) ct2[j - 1] + dp[0][j-1];

        for (int i = 1; i <= ct1.length; i++) {
            for (int j = 1; j <= ct2.length; j++) {
                if (ct1[i - 1] == ct2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            ((int) ct1[i - 1]) + dp[i - 1][j],
                            ((int) ct2[j - 1]) + dp[i][j - 1]
                    );
                }
            }
        }
        return dp[ct1.length][ct2.length];
    }
}

// runtime - 9 ms
class Solution {
    // s1 = abv
    // s2 = bbd
    // min(f(bv, bbd), f(abv, bd))
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        int[] memo = new int[n2 + 1];
        for (int i = 0; i < s2.length(); i++) memo[i + 1] = memo[i] + s2Chars[i];

        for (int i = 0; i < s1.length(); i++) {
            int prev = memo[0];
            memo[0] += s1Chars[i];
            for (int j = 0; j < s2.length(); j++) {// memo[i + 1][j + 1]
                int tmp = memo[j + 1];
                if (s1Chars[i] == s2Chars[j]) {
                    memo[j + 1] = prev;
                } else {
                    memo[j + 1] = Math.min(s1Chars[i] + memo[j + 1], memo[j] + s2Chars[j]);
                }
                prev = tmp;
            }
        }

        return memo[n2];
    }
}
