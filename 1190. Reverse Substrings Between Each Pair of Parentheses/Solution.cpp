class Solution {
public:
    string reverseParentheses(string s) {
        stack<int> openParenthesesIndices;
        string result;

        for(char currentChar:s) {
            if(currentChar == '(') {
                // Store the current length as the start index for future

                // reversal
                openParenthesesIndices.push(result.length());
            } else if (currentChar == ')') {
                int start = openParenthesesIndices.top();
                openParenthesesIndices.pop();

                // reverse the subtring between the matching parantheses
                reverse(result.begin()+start, result.end());
            } else {
                // append the non-parantheses characters to the processed string
                result += currentChar;
            }
        }
        return result;
    }
};

/**
 * 
 * WORMHOLE TELEPORTATION TECHNIQUE
 * 
 * class Solution {
public:
    string reverseParentheses(string s) {
        int n = s.length();
        stack<int> openParenthesesIndices;
        vector<int> pair(n);

        // First pass: Pair up parentheses
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                openParenthesesIndices.push(i);
            }
            if (s[i] == ')') {
                int j = openParenthesesIndices.top();
                openParenthesesIndices.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        // Second pass: Build the result string
        string result;
        for (int currIndex = 0, direction = 1; currIndex < n;
             currIndex += direction) {
            if (s[currIndex] == '(' || s[currIndex] == ')') {
                currIndex = pair[currIndex];
                direction = -direction;
            } else {
                result += s[currIndex];
            }
        }
        return result;
    }
};
 */