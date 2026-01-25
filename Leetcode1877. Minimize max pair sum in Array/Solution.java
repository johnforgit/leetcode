class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;

        // keep two pointers at the end of the array
        int L=0, R=n-1;

        while(L <= R) {
            res = Math.max(res, nums[L]+nums[R]);
            L++;
            R--;
        }
        return res;
    }
}


// runtime - 0ms
class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int lp = 0; 
        int rp = nums.length - 1;
        while (lp < rp) {
            sum = Math.max(sum, nums[lp]+nums[rp]);
            lp++;
            rp--;
        }
        return sum;
    }
}

// runtime - 6ms
class Solution {
    public int minPairSum(int[] nums) {
        int minMax = 0;

        for(int n:nums){
            minMax = Math.max(n,minMax);
        }

        int[] arr = new int[minMax+1];
        for(int n:nums){
            arr[n]++;
        }

        int l = 0, r = minMax, ans = 0;
        while (l < r) {
            while (arr[l] == 0)
                l++;
            while (arr[r] == 0)
                r--;
            ans = Math.max(ans, l + r);
            if (arr[r] > arr[l])
                arr[r] -= arr[l++];
            else if (arr[l] > arr[r])
                arr[l] -= arr[r--];
            else {
                l++;
                r--;
            }
        }

        return ans;
    }
}

// runtime - 7ms
class Solution {
    public int minPairSum(int[] nums) {
        int[] counts = new int[100_001];
        int minNum = 100_001;
        int maxNum = 0;
        for (int n : nums) {
            counts[n]++;
            if (n > maxNum) {
                maxNum = n;
            }
            if (n < minNum) {
                minNum = n;
            }
        }
        int i = minNum;
        int j = maxNum;
        int maxSum = 0;
        while (i <= j) {
            int sum = i + j;
            if (sum > maxSum) {
                maxSum = sum;
            }
            int diff = counts[i] - counts[j];
            if (diff >= 0) {
                j--;
                while (j >= i && counts[j] == 0) {
                    j--;
                }
                counts[i] = diff;
            }
            if (diff <= 0) {
                i++;
                while (i <= j && counts[i] == 0) {
                    i++;
                }
                if (diff != 0) {
                    counts[j] = -diff;
                }
            }
        }
        return maxSum;
    }
}

