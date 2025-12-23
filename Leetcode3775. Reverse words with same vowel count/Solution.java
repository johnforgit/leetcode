class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        if(words.length == 1)
            return s;
        int vowelCountFirst = countVowels(words[0]);
        StringBuilder result = new StringBuilder();
        result.append(words[0]);
        result.append(" ");
        for(int i=1; i<words.length; i++) {
            // find the vowelcount of our word
            int wordVowelCount = countVowels(words[i]);

            // check if the word has the same vowel count as that of our
            // first word
            if(vowelCountFirst == wordVowelCount) {
                result.append(reverseWord(words[i]));
            } else {
                result.append(words[i]);
            }

            // add space except after the last word
            if(i < (words.length - 1)) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public String reverseWord(String word) {
        String reversed = new StringBuilder(word).reverse().toString();
        return reversed;
    }

    public int countVowels(String word) {
        int count = 0;
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' 
                || ch == 'u') {
                count++;
            }
        }
        return count;
    }

}
