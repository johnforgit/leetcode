class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_volume=0
        n=len(height)
        L=0 
        R=n-1
        while L < R:
            h = min(height[L], height[R])
            w=R-L
            area = w*h
            max_volume = max(area, max_volume)

            if height[L] < height[R]:
                L += 1
            else:
                R -= 1
        return max_volume
