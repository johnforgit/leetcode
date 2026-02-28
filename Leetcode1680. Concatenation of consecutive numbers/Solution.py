class Solution:
    def concatenatedBinary(self, n: int) -> int:
        MOD = 10**9 + 7
        res = 0
        bits = 0

        for i in range(1, n+1):
            if (i & (i-1)) == 0:
                bits += 1
            res = ((res << bits) | i) % MOD

        return res


# runtime - 56ms
MOD = 10**9 + 7
ans = [0]*(10**5 + 1)
res = 0
length = -1
for i in range(1, 18):
    for e in range(2**(i - 1), min(2**i, len(ans))):
        res = ((res << i) | e) % MOD
        ans[e] = res

class Solution:
    def concatenatedBinary(self, n: int) -> int:
        return ans[n]


# runtime - 86ms
MOD = 10**9 + 7
ans = [0]*(10**5 + 1)
res = 0
for e in range(len(ans)):
    t = bin(e)[2:]
    i = 0
    while i<len(t):
        res = ((res << 1) | (t[i]=='1')) % MOD
        i += 1
    ans[e] = res

class Solution:
    def concatenatedBinary(self, n: int) -> int:
        return ans[n]


# runtime - 116ms
class Solution:
    def concatenatedBinary(self, n: int) -> int:
        ans, MOD = 0, 10**9+7
        for i in range(1, n+1):
            ans = (ans << i.bit_length())% MOD + i 
        return ans % MOD
        

        # 2^10 + 2^14 + 2^15   2^10
        
