class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        int L=0, R=nums.size()-1;
        int leftSize = 0;
        while(L <= R) {
            int m = L + ((R-L)/2);
            if(
                (((m-1) < 0) || (nums[m-1] != nums[m])) 
                && 
                ((m+1 == nums.size()) || (nums[m]!=nums[m+1]))
            ) {
                return nums[m];
            }

            if(nums[m-1] == nums[m]) {
                leftSize = m-1;
            } else {
                leftSize = m;
            }
            if(leftSize%2) {
                R = m-1;
            } else {
                L = m+1;
            }
        }
        return 0;
    }
};
