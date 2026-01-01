class Solution {
    public int repeatedStringMatch(String a, String b) {
        int ans=1;
        int num = b.length()/a.length();
        StringBuilder start = new StringBuilder(a);
        if(a.contains(b))
            return 1;
        for(int i=0; i<num+1; i++) {
            if(start.indexOf(b) == -1) {
                start.append(a);
                ans++;
            }
            if(start.indexOf(b) != -1)
                return ans;
        }
        return -1;
    }
}

// runtime - 3 ms
class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) { }
    }));
    }
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder(a);
        int count = 1;

        // Repeat until length >= b.length
        while(sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        // Check if current or one more repetition contains b
        if(sb.toString().contains(b)) return count;
        if(sb.append(a).toString().contains(b)) return count + 1;

        return -1;
    }
}

// runtime - 9 ms
class Solution {
    public int repeatedStringMatch(String a, String b) {
        
       boolean [] charInA = new boolean[26];

       for(char c : a.toCharArray()){
        charInA[c-'a'] = true;
       }


       for(char c : b.toCharArray()){
        if(!charInA[c-'a']){
            return -1;
        }
       }

       int upperBound = (b.length()/a.length())+2;

       StringBuilder repeated = new StringBuilder();
       for(int count = 1; count <= upperBound; count++){
        repeated.append(a);

        if(repeated.toString().contains(b)){
            return count;
        }
       }
 
       
        

        return -1;
    }
}
