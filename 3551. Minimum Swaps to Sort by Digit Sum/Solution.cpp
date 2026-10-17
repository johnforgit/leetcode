#include <algorithm>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
    static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

public:
    int minSwaps(vector<int>& a) {
        vector<int> b = a;
        sort(b.begin(), b.end(), [](int x, int y) {
            int sx = digitSum(x), sy = digitSum(y);
            return sx == sy ? x < y : sx < sy;
        });

        unordered_map<int, int> pos;
        for (int i = 0; i < static_cast<int>(a.size()); ++i)
            pos[a[i]] = i;

        int res = 0;
        for (int i = 0; i < static_cast<int>(a.size()); ++i) {
            if (a[i] != b[i]) {
                int j = pos[b[i]];
                swap(a[i], a[j]);
                pos[a[i]] = i;
                pos[a[j]] = j;
                ++res;
            }
        }
        return res;
    }
};

// -------------------------------------------------------------------------------
// Union find approach
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<int> digitSum(n);
        for (int i = 0; i < n; i++) {
            int x = nums[i], sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        vector<int> indices(n);
        iota(indices.begin(), indices.end(), 0);
        sort(indices.begin(), indices.end(), [&](int a, int b) {
            if (digitSum[a] != digitSum[b]) {
                return digitSum[a] < digitSum[b];
            }
            return nums[a] < nums[b];
        });

        vector<int> targetPos(n);
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        vector<int> parent(n);
        iota(parent.begin(), parent.end(), 0);
        int components = n;

        function<int(int)> find = [&](int x) -> int {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        };

        for (int i = 0; i < n; i++) {
            int ri = find(i);
            int rj = find(targetPos[i]);
            if (ri != rj) {
                parent[ri] = rj;
                components--;
            }
        }

        return n - components;
    }
};

// permutation cycles approach
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<int> digitSum(n);
        for (int i = 0; i < n; i++) {
            int x = nums[i], sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        vector<int> indices(n);
        iota(indices.begin(), indices.end(), 0);
        sort(indices.begin(), indices.end(), [&](int a, int b) {
            if (digitSum[a] != digitSum[b]) {
                return digitSum[a] < digitSum[b];
            }
            return nums[a] < nums[b];
        });

        vector<int> targetPos(n);
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        vector<int> visited(n);
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = targetPos[j];
                }
            }
        }

        return n - cycles;
    }
};
// -------------------------------------------------------------------------------

// -------------------------------------------------------------------------------
// runtime - 109ms
class Solution {
public:
    int minSwaps(vector<int>& nums) {

        int n=nums.size();

        vector<tuple<int,int,int>> digitSum(n);

        for(int i=0;i<n;i++){

            int num=nums[i];

            int sum=0;
            while(num>0){
                sum += num%10;
                num /= 10;
            }
            digitSum[i] = make_tuple(sum, nums[i], i);
        }

        sort(digitSum.begin(), digitSum.end(), [](const tuple<int,int,int>&a, const tuple<int,int,int>&b){
                if(get<0>(a) == get<0>(b))
                    return get<1>(a) < get<1>(b);
                return get<0>(a) < get<0>(b);
        });

        vector<int> vis(n,false);

        int swap=0;
        for(int i=0;i<n;i++){

            if(vis[i] || get<2>(digitSum[i]) == i) continue;

            int j=i;
            int cycle=0;

            while(!vis[j]){
                vis[j]=true;

                j = get<2>(digitSum[j]);

                cycle++;
            }
            swap += cycle-1;
        }

        return swap;

    }
};
// -------------------------------------------------------------------------------

// -------------------------------------------------------------------------------
// runtime - 127ms
class Solution {
    int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }

public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<pair<pair<int, int>, int>> arr(n);

        for (int i = 0; i < n; ++i) {
            arr[i] = {{getDigitSum(nums[i]), nums[i]}, i};
        }

        sort(arr.begin(), arr.end());

        vector<bool> visited(n, false);
        int swaps = 0;

        for (int i = 0; i < n; ++i) {
            if (visited[i] || arr[i].second == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;

            while (!visited[curr]) {
                visited[curr] = true;
                curr = arr[curr].second;
                cycleSize++;
            }

            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }
};
// -------------------------------------------------------------------------------


// -------------------------------------------------------------------------------
// runtime - 71ms
uint64_t A[100000];
uint8_t viz[100000];
class Solution {
public:
    static inline int digitSum(int x){
        int sum=0;
        for(; x>0; x/=10){
            sum+=x%10;
        }
        return sum;
    }
    static inline uint64_t pack ( int ds , int x , int i ) {
        return ((uint64_t)ds<<47ULL)+((uint64_t)x<<17ULL)+i ;
    }
    static int minSwaps(vector<int>& nums) {
        const int n=nums.size();
        for (int i=0; i<n; i++){
            int x=nums[i];
            A[i]=pack(digitSum(x), x, i);
        }
        sort(A, A+n);
        memset(viz, 0, n);
        int cnt=0, mask=(1<<17)-1;
        for(int i=0; i<n; i++){
            if (viz[i] || (A[i]&mask)==i) continue;
            int cycle=0;
            for(int j=i; !viz[j]; j=A[j]&mask){
                cycle++;
                viz[j]=1;
            }
            cnt+=(cycle-1);
        }
        return cnt;
    }
};
auto init = []()
{ 
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();
// -------------------------------------------------------------------------------


// -------------------------------------------------------------------------------
// runtime - 461ms
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int n = nums.size();

        vector<pair<int, int>> arr(n);

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            arr[i] = {sum, nums[i]};
        }
        
        sort(arr.begin(), arr.end());
        unordered_map<int, int> pos;

        for (int i = 0; i < n; i++) {
            pos[nums[i]] = i;
        }
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == arr[i].second) continue;
            int wanted = arr[i].second;
            int j = pos[wanted];
            swap(nums[i], nums[j]);
            pos[nums[j]] = j;
            pos[nums[i]] = i;
            swaps++;
        }
        return swaps;
    }
};
// -------------------------------------------------------------------------------

// -------------------------------------------------------------------------------
// runtime - 164ms
class Solution {
public:
    int digitSum(int num){
        int sum = 0;
        while(num){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<tuple<int, int, int>> mp;

        for(int i=0; i<n; i++){
            int sum = digitSum(nums[i]);
            mp.push_back({sum, nums[i], i});
        }

        sort(mp.begin(), mp.end());

        vector<bool> vis(n, 0);
        int swapCount = 0;

        for(int i=0; i<n; i++){
            if(vis[i]) continue;

            int curr = i;
            while(!vis[curr]){
                vis[curr] = true;
                curr = get<2>(mp[curr]);
                swapCount++;
            }
            swapCount--;
        }

        return swapCount;
    }
};
// -------------------------------------------------------------------------------

// -------------------------------------------------------------------------------


// -------------------------------------------------------------------------------

