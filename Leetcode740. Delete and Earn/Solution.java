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
