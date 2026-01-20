class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        for (int& x : nums) {
            int res = -1;
            int d = 1;
            while ((x & d) != 0) {
                res = x - d;
                d <<= 1;
            }
            x = res;
        }
        return nums;
    }
};

// runtime - 0ms
class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans;
        ans.reserve(nums.size());
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i];
            int v = 1;
            bool found = false;
            while (v < x) {
                if ( ( v | (v+1) ) == x ) {
                    ans.push_back(v);
                    found = true;
                    break;
                }
                ++v;
            }
            if (!found) ans.push_back(-1);
        }
        return ans;
    }
};


// runtime - 1ms
class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> result(nums.size());

        for (char i = 0; i < nums.size(); ++i) {
            if ((nums[i] & 1) == 0) {
                result[i] = -1;
            } else {
                result[i] = 3;
                while ((nums[i] & result[i]) == result[i]) {
                    result[i] = (result[i] << 1) + 1;
                }
                result[i] = nums[i] & (nums[i] - (result[i] >> 2) - 1);
            }
        }

        return result;
    }
};
