class Solution {
    public int countNegatives(int[][] grid) {
        int index = grid[0].length-1;
        int res = 0;

        for(int[] row:grid) {
            while(index>=0 && row[index]<0) {
                index--;
            }
            res += (grid[0].length-1) - index;
        }
        return res;
    }
}

class Solution {
    public int countNegatives(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int i=rows-1;
        int j = 0;
        int res=0;
        while(i>=0 && j<cols) {
            if(grid[i][j]<0) {
                res += cols-j;
                i--;
            } else {
                j++;
            }
        }

        return res;
    }
}
