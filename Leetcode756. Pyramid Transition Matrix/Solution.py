from collections import defaultdict
import itertools

class Solution:
    def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
        # build a map with allowed words
        T=defaultdict(list)
        for triple in allowed:
            T[(triple[0], triple[1])].append(triple[2])

        memo = {}

        # figure out what the next row will look like
        def solve(row):
            if len(row) == 1:
                return True
            if row in memo:
                return memo[row]

            options = []
            for i in range(len(row)-1):
                # for each pair, check the map T
                key = (row[i], row[i+1])
                # if the pair exists add it to our options
                if key in T:
                    options.append(T[key])

                # no block is allowed to sit there
                else:
                    memo[row]=False
                    return False
            
            for next_row_tuple in itertools.product(*options):
                next_row_str = "".join(next_row_tuple)
                if solve(next_row_str):
                    memo[row]=True
                    return True
            memo[row]=False
            return False

        return solve(bottom)
            
# runtime - 46 ms
class Solution:
    def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
        mp = defaultdict(list)
        for temp in allowed:
            lr = temp[:2]
            t = temp[2:]
            mp[lr].append(t)
        line = bottom
        def dfs(line):
            lth = len(line)
            if lth == 1:
                return True
            cand = []
            maxcand = 0
            for i in range(lth-1):
                lr = line[i:i+2]
                if lr not in mp:
                    continue
                toplist = mp[lr]
                cand.append(toplist)
                maxcand = max(maxcand, len(toplist))
            if len(cand) != lth-1:
                return False
            up = [None] * (lth-1)
            for i in range(maxcand):
                temp = ""
                for j in range(lth-1):
                    idx = min(len(cand[j])-1, i)
                    u = cand[j][idx]
                    #print(cand[j], u)
                    temp += u
                #print(line, temp)
                if len(temp) == lth-1:
                    if dfs(temp):
                        return True
            return False
        return dfs(bottom)
