class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int rows=mat.length;
        int cols = mat[0].length;
        List<Integer> res = new ArrayList<>();
        boolean going_up = true;
        int cur_row=0, cur_col=0;
        while(res.size() != rows*cols) {
            if (going_up){
                while(cur_row>=0 && cur_col<cols) {
                    res.add(mat[cur_row][cur_col]);
                    cur_row--;
                    cur_col++;
                }
                // if we break the boundaries of the matrix
                if(cur_col==cols) {
                    cur_col--;
                    cur_row += 2;
                } else {
                    cur_row += 1;
                }

                going_up=false;
            }
            else {
                while(cur_row<rows && cur_col >=0) {
                    res.add(mat[cur_row][cur_col]);
                    cur_row++;
                    cur_col--;
                }
                if(cur_row==rows) {
                    cur_col += 2;
                    cur_row--;
                } else {
                    cur_col++;
                }
                going_up=true;
            }
        }
        int[] Res = res.stream().mapToInt(Integer::intValue).toArray();
        return Res;
    }
}


// another solution. runtime - 0ms
class Solution {
    static {
        for (int i = 0; i < 300; i++)
            findDiagonalOrder(new int[][] { { 1, 2 }, { 3, 4 } });
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        boolean up = true;
        int i = 0;
        int j = 0;
        int idx = 0;
        int[] result = new int[mat.length * mat[0].length];
        while (idx < result.length) {
            while (idx < result.length && i >= 0 && j < mat[i].length) {
                result[idx] = mat[i][j];
                i--;
                j++;
                idx++;
            }
            i += 1 + (j == mat[0].length ? 1 : 0);
            j = Math.min(mat[0].length - 1, j);
            while (idx < result.length && j >= 0 && i < mat.length) {
                result[idx] = mat[i][j];
                i++;
                j--;
                idx++;
            }
            j += 1 + (i == mat.length ? 1 : 0);
            i = Math.min(mat.length - 1, i);
        }
        result[result.length - 1] = mat[mat.length - 1][mat[0].length - 1];
        return result;
    }
}

// runtime - 1ms
class Solution {
    private int[] res;
    private int itr, dir;

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        res = new int[m * n];
        itr = 0; 
        dir = 1;
        traverse(0, 0, mat, m, n);
        return res;
    }

    private void traverse(int i, int j, int[][] grid, int m, int n) {
        if (itr == m * n) return;

        res[itr++] = grid[i][j];

        int newI = i, newJ = j;

        if (dir == 1) {
            newI = i - 1;
            newJ = j + 1;
        } else {
            newI = i + 1;
            newJ = j - 1;
        }

        if (newI < 0 && newJ < n) {
            newI = 0;
            dir = -1;
        } else if (newJ == n) {
            newI = i + 1;
            newJ = n - 1;
            dir = -1;
        } else if (newJ < 0 && newI < m) {
            newJ = 0;
            dir = 1;
        } else if (newI == m) {
            newI = m - 1;
            newJ = j + 1;
            dir = 1;
        }

        traverse(newI, newJ, grid, m, n);
    }
}

// runtime - 2ms
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m * n];
        int r = 0, c = 0, dir = 1; 
        int idx = 0;
        while (idx < m * n) {
            ans[idx++] = mat[r][c];
            if (dir == 1) { 
                if (c == n - 1) {
                    r++;
                    dir = -1;
                } else if (r == 0) {
                    c++;
                    dir = -1;
                } else {
                    r--;
                    c++;
                }
            } else {
                if (r == m - 1) {
                    c++;
                    dir = 1;
                } else if (c == 0) {
                    r++;
                    dir = 1;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return ans;
    }
}

// runtime - 3ms
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        /*[0 0] 
        [0,1] [1,0]
        [2,0] [1,1] [0,2]
        [1,2] [2 1]
        [2,2]*/
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m*n];
        int i=0, j=0;

        for (int k=0; k < m*n; k++) {
                result[k] = mat[i][j];
                if ((i + j) % 2==0) {
                    if (j == n-1) { // hit last column
                        i++;
                    }
                    else if (i==0) { // hit top
                        j++;
                    }    
                     
                    else {
                        i--;
                        j++;
                    }
                }
                else  {// move down left
                   if (i == m-1) {//last row
                        j++;
                    }
                    else if (j==0) { // first column
                        i++; // move down
                    }    
                    
                    else {// move down left
                        i++;
                        j--;
                    }
                }
            }
            return result;
        }
    }

    // public int[] findDiagonalOrder(int[][] mat) {
    //     int row = 0, col = 0, rows = mat.length, cols = mat[0].length;
    //     int result[] = new int[rows * cols];

    //     for (int i = 0; i < rows * cols; i++) {
    //         result[i] = mat[row][col];
    //         if ((row + col) % 2 == 0) {
    //             if (col == cols - 1) {
    //                 row++;
    //             } else if (row == 0) {
    //                 col++;
    //             } else {
    //                 row--;
    //                 col++;
    //             }
    //         } else {
    //             if (row == rows - 1) {
    //                 col++;
    //             } else if (col == 0) {
    //                 row++;
    //             } else {
    //                 row++;
    //                 col--;
    //             }
    //         }
    //     }
    //     return result;
    // }
// }

// runtime - 4ms
class Solution {
  public int[] findDiagonalOrder(int[][] matrix) {
    final int m = matrix.length;
    final int n = matrix[0].length;
    int[] ans = new int[m * n];
    int d = 1; // left-bottom -> right-top
    int row = 0;
    int col = 0;

    for (int i = 0; i < m * n; ++i) {
      ans[i] = matrix[row][col];
      row -= d;
      col += d;
      // out-of-bounds
      if (row == m) {
        row = m - 1;
        col += 2;
        d = -d;
      }
      if (col == n) {
        col = n - 1;
        row += 2;
        d = -d;
      }
      if (row < 0) {
        row = 0;
        d = -d;
      }
      if (col < 0) {
        col = 0;
        d = -d;
      }
    }

    return ans;
  }
}
