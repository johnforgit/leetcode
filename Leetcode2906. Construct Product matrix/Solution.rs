impl Solution {
    pub fn construct_product_matrix(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        const MOD: i64 = 12345;
        let n = grid.len();
        let m = grid[0].len();
        let mut p = vec![vec![0; m]; n];
        
        let mut suffix: i64 = 1;
        for i in (0..n).rev() {
            for j in (0..m).rev() {
                p[i][j] = (suffix % MOD) as i32;
                suffix = (suffix * grid[i][j] as i64) % MOD;
            }
        }
        
        let mut prefix: i64 = 1;
        for i in 0..n {
            for j in 0..m {
                p[i][j] = ((p[i][j] as i64 * prefix) % MOD) as i32;
                prefix = (prefix * grid[i][j] as i64) % MOD;
            }
        }
        
        p
    }
}


runtime - 9ms
use std::iter::once;
impl Solution
{
    pub fn construct_product_matrix(mut grid: Vec<Vec<i32>>) -> Vec<Vec<i32>>
    {
        let prefix = grid
            .iter().flatten()
            .scan(1, |p, n| { *p = (*p * (*n % 12345)) % 12345; Some(*p) })
            .collect::<Vec<i32>>();

        let suffix = grid
            .iter().flatten().rev()
            .scan(1, |p, n| { *p = (*p * (*n % 12345)) % 12345; Some(*p) })
            .collect::<Vec<i32>>();

        once(&1).chain(prefix.iter())
            .zip(suffix.iter().rev().skip(1).chain(once(&1)))
            .map(|(p, s)| (p * s) % 12345)
            .collect::<Vec<i32>>()
            .chunks(grid[0].len())
            .map(|c| c.to_vec())
            .collect::<Vec<Vec<i32>>>()            
    }
}


runtime - 8ms
impl Solution {
    pub fn construct_product_matrix(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let modulo: i32 = 12345;
        let n = grid.len();
        let m = grid[0].len();
        let mut p: Vec<Vec<i32>> = vec![vec![0; m]; n];
        let mut suf = 1;

        for i in (0..n).rev() {
            for j in (0..m).rev() {
                p[i][j] = suf;
                suf = (((suf as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        let mut pre = 1;

        for i in 0..n {
            for j in 0..m {
                p[i][j] = (((p[i][j] as i64) * (pre as i64)) % (modulo as i64)) as i32;
                pre = (((pre as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        p
    }
}
