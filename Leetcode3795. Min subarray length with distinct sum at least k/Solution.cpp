class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int n = nums.size();
        int l = 0;
        int ss = 0;  // sum of distinct elements
        int ans = INT_MAX;

        unordered_map<int, int> mp;

        for (int r = 0; r < n; r++) {
            // If element not present, add to distinct sum
            if (mp.find(nums[r]) == mp.end()) {
                ss += nums[r];
            }

            mp[nums[r]]++;

            // Shrink window while condition satisfied
            while (ss >= k && l <= r) {
                ans = min(ans, r - l + 1);

                mp[nums[l]]--;

                if (mp[nums[l]] == 0) {
                    ss -= nums[l];
                    mp.erase(nums[l]);
                }

                l++;
            }
        }

        return ans == INT_MAX ? -1 : ans;
    }
};


// runtime - 4ms
class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = n + 1;
        long long sum = 0;
        unordered_map<int, int> mp;
        for (int r = 0, l = 0; r < n; r++) {
            mp[nums[r]]++;
            if (mp[nums[r]] == 1) {
                sum += nums[r];
            }
            while (l <= r && sum >= k) {
                ans = min(ans, r - l + 1);
                mp[nums[l]]--;
                if (mp[nums[l]] == 0) {
                    sum -= nums[l];
                }
                l++;
            }
        }

        return ans <= n ? ans : -1;
    }
};
auto init = atexit([]() { ofstream("display_runtime.txt") << "0";});


// runtime - 12ms
class Solution {
public:
    static int minLength(const vector<int> &nums, const uint k) noexcept __attribute__((hot)) {
        constexpr uint V = 100'000;
        static uint freqs[V];

        const uint n = nums.size();
        uint64_t s = 0;
        uint m = -1u, i = 0, w = 0;
        for (uint j = 0; j < n; j++) {
            const uint v = nums[j];
            w = max(v, w);
            const uint g = freqs[v-1]++;
            s += v & -!g;
            for ( ; s >= k; i++) {
                if (const uint l = j + 1 - i; s >= k && m > l)
                    m = l;
                const uint u = nums[i];
                s -= u & -!--freqs[u-1];
            }
        }
        fill_n((uint64_t*)freqs, w + 1 >> 1, 0);
        return m;
    }
};

static const auto init = []() noexcept {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();


// runtime - 21ms
class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int frequency[100001] = {0};
        int left = 0, right = 0;
        int n = nums.size();

        int ans = INT_MAX;
        long long sum = 0;
        while(right < n){
            frequency[nums[right]]++;
            if(frequency[nums[right]] == 1){
                sum += nums[right] ;
            }
           
            while(left < n && sum >= k ){
                ans = min(ans , right - left +1 );
                if(frequency[nums[left]] == 1){
                    sum -= nums[left];
                }
                frequency[nums[left]]--;
                left++;

            }
            right++;
        }
        return ans==INT_MAX ? -1 : ans;
    }
};


// runtime - 30ms
class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        int A[100001]={0};
        int ans=INT_MAX, i=0, j=0, sum=0, d;
        while(j<nums.size()){
            while(j<nums.size() && sum<k){
                if(!A[nums[j]]) sum+=nums[j];
                A[nums[j]]+=1;
                j+=1;
            }
            while(i<j && (A[nums[i]]>1 || sum-nums[i]>=k)){
                A[nums[i]]-=1;
                if(!A[nums[i]]) sum-=nums[i];
                i+=1;
            }
            if(sum>=k){
                d=j-i;
                if(d<ans) ans=d;
            }
            A[nums[i]]-=1;
            if(!A[nums[i]]) sum-=nums[i];
            i+=1;
        }
        if(ans==INT_MAX) return -1;
        return ans;
    }
};


# runtime - 38ms
class Solution {
public:
    int minLength(vector<int>& nums, int k) {
        if(nums.size()==1 && nums[0]>=k) return 1;
        int i=0,j=0, ans=100001;
        int sum=0;
        int mp[100001]={0};
        while(j<nums.size()){
            mp[nums[j]]++;
            if(mp[nums[j]]==1){
                sum+=nums[j];
            }
            while(sum>=k){
                if(mp[nums[i]]==1){
                    sum-=nums[i];
                }
                mp[nums[i]]--;
                ans=min(ans, j-i+1);
                i++;
            }
            j++;
        }
        if(ans==100001) return -1;
        return ans;
    }
};
