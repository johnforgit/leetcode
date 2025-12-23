class Solution {
    public int maxArea(int[] height) {
        int n=height.length;
        int maxVolume = 0;
        int L=0, R=n-1;
        while (L < R) {
            int w = R-L;
            int h=Math.min(height[L], height[R]);
            int area=w*h;
            maxVolume = Math.max(area, maxVolume);

            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return maxVolume;
    }
}
