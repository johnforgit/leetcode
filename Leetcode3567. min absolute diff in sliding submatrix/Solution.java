class Solution {

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m - k + 1][n - k + 1];
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                List<Integer> kgrid = new ArrayList<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        kgrid.add(grid[x][y]);
                    }
                }
                int kmin = Integer.MAX_VALUE;
                Collections.sort(kgrid);
                for (int t = 1; t < kgrid.size(); t++) {
                    if (kgrid.get(t).equals(kgrid.get(t - 1))) {
                        continue;
                    }
                    kmin = Math.min(kmin, kgrid.get(t) - kgrid.get(t - 1));
                }
                if (kmin != Integer.MAX_VALUE) {
                    res[i][j] = kmin;
                }
            }
        }
        return res;
    }
}



// runtime - 10ms
class Solution {
    public int getmn(int[] arr){
        Arrays.sort(arr);
        int mn = Integer.MAX_VALUE;
        for(int i = 1;i< arr.length;i++){
            if(arr[i - 1] != arr[i]){
                mn = Math.min(mn, Math.abs(arr[i] - arr[i - 1]));
            }
            
        }
        return mn == Integer.MAX_VALUE? 0:mn;
    }
    public int[][] minAbsDiff(int[][] grid, int k) {
        // 1 1 1
        // 1 1 1
        // 1 1 1
        int m = grid.length, n = grid[0].length;
        int[][] answ = new int[m - k + 1][n - k + 1];
        for(int row = 0; row < m - k + 1;row++){
            for(int col = 0; col < n - k + 1;col++){
                int[] arr = new int[k * k];
                int idx = 0;
                for(int i = row;i < row + k;i++){
                    for(int j = col;j < col + k;j++){
                        arr[idx] = grid[i][j];
                        idx++;
                    }
                }
                answ[row][col] = getmn(arr);
                

            }
        }
        return answ;
    }
}


// runtime - 11ms
class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int [][]res = new int[grid.length-k+1][grid[0].length-k+1];
        for(int i=k-1; i<grid.length; i++) {
            for(int j=k-1;j<grid[0].length;j++) {
                int []temp = new int[k*k];
                for(int ii=i-k+1;ii<=i;ii++) {
                    for(int jj=j-k+1;jj<=j;jj++) {
                        temp[(ii-(i-k+1))*k + (jj-(j-k+1))] = grid[ii][jj];
                    }
                }
                Arrays.sort(temp);
                res[i-(k-1)][j-(k-1)] = temp[temp.length-1] - temp[0];
                for(int kk=1; kk<temp.length; kk++) {
                    if(temp[kk]==temp[kk-1]) {
                        continue;
                    }
                    res[i-(k-1)][j-(k-1)] = Math.min(res[i-(k-1)][j-(k-1)], temp[kk]-temp[kk-1]);
                }
            }
        }
        return res;
    }
}



// runtime - 12ms
class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        for(int[] a: ans) Arrays.fill(a,Integer.MAX_VALUE);

        for(int i=0; i<=(m-k); i++){
            for(int j=0; j<=(n-k); j++){
                int[] arr = new int[k*k];
                int c =0;
                for(int a=i; a<i+k; a++){
                    for(int b=j; b<j+k; b++){
                        arr[c++]=grid[a][b];
                    }
                }
                Arrays.sort(arr);
                for(int a=0; a<arr.length-1; a++){
                    if(arr[a]!=arr[a+1]) ans[i][j]=Math.min(ans[i][j],arr[a+1]-arr[a]);
                }
                if(ans[i][j]==Integer.MAX_VALUE) ans[i][j]=0;
            }
        }
        return ans;
    }
}


// runtime - 13ms
class Solution {
    int getMin(int[][] g,int k,int x,int y){
        int m = g.length,n = g[0].length;
        int[] temp = new int[k*k];
        int st = 0;
        //linearize the submatrix 
        for(int i = 0;i<k;i++){
            for(int j = 0;j<k;j++){
                temp[st++] = g[i+x][j+y];
            }
        }
        //sort the linear array
        Arrays.sort(temp);
        int diff = Integer.MAX_VALUE;
        for(int i = 1;i<k*k;i++){
            //skip the same elements
            while(i<k*k && temp[i]==temp[i-1]) i++;
            if(i<k*k)  diff = Math.min(diff,Math.abs(temp[i]-temp[i-1]));
        }
        //return min diff
        return diff;
    }
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length,n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        //generate all submatrix
        for(int i = 0;i<=m-k;i++){
            for(int j = 0;j<=n-k;j++){
                int temp = getMin(grid,k,i,j);
                ans[i][j] = temp==Integer.MAX_VALUE?0:temp;
            }
        }
        return ans;
    }
}



// runtime - 15ms
class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int rows = m - k + 1;
        int cols = n - k + 1;
        int[][] ans = new int[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                int[] temp = new int[k*k];
                int index = 0;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        temp[index++] = grid[x][y];
                    }
                }
                Arrays.sort(temp);
                int minDiff = Integer.MAX_VALUE;
                for (int l = 1; l < temp.length; l++) {
                    if (temp[l] != temp[l - 1]) { 
                        int diff = temp[l] - temp[l - 1];
                        if (diff < minDiff) {
                            minDiff = diff;
                        }
                    }
                }  
                if (minDiff == Integer.MAX_VALUE) {
                    minDiff = 0;
                }
                ans[i][j] = minDiff;               
            }
        }
        return ans;
    }
}


// runtime - 16ms
class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int[][] ans = new int[grid.length-k+1][grid[0].length-k+1];
        for(int i = 0;i<ans.length;i++){
            for(int j = 0;j<ans[0].length;j++){
                int[] arr = new int[k*k];
                int z = 0;
                for(int m = i;m<i+k;m++){
                    for(int l = j;l<j+k;l++){
                        arr[z++] = grid[m][l];
                   }
                }
                Arrays.sort(arr);
                int minsum = Integer.MAX_VALUE;
                boolean present = true;
                for(int y = 0;y<arr.length-1;y++){
                    int diff = Math.abs(arr[y]-arr[y+1]);
                    if(arr[y]==arr[y+1])continue;
                    else if(diff<=minsum){
                        minsum = diff;
                        present = false;
                    }
                }
                if(present)ans[i][j] = 0;
                else ans[i][j] = minsum;
            }
        }
        return ans;
    }
}


// runtime - 17ms
class Solution {
    int getMin(int[][] g,int k,int x,int y){
        int m = g.length,n = g[0].length;
        int[] temp = new int[k*k];
        int st = 0;
        for(int i = 0;i<k;i++){
            for(int j = 0;j<k;j++){
                temp[st++] = g[i+x][j+y];
            }
        }
        Arrays.sort(temp);
        int diff = Integer.MAX_VALUE;
        for(int i = 1;i<k*k;i++){
            while(i<k*k && temp[i]==temp[i-1]) i++;
            if(i<k*k)  diff = Math.min(diff,Math.abs(temp[i]-temp[i-1]));
        }
        return diff;
    }
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length,n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        for(int i = 0;i<=m-k;i++){
            for(int j = 0;j<=n-k;j++){
                int temp = getMin(grid,k,i,j);
                ans[i][j] = temp==Integer.MAX_VALUE?0:temp;
            }
        }
        return ans;
    }
}
