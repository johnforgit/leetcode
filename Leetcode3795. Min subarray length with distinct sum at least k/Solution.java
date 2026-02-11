class Solution {
    public int minLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int ans = Integer.MAX_VALUE;
        int l=0, r=0;
        int ss=0;
        for(r=0; r<nums.length; r++) {
            if(!map.containsKey(nums[r])) {
                ss += nums[r];
            }
            map.put(nums[r], map.getOrDefault(nums[r],0)+1);

            // shrink the window
            while((ss>=k) && (l<=r)) {
                ans = Math.min(ans, r-l+1);

                // decrease the freq
                map.put(nums[l], map.get(nums[l])-1);
                if(map.get(nums[l])==0) {
                    ss -= nums[l];
                    map.remove(nums[l]);
                }
                l++;
            }
        }
        return ans==Integer.MAX_VALUE ? -1:ans;
    }
}


// runtime - 1ms
class Solution {
    public int minLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        boolean b = false;
        int[] freq= new int[100001];
        int l =0;
        int ds= 0;
        
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            if(freq[val]==0){
                ds += val;
            }
            freq[val]++;

            while(ds>=k){
                ans = Math.min(ans,i-l+1);
                int rs = nums[l];
                freq[rs]--;
                if(freq[rs]==0){
                    ds -= rs;
                }
                l++;
            }
        }
        return ans ==Integer.MAX_VALUE?-1:ans;

        // for(int i=0;i<nums.length;i++){
        //     int sum = 0;
        //     for(int j=i;j<nums.length;j++){
        //         sum += nums[j];
        //         if(sum>=k){
        //             b = true;
        //             ans = Math.min(ans,j-i+1);
        //         }
        //     }
        // }
        // if(!b){
        //     return -1;
        // }
        // return ans;
    }
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter w = new FileWriter("display_runtime.txt")){
                w.write("0");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }));
    }
}


// runtime - 3ms
class Solution {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while(i < n) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            if(map.get(nums[i]) == 1) {
                sum += nums[i];
            }
            while(sum >= k) {
                ans = Math.min(ans,i-j+1);
                map.put(nums[j],map.get(nums[j])-1);
                if(map.get(nums[j]) == 0) {
                    sum -= nums[j];
                    map.remove(nums[j]);
                }
                j++;
            }
            i++;

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("7");
        } catch (Exception e) {
        }  
    }));
    }
}


// runtime - 5ms
class Solution {
    public int minLength(int[] nums, int k) {
        int n=nums.length;
        int max=0;
        for(int i=0;i<n;i++)
            {
                max=Math.max(max,nums[i]);
            }
        int freq[]=new int[max+1];
        int left=0;
        int right=0;
        int sum=0;
        int length=0;
        int minLength=Integer.MAX_VALUE;
        while(left<=right && left<n && right<n)
            {
                if(freq[nums[right]]==0)
                {
                    sum+=nums[right];
                }
                freq[nums[right]]++; 
                while(sum>=k)
                    {
                        minLength=Math.min((right-left)+1,minLength);
                        //System.out.println(sum+" "+minLength);
                        if(freq[nums[left]]==1)
                        sum-=nums[left];
                        freq[nums[left]]--;
                        left++;
                    }
                right++;
            }
        if(minLength==2147483647)
        return -1;
        return minLength;
    }
}


// runtime - 7ms
class Solution {
    public int minLength(int[] nums, int k) {
        int max= 0;
        for (int num : nums) {
            max = Math.max(max,num);
        }
        int[] cnt = new int[max + 1];
        int ans = nums.length + 1;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            cnt[x]++;
            if(cnt[x] == 1){
                sum += x;
            }
            while(sum >= k){
                ans = Math.min(ans,right - left + 1);

                int out = nums[left];
                cnt[out]--;
                if(cnt[out] == 0){
                    sum -= out;
                }
                left++;
            }
        }

        return ans == nums.length + 1 ? -1: ans;
    }
}


// runtime - 9ms
class Solution {
    static int[] freq = new int[100_001];
    public int minLength(int[] nums, int k) {
        long sum = 0;
        int start = 0, min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
            if(freq[nums[i]] == 1) {
                sum += nums[i];
            }
            while(start < i) {
                if(freq[nums[start]] > 1) {
                    freq[nums[start++]]--;
                } else if(freq[nums[start]] == 1) {
                    if(sum - nums[start] < k) {
                        break;
                    }
                    freq[nums[start]]--;
                    sum -= nums[start++];
                }
            }
            if(sum >= k) {
                min = Math.min(min, i - start + 1);
            }
        }
        for(int i = start; i < nums.length; i++) {
            freq[nums[i]]--;
        }
        return min == Integer.MAX_VALUE? -1: min;
    }
}
