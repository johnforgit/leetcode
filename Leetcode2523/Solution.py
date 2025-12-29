class Solution:
    def closestPrimes(self, left: int, right: int) -> list[int]:
        is_prime = self.sieve(right)

        prev = -1
        min_diff = float('inf')
        res = [-1, -1]

        for i in range(left, right + 1):
            if is_prime[i]:
                if prev != -1:
                    diff = i - prev
                    if diff < min_diff:
                        min_diff = diff
                        res[0] = prev
                        res[1] = i
                prev = i

        return res

    def sieve(self, n: int) -> list[bool]:
        prime = [False] * (n + 1)

        if n >= 2:
            prime = [True] * (n + 1)
            prime[0] = prime[1] = False

            i = 2
            while i * i <= n:
                if prime[i]:
                    for j in range(i * i, n + 1, i):
                        prime[j] = False
                i += 1

        return prime
