class Solution:
    '''
    # solution using enumeration
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        for m in range(k, n+1):
            ans = ""
            for i in range(m, n+1):
                t = s[i-m : i]
                if(not ans or t<ans) and t.count("1") == k:
                    ans = t
            if ans:
                return ans
        return ans
    '''

    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        if s.count("1") < k:
            return ""
        ans = s
        left = cnt = 0
        for right, ch in enumerate(s):
            cnt += int(ch)
            while cnt>k or s[left] == "0":
                cnt -= int(s[left])
                left += 1
            if cnt == k:
                t = s[left : right+1]
                if len(t)<len(ans) or len(t)==len(ans) and t<ans:
                    ans = t
        
        return ans