class Solution {
public:
    int minimumPushes(string word) {
        int res=0, row=1, n=word.size();
        int quotient = n/8;
        int rem = n%8;

        while(quotient--) {
            // Add the product of 8 and the current row to the result
            res += 8 * row;

            // Increment the row for the next cycle
            row++;
        }
        // Product of remainder and final row accounts for the remaining
        // letters
        res += rem * row;
        return res;
    }
};
