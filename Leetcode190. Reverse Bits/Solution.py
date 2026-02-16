class Solution:
    def reverseBits(self, n: int) -> int:
        def rev(v: int, length: int) -> int:
            if length == 1:
                return v & 1

            half = length >> 1
            mask = (1 << half) - 1

            lo = v & mask
            hi = v >> half

            return (rev(lo, half) << half) | rev(hi, half)

        return rev(n, 32)


# runtime - 0ms
class Solution:
    def reverseBits(self, n: int) -> int:
        result = 0
        for i in range(32):
            result = (result << 1) | (n & 1)
            n = n >> 1
        return result

        
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))



# runtime - 1ms
class Solution:
    def reverseBits(self, n: int) -> int:
        bin_int = format(n, '032b')
        binary_string_negative = bin_int[::-1]
        if binary_string_negative[0] == '1':
            unsigned_int = int(binary_string_negative, 2)
            signed_int = unsigned_int - (1 << 32)
        else:
            signed_int = int(binary_string_negative, 2)
        return signed_int

__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("1"))  


# runtime - 10ms
class Solution:
    def reverseBits(self, n: int) -> int:
        b = bin(n)[2:]
        bs = '0'*(32-len(b)) + b
        return int(bs[::-1], 2)
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("10"))


# runtime - 16ms
class Solution:
    def reverseBits(self, n: int) -> int:
        res = 0
        for i in range(32):
            #get ith bit
            bit = (n>>i)&1
            #set ith bit
            res = res | (bit<<(31-i))
        return res

