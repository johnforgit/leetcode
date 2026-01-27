class Solution:
    def longestPrefix(self, s: str) -> str:
        ans = []
        cur = ""
        big = 0
        for i in range(len(s)):
            cur += s[i]
            if not s.startswith(cur):
                continue
            if s.endswith(cur) and len(cur) < len(s):
                big = max(big, i+1)

        return s[:big]
