class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        words.sort()
        d =  defaultdict(list)
        for i in range(1, len(words)):
            if len(words[i]) < k and len(words[i-1]) < k:
                continue
            if words[i][:k] == words[i-1][:k]:
                d[words[i][:k]].append(words[i])
                d[words[i-1][:k]].append(words[i-1])

        return len(d)

# runtime - 6ms
class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        p=[w[:k] for w in words if len(w)>=k]
        count=Counter(p)
        return sum(c>1 for c in count.values())
        


# runtime - 13ms
from collections import defaultdict

class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        groups = defaultdict(list)

        for word in words:
            if len(word) >= k:
                prefix = word[:k]
                groups[prefix].append(word)

        count = 0
        for g in groups.values():
            if len(g) > 1:
                count += 1

        return count


# runtime - 20ms
class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        count=0
        prefixc=defaultdict(int)
        for i in words:
            if len(i)<k:
                continue
            pre = i[:k]
            prefixc[pre]+=1
        for key,val in prefixc.items():
            if val>=2:
                count+=1
        return count
            


# runtime - 27ms
class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        d = {}
        for word in words:
            if len(word) >= k:
                d[word[:k]] = d.get(word[:k], 0) + 1

        ans = 0
        for v in d.values():
            if v > 1:
                ans += 1
        return ans
