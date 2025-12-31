class Solution:
    def findMaxForm(self, strs, m, n):
        # dp[z][o] = max strings using z zeros and o ones
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for s in strs:
            zeros = s.count('0')
            ones = s.count('1')

            # iterate backwards
            for z in range(m, zeros - 1, -1):
                for o in range(n, ones - 1, -1):
                    dp[z][o] = max(
                        dp[z][o], # if we use the string
                        dp[z - zeros][o - ones] + 1 # if we don't use the string
                    )
        return dp[m][n]
