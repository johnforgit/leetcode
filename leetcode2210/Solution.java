class Solution {
    /*
    public int countHillValley(int[] nums) {
        int res = 0; // number of peaks and valleys
        int n = nums.length;
        for (int i = 1; i < n - 1; ++i) {
            if (nums[i] == nums[i - 1]) {
                // deduplication
                continue;
            }
            int left = 0; // left side possibly unequal neighboring corresponding state
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] > nums[i]) {
                    left = 1;
                    break;
                } else if (nums[j] < nums[i]) {
                    left = -1;
                    break;
                }
            }
            int right = 0; // right side possibly unequal neighboring corresponding state
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] > nums[i]) {
                    right = 1;
                    break;
                } else if (nums[j] < nums[i]) {
                    right = -1;
                    break;
                }
            }
            if (left == right && left != 0) {
                // at this time, index i is part of a peak or valley.
                ++res;
            }
        }
        return res;
    }
    */

    public int countHillValley(int[] nums) {
        int left = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if(left < nums[i]) {
                if(nums[i+1] < nums[i]) {
                    res++;
                    left = nums[i];
                } else if (nums[i+1] == nums[i]) {
                    //nothing
                } else {
                    left = nums[i];

                }
            } else if (left == nums[i]) {

                // do nothing
            } else {
                if(nums[i+1] > nums[i]) {
                    res++;
                    left = nums[i];
                } else if (nums[i+1] !=nums[i]) {
                    left = nums[i];
                }
            }
        }
        return res;
    }
}
