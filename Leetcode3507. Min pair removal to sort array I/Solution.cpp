#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        int count = 0;

        while (nums.size() > 1) {
            bool isAscending = true;
            int minSum = INT_MAX;
            int targetIndex = -1;

            for (int i = 0; i < nums.size() - 1; i++) {
                int pairSum = nums[i] + nums[i + 1];

                if (nums[i] > nums[i + 1]) {
                    isAscending = false;
                }

                if (pairSum < minSum) {
                    minSum = pairSum;
                    targetIndex = i;
                }
            }

            if (isAscending) break;

            count++;
            nums[targetIndex] = minSum;
            nums.erase(nums.begin() + targetIndex + 1);
        }

        return count;
    }
};


// runtime - 1ms
class Solution {
    public int minimumPairRemoval(final int[] nums) {
        int n = nums.length, count = 0;

        while(n > 1) {
            int minSum = Integer.MAX_VALUE, minIdx = -1;
            boolean decreasing = true;

            for(int i = 1; i < n; ++i) {
                if(nums[i - 1] + nums[i] < minSum) {
                    minSum = nums[i - 1] + nums[i];
                    minIdx = i - 1;
                }

                if(nums[i - 1] > nums[i])
                    decreasing = false;
            }

            if(decreasing)
                return count;

            nums[minIdx] = minSum;

            for(int i = minIdx + 1; i < n - 1; ++i)
                nums[i] = nums[i + 1];

            count++;
            n--;
        }

        return count;
    }
}
