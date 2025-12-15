class Solution:
    def getDescentPeriods(self, prices: List[int]) -> int:
        '''
        n=len(prices)
        res=0

        L = 0
        while L < n:
            R = L+1
            while R < n and prices[R] == prices[R-1]-1:
                R += 1
            res += comb(R-L+1, 2)
            L = R
        return res
        '''

        
        # another solution
        n=1
        res=0
        prev=float('inf')
        for price in prices:
            if prev-price==1:
                n+=1
            else:
                res+=(n*(n+1))//2
                n=1
            prev=price
        res+=(n*(n+1))//2
        return res-1
        
        '''
        # another solution
        num_smooth_descent_periods = len(prices)
        length = 1
        prev_p = prices[0]
        for p in prices[1:]:
            if prev_p - p == 1:
                length += 1
            else:
                num_smooth_descent_periods += length * (length - 1) // 2
                length = 1
            prev_p = p
        if length > 1:
            num_smooth_descent_periods += length * (length - 1) // 2
        return num_smooth_descent_periods
        '''

      # using dynamic programming
      '''
      n = len(prices)
      res = 1  # total number of smooth descending periods, initial value is dp[0]
      prev = 1  # total number of smooth descending periods ending with the previous element, initial value is dp[0]
      # traverse the array starting from 1, and update prev and the total res according to the recurrence relation
      for i in range(1, n):
          if prices[i] == prices[i - 1] - 1:
              prev += 1
          else:
              prev = 1
          res += prev
      return res
      '''


        
