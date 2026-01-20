class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        int[] result = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int original = nums.get(i);
            int candidate = -1;
            for (int j = 1; j < original; j++) {
                if ((j | (j + 1)) == original) {
                    candidate = j;
                    break;
                }
            }
            result[i] = candidate;
        }
        return result;
    }
}

// runtime - 1ms
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int [] ans= new int[nums.size()];
        Arrays.fill(ans,-1);
        for(int i=0;i<ans.length;i++){
            int temp=nums.get(i);
            int min=Integer.MAX_VALUE;
            if( ((temp+1)& temp)!=0){
            for(int j=0;j<32;j++){
                if((temp & (1<<j))!=0){
                    int temp1=(temp & ~(1<<j));
                    if((temp1 | (temp1+1)) == temp){
                        min=Math.min(min,temp1);
                    }
                }
            }
            if(min != Integer.MAX_VALUE )
                ans[i]=min;
            }
            else{
                int n= temp;
                int position=-1;
                while(n>0){
                    position++;
                    n=n>>1;
                }
                ans[i]= (temp & ~(1<< (position)));
            } 
        }
        return ans;
    }
}

class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int res = -1;
            int d = 1;
            while ((x & d) != 0) {
                res = x - d;
                d <<= 1;
            }
            result[i] = res;
        }
        return result;
    }
}

// runtime - 2ms

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            result.add(helper(num));}

        int[] arrayResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arrayResult[i] = result.get(i);}
        
        return arrayResult;}

    private int helper(int num) {
        if (num % 4 == 1) {  // 4 mod 1 case
            return num - 1;
        }
        if (num % 4 == 3) {  // 4 mod 3 case
            int tmp = num;
            int i = 0;
            while (tmp > 0) {
                tmp /= 2;
                if (tmp % 2 == 0) break;
                i++;}

            return num - (1 << i);}
        return -1;  // just for num = 2
    }
}
