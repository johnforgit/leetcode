class Solution:
    def decodeCiphertext(self, encodedText: str, rows: int) -> str:
        if rows == 1:
            return encodedText

        n = len(encodedText)
        cols = n // rows
        res = []

        for c in range(cols):
            r, j = 0, c
            while r < rows and j < cols:
                res.append(encodedText[r * cols + j])
                r += 1
                j += 1

        return "".join(res).rstrip()



# runtime - 2ms

class Solution:
    def decodeCiphertext(self, encoded_text: str, rows: int) -> str:
        if rows == 1:
            return encoded_text

        N = len(encoded_text)
        cols = N // rows
        i, j, k = 0, 0, 0
        original_text = []

        while k < N:
            original_text.append(encoded_text[k])
            i += 1
            if i == rows:
                i = 0
                j += 1
            k = i*(cols + 1) + j

        return ''.join(original_text).rstrip()



# runtime - 8ms

class Solution:
    def decodeCiphertext(self, encoded_text: str, rows: int) -> str:
        if rows == 1:
            return encoded_text

        N = len(encoded_text)
        cols = N // rows
        i, j, k = 0, 0, 0
        original_text = []

        while k < N:
            original_text.append(encoded_text[k])
            i += 1
            if i == rows:
                i = 0
                j += 1
            k = i*(cols + 1) + j

        return ''.join(original_text).rstrip()



# runtime - 13ms

class Solution:
    def decodeCiphertext(self, encoded_text: str, rows: int) -> str:
        if rows == 1:
            return encoded_text

        N = len(encoded_text)
        cols = N // rows
        matrix = [encoded_text[i * cols:(i + 1) * cols] for i in range(rows)]
        original = []

        for c in range(cols):
            r, cc = 0, c
            while r < rows and cc < cols:
                original.append(matrix[r][cc])
                r += 1
                cc += 1

        return ''.join(original).rstrip()



# runtime - 19ms

class Solution:
    def decodeCiphertext(self, encoded_text: str, rows: int) -> str:
        if rows == 1:
            return encoded_text

        n = len(encoded_text)
        cols = n // rows
        grid = [encoded_text[r * cols:(r + 1) * cols] for r in range(rows)]

        res = []
        for c in range(cols):
            r, cc = 0, c
            while r < rows and cc < cols:
                res.append(grid[r][cc])
                r += 1
                cc += 1

        return ''.join(res).rstrip()
