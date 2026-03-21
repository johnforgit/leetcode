func countSubmatrices(grid [][]int, k int) int {
	n := len(grid)
	m := len(grid[0])
	cols := make([]int, m)
	res := 0

	for i := 0; i < n; i++ {
		rows := 0
		for j := 0; j < m; j++ {
			cols[j] += grid[i][j]
			rows += cols[j]
			if rows <= k {
				res++
			}
		}
	}

	return res
}
