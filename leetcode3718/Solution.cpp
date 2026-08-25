class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        unordered_set<int> seen(nums.begin(), nums.end());
        int ans = k;
        while(seen.count(ans)) {
            ans += k;
        }
        return ans;
    }
};

/*
runtime - 0ms
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        vector<bool> freq(101,0);

        for(int i=0;i<nums.size();i++)
        freq[nums[i]] = 1;

        int i = k;
        while(i <= 100) {
            if(!freq[i])
            return i;
            i += k;
        }
        return ((100 / k) + 1) * k;
    }
};

runtime - 1ms
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        int n=nums.size();
        unordered_set<int>st;
        for(int i=0;i<n;i++){
            if(nums[i]%k==0)st.insert(nums[i]);
        }
    int a=k;
    while(!st.empty()){
        if(!st.contains(a)) return a;
       
        a+=k;
    }
    return a;
    }
};

runtime - 2ms
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        set<int>st(nums.begin(),nums.end());
        for( int i=1;i<=101;i++){
            int it=i*k;
            if(st.count(it)!=1){
                return it;
            }
        }
        return 0;
    }
};


runtime - 3ms
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        unordered_map<int, int> m;
        for(int i=0;i<nums.size();i++) {
            m[nums[i]]++;
        }

        int i = k;
        while(true) {
            if(m[i] == 0)
            return i;

            i += k;
        }
        return -1;
    }
};
*/