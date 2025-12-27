import java.util.Arrays;

class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int max_sum=0, min_sum=0;
        int n=nums.length;

        for(int i=0; i<k; i++) {
            min_sum += nums[i];
            max_sum += nums[n-i-1];
        }
        return Math.abs(max_sum - min_sum);
    }
}
