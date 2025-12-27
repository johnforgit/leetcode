class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        rows = len(strs)
        cols = len(strs[0])
        isSorted = [0]*rows
        res=0

        # iterate through each string
        for c in range(cols):
            valid = True
            for r in range(1, rows):
                if not isSorted[r] and strs[r][c] < strs[r-1][c]:
                    res += 1
                    valid = False
                    break
            if not valid:
                continue
            for r in range(1, rows):
                if strs[r][c] > strs[r-1][c]:
                    isSorted[r]=1

        return res
            

# runtime - 0 ms
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        
        pairSortedEarlier=[False] * (len(strs)-1)
        deletions=0

        for col in range(len(strs[0])):
            
            
            # wrong order deletion loop - ignores already sorted rows cases
            deleted=0
            for row in range(len(strs)-1):
                if not pairSortedEarlier[row] and strs[row][col]>strs[row+1][col]:
                    deletions+=1
                    deleted=1
                    break

            # marking any new sorted pairs for next col
            if not deleted:
                for row in range(len(strs)-1):
                    if not pairSortedEarlier[row] and strs[row][col]<strs[row+1][col]:
                        pairSortedEarlier[row]=True
        
        return deletions

# runtime - 1 ms
class Solution:
    def minDeletionSize(self, strs):
        n = len(strs)
        m = len(strs[0])
        
        sorted_pairs = [False] * (n - 1)
        deletions = 0
        
        for col in range(m):
            # Check if this column causes a violation
            bad = False
            for i in range(n - 1):
                if not sorted_pairs[i] and strs[i][col] > strs[i + 1][col]:
                    bad = True
                    break
            
            if bad:
                deletions += 1
                continue
            
            # Update sorted_pairs
            for i in range(n - 1):
                if not sorted_pairs[i] and strs[i][col] < strs[i + 1][col]:
                    sorted_pairs[i] = True
        
        return deletions

# runtime - 2 ms
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        n = len(strs)
        m = len(strs[0])

        resolved = [False] * (n - 1)
        unresolved = n - 1
        deletions = 0

        for col in range(m):
            if unresolved == 0:
                break

            bad = False
            for i in range(n - 1):
                if not resolved[i] and strs[i][col] > strs[i + 1][col]:
                    bad = True
                    break

            if bad:
                deletions += 1
                continue

            for i in range(n - 1):
                if not resolved[i] and strs[i][col] < strs[i + 1][col]:
                    resolved[i] = True
                    unresolved -= 1

        return deletions
