// runtime - 0ms

class Solution {
public:
    string removeOuterParentheses(string s) {
        int level = 0;
        string res;
        for(auto c:s) {
            if(c == ')')
                level--;
            if(level > 0)
                res.push_back(c);
            if(c == '(')
                level++;
        }
        return res;
    }
};

// runtime - 1ms
class Solution {
public:
    string removeOuterParentheses(string s) {
        string res;
        stack<char> st;
        for (auto c : s) {
            if (c == ')') {
                st.pop();
            }
            if (!st.empty()) {
                res.push_back(c);
            }
            if (c == '(') {
                st.emplace(c);
            }
        }
        return res;
    }
};