class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for(int i=0; i<n; i++) {
            res[i] = nums[(((i+nums[i])%n) + n) % n];
        }
        return res;
    }
}


// runtime - 0ms
import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;

class Solution {
  static final int INT_MAX = (int) 1e9, INT_MIN = -INT_MAX;
  static final long LONG_MAX = (long) 1e14, LONG_MIN = -LONG_MAX;
  static final int mod = (int) 1e9 + 7;

  public int[] constructTransformedArray(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
      res[i] = nums[(((i + nums[i]) % n) + n) % n];
    }
    return res;
  }
}
