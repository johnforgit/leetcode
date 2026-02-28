class Solution {
public:
    int concatenatedBinary(int n) {
        const int MOD = 1e9 + 7;
        long long res = 0;
        int bits = 0;

        for(int i=1; i<=n; i++){
            if((i & (i-1)) == 0) bits++;
            res = ((res<<bits) | i) % MOD;
        }
        return res;
    }
};


// runtime - 15ms
class Solution {
public:
    const int mod = 1e9 + 7;
    int concatenatedBinary(int n) {
        long result = 0;
        for (int i = 1; i <= n; ++i) {
            result = ((result << (std::bit_width((unsigned)i))) | i) % mod;
        }
        return result;
    }
};



// runtime - 19ms
class Solution {
public:
    int concatenatedBinary(int n) {
        const int MOD=1e9+7;
        long long result =0;
        int bits=0;
        for(int i=1;i<=n;i++){
            if((i&(i-1))==0) bits++;
            result=((result<<bits)|i)%MOD;
        }
        return result;
    }
};


// runtime - 20ms
class Solution {
public:
    int concatenatedBinary(int n) {
        long long s=0;
        int mod=1e9+7,k=1;
        while(k<=n){
            int d=31-__builtin_clz(k)+1;
            s=(s<<d)|k;
            if(s>mod)s=s%mod;
            k++;
        }
        return s;
    }
};



// runtime - 21ms
class Solution {
public:
    int concatenatedBinary(int n) {
        constexpr const uint64_t cTASK_MOD = 1000000007;
        uint64_t result = 1;
        uint shift = 2;
        for (int i = 2; i <= n; i++) {
            if (i == (0b1 << shift)) {
                shift++;
            }
            result = result << shift;
            result += i;
            result = result % cTASK_MOD;
        }
        return result;
    }
};


// runtime - 23ms
class Solution {
public:
    int concatenatedBinary(int n) {
        long long ans = 0, len = 0, MOD = 1e9+7;
        for(int i = 1; i <= n; i++){
            if((i & (i-1)) == 0)len++;
            ans = ((ans << len) | i) % MOD;
        }
        return ans;
    }
};
