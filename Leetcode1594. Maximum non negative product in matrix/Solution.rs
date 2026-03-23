impl Solution {
    pub fn max_product_path(grid: Vec<Vec<i32>>) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let m = grid.len();
        let n = grid[0].len();
        let mut maxgt = vec![vec![0i64; n]; m];
        let mut minlt = vec![vec![0i64; n]; m];
        maxgt[0][0] = grid[0][0] as i64;
        minlt[0][0] = grid[0][0] as i64;
        
        for i in 1..n {
            maxgt[0][i] = maxgt[0][i-1] * grid[0][i] as i64;
            minlt[0][i] = maxgt[0][i];
        }
        for i in 1..m {
            maxgt[i][0] = maxgt[i-1][0] * grid[i][0] as i64;
            minlt[i][0] = maxgt[i][0];
        }
        for i in 1..m {
            for j in 1..n {
                let grid_val = grid[i][j] as i64;
                if grid_val >= 0 {
                    let max_prev = maxgt[i][j-1].max(maxgt[i-1][j]);
                    let min_prev = minlt[i][j-1].min(minlt[i-1][j]);
                    maxgt[i][j] = max_prev * grid_val;
                    minlt[i][j] = min_prev * grid_val;
                } else {
                    let max_prev = maxgt[i][j-1].max(maxgt[i-1][j]);
                    let min_prev = minlt[i][j-1].min(minlt[i-1][j]);
                    maxgt[i][j] = min_prev * grid_val;
                    minlt[i][j] = max_prev * grid_val;
                }
            }
        }
        
        let result = maxgt[m-1][n-1];
        if result < 0 {
            -1
        } else {
            (result % MOD) as i32
        }
    }
}
