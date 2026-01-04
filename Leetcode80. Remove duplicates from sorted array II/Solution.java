class Solution {
    public int removeDuplicates(int[] nums) {
        int L=0, R=0;
        int n = nums.length;

        while(R<n) {
            int count = 1;
            while((R+1)<n && (nums[R]==nums[R+1])) {
                count++;
                R++;
            }
            for(int i=0; i<Math.min(2, count); i++) {
                nums[L]=nums[R];
                L++;
            }
            R++;
        }

        return L;
    }
}

// runtime - 0 ms
class Solution {
    public int removeDuplicates(int[] nums) {
        int n=nums.length;
        int k = 2;
        for(int i=2; i<n; i++) {
            if(nums[k-2] != nums[i]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
