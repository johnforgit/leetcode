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


// runtime - 6 ms
class Solution {
    public int[] closestPrimes(int left, int right) {
        int diff = Integer.MAX_VALUE;
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        int prev = -1;
        for(int i = left; i <= right; i++) {
        	if(isPrime(i)) {
        		if(prev != -1) {
        			if(diff > i - prev) {
        				ans[0] = prev;
        				ans[1] = i;
                        diff = i - prev;
                        if(diff <= 2){
                            return ans;
                        }
        			}
        		}
        		prev = i;
        	}
        }
        return ans;
        
    }

	private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
	}
}
