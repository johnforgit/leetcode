class Solution:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        hBars.sort()
        vBars.sort()
        hmax, vmax = 1,1
        hcur, vcur = 1,1

        for i in range(1, len(hBars)):
            if hBars[i]==hBars[i-1]+1:
                hcur += 1
            else:
                hcur = 1
            hmax = max(hmax, hcur)

        for i in range(1, len(vBars)):
            if vBars[i]==vBars[i-1]+1:
                vcur += 1
            else:
                vcur = 1
            vmax = max(vmax, vcur)

        side = min(hmax, vmax)+1
        return side*side


# runtime - 0 ms
class Solution:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        h = len(hBars)
        v = len(vBars)
        hBars.sort()
        vBars.sort()

        def find_continuous_chunk(nums) -> list:
            if not nums:
                print("warning: empty inputs")
                return []
            counts = 1
            res = 0
            n = len(nums)
            for i in range(n-1):
                if nums[i] == nums[i+1] - 1:
                    counts += 1
                    res = max(res, counts)
                else:
                    counts = 1
            return max(res, counts)
        
        hcounts = find_continuous_chunk(hBars)
        vcounts = find_continuous_chunk(vBars)
        return (min(vcounts, hcounts)+1) ** 2
