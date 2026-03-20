class Solution {

    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        int[][][] sum = new int[n + 1][m + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'X') {
                    sum[i + 1][j + 1][0] =
                        sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0] + 1;
                    sum[i + 1][j + 1][1] = 1;
                } else if (grid[i][j] == 'Y') {
                    sum[i + 1][j + 1][0] =
                        sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0] - 1;
                    sum[i + 1][j + 1][1] = sum[i + 1][j][1] | sum[i][j + 1][1];
                } else {
                    sum[i + 1][j + 1][0] =
                        sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0];
                    sum[i + 1][j + 1][1] = sum[i + 1][j][1] | sum[i][j + 1][1];
                }
                ans += (sum[i + 1][j + 1][0] == 0 && sum[i + 1][j + 1][1] == 1)
                    ? 1
                    : 0;
            }
        }
        return ans;
    }
}

// runtime - 16ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] colX = new int[n];
        int[] colY = new int[n];
        int ans = 0;


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                if(grid[i][j] == 'X') colX[j]++;
                if(grid[i][j] == 'Y') colY[j]++;
            }
            
            int x = 0, y = 0;
            for(int j = 0; j < n; j++) {

                x += colX[j];
                y += colY[j];

                if(x == y && x > 0) ans++;
            }
        }

        return ans;
    }
}


// runtime - 17ms
class Solution {
    static class Pair{
        int x;
        int y;
        int dot;
        Pair(int x,int y,int dot){
            this.x=x;
            this.y=y;
            this.dot=dot;
        }
    }
    public int numberOfSubmatrices(char[][] grid) {
        Pair arr[]=new Pair[grid[0].length];
        int x=0;int y=0;int dot=0;
        int cnt=0;
        for(int i=0;i<arr.length;i++){
           if(grid[0][i]=='X'){
                x++;
            }else if(grid[0][i]=='Y'){
                y++;
            }else{
                dot++;
            }
            arr[i]=new Pair(x,y,dot);
            if(arr[i].x == arr[i].y && arr[i].x > 0) {
                cnt++;
            }
        }
        for(int i=1;i<grid.length;i++){
            x=0;y=0;dot=0;
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]=='X'){
                    x++;
                }else if(grid[i][j]=='Y'){
                    y++;
                }else{
                    dot++;
                }
                arr[j].x+=x;
                arr[j].y+=y;
                arr[j].dot+=dot;
            }
            for(int k=0;k<arr.length;k++){
                if (arr[k].x == arr[k].y && arr[k].x > 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
}


// runtime - 18ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int[] xSum = new int[grid[0].length];
        int[] ySum = new int[grid[0].length];
        int x;
        int y;
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            x = 0;
            y = 0;

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    x++;
                } else if (grid[i][j] == 'Y') {
                    y++;
                }
                xSum[j] += x;
                ySum[j] += y;

                if (xSum[j] > 0 && xSum[j] == ySum[j]) {
                    result++;
                }
            }
        }

        return result;
    }
}


// runtime - 19ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
    	int res = 0;
    	int[] row = new int[grid[0].length];
    	boolean[] hasX = new boolean[grid[0].length];
    	for (int i = 0; i < grid.length; ++i) {
    		int rowSum = 0;
    		boolean rowHasX = false;
    		for (int j = 0; j < grid[0].length; j++) {
    			char cur = grid[i][j];
    			rowHasX |= cur == 'X';
    			hasX[j] = rowHasX || hasX[j];
    			int val = cur == 'X' ? 1 : cur == 'Y' ? -1 : 0;
				val += rowSum;
    			rowSum = val;
				val += row[j];
    			if (hasX[j] && val == 0) ++res; // TODO: check if at least one X present
    			row[j] = val;
    		}
    	}
        return res;
    }
}


// runtime - 20ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[2][n];
        int ans=0;
        int xCount=0;
        int yCount=0;
        for(int i=0;i<n;i++){
            if(grid[0][i]=='X') xCount++;
             if(grid[0][i]=='Y') yCount++;
            dp[0][i]=xCount;
            dp[1][i]=yCount;
            if(xCount!=0 && yCount==xCount) ans++;
        }
        for(int i=1;i<m;i++){
            xCount=0;
            yCount=0;
            for(int j=0;j<n;j++){
              if(grid[i][j]=='X') xCount++;
               if(grid[i][j]=='Y') yCount++;
               dp[0][j]=dp[0][j]+xCount;
               dp[1][j]=dp[1][j]+yCount;
               if(dp[0][j]!=0 && dp[0][j]==dp[1][j]) ans++;
            }
        }
        return ans;
    }
}


// runtime - 22ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] x = new int[n][m];
        int[][] y = new int[n][m];

        if(grid[0][0] == 'X') x[0][0] = 1;
        if(grid[0][0] == 'Y') y[0][0] = 1;

        for(int i = 1; i < m; i ++){

            if(grid[0][i] == 'X') {
                x[0][i] = 1 + x[0][i-1];
                y[0][i] = y[0][i-1];
            }else if(grid[0][i] == 'Y') {
                x[0][i] = x[0][i-1];
                y[0][i] = 1 + y[0][i-1];
            }else {
                x[0][i] = x[0][i-1];
                y[0][i] = y[0][i-1];
            }
        }

        for(int i = 1; i < n; i ++){

            if(grid[i][0] == 'X') {
                x[i][0] = 1 + x[i-1][0];
                y[i][0] = y[i-1][0];
            }else if(grid[i][0] == 'Y') {
                x[i][0] = x[i-1][0];
                y[i][0] = 1 + y[i-1][0];
            }else {
                x[i][0] = x[i-1][0];
                y[i][0] = y[i-1][0];
            }
        }

        for(int i = 1; i < n ; i++)  {

            for(int j = 1; j  < m ; j++) {

                if(grid[i][j] == 'X') {
                    x[i][j] = 1 + x[i-1][j] + x[i][j-1] - x[i-1][j-1];
                    y[i][j] = y[i-1][j] + y[i][j-1] - y[i-1][j-1];
                }else if(grid[i][j] == 'Y') {
                    x[i][j] = x[i-1][j] + x[i][j-1] - x[i-1][j-1];
                    y[i][j] = 1 + y[i-1][j] + y[i][j-1] - y[i-1][j-1];
                }else {
                    x[i][j] = x[i-1][j] + x[i][j-1] - x[i-1][j-1];
                    y[i][j] = y[i-1][j] + y[i][j-1] - y[i-1][j-1];
                }
            }
        }
        
        int count = 0;

        for(int i = 0; i < n ; i ++) {

            for(int j = 0; j < m ; j ++) {

                if(x[i][j] == y[i][j] && x[i][j] != 0) count ++;
            }
        }

        return count;
    }
}


// runtime - 23ms
class Solution {
        public int numberOfSubmatrices(char[][] A) {
        int n = A.length, m = A[0].length, res = 0;
        int[][] X = new int[n + 1][m + 1], Y = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                X[i + 1][j + 1] = X[i][j + 1] + X[i + 1][j] - X[i][j] + (A[i][j] == 'X' ? 1 : 0);
                Y[i + 1][j + 1] = Y[i][j + 1] + Y[i + 1][j] - Y[i][j] + (A[i][j] == 'Y' ? 1 : 0);
                if (X[i + 1][j + 1] == Y[i + 1][j + 1] && X[i + 1][j + 1] > 0) {
                    res++;
                }
            }
        }
        return res;
    }
}


// runtime - 24ms
class Solution {

    public int numberOfSubmatrices(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] prefixX = new int[m+1][n+1];
        int[][] prefixY = new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){

                prefixX[i][j] =
                    prefixX[i-1][j] +
                    prefixX[i][j-1] -
                    prefixX[i-1][j-1];

                prefixY[i][j] =
                    prefixY[i-1][j] +
                    prefixY[i][j-1] -
                    prefixY[i-1][j-1];

                if(grid[i-1][j-1]=='X')
                    prefixX[i][j]++;

                if(grid[i-1][j-1]=='Y')
                    prefixY[i][j]++;
            }
        }

        int ans = 0;

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){

                int countX = prefixX[i][j];
                int countY = prefixY[i][j];

                if(countX == countY && countX > 0)
                    ans++;
            }
        }

        return ans;
    }
}


// runtime - 25ms
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] px = new int[n + 1][m + 1];
        int[][] py = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                px[i][j] = px[i-1][j] + px[i][j-1] - px[i-1][j-1];
                py[i][j] = py[i-1][j] + py[i][j-1] - py[i-1][j-1];

                if (grid[i-1][j-1] == 'X') px[i][j]++;
                if (grid[i-1][j-1] == 'Y') py[i][j]++;
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                int x = px[i][j];
                int y = py[i][j];

                if (x > 0 && x == y) ans++;
            }
        }

        return ans;
    }
}
