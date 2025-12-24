import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        int num_boxes=0;
        Arrays.sort(capacity);
        for(int a:apple) 
            totalApples += a;
        for(int i=capacity.length-1; i>=0; i--) {
            if(totalApples <= 0)
                break;
            totalApples -= capacity[i];
            num_boxes++;
        }
        return num_boxes;
    }
}
