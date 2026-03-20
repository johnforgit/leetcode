impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k = k as usize;
        let m = grid.len();
        let n = grid[0].len();
        let mut res = vec![vec![0; n - k + 1]; m - k + 1];
        for i in 0..=m - k {
            for j in 0..=n - k {
                let mut kgrid = Vec::new();
                for x in i..i + k {
                    for y in j..j + k {
                        kgrid.push(grid[x][y]);
                    }
                }
                let mut kmin = i32::MAX;
                kgrid.sort();
                for t in 1..kgrid.len() {
                    if kgrid[t] == kgrid[t - 1] {
                        continue;
                    }
                    kmin = std::cmp::min(kmin, kgrid[t] - kgrid[t - 1]);
                }
                if kmin != i32::MAX {
                    res[i][j] = kmin;
                }
            }
        }
        res
    }
}



// runtime - 0ms
impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k_usize = k as usize;
        let rows = grid.len();
        let cols = grid[0].len();
        let target_rows = (rows - k_usize + 1);
        let target_cols = (cols - k_usize + 1);
        let mut res = vec![vec![0_i32; target_cols]; target_rows];

        if k_usize == 1 {
            return res;
        }

        for i in 0..target_rows {
            for j in 0..target_cols {
                let mut temp  = Vec::with_capacity(k_usize * k_usize);

                for i_grid in i..(i + k_usize) {
                    for j_grid in j..(j + k_usize) {
                        temp.push(grid[i_grid][j_grid]);
                    }
                }

                temp.sort_unstable();
                let mut min = i32::MAX;
                for w in temp.windows(2) {
                    if w[0] != w[1] {
                        min = min.min((w[1] - w[0]).abs());
                    }
                }

                if min != i32::MAX {
                    res[i][j] = min;
                }
            }
        }

        res
    }
}


// runtime - 1ms
impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k = k as usize;
        let mut result = vec![vec![0; grid[0].len() + 1 - k]; grid.len() + 1 - k];

        for i in 0..grid.len() + 1 - k {
            for j in 0..grid[0].len() + 1 - k {
                let mut values = Vec::new();
                for x in i..i + k {
                    for y in j..j + k {
                        values.push(grid[x][y]);
                    }
                }
                values.sort_unstable();
                if values.len() == 1 {
                    result[i][j] = 0;
                } else {
                    result[i][j] = (1..values.len())
                        .map(|l| (values[l - 1] - values[l]).abs())
                        .filter(|d| &0 < d)
                        .min()
                        .unwrap_or(0);
                }
            }
        }
        result
    }
}



// runtime - 2ms
impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k = k as usize;
        let m = grid.len();
        let n = grid[0].len();
        let mut res = vec![vec![0; n - k + 1]; m - k + 1];
        for i in 0..=m - k {
            for j in 0..=n - k {
                let mut kgrid = Vec::new();
                for x in i..i + k {
                    for y in j..j + k {
                        kgrid.push(grid[x][y]);
                    }
                }
                let mut kmin = i32::MAX;
                kgrid.sort();
                for t in 1..kgrid.len() {
                    if kgrid[t] == kgrid[t - 1] {
                        continue;
                    }
                    kmin = std::cmp::min(kmin, kgrid[t] - kgrid[t - 1]);
                }
                if kmin != i32::MAX {
                    res[i][j] = kmin;
                }
            }
        }
        res
    }
}


// runtime - 3ms
impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k = k as usize;
        let m = grid.len();
        let n = grid[0].len();
        let rows_out = m - k + 1;
        let cols_out = n - k + 1;
        let mut ans = vec![vec![0i32; cols_out]; rows_out];

        for i in 0..rows_out {
            for j in 0..cols_out {
                // Collect all values in submatrix [i..i+k][j..j+k]
                let mut vals: Vec<i32> = Vec::with_capacity(k * k);
                for r in i..i + k {
                    for c in j..j + k {
                        vals.push(grid[r][c]);
                    }
                }
                vals.sort_unstable();
                vals.dedup();
                if vals.len() < 2 {
                    ans[i][j] = 0;
                } else {
                    let mut min_diff = i32::MAX;
                    for idx in 1..vals.len() {
                        let d = vals[idx] - vals[idx - 1];
                        if d < min_diff {
                            min_diff = d;
                        }
                    }
                    ans[i][j] = min_diff;
                }
            }
        }
        ans
    }
}
