func constructProductMatrix(grid [][]int) [][]int {
	const MOD = 12345
	n, m := len(grid), len(grid[0])
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, m)
	}

	suffix := int64(1)
	for i := n - 1; i >= 0; i-- {
		for j := m - 1; j >= 0; j-- {
			p[i][j] = int(suffix)
			suffix = (suffix * int64(grid[i][j])) % MOD
		}
	}

	prefix := int64(1)
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			p[i][j] = int((int64(p[i][j]) * prefix) % MOD)
			prefix = (prefix * int64(grid[i][j])) % MOD
		}
	}

	return p
}


// runtime - 0ms
func constructProductMatrix(grid [][]int) [][]int {
	const MOD = 12345
	const SHIFT = 32
	const MASK = 1<<SHIFT - 1
	prefix := 1
	for i, row := range grid {
		for j := range row {
			val := grid[i][j]
			grid[i][j] = (prefix << SHIFT) | (val % MOD)
			prefix = (prefix * (val % MOD)) % MOD
		}
	}
	suffix := 1
	for i := len(grid) - 1; i >= 0; i-- {
		for j := len(grid[i]) - 1; j >= 0; j-- {
			prefix, val := grid[i][j]>>SHIFT, grid[i][j]&MASK
			grid[i][j] = (prefix * suffix) % MOD
			suffix = (suffix * val) % MOD
		}
	}
	return grid
}



// runtime - 1ms
func constructProductMatrix(grid [][]int) [][]int {
	const MOD = 12345
	const SHIFT = 16
	const MASK = 1<<SHIFT - 1
	prefix := 1
	for i, row := range grid {
		for j := range row {
			val := grid[i][j]
			grid[i][j] = (prefix << SHIFT) | (val % MOD)
			prefix = (prefix * (val % MOD)) % MOD
		}
	}
	suffix := 1
	for i := len(grid) - 1; i >= 0; i-- {
		for j := len(grid[i]) - 1; j >= 0; j-- {
			prefix, val := grid[i][j]>>SHIFT, grid[i][j]&MASK
			grid[i][j] = (prefix * suffix) % MOD
			suffix = (suffix * val) % MOD
		}
	}
	return grid
}
