class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        # explore from the current cell
        def dfs(x, y, seen):
            if(x<0 or x>=m or y<0 or y>=n or land[x][y]!=1 or (x,y) in seen):
                return seen
            # add the current cell to the set of seen farmlands
            seen.add((x,y))
            # traverse in all 4 directions from the current cell
            self.v.add((x,y))
            dfs(x+1, y, seen)
            dfs(x-1, y, seen)
            dfs(x, y-1, seen)
            dfs(x, y+1, seen)
            return seen


        self.v = set()
        m,n = len(land), len(land[0])
        seen = set()
        res=[]
        for i in range(m):
            for j in range(n):
                if(land[i][j]==1) and (i,j) not in self.v:
                    val = sorted(list(dfs(i, j, set())))
                    ans = list(val)[0] + list(val)[-1]
                    res.append(ans)
        return res


# runtime - 40ms
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        farmlands = []
        n, m = len(land), len(land[0])

        for i in range(n):
            j = 0
            while j < m:
                if land[i][j] == 0:
                    j += 1
                elif land[i][j] < 0:
                    j += -land[i][j]
                elif land[i][j] == 1:
                    height, width = 0, 0
                    k = j
                    while k < m and land[i][k] == 1:
                        width += 1
                        k += 1
                    k = i
                    while k < n and land[k][j] == 1:
                        height += 1
                        land[k][j] = -width  # mark left edge of farmland so we can skip
                        k += 1
                    farmlands.append((i, j, i + height - 1, j + width - 1))
                    j += width

        return farmlands


# runtime - 45ms
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        farmlands = []
        n, m = len(land), len(land[0])

        for i in range(n):
            j = 0
            while j < m:
                if land[i][j] == 0:
                    j += 1
                elif land[i][j] < 0:
                    j += -land[i][j]
                elif land[i][j] == 1:
                    height, width = 0, 0
                    k = j
                    while k < m and land[i][k] == 1:
                        width += 1
                        k += 1
                    k = i
                    while k < n and land[k][j] == 1:
                        height += 1
                        land[k][j] = -width  # mark left edge of farmland so we can skip
                        k += 1
                    farmlands.append((i, j, i + height - 1, j + width - 1))
                    j += width

        return farmlands


# runtime - 50ms
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        m, n = len(land), len(land[0])
        res = []

        for i in range(m):
            for j in range(n):
                if land[i][j] == 1 and \
                   (i == 0 or land[i-1][j] == 0) and \
                   (j == 0 or land[i][j-1] == 0):

                    r, c = i, j

                    while r + 1 < m and land[r + 1][j] == 1:
                        r += 1
                    while c + 1 < n and land[i][c + 1] == 1:
                        c += 1

                    res.append([i, j, r, c])

        return res


# runtime - 55ms
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        n, m = len(land), len(land[0])
        groups = []

        for i in range(n):
            for j in range(m):
                if land[i][j] != 1 or (i > 0 and land[i -1][j] == 1) or (j > 0 and land[i][j - 1] == 1):
                    continue

                cx, cy = i, j
                while cx < n and land[cx][cy] == 1:
                    cx += 1
                cx -= 1

                while cy < m and land[cx][cy] == 1:
                    cy += 1
                cy -= 1

                bot, right = cx, cy
                groups.append([i, j, bot, right])
        return groups


# runtime - 60ms
class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        m, n = len(land), len(land[0])
        visited = [[False] * n for _ in range(m)]
        result = []

        for i in range(m):
            for j in range(n):
                if land[i][j] == 1 and visited[i][j] == False:
                    ii, jj = i, j
                    while ii<m and land[ii][j]==1:
                        ii += 1
                    while jj<n and land[i][jj]==1:
                        jj += 1
                    result.append([i, j, ii-1, jj-1])
                    for x in range(i, ii):
                        for y in range(j, jj):
                            visited[x][y] = True
        
        return result
