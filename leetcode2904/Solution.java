class Solution {
     public String shortestBeautifulSubstring(String s, int k) {
          int total = 0;
          for (int i = 0; i < s.length(); i++) 
               total += s.charAt(i) - '0';
          if(total < k)
               return "";
          String ans = s;
          int cnt = 0, left = 0;
          for(int right=0; right<s.length(); right++) {
               cnt += s.charAt(right)-'0';
               while((cnt>k) || s.charAt(left)=='0')
                    cnt -= s.charAt(left++)-'0';
               if(cnt==k) {
                    String t = s.substring(left, right+1);
                    if(t.length() < ans.length() || 
                         (t.length() == ans.length() && t.compareTo(ans)<0)
                    ) {
                         ans = t;
                    }
               }
          }
          return ans;
     }

     public void main(String[] args) {
          String s = "0011011011";
          int k = 3;
          String res = shortestBeautifulSubstring(s, k);
          System.out.println("Shortest beautiful substring --> " + res);
     }



     /* 
     public String shortestBeautifulSubstring(String s, int k) {
        char[] input = s.toCharArray();

        int head = -1;
        int tail = -1;

        int back = 0;
        int countOnes = 0;

        for (int front = 0; front < input.length; ++front) {
            countOnes += input[front] - '0';
            if (countOnes < k) {
                continue;
            }

            while (back < front && input[back] == '0') {
                countOnes -= input[back] - '0';
                ++back;
            }

            if (head == -1 || head - tail + 1 > front - back + 1) {
                head = front;
                tail = back;
            } else if (head - tail + 1 == front - back + 1
                    && s.substring(tail, head + 1).compareTo(s.substring(back, front + 1)) > 0) {
                head = front;
                tail = back;
            }
            while (back < front && countOnes == k) {
                countOnes -= input[back] - '0';
                ++back;
            }
        }
        return head != -1 ? s.substring(tail, head + 1) : "";
    }
        */

    /**
     * Solution using enumeration
     * class Solution {
     *    public String shortestBeautifulSubstring(String s, int k) {
     *         int n = s.length();
     *         for (int m = k; m <= n; m++) {
     *              String ans = "";
     *              for (int i = m; i <= n; i++) {
     *                   String t = s.substring(i - m, i);
     *                   int cnt = 0;
     *                   for (int j = 0; j < t.length(); j++) {
     *                        cnt += t.charAt(j) - '0';
     *                   }
     *                   if ((ans.isEmpty() || t.compareTo(ans) < 0) && cnt == k) {
     *                        ans = t;
     *                   }
     *              }
     *              if (!ans.isEmpty()) {
     *                   return ans;
     *              }
     *         }
     *         return "";
     *    }
     * }
     */

}