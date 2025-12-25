class Solution {
    public int findMin(int[] nums) {
        int n=nums.length;
        int L=0, R=n-1;

        while(L < R) {
            int mid = (L+R)/2;
            if(nums[mid] > nums[R]) {
                L = mid+1;
            } else {
                R = mid;
            }
        }
        return nums[L];
    }
}
