class Solution {

    public boolean checkStrings(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] count1 = new int[256];
        int[] count2 = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            int offset = (i & 1) << 7;
            count1[offset + s1.charAt(i)]++;
            count2[offset + s2.charAt(i)]++;
        }

        return Arrays.equals(count1, count2);
    }
}



// runtime - 3ms
class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[] even = new int[26];
        int[] odd = new int[26];
        int len=s1.length();
        for(int i=0;i<len;i+=2){
            even[s1.charAt(i)-'a']++;
            even[s2.charAt(i)-'a']--;
        }
        for(int i:even) if(i!=0) return false;
        for(int i=1;i<len;i+=2){
            odd[s1.charAt(i)-'a']++;
            odd[s2.charAt(i)-'a']--;
        }
        for(int i:odd) if(i!=0) return false;
        return true;
    }
}
