class Solution:
    def minimumPushes(self, word: str) -> int:
        res=0
        n=len(word)
        row=1
        quotient = n//8
        rem=n%8
        while quotient > 0:
            # Add the product of 8 and the current row to the result
            res += 8 * row

            # increment the row for the next cycle and decrement the quotient
            quotient -= 1
            row += 1
        res += rem * row
        return res
