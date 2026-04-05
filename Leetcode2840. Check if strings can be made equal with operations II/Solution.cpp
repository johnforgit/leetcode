#include <vector>
#include <algorithm>

using namespace std;

// Fast I/O optimization
auto speedup = []() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    long long maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size();
        int n = coins[0].size();
        
        // Using a flat array instead of vector of vectors to keep memory contiguous
        // dp[2][n][3] uses a "rolling row" technique
        long long dp[2][501][3];
        long long INF = 1e16;

        // Initialize only what we need
        for(int j = 0; j < n; ++j) {
            for(int k = 0; k < 3; ++k) dp[0][j][k] = dp[1][j][k] = -INF;
        }

        // Base Case
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) dp[0][0][1] = 0;

        for (int i = 0; i < m; ++i) {
            int curr = i % 2;
            int prev = (i + 1) % 2;
            
            // If i > 0, we need to reset the current row before filling
            if (i > 0) {
                for(int j = 0; j < n; ++j) {
                    dp[curr][j][0] = dp[curr][j][1] = dp[curr][j][2] = -INF;
                }
            }

            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 3; ++k) {
                    if (i == 0 && j == 0) continue;

                    long long bestPrev = -INF;
                    // Check Above (from previous row)
                    if (i > 0) bestPrev = max(bestPrev, dp[prev][j][k]);
                    // Check Left (from current row)
                    if (j > 0) bestPrev = max(bestPrev, dp[curr][j-1][k]);

                    // Option 1: Normal move
                    if (bestPrev != -INF) {
                        dp[curr][j][k] = max(dp[curr][j][k], bestPrev + coins[i][j]);
                    }

                    // Option 2: Neutralize move
                    if (k > 0 && coins[i][j] < 0) {
                        long long bestPrevK = -INF;
                        if (i > 0) bestPrevK = max(bestPrevK, dp[prev][j][k-1]);
                        if (j > 0) bestPrevK = max(bestPrevK, dp[curr][j-1][k-1]);
                        
                        if (bestPrevK != -INF) {
                            dp[curr][j][k] = max(dp[curr][j][k], bestPrevK);
                        }
                    }
                }
            }
        }

        int lastRow = (m - 1) % 2;
        return max({dp[lastRow][n-1][0], dp[lastRow][n-1][1], dp[lastRow][n-1][2]});
    }
};



// runtime - 0ms
class Solution {
public:
    bool checkStrings(string s1, string s2) {
        vector<int> even(26, 0), odd(26, 0);

        for(int i = 0; i < s1.size(); i++){
            if(i % 2 == 0){
                even[s1[i] - 'a']++;
                even[s2[i] - 'a']--;
            } else {
                odd[s1[i] - 'a']++;
                odd[s2[i] - 'a']--;
            }
        }

        for(int i = 0; i < 26; i++){
            if(even[i] != 0 || odd[i] != 0){
                return false;
            }
        }
        return true;
    }
};


// runtime - 1ms
class Solution {
public:
    bool checkStrings(string s1, string s2) {
        int n = s1.size()   ;

        vector<int> even1(26,0),odd1(26,0)  ;
        vector<int> even2(26,0),odd2(26,0) ; 

        for(int i=0;i<n;i++){
            if(i%2 ==0){
                even1[s1[i]-'a']++;
                even2[s2[i]-'a']++ ;
            }
            else{
                odd1[s1[i]- 'a']++ ;
                odd2[s2[i]- 'a']++ ;
            }
        }

        if(even1 == even2 && odd1 == odd2)
        return true ;
        else 
        return false ;
    }
};



// runtime - 2ms
class Solution {
public:
    bool checkStrings(string s1, string s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int counts[52] = {0};

        for (int i = 0; i < s1.length(); i++) {
            if(i%2==0){
                counts[s1[i]-'a']++;
                counts[s2[i]-'a']--;
            }
            else{
                counts[s1[i]-'a'+26]++;
                counts[s2[i]-'a'+26]--;
            }
        }

        for (int i = 0; i < 52; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }

        return true;
    }
};


// runtime - 3ms
class Solution {
public:
    bool checkStrings(string s1, string s2) {
        int n=s1.size();
        vector<int> hashs1odd(201,0);
        vector<int> hashs1even(201,0);
        vector<int> hashs2odd(201,0);
        vector<int> hashs2even(201,0);
        for(int i=0;i<n;i++){
            if(i%2!=0){
                hashs1odd[s1[i]]++;
                hashs2odd[s2[i]]++;
            }
            else{
                hashs1even[s1[i]]++;
                hashs2even[s2[i]]++;
            }
        }
        for(int i=0;i<201;i++){
            if(hashs1odd[i]!=hashs2odd[i] || hashs1even[i]!=hashs2even[i]) return false;
        }
        return true;
    }
};

