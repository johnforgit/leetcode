#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int prefixConnected(vector<string>& words, int k) {
        sort(words.begin(), words.end());
        unordered_map<string, vector<string>> mp;

        for (int i = 1; i < words.size(); i++) {
            if (words[i].length() < k || words[i - 1].length() < k) {
                continue;
            }

            string prefix1 = words[i].substr(0, k);
            string prefix2 = words[i - 1].substr(0, k);

            if (prefix1 == prefix2) {
                mp[prefix1].push_back(words[i]);
                mp[prefix1].push_back(words[i - 1]);
            }
        }

        return mp.size();
    }
};


// runtime - 6ms
class Solution {
public:
    int prefixConnected(vector<string>& words, int k) 
    {
        unordered_map<string_view, int> dic;
        for (auto& w : words)
        {
            if (w.size() < k) continue;
            auto s = string_view(w).substr(0, k);
            dic[s]++;
        }    

        int ans = 0;
        for (auto [_, v] : dic)
            if (v > 1) ++ans;

        return ans;
    }
};



// runtime - 8ms
class Solution {
public:
    int prefixConnected(vector<string>& words, int k) {
        unordered_map<string_view,int> freq;
        for(string_view s : words){
            if(s.size() < k) continue;
            freq[s.substr(0, k)]++;
        }
        int ans = 0;
        for(auto [s, c] : freq){
            ans += (c>=2);
        }
        return ans;
    }
};



// runtime - 10ms
class Solution {
public:
    // TC : O(W * K)
    // SC : O(W * K)
    int prefixConnected(vector<string>& words, int k) {
        std::unordered_map<std::string_view, int> count;
        count.reserve(words.size());

        for (auto& word : words) {
            if (word.size() < k) continue;
            ++count[std::string_view{word}.substr(0, k)];
        }

        int res = 0;
        for (auto& [_, cnt] : count) {
            if (cnt > 1) ++res;
        }
        return res;
    }
};



// runtime - 12ms
class Solution {
public:
    int prefixConnected(vector<string>& words, int k) {
        unordered_map<string,int>mp;
        for(int i=0;i<words.size();i++){
            if(words[i].size()>=k){
                string pre=words[i].substr(0,k);
                mp[pre]++;
            }
        }
        int ans=0;
        for(auto &it:mp){
            if(it.second>=2)ans++;
        }
        return ans;
    }
};
