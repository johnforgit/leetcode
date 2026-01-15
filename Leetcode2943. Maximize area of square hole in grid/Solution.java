class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int hmax=1, vmax=1;
        int hcur=1, vcur=1;

        // find the max height in consecutive horizontal segments
        for(int i=1; i<hBars.length; i++) {
            if(hBars[i] == hBars[i-1]+1) {
                hcur++;
            } else {
                hcur = 1;
            }
            hmax = Math.max(hmax, hcur);
        }

        for(int i=1; i<vBars.length; i++) {
            if(vBars[i]==vBars[i-1]+1) {
                vcur++;
            } else {
                vcur=1;
            }
            vmax = Math.max(vmax, vcur);
        }

        int side = Math.min(hmax, vmax)+1;
        return side*side;
    }
}

// runtime - 0 ms
class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int max_hori = 0;
        int max_ver = 0;
        int cnt =  0;

        for(int i=0;i<hBars.length-1;i++){

            if(hBars[i+1]-hBars[i] == 1 ) cnt +=1;
            else cnt=0;

            max_hori = Math.max(max_hori,cnt);
        }

        cnt = 0;

        for(int i=0;i<vBars.length-1;i++){

            if(vBars[i+1]-vBars[i] == 1 ) cnt +=1;
            else cnt=0;

            max_ver = Math.max(max_ver,cnt);
        }

        int side = Math.min(max_hori,max_ver) + 2;

        return side*side;
    }
}
