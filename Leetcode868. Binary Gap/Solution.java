class Solution {
    public int binaryGap(int n) {
        int gap = 0;
        int last =-1;
        for(int i=0; i<32; ++i) {
            if(((n >> i) & 1) > 0) {
                if(last >= 0)
                    gap = Math.max(gap, i-last);
                last = i;
            }
        }
        return gap;
    }
}
