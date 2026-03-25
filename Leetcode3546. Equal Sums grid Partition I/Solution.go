func canPartitionGrid(grid [][]int) bool {
    m, n := len(grid), len(grid[0])
    total := 0
    
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            total += grid[i][j]
        }
    }
    
    if total%2 != 0 {
        return false
    }
    
    target := total / 2
    sum := 0
    
    for i := 0; i < m-1; i++ {
        for j := 0; j < n; j++ {
            sum += grid[i][j]
        }
        if sum == target {
            return true
        }
    }
    
    sum = 0
    
    for j := 0; j < n-1; j++ {
        for i := 0; i < m; i++ {
            sum += grid[i][j]
        }
        if sum == target {
            return true
        }
    }
    
    return false
}
