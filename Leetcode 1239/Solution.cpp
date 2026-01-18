#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxLength(vector<string>& arr) {
        vector<int> dp;
        dp.push_back(0);
        int res = 0;

        for (const string& s : arr) {
            int mask = 0;
            bool dup = false;

            for (char c : s) {
                int bit = 1 << (c - 'a');
                if (mask & bit) {
                    dup = true;
                    break;
                }
                mask |= bit;
            }

            if (dup) continue;

            int size = dp.size();
            for (int i = size - 1; i >= 0; i--) {
                if (dp[i] & mask) continue;
                int newMask = dp[i] | mask;
                dp.push_back(newMask);
                res = max(res, __builtin_popcount(newMask));
            }
        }
        return res;
    }
};
