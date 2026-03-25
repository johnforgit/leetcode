class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        total = sum(sum(row) for row in grid)
        
        if total % 2:
            return False
        
        target = total // 2
        s = 0
        
        for i in range(m - 1):
            s += sum(grid[i])
            if s == target:
                return True
        
        s = 0
        
        for j in range(n - 1):
            for i in range(m):
                s += grid[i][j]
            if s == target:
                return True
        
        return False



# runtime - 44ms
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:

                # Sum rows of the grid or its transpose and 
                # determine whether the equal partition exists
        def partitionExists(arr: list, sm = 0)-> bool:

            for row in arr:
                sm+= sum(row)
                if sm == halfSum: return True
                if sm >  halfSum: return False


                # Determine the target sum for each partition
                # and whether sum(grid) is odd
        halfSum, mod_ = divmod(sum(chain(*grid)), 2)
        if mod_ : return False

                # Check horizontal and vertical partitions
        return (partitionExists(grid) or 
                partitionExists(zip(*grid)))   



# runtime - 48ms
from typing import List

class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        def is_valid(sums: List[int]) -> bool:
            total = sum(sums)
            if total % 2 != 0:
                return False
            
            target = total//2
            running_total = 0
            for i in sums:
                running_total += i
                if running_total == target:
                    return True
            return False
            

        # Use lists to preserve duplicate sum values
        row_sums = [sum(row) for row in grid]
        col_sums = [sum(col) for col in zip(*grid)]
        
        return is_valid(row_sums) or is_valid(col_sums)


# runtime - 52ms
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        totalSum = sum(map(sum, grid))
        def canPartition(grid:list[list[int]]) -> bool:
            runningSum = 0
            for row in grid:
                runningSum += sum(row)
                if runningSum * 2 == totalSum:
                    return True
            return False
        return canPartition(grid) or canPartition(zip(*grid))



# runtime - 56ms
class Solution:
    def canPartitionGrid(self, G: List[List[int]]) -> bool:
        def checkRows(G):
            m, n = len(G), len(G[0])
            rows = [sum(G[i]) for i in range(m)]
            total = sum(rows)
            prefixSum = 0
            for row in rows:
                prefixSum += row
                if prefixSum == total - prefixSum:
                    return True
            return False
        
        if checkRows(G):
            return True
        GT = list(zip(*G))
        return checkRows(GT)


# runtime - 60ms
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        s = sum(sum(row) for row in grid)
        if s % 2:
            return False
        pre = 0
        for i, row in enumerate(grid):
            pre += sum(row)
            if pre * 2 == s and i != len(grid) - 1:
                return True
        pre = 0
        for j, col in enumerate(zip(*grid)):
            pre += sum(col)
            if pre * 2 == s and j != len(grid[0]) - 1:
                return True
        return False
