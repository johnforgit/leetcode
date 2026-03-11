class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int bits = 32 - Integer.numberOfLeadingZeros(n);
        int mask = (1 << bits) - 1;
        return ~n & mask;
    }
}


// bit smearing
class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int mask = n;
        for (int i = 0; i <= 4; i++)
            mask |= mask >> (1 << i);
        return ~n & mask;
    }
}
