impl Solution {
    pub fn can_partition_grid(grid: Vec<Vec<i32>>) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let mut total: i64 = 0;
        
        for i in 0..m {
            for j in 0..n {
                total += grid[i][j] as i64;
            }
        }
        
        if total % 2 != 0 {
            return false;
        }
        
        let target = total / 2;
        let mut sum: i64 = 0;
        
        for i in 0..m-1 {
            for j in 0..n {
                sum += grid[i][j] as i64;
            }
            if sum == target {
                return true;
            }
        }
        
        sum = 0;
        
        for j in 0..n-1 {
            for i in 0..m {
                sum += grid[i][j] as i64;
            }
            if sum == target {
                return true;
            }
        }
        
        false
    }
}
