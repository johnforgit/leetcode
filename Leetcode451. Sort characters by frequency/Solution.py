class Solution:
    def frequencySort(self, s: str) -> str:
        count = Counter(s)
        buckets = defaultdict(list)

        for char, cnt in count.items():
            buckets[cnt].append(char)

        res = []

        for i in range(len(s), 0, -1):
            for c in buckets[i]:
                res.append(c*i)

        return "".join(res)


# runtime - 0ms
class Solution:
    def frequencySort(self, s: str) -> str:
        from collections import Counter
        freq = Counter(s)
        chars = sorted(freq.keys(),key = lambda x:freq[x] , reverse=True)
        return '' . join(ch*freq[ch] for ch in chars)


# runtime - 1ms
class Solution:
    def frequencySort(self, s: str) -> str:
        arr=list(set(s))
        arr1=sorted([[s.count(j),j] for j in arr])[::-1]
        res=""
        for j in arr1:
            res+=j[1]*j[0]
        return res

# runtime - 3ms
from collections import Counter
class Solution:
    def frequencySort(self, s: str) -> str:
        count = Counter(s)
        res = ""
        for char in sorted(count, key=lambda x: -count[x]):
            res += char * count[char]  # e.g., 'e' * 2 = "ee"
        return res


# runtime - 7ms
from collections import defaultdict
class Solution:
    def frequencySort(self, s: str) -> str:
        char_freq=defaultdict(int)
        final_str=""
        for char in s:
            char_freq[char]=char_freq[char]+1
        
        print(char_freq)
        char_freq_sort = sorted(char_freq.items(),key = lambda x:x[1],reverse=True )
        print(char_freq_sort)
        for char in char_freq_sort:
            final_str+=char[0]*char[1]
        print(final_str)
        
        # freq_bucket=[[] for _ in range(len(s)+1)]
        # for char,freq in char_freq.items():
        #     freq_bucket[freq].append(char)
        # print(freq_bucket)
        # for i in range(len(s),0,-1):
        #      for char in freq_bucket[i]:
        #         final_str+=i*char
               
                        
        return final_str



        
