var countSubmatrices = function (grid, k) {
    const n = grid.length;
    const m = grid[0].length;
    const cols = new Array(m).fill(0);
    let res = 0;

    for (let i = 0; i < n; i++) {
        let rows = 0;
        for (let j = 0; j < m; j++) {
            cols[j] += grid[i][j];
            rows += cols[j];
            if (rows <= k) {
                res++;
            }
        }
    }

    return res;
};
