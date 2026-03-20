class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        res = [[0] * (n - k + 1) for _ in range(m - k + 1)]
        for i in range(m - k + 1):
            for j in range(n - k + 1):
                kgrid = []
                for x in range(i, i + k):
                    for y in range(j, j + k):
                        kgrid.append(grid[x][y])
                kmin = float("inf")
                kgrid.sort()
                for t in range(1, len(kgrid)):
                    if kgrid[t] == kgrid[t - 1]:
                        continue
                    kmin = min(kmin, kgrid[t] - kgrid[t - 1])
                if kmin != float("inf"):
                    res[i][j] = kmin
        return res



# runtime - 25ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        rows = len(grid)
        cols = len(grid[0])
        res = [[0] * (cols - k + 1) for _ in range(rows - k + 1)]
        def diff(window):
            if len(window) < 2:
                return 0
            min_diff = float('inf')
            for i in range(len(window) - 1):
                diff = window[i+1] - window[i]
                if diff > 0 and diff < min_diff:
                    min_diff = diff
            return min_diff if min_diff != float('inf') else 0
        for r in range(rows - k + 1):
            cur = []
            for i in range(k):
                for j in range(k):
                    insort(cur, grid[r+i][j])
            res[r][0] = diff(cur)
            for c in range(1, cols - k + 1):
                for i in range(k):
                    remove = grid[r+i][c-1]
                    index = bisect_left(cur, remove)
                    cur.pop(index)
                for i in range(k):
                    add = grid[r+i][c+k-1]
                    insort(cur, add)
                res[r][c] = diff(cur)
        return res

# runtime - 34ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        res = [[0] * (n - k + 1) for _ in range(m - k + 1)]
        for i in range(m - k + 1):
            for j in range(n - k + 1):
                kgrid = []
                for x in range(i, i + k):
                    for y in range(j, j + k):
                        kgrid.append(grid[x][y])
                kmin = float("inf")
                kgrid.sort()
                for t in range(1, len(kgrid)):
                    if kgrid[t] == kgrid[t - 1]:
                        continue
                    kmin = min(kmin, kgrid[t] - kgrid[t - 1])
                if kmin != float("inf"):
                    res[i][j] = kmin
        return res


# runtime - 35ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        n, m = len(grid), len(grid[0])
        res = []

        for i in range(n - k + 1):
            res.append([])
            for j in range(m - k + 1):
                p = set()
                min_diff = 0

                for r in range(i, i + k):
                    for c in range(j, j + k):
                        p.add(grid[r][c])
                        
                p = list(p)
                p.sort()

                for r in range(1, len(p)):
                    if min_diff == 0 or p[r] - p[r - 1] < min_diff:
                        min_diff = p[r] - p[r - 1]

                res[-1].append(min_diff)

        return res


# runtime - 36ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m,n=len(grid), len(grid[0])
        res = []
        for i in range(m-k+1):
            res.append([])
            for j in range(n-k+1):
                aset=set()
                if k==1:
                    res[-1].append(0)
                    continue
                for r in range(i, i+k):
                    for c in range(j, j+k):
                        aset.add(grid[r][c])
                arr=list(aset)
                arr.sort()
                #print(arr)
                dif = arr[1]-arr[0] if len(arr)>1 else 0
                for h in range(2, len(arr)): dif = min(arr[h]-arr[h-1], dif)
                res[-1].append(dif)
        return res

# runtime - 38ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        n, m = len(grid), len(grid[0])
        res = []

        for i in range(n - k + 1):
            res.append([])
            for j in range(m - k + 1):
                p = set()
                min_diff = 0

                for r in range(i, i + k):
                    for c in range(j, j + k):
                        p.add(grid[r][c])
                        
                p = list(p)
                p.sort()

                for r in range(1, len(p)):
                    if min_diff == 0 or p[r] - p[r - 1] < min_diff:
                        min_diff = p[r] - p[r - 1]

                res[-1].append(min_diff if min_diff != float("inf") else 0)

        return res


# runtime - 39ms
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        n, m = len(grid), len(grid[0])
        res = []

        for i in range(n - k + 1):
            res.append([])
            for j in range(m - k + 1):
                p = set()
                min_diff = 0

                for r in range(i, i + k):
                    for c in range(j, j + k):
                        p.add(grid[r][c])
                        
                p = list(p)
                p.sort()

                for r in range(1, len(p)):
                    if min_diff == 0 or p[r] - p[r - 1] < min_diff:
                        min_diff = p[r] - p[r - 1]

                res[-1].append(min_diff)

        return res
