class Solution:
    def repeatedStringMatch(self, a: str, b: str) -> int:
        num = len(b)//len(a)
        if b in a:
            return 1
        ans=1
        start=a
        for i in range(num+1):
            if b not in start:
                start += a
                ans += 1
            if b in start:
                return ans
        return -1
