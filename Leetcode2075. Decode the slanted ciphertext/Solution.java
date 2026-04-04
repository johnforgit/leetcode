class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1)
            return encodedText;

        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder res = new StringBuilder(n);

        for (int c = 0; c < cols; c++) {
            int r = 0, j = c;
            while (r < rows && j < cols) {
                res.append(encodedText.charAt(r * cols + j));
                r++;
                j++;
            }
        }

        int end = res.length() - 1;
        while (end >= 0 && res.charAt(end) == ' ') {
            end--;
        }

        return res.substring(0, end + 1);
    }
}


// runtime - 9ms
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        int encodedSize = encodedText.length();
        int columns = encodedSize/rows;
        char[][] matrix = new char[rows][columns];

        int c = 0;
        for (int i = 0; i < rows; i++) {
            if (c == encodedSize) break;
            for (int j = 0; j < columns; j++) {
                if (c == encodedSize) break;
                matrix[i][j] = encodedText.charAt(c);
                c++;
            }
        }

        StringBuilder originalText = new StringBuilder();
        
        int row = 0, column = 0;
        int columnCounter = 0;
        while (column < columns) {
            char character = matrix[row][column];
            if (character == '\0') break;
            originalText.append(character);
            row++;
            column++;
            if (row == rows) {
                row = 0;
                columnCounter++;
                column = columnCounter;
            };

        }
        int limit = originalText.length();
        for (int i = originalText.length() - 1; i >= 0; i--) {
            if (originalText.charAt(i) == ' ') {
                limit--;
            } else {
                break;
            }
        }
        originalText.setLength(limit);
        return originalText.toString();
    }
}



// runtime - 17ms
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;

        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder result = new StringBuilder();

        // start from each column of first row
        for (int startCol = 0; startCol < cols; startCol++) {

            int r = 0;
            int c = startCol;

            // traverse diagonal
            while (r < rows && c < cols) {
                result.append(encodedText.charAt(r * cols + c));
                r++;
                c++;
            }
        }

        // remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }

        return result.substring(0, end + 1);
    }
}


// runtime - 21ms
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if(rows == 1)
            return encodedText;

        int len = encodedText.length();

        if(len == 0)
            return encodedText;

        int cols = (int) Math.ceil( (double) len/rows);

        char[][] matrix = new char[rows][cols];
        char[] encodedArr = encodedText.toCharArray();
        int e=0;

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                matrix[i][j] = encodedArr[e++];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int startCol = 0; startCol < cols; startCol++) {
            int r = 0;
            int c = startCol;

            while (r < rows && c < cols) {
                sb.append(matrix[r][c]);
                r++;
                c++;
            }
        }

        return sb.toString().stripTrailing();
    }
}
