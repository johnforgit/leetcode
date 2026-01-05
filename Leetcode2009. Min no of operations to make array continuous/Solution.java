import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Remove duplicates
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        // Convert to list and sort
        List<Integer> A = new ArrayList<>(set);
        Collections.sort(A);

        int i = 0;
        int m = A.size();

        for (int j = 0; j < m; j++) {
            if (A.get(i) < A.get(j) - n + 1) {
                i++;
            }
        }

        return n - (m == 0 ? 0 : (m - i));
    }
}

// runtime - 37 ms
class Solution {
    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        int n = 1, len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i - 1]) nums[n++] = nums[i];
        }

        int left = 0, right = 0;
        while (right < n) {
            if (nums[right] - nums[left] >= len) left++;
            right++; 
        }

        return left + (len - n);
    }
}
