class Solution:
    def gcdOfOddEvenSums(self, n: int) -> int:
        '''
        odd_sum=0
        even_sum=0
        for i in range(1, (2*n)+1):
            if i%2==0:
                even_sum += 1
            else:
                odd_sum += 1
        return gcd(odd_sum, even_sum)
        '''
        return n
