class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean[] isPrime = sieve(right);
        
        int prev = -1;
        int minDiff = Integer.MAX_VALUE;
        int[] res = {-1, -1};

        for (int i = left; i <= right; i++) {
            if (isPrime[i]) {
                if (prev != -1) {
                    int diff = i - prev;
                    if (diff < minDiff) {
                        minDiff = diff;
                        res[0] = prev;
                        res[1] = i;
                    }
                }
                prev = i;
            }
        }
        return res;
    }

    private boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        if (n >= 2) {
            Arrays.fill(prime, true);
            prime[0] = prime[1] = false;
            for (int i = 2; i * i <= n; i++) {
                if (prime[i]) {
                    for (int j = i * i; j <= n; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }
        return prime;
    }
}
