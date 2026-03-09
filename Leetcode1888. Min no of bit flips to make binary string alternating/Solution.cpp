class Solution {
public:
    int minFlips(auto& s) {
        int n = s.length();
        int op[2] = {0, 0};

        for (int i = 0; i < n; i++)
            op[(s[i] ^ i) & 1]++;

        int res = min(op[0], op[1]);

        for (int i = 0; i < n - 1; i++) {
            op[(s[i] ^ i) & 1]--;
            op[(s[i] ^ (n + i)) & 1]++;
            res = min(res, min(op[0], op[1]));
        }

        return res;
    }
};


// runtime - 1ms
class Solution {
public:
    int minFlips(auto& s) {
        int n = s.length();
        int res = n;
        int op[2] = {0, 0};

        for (int i = 0; i < n; i++)
            op[(s[i] ^ i) & 1]++;

        for (int i = 0; i < n; i++) {
            op[(s[i] ^ i) & 1]--;
            op[(s[i] ^ (n + i)) & 1]++;
            res = min(res, min(op[0], op[1]));
        }

        return res;
    }
};


// runtime - 5ms
class Solution {
public:
    int minFlips(string s) {
        int n = s.size(), d1 = 0, d2 = 0, ans = n;
        s += s;
        for (int i = 0; i < s.size(); i++) {
            d1 += s[i] != (i % 2 ? '1' : '0');
            d2 += s[i] != (i % 2 ? '0' : '1');
            if (i >= n) {
                d1 -= s[i - n] != ((i - n) % 2 ? '1' : '0');
                d2 -= s[i - n] != ((i - n) % 2 ? '0' : '1');
            }
            if(i>=n-1) ans=min(ans,min(d1,d2));
        }
        return ans;
    }
};


// runtime - 9ms
class Solution {
public:
    int minFlips(string s) {
        ios_base::sync_with_stdio(0);
        cin.tie(0);
        cout.tie(0);
        int n = s.length();
        int res = n;
        int op[2] = {0, 0};
        for (int i = 0; i < s.length(); i++){
            op[(s[i] ^ i) & 1]++;
        }

        for (int i = 0; i < n; i++){
            op[(s[i] ^ i) & 1]--;
            op[(s[i] ^ (n+i)) & 1]++;
            res = min(res, min(op[0], op[1]));
        }
        return res;
    }
};


