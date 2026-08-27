import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        for(int num : nums) {
            seen.add(num);
        }

        int ans = k;
        while(seen.contains(ans)) {
            ans += k;
        }
        return ans;
    }

    /**
     * runtime - 0ms
     * public int missingMultiple(int[] nums, int k) {
     *    for (int multiple = k; ; multiple += k) {
     *         boolean found = false;
     *         for (int num : nums) {
     *              if (num == multiple) {
     *                   found = true;
     *                   break;
     *              }
     *         }
     *         if (!found) {
     *              return multiple;
     *         }
     *    }
     * }
     */
}
