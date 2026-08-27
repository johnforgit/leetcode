/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var missingMultiple = function(nums, k) {
    const seen = new Set(nums);
    let ans = k;
    while(seen.has(ans)) {
        ans += k;
    }
    return ans;
};


/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var missingMultiple = function(nums, k) {
    const present = new Set(nums);

    let multiple = k;

    while (present.has(multiple)) {
        multiple += k;
    }

    return multiple;
};