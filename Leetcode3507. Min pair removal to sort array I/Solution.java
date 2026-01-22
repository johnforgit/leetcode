import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        int count = 0;

        while (list.size() > 1) {
            boolean isAscending = true;
            int minSum = Integer.MAX_VALUE;
            int targetIndex = -1;

            for (int i = 0; i < list.size() - 1; i++) {
                int pairSum = list.get(i) + list.get(i + 1);

                if (list.get(i) > list.get(i + 1)) {
                    isAscending = false;
                }

                if (pairSum < minSum) {
                    minSum = pairSum;
                    targetIndex = i;
                }
            }

            if (isAscending) break;

            count++;
            list.set(targetIndex, minSum);
            list.remove(targetIndex + 1);
        }

        return count;
    }
}


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

// manual solution
class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int count = 0;

        while (n > 1) {
            boolean isAscending = true;
            int minSum = Integer.MAX_VALUE;
            int targetIndex = -1;

            for (int i = 0; i < n - 1; i++) {
                int pairSum = nums[i] + nums[i + 1];

                if (nums[i] > nums[i + 1]) isAscending = false;

                if (pairSum < minSum) {
                    minSum = pairSum;
                    targetIndex = i;
                }
            }

            if (isAscending) break;

            count++;
            nums[targetIndex] = minSum;

            // shift left
            for (int i = targetIndex + 1; i < n - 1; i++) {
                nums[i] = nums[i + 1];
            }
            n--;
        }

        return count;
    }
}

