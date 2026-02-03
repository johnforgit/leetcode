#include <unordered_map>
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    string frequencySort(string s) {
        // Count frequency
        unordered_map<char, int> count;
        for (char c : s) {
            count[c]++;
        }

        // Buckets: index = frequency
        vector<vector<char>> buckets(s.size() + 1);
        for (auto &p : count) {
            buckets[p.second].push_back(p.first);
        }

        // Build result
        string res;
        for (int i = s.size(); i > 0; i--) {
            for (char c : buckets[i]) {
                res.append(i, c);  // repeat char i times
            }
        }

        return res;
    }
};



// runtime - 0ms
class Solution {
public:
typedef pair<char,int>P;
    string frequencySort(string s) {
        vector<P>vec(123);
        for(char &ch : s){
            int freq = vec[ch].second;
            vec[ch] = {ch, freq + 1};
        }
        auto lambda = [&](P &p1, P &p2){
            return p1.second > p2.second;
        };
        sort(vec.begin(), vec.end(), lambda);
        string result = "";

        for(int i =0; i<= 122; i++){
            if(vec[i].second >0){
                char ch = vec[i].first;
                int freq = vec[i].second;
                string temp = string(freq,ch);
                result +=temp;

            }
        }
        return result;
        
    }
};

// runtime - 1ms
#include <bits/stdc++.h>
using namespace std;

bool cmp(pair<char, int> a, pair<char, int> b) {
    return a.second > b.second;
}

class Solution {
public:
    string frequencySort(string s) {
        //
        unordered_map<char, int> freq;
        for (char c : s) {
            freq[c]++;
        }

        vector<pair<char, int>> v;
        for (auto it : freq) {
            v.push_back(it);
        }

        sort(v.begin(), v.end(), cmp);

        string ans = "";
        for (auto p : v) {
            for (int i = 0; i < p.second; i++) {
                ans += p.first;
            }
        }

        return ans;
    }
};


// runtime - 2ms
#include <bits/stdc++.h>
using namespace std;

bool cmp(pair<char, int> a, pair<char, int> b) {
    return a.second > b.second;
}

class Solution {
public:
    string frequencySort(string s) {
        //
        unordered_map<char, int> freq;
        for (char c : s) {
            freq[c]++;
        }

        vector<pair<char, int>> v;
        for (auto it : freq) {
            v.push_back(it);
        }

        sort(v.begin(), v.end(), cmp);

        string ans = "";
        for (auto p : v) {
            for (int i = 0; i < p.second; i++) {
                ans += p.first;
            }
        }

        return ans;
    }
};
