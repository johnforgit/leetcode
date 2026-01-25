import java.util.*;

class Solution {
    public long maxProduct(int[] nums) {
        long ans = 0;
        Arrays.sort(nums);
        long scale = 100000; // 10^5

        // iterating forwards
        for (int i = 0; i < nums.length - 1; i++) {
            long p = (long) nums[i] * nums[nums.length - 1];
            ans = Math.max(ans, p * scale);
            ans = Math.max(ans, p * (-scale));
        }

        // iterating backwards
        for (int i = 1; i < nums.length; i++) {
            long p = (long) nums[i] * nums[0];
            ans = Math.max(ans, p * scale);
            ans = Math.max(ans, p * (-scale));
        }

        return ans;
    }
}


// runtime - 2ms
class Solution {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long pmx1 = Integer.MIN_VALUE;
        long pmx2 = Integer.MIN_VALUE;
        long pmi1 = Integer.MAX_VALUE;
        long pmi2 = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] != 0) {
                cnt++;
            }
            if(pmx1 <= nums[i]) {
                pmx2 = pmx1;
                pmx1 = nums[i];
            } else if(pmx2 <= nums[i]) {
                pmx2 = nums[i];
            }

            if(pmi1 >= nums[i]) {
                pmi2 = pmi1;
                pmi1 = nums[i];
            } else if(pmi2 >= nums[i]) {
                pmi2 = nums[i];
            }
        }
        if(cnt < 2) {
            return 0;
        }

        long l1 = 100000;
        long l2 = -100000;
        
        ans = Math.max(ans, pmx1*pmx2*l1);
        ans = Math.max(ans, pmx1*pmi1*l2);
        ans = Math.max(ans, pmi1*pmi2*l1);
        return ans;
    }
}

// runtime - 3ms
class Solution {
    public long maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int x : nums) {
            // two largest
            if (x > max1) {
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max2 = x;
            }

            // two smallest
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }
        }

        long P = 100000L;

        long a = 1L * max1 * max2 * P;      // + + +
        long b = 1L * min1 * min2 * P;      // - - +
        long c = 1L * max1 * min1 * (-P);   // + - -

        return Math.max(a, Math.max(b, c));
    }
}

// runtime - 4ms
class Solution {
    public long maxProduct(int[] nums) {
    long a = 0, b = 0;
    for (int x : nums) {
        long ax = Math.abs(x);
        if (ax >= a) { b = a; a = ax; }
        else if (ax > b) { b = ax; }
    }
    return 100000L * a * b;
}
}


// runtime - 5ms
class Solution {
    public long maxProduct(int[] nums) {
        long ans, max1 = -1, max2 = -1;
        for(int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
            if(nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
            }
            else if(nums[i] > max2) max2 = nums[i];
        }
        ans = max1 * max2 * 100000;
        return ans;
    }
}


// runtime - 6ms
class Solution {
    public long maxProduct(int[] arr) {
    Integer max1 = null, max2 = null;
    for (int x : arr) {
    if (max1 == null || Math.abs(x) > Math.abs(max1)) {
        max2 = max1;
        max1 = x;
    } else if (max2 == null || Math.abs(x) > Math.abs(max2)) {
        max2 = x;
    }
}
return (long)Math.abs(max1) * (long)Math.abs(max2) * (long) Math.pow(10, 5);
    }
}


// runtime - 8ms
class Solution {
    public long maxProduct(int[] nums) {
        long m1=0;
        int idx1=-1;
        for(int i=0;i<nums.length;i++){
            // m1=Math.max(m1,Math.abs(nums[i]));
            if(Math.abs(nums[i])>=m1){
                m1=Math.abs(nums[i]);
                idx1=i;
            }
        }
        long m2=0;
        for(int i=0;i<nums.length;i++){
            if(i==idx1)continue;
            m2=Math.max(m2,Math.abs(nums[i]));
        }
        return m1*m2*100000;
    }
}
