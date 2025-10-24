class Solution:
    def hasSameDigits(self, s: str) -> bool:
        n=len(s)
        s_list=list(s)
        for i in range(1, n-1):
            for j in range(n-i):
                digit1 = ord(s_list[j]) - ord("0")
                digit2 = ord(s_list[j+1]) - ord("0")
                s_list[j] = chr(((digit1 + digit2)%10) + ord("0"))
        return s_list[0] == s_list[1]
    
    def hasSameDigits(self, s:str) -> bool:
        n=len(s)-2
        cur=1
        left=int(s[0])
        right=int(s[n+1])

        for i in range(n):
            cur *= (n-i)
            cur //= i+1
            left = (left + int(s[i+1]) * cur) % 10
            right = (right + int(s[n-i]) * cur) % 10
        return left == right
         
