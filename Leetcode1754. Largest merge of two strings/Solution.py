class Solution:
    def largestMerge(self, word1: str, word2: str) -> str:
        m = []

        while word1 and word2:
            if word1 > word2:
                m.append(word1[0])
                word1 = word1[1:]
            else:
                m.append(word2[0])
                word2 = word2[1:]

        return "".join(m) + word1 + word2
