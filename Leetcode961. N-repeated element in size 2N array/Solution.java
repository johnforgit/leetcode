import java.util.HashSet;

class Solution {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();

        for (int x : nums) {
            if (seen.contains(x)) {
                return x;   // first duplicate is the answer
            }
            seen.add(x);
        }
        return -1; // never reached due to problem guarantee

        /**
        Set<Integer> s = new HashSet<>();
        for(int i:nums) {
            if(!s.add(i))
              return i;
        }
        return nums[nums.length-1];
         */
    }
}
