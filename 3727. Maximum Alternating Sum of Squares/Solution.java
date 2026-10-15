class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        long ans = 0;
        for(int i=0; i<n/2; i++) {
            ans -= nums[i];
        }
        for(int i=n/2; i<n; i++) {
            ans += nums[i];
        }
        return ans;
    }
}

/**
 * 
 * ==============================================
 *              Complexity Analysis
 * ==============================================
 * Let n be the length of the array nums.
 * 
 * Time complexity: O(nlogn).
 * Squaring each element takes O(n) time, sorting the array takes O(nlogn) time, and summing the elements takes O(n) time. Thus, the overall time complexity is dominated by sorting: O(nlogn).
 * Note: If quickselect or std::nth_element is used to partition the elements around the median instead of fully sorting, an average time complexity of O(n) can be achieved.
 * 
 * Space complexity: O(n) or O(logn).
 * The space complexity depends on the programming language and sorting implementation:
 * In Python, nums.sort() uses Timsort, which takes O(n) auxiliary space. In addition, slicing nums creates new lists consuming O(n) extra space.
 * In C++, Java, and Go, in-place sorting algorithms typically require O(logn) auxiliary stack space for recursion.
 */


/**
 * runtime - 7ms
 * class Solution {
 *      public long maxAlternatingSum(int[] nums) {
 *      int length = nums.length;
 *      int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
 *      for (int i = 0; i < length; ++i) {
 *          int value = nums[i];
 *          if (value < 0) nums[i] = value = -value;
 *          if (value < min) min = value;
 *          if (value > max) max = value;
 *      }
 *      int[] counts = new int[max + 1];
 *      for (int i = 0; i < length; ++i) {
 *              ++counts[nums[i]];
 *      }
 *      int negatives = length >> 1;
 *      long result = 0;
 *      for (int num = min; num <= max; ++num) {
 *          int count = counts[num];
 *          if (count == 0) continue;
 *          long sq = num * num;
            if (count <= negatives && negatives != 0) {
                result -= sq * count;
                negatives -= count;
            } else if (negatives == 0) {
                result += sq * count;
            } else {
                result -= sq * negatives;
                result += sq * (count - negatives);
                negatives = 0;
            }
        }
        return result;
    }
}

runtime - 9ms
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }
        int idx = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[idx++] = i + min;
            }
        }
        long res = 0;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            res += (long) nums[r] * nums[r] - (long) nums[l] * nums[l];
            l++;
            r--;
        }
        if (l == r)
            res += (long) nums[l] * nums[l];
        return res;
    }
}


runtime - 11ms
class Solution {
    public long maxAlternatingSum(int[] arr) {
        int n = arr.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = arr[i] * arr[i] * 1L;
        }        
        quickSelect(nums, 0, n - 1, n / 2);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i >= n / 2 ? nums[i] : -nums[i];
        }
        return sum;
    }
    
    private void quickSelect(long[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        
        int pivotIndex = start + new Random().nextInt(end - start + 1);
        int[] pivot = partition(nums, start, end, pivotIndex);
        if (pivot[0] > k) {
            quickSelect(nums, start, pivot[0] - 1, k);
        } else if (pivot[1] < k) {
            quickSelect(nums, pivot[1] + 1, end, k);
        }
    }
    
    private int[] partition(long[] nums, int start, int end, int pivotIndex) {
        long pivot = nums[pivotIndex]; 
        int i = start, idx = start, j = end;
        while (idx <= j) {
            if (nums[idx] < pivot) {
                swap(nums, i++, idx++);
            }
            else if (nums[idx] > pivot) {
                swap(nums, j--, idx);
            }
            else {
                idx++;
            }
        }
        return new int[]{i, j};
    }
    
    private void swap(long[] nums, int i, int j) {
        long temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


runtime - 12ms
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int [] freq = new int[40_001];

        int n = nums.length;
        int m =nums[0];

        for(int i=0;i<n;i++){
            int t= Math.abs(nums[i]);
            freq[t]++;
            m = Math.max(m,t);
        }
        int k =0;
        for(int i =0;i<=m;i++){
            while(freq[i]!=0){
                nums[k++] = i;
                freq[i]--;
            }
        }

        int l =0;
        int r = n/2;

        long res = 0;
        if((n&1)==1){
            res = (long)nums[r]*nums[r];
            r++;
        }

        while(r<n){
            res += (long)nums[r]*nums[r] - (long)nums[l]*nums[l];
            r++;
            l++;
        }
        return res;
    }
}
 */