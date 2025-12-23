class Solution:
    def reverseWords(self, s: str) -> str:
        cur=s.split(" ")
        vowels = "aeiou"
        c = Counter(cur[0])
        res=[cur[0]]
        target = 0
        for a, b in c.items():
            if a in vowels:
                target += b
        
        for words in cur[1:]:
            now = 0
            c=Counter(words)
            for a, b in c.items():
                if a in vowels:
                    now += b
            if now==target:
                res.append(words[::-1])
            else:
                res.append(words)
        return " ".join(res)
            

        
