function reverseSubmatrix(
    grid: number[][],
    x: number,
    y: number,
    k: number,
): number[][] {
    for (let i0 = x, i1 = x + k - 1; i0 < i1; i0++, i1--) {
        for (let j = y; j < y + k; j++) {
            [grid[i0][j], grid[i1][j]] = [grid[i1][j], grid[i0][j]];
        }
    }
    return grid;
}


// runtime - 0ms


function reverseSubmatrix(grid: number[][], x: number, y: number, k: number): number[][] {
    let row = x;
    let endRow = x + k - 1;
    let col = y;
    let endCol = y + k - 1;

    while (row <= endRow) {
        while (col <= endCol) {
            const temp = grid[row][col];
            grid[row][col] = grid[endRow][col];
            grid[endRow][col] = temp;
            col++;
        }
        col = y;
        row++;
        endRow--;
    }

    return grid;
};
