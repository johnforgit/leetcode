Cantor's Diagonal Argument

  class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Character curr = nums[i].charAt(i);
            ans.append(curr == '0' ? '1' : '0');
        }
        
        return ans.toString();
    }
}


// runtime - 4ms
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> integers = new HashSet();
        for (String num : nums) {
            integers.add(Integer.parseInt(num, 2));
        }
        
        int ans = Integer.parseInt(nums[0], 2);
        int n = nums.length;
        Random rand = new Random();
        
        while (integers.contains(ans)) {
            ans = rand.nextInt((int) Math.pow(2, n));
        }

        String s = Integer.toBinaryString(ans);
        while (s.length() < n) {
            s = "0" + s;
        }
        
        return s;
    }
}

// runtime - 1ms
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        ArrayList<String> arr = new ArrayList<>();
        for(String s:nums){
            arr.add(s);
        }
        for(int i=0;i<arr.size();i++){
            for(int j=0;j<arr.get(i).length();j++){
                StringBuilder sb = new StringBuilder(arr.get(i));
                sb.setCharAt(j,sb.charAt(j)=='1'? '0' : '1');
                if(!arr.contains(sb.toString())){
                    return sb.toString();
                }
            }
        }
        return "";
    }
}


Recursively generate all strings. runtime - 3ms
  class Solution {
    int n;
    Set<String> numsSet = new HashSet();
    
    private String generate(String curr) {
        if (curr.length() == n) {
            if (!numsSet.contains(curr)) {
                return curr;
            }
            
            return "";
        }
        
        String addZero = generate(curr + "0");
        if (addZero.length() > 0) {
            return addZero;
        }
        
        return generate(curr + "1");
    }
    
    public String findDifferentBinaryString(String[] nums) {
        n = nums.length;
        for (String s : nums) {
            numsSet.add(s);
        }
        
        return generate("");
    }
}
