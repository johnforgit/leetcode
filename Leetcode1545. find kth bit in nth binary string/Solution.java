class Solution {

    public char findKthBit(int n, int k) {
        StringBuilder sequence = new StringBuilder("0");

        // Generate sequence until we have enough elements or reach nth iteration
        for (int i = 1; i < n && k > sequence.length(); ++i) {
            sequence.append('1');

            // Append the inverted and reversed part of the existing sequence
            for (int j = sequence.length() - 2; j >= 0; --j) {
                char invertedBit = (sequence.charAt(j) == '1') ? '0' : '1';
                sequence.append(invertedBit);
            }
        }

        // Return the kth bit
        return sequence.charAt(k - 1);
    }
}


// runtime - 0ms -- using bit manipulation
class Solution {

    public char findKthBit(int n, int k) {
        // Find the position of the rightmost set bit in k
        // This helps determine which "section" of the string we're in
        int positionInSection = k & -k;

        // Determine if k is in the inverted part of the string
        // This checks if the bit to the left of the rightmost set bit is 1
        boolean isInInvertedPart = (((k / positionInSection) >> 1) & 1) == 1;

        // Determine if the original bit (before any inversion) would be 1
        // This is true if k is even (i.e., its least significant bit is 0)
        boolean originalBitIsOne = (k & 1) == 0;

        if (isInInvertedPart) {
            // If we're in the inverted part, we need to flip the bit
            return originalBitIsOne ? '0' : '1';
        } else {
            // If we're not in the inverted part, return the original bit
            return originalBitIsOne ? '1' : '0';
        }
    }
}

// using iterative divide and conquer
class Solution {

    public char findKthBit(int n, int k) {
        int invertCount = 0;
        int len = (1 << n) - 1; // Length of Sn is 2^n - 1

        while (k > 1) {
            // If k is in the middle, return based on inversion count
            if (k == len / 2 + 1) {
                return invertCount % 2 == 0 ? '1' : '0';
            }

            // If k is in the second half, invert and mirror
            if (k > len / 2) {
                k = len + 1 - k; // Mirror position
                invertCount++; // Increment inversion count
            }

            len /= 2; // Reduce length for next iteration
        }

        // For the first position, return based on inversion count
        return invertCount % 2 == 0 ? '0' : '1';
    }
}


// using recursion
class Solution {

    public char findKthBit(int n, int k) {
        // Base case: for S1, return '0'
        if (n == 1) return '0';

        // Calculate the length of Sn
        int len = 1 << n; // Equivalent to 2^n

        // If k is in the first half of the string, recurse with n-1
        if (k < len / 2) {
            return findKthBit(n - 1, k);
        }
        // If k is exactly in the middle, return '1'
        else if (k == len / 2) {
            return '1';
        }
        // If k is in the second half of the string
        else {
            // Find the corresponding bit in the first half and invert it
            char correspondingBit = findKthBit(n - 1, len - k);
            return (correspondingBit == '0') ? '1' : '0';
        }
    }
}
