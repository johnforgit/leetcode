class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        // vector v containing values of all the alphabets.
        vector<int> v(26);
        for(int i=0; i<26; i++) v[i]=i+1;
        for(int i=0; i<chars.size(); i++){
            v[chars[i]-'a'] = vals[i];
        }
        int n = s.size();
        // vector corresponding to string s
        vector<int> vt(n);
        for(int i=0; i<s.size(); i++){
            vt[i] = v[s[i]-'a'];
        }
        int ans=0, temp=0;
        for(int i=0; i<n; i++){
            temp += vt[i];
            if(temp<0) temp=0;
            // assign maximum value to ans
            ans = max(ans, temp);
        }
        return ans;
    }
};

// runtime - 0ms
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        // Build value map for each character
        vector<int> charValue(26);
        for (int i = 0; i < 26; i++) {
            charValue[i] = i + 1; // default value is 1-indexed position
        }
        
        // Override with custom values from chars and vals
        for (int i = 0; i < chars.length(); i++) {
            charValue[chars[i] - 'a'] = vals[i];
        }
        
        // Kadane's algorithm to find maximum subarray sum
        int maxCost = 0; // empty string has cost 0
        int currentSum = 0;
        
        for (char c : s) {
            int value = charValue[c - 'a'];
            currentSum = max(0, currentSum + value);
            maxCost = max(maxCost, currentSum);
        }
        
        return maxCost;
    }
};

// runtime - 1ms
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        int value[26];
        for(int i=0;i<26;i++){
            value[i]=i+1;
        }
        for(int i=0;i<chars.size();i++){
            value[chars[i]-'a']=vals[i];

        }
        int maxSum=0,currSum=0;
        for(int i=0;i<s.size();i++){
            currSum+=value[s[i]-'a'];
            if(currSum<0) currSum=0;
            maxSum=max(maxSum,currSum);
        }
        return maxSum;
        
    }
};


// runtime - 2ms
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {

        int n = s.length();

        vector<int> scores(n) , freq(26 , 1002);

        for(int i=0;i<chars.length();i++){

            freq[chars[i] - 'a'] = vals[i];
        }

        for(int i=0;i<26;i++){
            if(freq[i] == 1002)
                freq[i] = i+1;
        }

        int ans = 0 , curr = 0;

        for(int i=0;i<n;i++){
            scores[i] = freq[s[i] - 'a'];

            curr += scores[i];

            ans = max(ans , curr);

            if(curr < 0){
                curr = 0;
            }
        }

        // int ans = 0 , curr = 0;

        // for(int i=0;i<n;i++){

        //     curr += scores[i];

        //     ans = max(ans , curr);

        //     if(curr < 0){
        //         curr = 0;
        //     }
        // }

        return ans;
    }
};


// runtime - 3ms
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        vector<int> costs(26);
        iota(costs.begin(), costs.end(), 1);
        for (int i = 0; i < chars.size(); i++) {
            costs[chars[i] - 'a'] = vals[i];
        }
        //dp[i] = max(dp[i-1] + costs[i], costs[i])
        int ans = 0;
        int dp = 0;
        for (int i = 0; i < s.size(); i++) {
            dp = max(dp + costs[s[i] - 'a'], costs[s[i] - 'a']);
            ans = max(ans, dp);
        }
        return ans;
    }
};


// runtime - 4ms
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        
        int cost[26];

        for(char c = 'a'; c <= 'z'; ++c)
        {
            size_t index = chars.find(c);
            if(index == string::npos)
                cost[c - 'a'] = c - 'a' + 1;
            else
                cost[c - 'a'] = vals[index];
        }
        
        int maxCost = 0;
        int currCost = 0;
        for(char &c : s)
        {
            currCost += cost[c - 'a'];
            maxCost = max(maxCost, currCost);
            if(currCost < 0)
                currCost = 0;
        }

        return maxCost;
    }
};


