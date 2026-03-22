impl Solution {
    pub fn reverse_submatrix(grid: Vec<Vec<i32>>, x: i32, y: i32, k: i32) -> Vec<Vec<i32>> {
        let x = x as usize;
        let y = y as usize;
        let k = k as usize;
        let mut grid = grid;
        let mut i0 = x;
        let mut i1 = x + k - 1;
        
        while i0 < i1 {
            for j in y..y + k {
                let temp = grid[i0][j];
                grid[i0][j] = grid[i1][j];
                grid[i1][j] = temp;
            }
            i0 += 1;
            i1 -= 1;
        }
        
        grid
    }
}
