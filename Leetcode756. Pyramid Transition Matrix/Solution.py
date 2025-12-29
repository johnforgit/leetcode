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
            
