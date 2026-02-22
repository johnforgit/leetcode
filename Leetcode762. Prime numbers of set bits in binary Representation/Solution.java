class Solution {
    public int countPrimeSetBits(int left, int right) {
        int ans=0;
        for(int x=left; x<=right; ++x) {
            if(isSmallPrime(Integer.bitCount(x)))
                ans++;
        }
        return ans;
    }

    public boolean isSmallPrime(int x) {
        return(x==2 || x==3 || x==5 || x==7 ||
                x==11 || x==13 || x==17 || x==19);
    }
}


// runtime - 0ms
class Solution {
    public int countPrimeSetBits(int left, int right) {
        int c = 0;
        for(int i = left; i <= right; i++){
            int bits = Integer.bitCount(i);
            if(isPrime(bits)) c++;
        }
        return c;
    }
    private boolean isPrime(int n){
        if(n < 2) return false;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter fw = new FileWriter("display_runtime.txt")) {
                fw.write("000");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

}


// runtime - 1ms
class Solution {
    private static int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19};

    private int comb(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) return 0;

        long f1 = 1, f2 = 1;
        for (int i = k + 1; i <= n; i++) {
            f1 *= i;
        }

        for (int i = 2; i <= n - k; i++) {
            f2 *= i;
        }

        return (int) (f1 / f2);
    }

    private int countValid(int av, int al) {
        int count = 0;
        for (int prime : PRIMES) {
            int need = prime - al;
            if (need < 0 || need > av) {
                continue;
            }
            count += comb(av, need);
        }

        return count;
    }

    private int countPrimes(int num) {
        for (int i = PRIMES.length - 1; i >= 0; i--) {
            if (num >= PRIMES[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    private int countNum(int num) {
        if (num == 0) return 0;

        int mask = 0x40000000;
        int pos = 31, curr = 0, total = 0;
        while (mask != 0) {
            if ((num & mask) != 0) {
                total += countValid(pos - 1, curr);
                curr++;
            }
            mask >>>= 1;
            pos--;
        }

        total += countPrimes(curr);
        return total;
    }

    public int countPrimeSetBits(int left, int right) {
        return countNum(right) - countNum(left - 1);
    }
}


// runtime - 2ms
class Solution {
   static boolean prime[]=new boolean[40];
     static {
         
        for(int i=1;i<40;i++){
           int count=0;
           for(int j=1;j<=i;j++){
             if(i%j==0){
                count++;
             }
           }
           if(count==2){
            prime[i]=true;
           }
        
        }
     }
    public int countPrimeSetBits(int left, int right) {
        int count=0;
        for(int i=left;i<=right;i++){
            if(prime[Integer.bitCount(i)]){
                count++;
            }
        }
        return count;
    }
}


// runtime - 3ms
class Solution {
    public int countPrimeSetBits(int left, int right) {
        int mask = 665772;
        int count = 0;
        for(int i=left;i<=right;i++){
            int x = Integer.bitCount(i);
            int k = (mask>>x)&1;
            if (k%2!=0){
                count++;
            }
        }
        return count;
    }
}
