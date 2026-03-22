// runtime - 0ms (using bit masking)
const findRotation = (mat, target) => {
    const n = mat.length;
    let m = 0b1111;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] !== target[i][j]) m &= 0b1110;
            if (mat[i][j] !== target[j][n - 1 - i]) m &= 0b1101;
            if (mat[i][j] !== target[n - 1 - i][n - 1 - j]) m &= 0b1011;
            if (mat[i][j] !== target[n - 1 - j][i]) m &= 0b0111;
            if (m === 0) return false;
        }
    }

    return m !== 0;
};

var findRotation = function (mat, target) {
    const n = mat.length;
    // at most 4 rotations
    for (let k = 0; k < 4; k++) {
        // rotation operation
        for (let i = 0; i < Math.floor(n / 2); i++) {
            for (let j = 0; j < Math.floor((n + 1) / 2); j++) {
                [
                    mat[i][j],
                    mat[n - 1 - j][i],
                    mat[n - 1 - i][n - 1 - j],
                    mat[j][n - 1 - i],
                ] = [
                    mat[n - 1 - j][i],
                    mat[n - 1 - i][n - 1 - j],
                    mat[j][n - 1 - i],
                    mat[i][j],
                ];
            }
        }

        if (isEqual(mat, target)) {
            return true;
        }
    }
    return false;
};

function isEqual(mat, target) {
    const n = mat.length;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] !== target[i][j]) {
                return false;
            }
        }
    }
    return true;
}

// using matrix rotation
const findRotation = (mat, target) => {
    const n = mat.length, m = mat[0].length;
    const tar = JSON.stringify(target);
    for (let k = 0; k < 4; k++) {
        if (JSON.stringify(mat) === tar) return true;
        for (let i = 0; i < n; i++)
            for (let j = i; j < m; j++)
                [mat[i][j], mat[j][i]] = [mat[j][i], mat[i][j]];
        mat.reverse();
    }
    return false;
};
