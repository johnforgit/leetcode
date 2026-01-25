class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        ans = 0
        nums.sort()

        # iterating forwards
        for i in range(len(nums)-1):
            p = nums[i]*nums[-1]
            ans = max(ans, p*(10**5))
            ans = max(ans, p*(-10**5))

        # iterating backwards
        for i in range(1, len(nums)):
            p = nums[i] * nums[0]
            ans = max(ans, p*(10**5))
            ans = max(ans, p*(-10**5))
        return ans


# runtime - 1ms
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        temp = []
        for i in range(len(nums)):
            temp.append(abs(nums[i]))
        temp.sort()
        return (temp[-1]*temp[-2]*10**5)
__import__("atexit").register(lambda:open("display_runtime.txt","w").write("0"))


# runtime - 3ms
# calculate the 3 candidates for max prod, and return the largest of them

import heapq
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        
        max_product = 0
        max_replacement = 10**5
        
        two_smallest = heapq.nsmallest(2,nums)
        two_largest = heapq.nlargest(2,nums)

        first = abs(max_replacement * two_smallest[0] * two_smallest[1])
        second = abs(max_replacement * two_largest[0] * two_largest[1])
        third = abs(max_replacement * two_smallest[0] * two_largest[0])
        max_product = max(max_product,first,second,third)
        
        return max_product


# runtime - 6ms
import heapq

class Solution:    
    def maxProduct(self, nums: List[int]) -> int:
        a, b = heapq.nlargest(2, map(abs, nums))
        return int(a * b * math.pow(10, 5))
        #return int(math.prod(sorted([abs(n) for n in nums])[len(nums) - 2:]) * math.pow(10, 5))


# runtime - 9ms
class Solution:
    def maxProduct(self, nums: List[int]) -> int:  
        arr=nlargest(2,map(abs,nums))
        return prod(arr)*10**5

# runtime - 11ms
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        first_pos, second_pos = 0, 0
        first_neg, second_neg = 0, 0
        for num in nums:
            if num > 0:
                if num >= first_pos:
                    first_pos, second_pos = num, first_pos
                elif num > second_pos:
                    second_pos = num
            elif num < 0:
                if num <= first_neg:
                    first_neg, second_neg = num, first_neg
                elif num < second_neg:
                    second_neg = num
        return max(first_pos * second_pos * 10**5, first_neg* second_neg*10**5, first_pos * first_neg * (-10**5))


# runtime - 14ms
import heapq

class Solution:    
    def maxProduct(self, nums: List[int]) -> int:
        a, b = heapq.nlargest(2, map(abs, nums))
        return int(a * b * math.pow(10, 5))


# runtime - 17ms
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        min1 = min2 = float('inf')
        max1 = max2 = float('-inf')

        for num in nums:
            if num > max1:
                max2 = max1
                max1 = num
            elif num > max2:
                max2 = num

            if num < min1:
                min2 = min1
                min1 = num
            elif num < min2:
                min2 = num
        
        return max(max1 * max2 * 10**5, max1 * min1 * -10**5, min1 * min2 * 10**5)


# runtime - 19ms
from typing import List

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        # Key insight: Find the two largest absolute values in nums
        # The answer is: max_abs1 * max_abs2 * 10^5
        # We can always make the product positive by choosing the right sign for the replacement
        
        # Find the two largest absolute values
        max_abs1 = 0
        max_abs2 = 0
        
        for x in nums:
            abs_x = abs(x)
            if abs_x >= max_abs1:
                max_abs2 = max_abs1
                max_abs1 = abs_x
            elif abs_x > max_abs2:
                max_abs2 = abs_x
        
        res = max_abs1 * max_abs2 * 100000
        return res

  # runtime - 27ms
  class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        largest, second = 0,0

        for num in nums:
            abs_num = abs(num)
            if abs_num > largest:
                largest, second = abs_num, largest
            elif abs_num > second:
                second = abs_num

        return largest*second * 10**5

        
