# define ll long long int

class Solution {
public:
    std::vector<ll> dp;
    int climbStairs(int n) {
        /*
        dp[0]=1;
        for(int i=1; i<=n;i++) {
            for(int j=1; j<=2; j++) {
                if(i-j < 0)
                    break;
                dp[i] = dp[i] + dp[i-j];
            }
        }
        return dp[n];
        */
        int one=1, two=1;
        for(int i=0; i<n-1; i++) {
            int temp = one;
            one = one + two;
            two = temp;
        }
        return one;
    }
};
