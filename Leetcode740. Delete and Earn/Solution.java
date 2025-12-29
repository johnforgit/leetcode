class Solution {
    public int deleteAndEarn(int[] nums) {
        int earn1=0, earn2=0;
		int n=nums.length;
		
		// count the frequency of each integer in nums
		Map<Integer, Integer>count = new HashMap<>();
		for(int num:nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}
		
		// Get unique numbers and sort them
        int[] uniqueNums = count.keySet().stream()
                                .mapToInt(Integer::intValue)
                                .toArray();
        Arrays.sort(uniqueNums);
		
		for(int i=0; i<uniqueNums.length; i++) {
			int curEarn = uniqueNums[i] * count.get(uniqueNums[i]);
			if(i>0 && uniqueNums[i]==uniqueNums[i-1]+1) {
				// check for the condition
				int temp = earn2;
				earn2 = Math.max(curEarn+earn1, earn2);
				earn1 = temp;
			} else {
				int temp = earn2;
				earn2 = curEarn + earn2;
				earn1 = temp;
			}
		}
		return earn2;
    }
}


// runtime - 0 ms
class Solution {
    static {
        for(int i = 0; i < 500; i++) deleteAndEarn(new int[] {1});
    }
    public static int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int i : nums){
            if(i > max){
                max = i;
            }
        }
        int[] points = new int[max + 1];
        for(int num : nums){
            points[num] += num;
        }
        int[] dp = new int[max + 1];
        dp[1] = points[1];
        for(int i = 2; i < points.length; i++){
            int x = points[i] + dp[i - 2];
            int y = dp[i - 1];
            int res = Math.max(x, y);
            dp[i] = res;
        }
        return dp[max];
    }
}

// runtime - 2 ms
class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] sum = new int[max + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        int[] dp = new int[max + 1];
        dp[0] = 0;
        dp[1] = sum[1];
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(sum[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[max];
    }
}
