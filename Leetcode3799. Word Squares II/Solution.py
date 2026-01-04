class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        res = []
        for p in permutations(words, 4):
            top, left, right, bottom = p[0], p[1], p[2], p[3]
            if top[0]==left[0] and top[3]==right[0] and bottom[0]==left[3] and bottom[3]==right[3]:
                res.append(p)

        res.sort()
        return res


# runtime - 18 ms
class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        res=[]
        n=len(words)
        start_char = defaultdict(list) # keep a list of words and their starting letter
        words.sort()
        for i in words:
            start_char[i[0]].append(i)
        
        for i in range(n):
            t=words[i] # take a word from words
            # check for words in the dictionary with the same first letter
            for j in start_char[t[0]]: 
                if j==t: # if both words are equal continue
                    continue
                for k in start_char[t[3]]: # check for words with the same 4th letter
                    if j==k or t==k: # if they're the same continue
                        continue
                    left_end = j[3]
                    right_end = k[3]
                    for b in start_char[left_end]:
                        if(b[3] == right_end):
                            if b not in [t,j,k]:
                                res.append((t,j,k,b))

        
        return sorted(res)

