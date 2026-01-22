class Solution {
    public int gcdOfOddEvenSums(int n) {
        int odd_sum=0, even_sum=0;
        int gcd = 0;
        for(int i=1; i<=2*n; i++) {
            if(i%2==0)
                even_sum += i;
            else
                odd_sum += i;
        }

        while(odd_sum != 0) {
            int temp = even_sum%odd_sum;
            even_sum = odd_sum;
            odd_sum = temp;
        }
        return even_sum;
    }
}

// runtime - 0ms
return n
