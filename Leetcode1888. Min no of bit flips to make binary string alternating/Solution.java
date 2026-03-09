// solution using prefix array
class Solution {

    public int minFlips(String s) {
        int n = s.length();
        int[][] pre = new int[n][2];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // Compute the prefix array
            pre[i][0] = (i == 0 ? 0 : pre[i - 1][1]) + (ch == '1' ? 1 : 0);
            pre[i][1] = (i == 0 ? 0 : pre[i - 1][0]) + (ch == '0' ? 1 : 0);
        }

        int ans = Math.min(pre[n - 1][0], pre[n - 1][1]);
        // If the length is odd, consider the move operation
        if (n % 2 == 1) {
            int[][] suf = new int[n][2];
            for (int i = n - 1; i >= 0; i--) {
                char ch = s.charAt(i);
                // Suffix Array Construction
                suf[i][0] =
                    (i == n - 1 ? 0 : suf[i + 1][1]) + (ch == '1' ? 1 : 0);
                suf[i][1] =
                    (i == n - 1 ? 0 : suf[i + 1][0]) + (ch == '0' ? 1 : 0);
            }

            // Traverse all split points
            for (int i = 0; i < n - 1; i++) {
                ans = Math.min(ans, pre[i][0] + suf[i + 1][0]);
                ans = Math.min(ans, pre[i][1] + suf[i + 1][1]);
            }
        }

        return ans;
    }
}




class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int res = n;
        int[] op = {0, 0};

        for (int i = 0; i < n; i++)
            op[(s.charAt(i) ^ i) & 1]++;

        for (int i = 0; i < n; i++) {
            op[(s.charAt(i) ^ i) & 1]--;
            op[(s.charAt(i) ^ (n + i)) & 1]++;
            res = Math.min(res, Math.min(op[0], op[1]));
        }

        return res;
    }
}



// runtime -6ms
class Solution {
    public static int minFlips(String s) {
        final int n = s.length();
        int p = 0, k = 0;
        for (int i = 0; i < n; i++) {
            final char c = s.charAt(i);
            k += p ^ c & 1;
            p ^= 1;
        }
        int r = Math.min(k, n - k);
        if ((n & 1) != 0) {
            p = 0;
            for (int i = 0; i + 1 < n; i++) {
                final char c = s.charAt(i);
                k += (p ^ c & 1 ^ 1) - (p ^ c & 1);
                p ^= 1;
                r = Math.min(r, Math.min(k, n - k));
            }
        }
        return r;
    }
}


// runtime - 7ms
class Solution {
    public static int minFlips(String s) {
        final int n = s.length();
        int p = 0, k = 0;
        for (int i = 0; i < n; i++) {
            final char c = s.charAt(i);
            k += p ^ c & 1;
            p ^= 1;
        }
        int r = Math.min(k, n - k);
        if ((n & 1) != 0) {
            p = 0;
            for (int i = 0; i + 1 < n; i++) {
                final char c = s.charAt(i);
                k += (p ^ c & 1 ^ 1) - (p ^ c & 1);
                p ^= 1;
                r = Math.min(r, Math.min(k, n - k));
            }
        }
        return r;
    }
}


// runtime - 8ms
class Solution {
    public int minFlips(String s) {
        char [] arr=s.toCharArray();
        int len=arr.length;
        char [] odd=new char[2*arr.length];
        int flag=0;
        for(int i=0;i<odd.length;i++){
            odd[i]=(char)('0'+flag);
            flag=1-flag;
        }
        int val=0;
        for(int i=0;i<arr.length;i++){
            val+=odd[i]^arr[i];
        }
        int min=Math.min(val,len-val);
        for(int i=len;i<2*len;i++){
            val=val-(arr[i-len]^odd[i-len]);
            val+=arr[i-len]^odd[i];
            min=Math.min(min,Math.min(val,len-val));
        }
        return min;
    }
}


// runtime - 9ms
class Solution {
    public int minFlips(String s) {
        char[] cs = s.toCharArray();
        boolean expectZero = true;
        int zeroOne = 0;

        for (char c : cs) {
            if (expectZero ^ c == '0') ++zeroOne;
            expectZero = !expectZero;
        }
        int res = Math.min(zeroOne, cs.length - zeroOne);
        if (cs.length % 2 == 0) return res;

        expectZero = true;
        int oneZero = 0;
        for (int i = 0; i < cs.length; ++i) {
            if (expectZero ^ cs[i] == '0') {
                --zeroOne;
            } else {
                ++oneZero;
            }
            res = Math.min(res, Math.min(oneZero + zeroOne, cs.length - oneZero - zeroOne));
            expectZero = !expectZero;
        }

        return res;
    }
}


// runtime - 11ms
class Solution {
    public int minFlips(String s) {
        int one = 0, two = 0, oneFlipped = 0, twoFlipped = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' == i % 2) {
                one++;
                oneFlipped++;
            } else {
                two++;
                twoFlipped++;
            }
            oneFlipped = Math.min(oneFlipped, two);
            twoFlipped = Math.min(twoFlipped, one);
        }
        if (s.length() % 2 == 0) return Math.min(one, two);
        else return Math.min(Math.min(one, two), Math.min(oneFlipped, twoFlipped));
    }
}
