class Solution {
    public boolean isValid(String word) {
        if(word.length() < 3) 
            return false;
        boolean has_vowel=false;
        boolean has_consonant=false;
        for(char c:word.toCharArray()) {
            if(Character.isLetter(c)) {
                char ch=Character.toLowerCase(c);
                if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
                    has_vowel=true;
                } else {
                    has_consonant=true;
                } 
            } else if(!Character.isDigit(c)) {
                return false;
            }
        }
        return has_vowel && has_consonant;
    }
}
