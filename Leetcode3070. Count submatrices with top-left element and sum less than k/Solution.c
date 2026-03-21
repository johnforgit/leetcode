int countSubmatrices(int** grid, int gridSize, int* gridColSize, int k) {
    int n = gridSize;
    int m = *gridColSize;
    int* cols = (int*)calloc(m, sizeof(int));
    int res = 0;

    for (int i = 0; i < n; i++) {
        int rows = 0;
        for (int j = 0; j < m; j++) {
            cols[j] += grid[i][j];
            rows += cols[j];
            if (rows <= k) {
                res++;
            }
        }
    }

    free(cols);
    return res;
}
