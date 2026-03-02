class Solution:
    def minSwaps(self, grid: List[List[int]]) -> int:
        n = len(grid)
        pos = [-1] * n
        for i in range(n):
            for j in range(n - 1, -1, -1):
                if grid[i][j] == 1:
                    pos[i] = j
                    break

        ans = 0
        for i in range(n):
            k = -1
            for j in range(i, n):
                if pos[j] <= i:
                    ans += j - i
                    k = j
                    break

            if k != -1:
                for j in range(k, i, -1):
                    pos[j], pos[j - 1] = pos[j - 1], pos[j]
            else:
                return -1

        return ans



# runtime - 0ms
def trailingZeroes(row: list[int]) -> int:
    try:
        return row[::-1].index(1)
    except ValueError:
        return len(row)


def findMatchIdx(rows: list[int], zeroesNeeded: int) -> int:
    for i, zeroes in enumerate(rows):
        if zeroes >= zeroesNeeded:
            return i
    
    return -1


class Solution:
    def minSwaps(self, grid: List[List[int]]) -> int:
        swaps = 0
        rows = [trailingZeroes(row) for row in grid]
        for zeroesNeeded in range(len(rows))[::-1]:
            matchIdx = findMatchIdx(rows, zeroesNeeded)
            if matchIdx < 0: return -1

            swaps += matchIdx
            del rows[matchIdx]
        
        return swaps


# runtime - 2ms
class Solution:
    def rightMost(self, row: List[int]) -> int:
        for i, v in enumerate(reversed(row)):
            if v:
                return len(row) - i - 1
        return 0


    def countSwaps(self, arr: List[int]):
        res = 0
        n = len(arr)
        for i in range(len(arr)):
            if arr[i] <= i:
                continue
            found = False
            for j in range(i + 1, n):
                if arr[j] <= i:
                    found = True
                    res += (j - i)
                    arr.insert(i, arr.pop(j))
                    break

            if not found:
                return -1
        return res


        


    def minSwaps(self, grid: List[List[int]]) -> int:
        maxRight = [self.rightMost(row) for row in grid]
        return self.countSwaps(maxRight)

        

# runtime - 3ms
from typing import List

class Solution:
    def minSwaps(self, grid: List[List[int]]) -> int:
        n = len(grid)
        rightmost = []
        for row in grid:
            pos = -1
            for j in range(n - 1, -1, -1):
                if row[j] == 1:
                    pos = j
                    break
            rightmost.append(pos)
        swaps = 0
        for i in range(n):
            target = i
            k = i
            while k < n and rightmost[k] > target:
                k += 1
            if k == n:
                return -1
            while k > i:
                rightmost[k], rightmost[k - 1] = rightmost[k - 1], rightmost[k]
                swaps += 1
                k -= 1
        return swaps
