class Solution {

    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1000000007;
        int[] cnt = new int[30];

        // Count the number of 1 bits at each position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }

        long res = 0;
        for (int i = 0; i < k; i++) {
            long cur = 0;
            // Construct the current number from the most significant bit to the least significant bit
            for (int j = 29; j >= 0; j--) {
                if (cnt[j] > 0) {
                    cur |= 1L << j;
                    cnt[j]--;
                }
            }
            res = (res + cur * cur) % MOD;
        }

        return (int) res;
    }
}



/**
 * 
 * --------------------------------------------------------------------------------------
 * runtime - 19ms
 * class Solution {
 *      // Something to do with moving bits around in each position, 
 *      since (0 AND 1, 1 OR 0) = (0, 1)
 *      // and (0 OR 1, 1 AND 0) = (1, 0). AND and OR have no effect on bits that are the same.
 *      // We want to move all the bits to a single number where we can, 
 *      since a larger square is better than a larger summand.
 *      private static final int MOD = 1000000007;
 *      
 *      public int maxSum(List<Integer> nums, int k) {
 *              int[] bitCount = new int[31];
 *              for (int num : nums) {
 *                      for (int i = 30; num > 0; i--) {
 *                              bitCount[i] += num & 1;
 *                              num >>= 1;
 *                      }
 *              }
 *              int selected = 0;
 *              long result = 0;
 *              int element, elementCount, remaining;
 *              while (selected < k) {
 *                      element = 0;
 *                      elementCount = Integer.MAX_VALUE;
 *                      for (int i = 0; i < bitCount.length; i++) {
 *                              element <<= 1;
 *                              remaining = bitCount[i] - selected;
 *                              if (remaining > 0) {
 *                                      element |= 1;
 *                                      elementCount = Math.min(elementCount, remaining);
 *                              }
 *                      }
 *                      elementCount = Math.min(elementCount, k - selected);
 *                      result = (result + ((long) element * element % MOD) * elementCount) % MOD;
 *                      selected += elementCount;
 *              }
 *              return (int) result;
 *      }
 * }
 * --------------------------------------------------------------------------------------
 * 
 * 
 * --------------------------------------------------------------------------------------
 * runtime - 20ms
 * class Solution {
 *      private static final int MOD = (int) 1e9 + 7;
 *      public int maxSum(List<Integer> nums, int k) {
 *      int n = nums.size();
 *      int[] bits = new int[32];
 *      int res = 0;
 *      for (int num : nums) {
 *              for (int i = 31; i >= 0; i--) {
 *                      int bit = (num >> i) & 1;
 *                      bits[i] += bit;
 *              }
 *      }

    while (k > 0) {
      int min = Integer.MAX_VALUE;
      int number = 0;

      for (int bit : bits) {
        if (bit != 0) {
          min = Math.min(bit, min);
        }
      }

      for (int i = 0; i < 32; i++) {
        if (bits[i] != 0) {
          number = number | (1 << i);
          bits[i] -= min;
        }
      }

      if (number == 0) {
        break;
      }

      res = (res + (int) ((((long) number * number) % MOD * Math.min(min, k)) % MOD)) % MOD;
      k -= min;
    }

    return res;
  }
}
--------------------------------------------------------------------------------------
 */