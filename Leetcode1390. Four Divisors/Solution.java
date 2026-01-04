both solutions work

class Solution {
    /*
    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for(int n:nums) {
            int val = sumOne(n);
            if(val != -1) 
                res += val;
        }
        return res;
    }

    public int sumOne(int n) {
        int p = (int) Math.round(Math.cbrt(n));
        if ((long) p * p * p == n && isPrime(p)) {
            return 1 + p + p * p + p * p * p;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int a = i;
                int b = n / i;

                if (a != b && isPrime(a) && isPrime(b)) {
                    return 1 + a + b + n;
                }
                return -1;
            }
        }
        return -1;
    }
    
    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    */
    private int sumofdivisor(int n){
        int sum=0;
        int count=0;
        for(int i=1;i*i<=n;i++){
            if(n%i==0){
                int d1=i;
                int d2=n/i;
                count++;
                sum+=d1;
                if(d1!=d2){
                    count++;
                    sum+=d2;
                }
            }
            if(count>4) return 0;
        }
        return count==4?sum:0;
    }
    public int sumFourDivisors(int[] nums) {
        int ans=0;
        for(int num : nums){
            ans+=sumofdivisor(num);
        }
        return ans;
    }
}
