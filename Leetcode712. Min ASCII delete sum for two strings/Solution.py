class Solution:
    def minimumDeleteSum(self, s1: str, s2: str) -> int:
        n,m=len(s1), len(s2)

        # dp[i][j] - max ASCII sum of common subsequence
        dp = [[0] * (m+1) for _ in range(n+1)]
        for i in range(n):
            for j in range(m):
                if(s1[i] == s2[j]):
                    dp[i+1][j+1] = dp[i][j] + ord(s1[i])
                else:
                    dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j])
        
        total = sum(ord(c) for c in s1) + sum(ord(c) for c in s2)
        return total - 2*dp[n][m]

# runtime - 30 ms
class Solution:
    def minimumDeleteSum(self, s1: str, s2: str) -> int:
        
        dp = [0]*len(s1)
        
        for c in s2:
            curr_val = 0
            for i in range(len(dp)):
                if c == s1[i]:
                    temp = dp[i]
                    dp[i] = max(dp[i], curr_val+ord(c))
                    curr_val = max(temp, curr_val)
                
                elif curr_val < dp[i]:
                    curr_val = dp[i]
        
        remain_val = max(dp)
        s1_val = sum(ord(c) for c in s1)
        s2_val = sum(ord(c) for c in s2)

        return s1_val + s2_val - 2*remain_val

# runtime - 42 ms
class Solution:
    def minimumDeleteSum(self, s1: str, s2: str) -> int:
        dp = [0] * len(s2)
        for c1 in s1:
            prev = 0
            o1 = ord(c1)
            for i, c2 in enumerate(s2):
                last = dp[i]
                if c1 == c2:
                    dp[i] = prev + o1
                if prev < last:
                    prev = last
        return sum(map(ord, f'{s1}{s2}')) - 2 * max(dp)
