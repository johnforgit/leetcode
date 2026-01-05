#include <vector>
#include <unordered_set>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();

        // Remove duplicates
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> A(s.begin(), s.end());
        sort(A.begin(), A.end());

        int i = 0;
        int m = A.size();

        for (int j = 0; j < m; j++) {
            if (A[i] < A[j] - n + 1) {
                i++;
            }
        }

        return n - (m - i);
    }
};

// runtime - 29 ms
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        int ans = n;

        sort(nums.begin(), nums.end());
        nums.erase(unique(nums.begin(), nums.end()), nums.end());
        int m = nums.size();

        int r = 0;
        for(int l=0;l<m;l++){
            while(r<m && nums[r] <= nums[l]+n-1){
                r++;
            }
            int count = r - l; 
            ans = min(ans, n-count);
        }

        return ans;
    }
};

// runtime - 36 ms
class Solution {
public:
    int minOperations(vector<int>& nums) {
        const int n = nums.size();
        int jumps = 0;
        sort(nums.begin() , nums.end());
        nums.erase(unique(nums.begin() , nums.end()) , nums.end());
        const int m = nums.size();
        for(int i = 0; i < m; ++i){
            if(nums[i] - nums[jumps] >= n) ++jumps;
        }
        return n - m + jumps;
    }
};
