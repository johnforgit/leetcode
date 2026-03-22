var reverseSubmatrix = function (grid, x, y, k) {
    for (let i0 = x, i1 = x + k - 1; i0 < i1; i0++, i1--) {
        for (let j = y; j < y + k; j++) {
            [grid[i0][j], grid[i1][j]] = [grid[i1][j], grid[i0][j]];
        }
    }
    return grid;
};

// runtime - 1ms
/**
 * @param {number[][]} grid
 * @param {number} x
 * @param {number} y
 * @param {number} k
 * @return {number[][]}
 */
var reverseSubmatrix = function(grid, x, y, k) {
    let subMatrix = [];
    for (let i = 0; i < k; i++) {
        subMatrix[i] = [];
        for (let j = 0; j < k; j++) {
            subMatrix[i][j] = grid[x + i][y + j];
        }
    }

    subMatrix.reverse();

    for (let i = 0; i < k; i++) {
        for (let j = 0; j < k; j++) {
            grid[x + i][y + j] = subMatrix[i][j];
        }
    }

    return grid;
};


// runtime - 0ms
/**
 * @param {number[][]} grid
 * @param {number} x
 * @param {number} y
 * @param {number} k
 * @return {number[][]}
 */
var reverseSubmatrix = function(grid, x, y, k) {
  let start = x;
  let end = x + k - 1;

  while (start < end) {
    for (let i = y; i < y + k; i++) {
      let current = grid[start][i];
      grid[start][i] = grid[end][i];
      grid[end][i] = current;
    }

    start++;
    end--;
  }

  return grid;
};
