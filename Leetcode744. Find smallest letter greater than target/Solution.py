class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        res=letters[0]
        for c in letters:
            if(c > target):
                res=c
                break

        return res
