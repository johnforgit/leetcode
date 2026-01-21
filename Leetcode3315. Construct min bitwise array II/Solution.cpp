class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num == 2) {
                res[i] = -1;
            } else {
                int next = num + 1;
                int lowestSetBit = next & -next;   // (num+1) & (-(num+1))
                int minValue = num - (lowestSetBit / 2);
                res[i] = minValue;
            }
        }

        return res;
    }
};
