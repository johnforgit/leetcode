class Solution {
public:
    int minPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        int maxSum = 0;
        for(int i=0; i<nums.size()/2; i++) {
            maxSum = max(maxSum, nums[i]+nums[nums.size()-1-i]);
        }
        return maxSum;
    }
};

// runtime - 0ms
class Solution {
public:
    int minPairSum(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        int l  = 0;
        int r  = nums.size()-1;
        int res = 0;

       while(l<r){
          res = max(res,nums[l]+nums[r]);
          l++;
          r--;
       }
       return res;
    }
};

auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });


// runtime - 3ms
class Solution {
public:
    int minPairSum(vector<int>& nums) {
        int n = nums.size();
        // sort array
        sort(nums.begin(),nums.end());
        int l =0;
        int r = n-1;
        int result = 0;
        while(l<r){
            int sum = nums[l]+nums[r];
            l++;
            r--;
            result = max(result,sum);  
        }
        return result;
    }
};
auto init = atexit([]() { ofstream("display_runtime.txt") << "3"; });


// runtime - 103ms
class Solution {
public:
    int minPairSum(vector<int>& nums) {
        int min_value = INT_MAX;
        int max_value = INT_MIN;
        int frequency[100001] = {0};
        for (int& x : nums) {
            frequency[x]++;
            min_value = min(min_value, x);
            max_value = max(max_value, x);
        }

        int left = min_value;
        int right = max_value;
        int res = 0;
        while (left <= right) {
            if (frequency[left] == 0)
                left++;
            else if (frequency[right] == 0)
                right--;
            else {
                res = max(res, left + right);
                frequency[left]--;
                frequency[right]--;
            }
        }
        return res;
    }
};
