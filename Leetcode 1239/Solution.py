class Solution:
    def maxLength(self, arr: List[str]) -> int:
        dp=[0]
        res=0
        
        for s in arr:
            a, dup = 0,0
            for c in s:
                dup |= a & (1<<(ord(c) - ord('a')))
                a |= 1 << (ord(c) - ord('a'))
            if dup > 0:
                continue
            for i in range(len(dp)-1, -1, -1):
                if(dp[i] & a) > 0:
                    continue
                dp.append(dp[i] | a)
                res = max(res, bin(dp[i] | a).count('1'))

        return res

# runtime - 4ms
class Solution:
    def get_mask(self, w:str)->int:
        m = 0
        for c in w:
            bit = 1 << (ord(c) - 97)
            if m & bit:
                return -1
            m |= bit
        return m

    def maxLength(self, arr: List[str]) -> int:
        M, L = [], []
        for w in arr:
            m = self.get_mask(w)
            if m != -1:
                M.append(m)
                L.append(len(w))
        if len(M) == 0:
            return 0
        pairs = sorted(zip(M, L), key=lambda x: -x[1])
        M, L = zip(*pairs)
        
        S = [0]*(len(M)+1)
        for i in range(len(M)-1, -1, -1):
            S[i] = S[i+1] + L[i]
        
        self.res = 0
        def dfs(i, cur_mask, cur_len):
            if i == len(M):
                if cur_len > self.res:
                    self.res = cur_len
                return
            if cur_len + S[i] <= self.res:
                return
            dfs(i+1, cur_mask, cur_len)
            if not (cur_mask & M[i]):
                dfs(i+1, cur_mask | M[i], cur_len+L[i])
        dfs(0, 0, 0)
        return self.res
