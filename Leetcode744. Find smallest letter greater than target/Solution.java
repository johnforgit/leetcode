class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char res = letters[0];
        for(char c:letters) {
            if((c-'a') > (target-'a')) {
                res = c;
                break;
            } 
        }
        return res;
    }
}
