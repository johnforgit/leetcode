class Solution:
    def largestMagicSquare(self, grid):
        total_rows, total_cols = len(grid), len(grid[0])

        # Prefix sum arrays
        row_prefix_sum = [[0] * (total_cols + 1) for _ in range(total_rows)]
        col_prefix_sum = [[0] * total_cols for _ in range(total_rows + 1)]
        main_diag_prefix = [[0] * (total_cols + 1) for _ in range(total_rows + 1)]
        anti_diag_prefix = [[0] * (total_cols + 2) for _ in range(total_rows + 1)]

        # Build prefix sums
        for row in range(total_rows):
            for col in range(total_cols):
                row_prefix_sum[row][col + 1] = row_prefix_sum[row][col] + grid[row][col]
                col_prefix_sum[row + 1][col] = col_prefix_sum[row][col] + grid[row][col]
                main_diag_prefix[row + 1][col + 1] = main_diag_prefix[row][col] + grid[row][col]
                anti_diag_prefix[row + 1][col] = anti_diag_prefix[row][col + 1] + grid[row][col]

        def is_magic_square(start_row, start_col, size):
            target_sum = row_prefix_sum[start_row][start_col + size] - row_prefix_sum[start_row][start_col]

            # Check all rows
            for r in range(start_row, start_row + size):
                if row_prefix_sum[r][start_col + size] - row_prefix_sum[r][start_col] != target_sum:
                    return False

            # Check all columns
            for c in range(start_col, start_col + size):
                if col_prefix_sum[start_row + size][c] - col_prefix_sum[start_row][c] != target_sum:
                    return False

            # Check both diagonals
            if main_diag_prefix[start_row + size][start_col + size] - main_diag_prefix[start_row][start_col] != target_sum:
                return False

            if anti_diag_prefix[start_row + size][start_col] - anti_diag_prefix[start_row][start_col + size] != target_sum:
                return False

            return True

        max_possible_size = min(total_rows, total_cols)

        # Try larger squares first
        for size in range(max_possible_size, 0, -1):
            for row in range(total_rows - size + 1):
                for col in range(total_cols - size + 1):
                    if is_magic_square(row, col, size):
                        return size

        return 1
