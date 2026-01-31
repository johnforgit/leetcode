class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        char res = letters[0];
        for(char c : letters) {
            if(c > target) {
                res = c;
                break;
            }
        }
        return res;
    }
};
