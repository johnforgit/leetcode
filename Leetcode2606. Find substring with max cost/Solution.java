class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        // build value map for each character
        int val[] = new int[26];
        for(int i=0; i<26; i++) {
            val[i] = i+1;
        }
        // override with custom values from chars and vals
        for(int i=0; i<chars.length(); i++) {
            val[chars.charAt(i)-'a'] = vals[i];
        }

        // kadane's algorithm to find max subarray
        int max=0;
        int n = s.length();
        int cur_sum = 0;
        for(char c:s.toCharArray()) {
            int value = val[c-'a'];
            cur_sum = Math.max(0, cur_sum+value);
            max = Math.max(max, cur_sum);
        }

        return max;
    }
}

// runtime - 3ms
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] map = new int[26];

        for(int i = 0; i < map.length; i++){
            map[i] = i + 1;
        }

        for(int i = 0; i < chars.length(); i++){
            map[chars.charAt(i) - 'a'] = vals[i];
        }

        int result = 0;
        int curr = 0;

        for(int i = 0; i < s.length(); i++){
            if(curr < 0)
                curr = 0;
            
            curr += map[s.charAt(i) - 'a'];
            result = Math.max(result, curr);
        }

        return result;
    }
}


// runtime - 5ms
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] val = new int[26];
        Arrays.fill(val, Integer.MIN_VALUE);

        for (int i = 0; i < chars.length(); i++) {
            val[chars.charAt(i) - 'a'] = vals[i];
        }

        int prefixSum = 0;
        int minPrefix = 0;
        int res = 0;

        for (char c : s.toCharArray()) {
            int v = val[c - 'a'] == Integer.MIN_VALUE
                    ? c - 'a' + 1
                    : val[c - 'a'];

            prefixSum += v;

            res = Math.max(res, prefixSum - minPrefix);
            minPrefix = Math.min(minPrefix, prefixSum);
        }

        return res;
    }
}


// runtime - 6ms
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length();
        int [] value = new int[26];

        Arrays.fill(value, -1001);

        for(int i = 0 ; i < vals.length ; i++){
            value[chars.charAt(i) - 'a'] = vals[i];
        }

        int sum = 0, max = 0;

        for(int i = 0 ; i < n ; i++){
            char ch = s.charAt(i);
            if(value[ch - 'a'] != -1001){
                sum += value[ch - 'a'];
            }else{
                sum += (ch - 'a' + 1);
            }

            if(sum < 0){
                sum = 0;
            }

            if(sum > max){
                max = sum;
            }
        }

        return max;
    }
}


// runtime - 9ms
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] map = new int[26]; 
        Arrays.fill(map,-1001);
        for(int i = 0;i<chars.length();i++){
            map[chars.charAt(i) - 'a'] = vals[i];
        }

        int res = 0;
        int max = 0;
        for(int i = 0;i<s.length();i++){
            int val = (s.charAt(i) - 'a')+1;
            if(map[s.charAt(i) - 'a'] != -1001) {
                val = map[s.charAt(i) - 'a'];
            }
            res+=val;
            if(res<0) res = 0;
            max = Math.max(res,max);
        }
        return max;
    }
}
