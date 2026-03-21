impl Solution {
    pub fn count_submatrices(grid: Vec<Vec<i32>>, k: i32) -> i32 {
        let n = grid.len();
        let m = grid[0].len();
        let mut cols = vec![0; m];
        let mut res = 0;

        for i in 0..n {
            let mut row_sum = 0;
            for j in 0..m {
                cols[j] += grid[i][j];
                row_sum += cols[j];
                if row_sum <= k {
                    res += 1;
                }
            }
        }

        res
    }
}

// runtime - 0ms
impl Solution {
    pub fn count_submatrices(grid: Vec<Vec<i32>>, k: i32) -> i32 {
        let n = grid.len();
        let m = grid[0].len();
        let mut cols = vec![0; m];
        let mut res = 0;

        for i in 0..n {
            let mut row_sum = 0;
            for j in 0..m {
                cols[j] += grid[i][j];
                row_sum += cols[j];
                if row_sum <= k {
                    res += 1;
                }
            }
        }

        res
    }
}
