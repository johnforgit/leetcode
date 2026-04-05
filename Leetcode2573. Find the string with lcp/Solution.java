class Solution {

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';

        // construct the string starting from 'a' to 'z' sequentially
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (current > 'z') {
                    return "";
                }
                word[i] = current;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = word[i];
                    }
                }
                current++;
            }
        }

        // verify if the constructed string meets the LCP matrix requirements
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] != word[j]) {
                    if (lcp[i][j] != 0) {
                        return "";
                    }
                } else {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else {
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                            return "";
                        }
                    }
                }
            }
        }

        return new String(word);
    }
}



// runtime - 3ms
class Solution {

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';

        // construct the string starting from 'a' to 'z' sequentially
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (current > 'z') {
                    return "";
                }
                word[i] = current;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = word[i];
                    }
                }
                current++;
            }
        }

        // verify if the constructed string meets the LCP matrix requirements
        // verify last column for each row
        for (int i = n - 1; i >= 0; i--) {
            if (((word[i] == word[n - 1]) && (lcp[i][n - 1] != 1))
                    || ((word[i] != word[n - 1]) && (lcp[i][n - 1] > 0))) {
                return "";
            }
        }
        // verify last row for each column
        for (int j = n - 1; j >= 0; j--) {
            if (((word[j] == word[n - 1]) && (lcp[n - 1][j] != 1))
                    || ((word[j] != word[n - 1]) && (lcp[n - 1][j] > 0))) {
                return "";
            }
        }

        // verify remaining rows and cols
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (word[i] == word[j]) {
                    if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                }
            }
        }

        return new String(word);
    }
}


// runtime - 4ms
class Solution {

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';

        // construct the string starting from 'a' to 'z' sequentially
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (current > 'z') {
                    return "";
                }
                word[i] = current;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = word[i];
                    }
                }
                current++;
            }
        }

        // verify if the constructed string meets the LCP matrix requirements
        // verify last column for each row
        for (int i = n - 1; i >= 0; i--) {
            if (((word[i] == word[n - 1]) && (lcp[i][n - 1] != 1))
                    || ((word[i] != word[n - 1]) && (lcp[i][n - 1] > 0))) {
                return "";
            }
        }
        // verify last row for each column
        for (int j = n - 1; j >= 0; j--) {
            if (((word[j] == word[n - 1]) && (lcp[n - 1][j] != 1))
                    || ((word[j] != word[n - 1]) && (lcp[n - 1][j] > 0))) {
                return "";
            }
        }

        // verify remaining rows and cols
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (word[i] == word[j]) {
                    if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                }
            }
        }

        return new String(word);
    }
}
/*
        abab
        b
*/
