class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        price.sort()
        l, r = 1, price[-1]-price[0]
        ans = 0

        def isgood(mid):
            cnt=1
            left=price[0]
            for i in range(1, len(price)):
                if(price[i]-left >= mid):
                    left = price[i]
                    cnt += 1
                    if cnt >= k:
                        return True
            return False

            
        while l<=r:
            mid = (l+r)//2
            if isgood(mid):
                ans = max(ans, mid)
                l = mid+1
            else:
                r = mid-1
        
        return ans


# runtime - 289ms
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        # from bisect import bisect_left
        # def f(d:int)->bool:
        #     cnt = 1
        #     pre = price[0]
        #     for p in price:
        #         if p - pre >= d:
        #             cnt += 1
        #             pre = p
        #     return cnt < k
        
        # price.sort()
        # left = 0
        # right = (price[-1] - price[0]) // (k-1) + 1
        # return bisect_left(range(right), True, key= f) - 1
        price.sort()
        def check(tastness:int)->bool:
            cnt = 1
            pre = price[0]
            for p in price:
                if p - pre >= tastness:
                    cnt += 1
                    pre = p
            return cnt >= k
        
        left = 1
        right = (price[-1] - price[0]) // (k - 1) + 1 # [ )
        while left < right:
            mid = (left + right) // 2
            if check(mid): # mid tastness cannot satisfies the condition
                left = mid + 1
            else:
                right = mid
        return left - 1


# runtime - 299ms
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        # testiness is the min abs(diff(cand)) -> 
        def check(taste:int)-> int:
            num = 1
            pre = price[0]
            for p in price[1:]:
                if p-pre >=taste: #the tastiness is good
                    num += 1
                    pre = p
            return num
        
        #sort the price first so the min distance is always between two neighbors
        price.sort()
        l = 0 #min is 0 tastiness
        r = (price[-1]-price[0])//(k-1) #max is the maximum distance it can get to
        while l<=r:
            mid = l + (r-l)//2
            if check(mid) >= k:
                l = mid +1
            else:
                r = mid -1
        return r

# runtime - 309ms
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        # tastiness abs(2 numbers)
        # max this
        price.sort()
        ret = 0
        low, high = 0, price[-1] - price[0]

        def countPile(target):
            _sum = 0
            ret = 0

            for p in price:
                if p >= _sum:
                    _sum = p + target
                    ret += 1
            return ret

        while low <= high:
            mid = (low + high) // 2

            pile = countPile(mid)

            if pile >= k:
                low = mid + 1
                ret = mid
            else:
                high = mid - 1
        return ret

# runtime - 319ms
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        price.sort()
        n = len(price)
        @cache
        def f(target):
            cnt = 1
            pre = price[0]
            for i in range(1, n):
                if price[i] - pre >= target:
                    pre = price[i]
                    cnt += 1
            return cnt >= k

        l, r = 0, (price[-1] - price[0])// (k-1)
        while l <= r:
            mid = (l+r)//2
            if f(mid):
                l = mid + 1
            else:
                r = mid - 1
        

        return l-1
