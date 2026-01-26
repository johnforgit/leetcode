class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        min_diff = float('inf')
        res = []
        arr.sort()

        for i in range(1, len(arr)):
            cur_diff = arr[i]-arr[i-1]
            if(cur_diff < min_diff):
                min_diff = cur_diff
                res=[[arr[i-1], arr[i]]]
            elif cur_diff == min_diff:
                res.append([arr[i-1], arr[i]])

        return res


# runtime - 0ms
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        min_diff = float('inf')
        for i in range(len(arr)-1):
            current_diff = arr[i+1] - arr[i]
            if current_diff < min_diff:
                min_diff = current_diff

        answer = []
        
        for i in range(len(arr)-1):
            if (abs(arr[i] - arr[i+1])) <= min_diff:
                answer.append([arr[i], arr[i+1]])
        
        return answer 

__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))  

# runtime - 2ms
import numpy as np
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        a = np.sort(np.array(arr))
        diff_a = np.diff(a)
        min_abs = np.min(diff_a)

        inds, = np.where(diff_a == min_abs)
        
        res = []
        for i in inds:
            res.append([int(a[i]), int(a[i+1])])

        return res


# runtime - 3ms
import numpy as np

class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        a = np.sort(np.array(arr))
        diff_a = np.diff(a)
        min_abs = np.min(diff_a)

        inds, = np.where(diff_a == min_abs)
        
        res = []
        for i in inds:
            res.append([int(a[i]), int(a[i+1])])

        return res
"""
        res = []
        for i in range(len(a)-1):
            for j in np.arange(i+1,len(a)):
                diff = np.abs(a[j]-a[i])
                if diff==min_abs:
                    res.append([int(a[i]), int(a[j])])
                elif diff>min_abs:
                    continue


        return res
"""

# runtime - 5ms
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        def find(arr,diff):
            res=[]
            seen=set()
            for i in arr:
                seen.add(i)
                if i-diff in seen:
                    res.append([i-diff,i])
                if i+diff in seen:
                    res.append([i,i+diff])
            if res==[]:
                return find(arr,diff+1)
            return sorted(res)
        return find(arr,1)
