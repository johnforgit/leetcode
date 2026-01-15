class Solution {
public:
    int minimumDeleteSum(string s1, string s2) {
        int n = s1.size();
        int m = s2.size();

        vector<vector<int>> dp(n+1, vector<int>(m+1, 0));

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(s1[i]==s2[j]) {
                    dp[i+1][j+1] = dp[i][j] + s1[i];
                } else {
                    dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }

        int total = 0;
        for(char c:s1) total += c;
        for(char c:s2) total += c;

        return total - 2*dp[n][m];
    }
};

// runtime - 15 ms
class Solution {
public:
    int find_max(vector<vector<int>> &dp,int i,int j,int n,int m,string &s1, string &s2)
    {
        if(i>=n || j>=m)
        return 0;
        if(dp[i][j] == 1+dp[i+1][j+1])
        return s1[i]+find_max(dp,i+1,j+1,n,m,s1,s2);
        return max(find_max(dp,i+1,j,n,m,s1,s2),find_max(dp,i,j+1,n,m,s1,s2));
    }
    int minimumDeleteSum(string s1, string s2) {
        int n = s1.size();
        int m = s2.size();
        vector<vector<int>> dp(n+1,vector<int>(m+1,0));
        for(int i=n-1;i>=0;i--)
        {
            for(int j=m-1;j>=0;j--)
            {
                if(s1[i] == s2[j])
                {
                    dp[i][j] = s1[i]+dp[i+1][j+1];
                }
                else{
                    dp[i][j] = max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        int total_ascii = 0;
        for(int i=0;i<n;i++)
        total_ascii += s1[i];
        for(int i=0;i<m;i++)
        total_ascii += s2[i];
        cout<<total_ascii;
        return total_ascii-2*dp[0][0];
    }
};

// runtime - 1 ms
class Solution {
public:
    int minimumDeleteSum(string s1, string s2) {
        if (s2.length() > s1.length()) {
            swap(s1, s2);
        }

        int n = s1.length();
        int m = s2.length();
        vector<int> dp(m + 1, 0);

        for (int j = 1; j <= m; ++j) {
            dp[j] = dp[j - 1] + s2[j - 1];
        }

        for (int i = 1; i <= n; ++i) {
            int prev_diag = dp[0];
            
            dp[0] += s1[i - 1];

            for (int j = 1; j <= m; ++j) {
                int temp = dp[j];

                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = prev_diag;
                } else {
                    dp[j] = min(
                        dp[j] + s1[i - 1],
                        dp[j - 1] + s2[j - 1]
                    );
                }
                prev_diag = temp;
            }
        }

        return dp[m];
    }
};
