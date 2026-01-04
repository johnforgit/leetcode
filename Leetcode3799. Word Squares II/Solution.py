class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        res = []
        for p in permutations(words, 4):
            top, left, right, bottom = p[0], p[1], p[2], p[3]
            if top[0]==left[0] and top[3]==right[0] and bottom[0]==left[3] and bottom[3]==right[3]:
                res.append(p)

        res.sort()
        return res

  
