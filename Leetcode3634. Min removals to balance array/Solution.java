class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int mx = 0, l = 0;

        for (int r = 0; r < n; r++) {
            while (l < r && (long) nums[l] * k < nums[r]) {
                l++;
            }
            mx = Math.max(mx, r - l + 1);
        }

        return n - mx;
    }
}


// runtime - 15ms
class Solution {
    public int minRemoval(int[] nums, int k) {
        nums = sort(nums);
        int i=0, j=0, maxLength = 0;

        while(j < nums.length) {
            if(1.0*nums[j]/nums[i] <= k) {
                maxLength = Math.max(maxLength, j-i+1);
                j++;
            } else {
                i++;
            }
        }

        return nums.length - maxLength;
    }

    int[] sort(int[] nums) {
        int mask = 1023;
        int bits = 10;
        for(int i=0; i<32/bits; i++) {
            nums = countSort(nums, mask, i*bits, mask+1);
        }
        return nums;
    }

    int[] countSort(int[] nums, int mask, int shift, int buckets) {
        int[] count = new int[buckets];
        
        for(int n : nums) {
            count[(n >> shift) & mask]++;
        }

        int start = 0;
        for(int i=0; i<buckets; i++) {
            int temp = count[i];
            count[i] = start;
            start += temp;
        }

        int[] output = new int[nums.length];
        for(int n : nums) {
            int i = (n >> shift) & mask;
            output[count[i]] = n;
            count[i]++;
        }

        return output;
    }
}


// runtime - 16ms
class Solution {
    public int minRemoval(int[] nums, int k) {
        nums = sort(nums);
        int i=0, j=0, maxLength = 0;

        while(j < nums.length) {
            if(1.0*nums[j]/nums[i] <= k) {
                maxLength = Math.max(maxLength, j-i+1);
                j++;
            } else {
                i++;
            }
        }

        return nums.length - maxLength;
    }

    int[] sort(int[] nums) {
        int bits = 10;
        int buckets = 1 << bits;
        for(int i=0; i<32/bits; i++) {
            nums = countSort(nums, buckets, i*bits);
        }
        return nums;
    }

    int[] countSort(int[] nums, int buckets, int shift) {
        int[] count = new int[buckets];
        int mask = buckets-1;

        for(int n : nums) {
            count[(n >> shift) & mask]++;
        }

        int start = 0;
        for(int i=0; i<buckets; i++) {
            int temp = count[i];
            count[i] = start;
            start += temp;
        }

        int[] output = new int[nums.length];
        for(int n : nums) {
            int i = (n >> shift) & mask;
            output[count[i]] = n;
            count[i]++;
        }

        return output;
    }
}


// runtime - 17ms
class Solution {
    public int minRemoval(int[] nums, int k) {
        nums = sort(nums);
        int i=0, j=0, maxLength = 0;

        while(j < nums.length) {
            if(1.0*nums[j]/nums[i] <= k) {
                maxLength = Math.max(maxLength, j-i+1);
                j++;
            } else {
                i++;
            }
        }

        return nums.length - maxLength;
    }

    int[] sort(int[] nums) {
        int bits = 10;
        int buckets = 1 << bits;
        for(int i=0; i<32/bits; i++) {
            nums = countSort(nums, buckets, i*bits);
        }
        return nums;
    }

    int[] countSort(int[] nums, int buckets, int shift) {
        int[] count = new int[buckets];
        int mask = buckets-1;

        for(int n : nums) {
            count[(n >> shift) & mask]++;
        }

        int start = 0;
        for(int i=0; i<buckets; i++) {
            int temp = count[i];
            count[i] = start;
            start += temp;
        }

        int[] output = new int[nums.length];
        for(int n : nums) {
            int i = (n >> shift) & mask;
            output[count[i]] = n;
            count[i]++;
        }

        return output;
    }
}


// runtime - 18ms
class Solution {

    public int minRemoval(int[] nums, int k) {

        int result = 0;

        int min = nums[0];
        int max = nums[0];

        boolean isSorted = true;
        boolean kIsEquals = true;

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            min = getMin(min, num);
            max = getMax(max, num);
            if (num < nums[i - 1]) isSorted = false;
            int dif = abs(num - nums[i - 1]);
            if (dif != k) kIsEquals = false;
        }

        if (max < min * k) return 0;

        if (k == 1 || kIsEquals) return minRemovalK(nums, k, isSorted, min, max);

        while (min * k < max) {

            long different = (long) min * k - max;
            if (0 <= different) break;

            int minRemoveCount = 0;
            int maxRemoveCount = 0;

            int min2 = min * k;
            float max2 = (float) max / k;
            for (int num : nums) {
                if (num == -1) continue;
                if (num < max2) minRemoveCount++;
                if (min2 < num) maxRemoveCount++;
            }

            if (result == 0 && 1000 < nums.length && minRemoveCount == maxRemoveCount) {
                result = minRemoveCount;
                break;
            }

            if (minRemoveCount < maxRemoveCount) {
                int newMin = Integer.MAX_VALUE;
                for (int i = 0; i < nums.length; i++) {
                    int num = nums[i];
                    if (num == -1) continue;
                    if (num == min) {
                        nums[i] = -1;
                        result++;
                    } else newMin = getMin(newMin, num);
                }
                min = newMin;
            } else {
                int newMax = 0;
                for (int i = 0; i < nums.length; i++) {
                    int num = nums[i];
                    if (num == -1) continue;
                    if (num == max) {
                        nums[i] = -1;
                        result++;
                    } else newMax = getMax(newMax, num);
                }
                max = newMax;
            }

        }

        return result;
    }

    private int minRemovalK(int[] nums, int k, boolean isSorted, int min, int max) {

        if (!isSorted) Arrays.sort(nums);

        int pValue = -1;
        int pCount = 0;

        int cValue = -1;
        int cCount = 0;

        int minRemoveCount = 0;
        int maxRemoveCount = 0;

        int min2 = min * k;
        float max2 = (float) max / k;

        for (int num : nums) {
            if (num == pValue) pCount++;
            if (num == cValue) cCount++;
            else {
                cValue = num;
                cCount = 1;
            }
            if (pCount < cCount) {
                pValue = cValue;
                pCount = cCount;
            }
            if (num < max2) minRemoveCount++;
            if (min2 < num) maxRemoveCount++;
        }

        int result = getMin(minRemoveCount, maxRemoveCount);
        return getMin(result, nums.length - pCount);
    }

    private int getMin(int a, int b) {
        return (a < b ? a : b);
    }

    private int getMax(int a, int b) {
        return (a < b ? b : a);
    }

    private int abs(int k) {
        return (k < 0 ? -k : k);
    }

}
