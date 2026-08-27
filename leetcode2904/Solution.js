// Solution using sliding window

var shortestBeautifulSubstring = function (s, k) {
    if ([...s].filter((c) => c === "1").length < k) return "";
    let ans = s,
        cnt = 0,
        left = 0;
    for (let right = 0; right < s.length; right++) {
        cnt += s[right] - "0";
        while (cnt > k || s[left] === "0") {
            cnt -= s[left++] - "0";
        }
        if (cnt === k) {
            const t = s.slice(left, right + 1);
            if (t.length < ans.length || (t.length === ans.length && t < ans)) {
                ans = t;
            }
        }
    }
    return ans;
};

/**
 * Solution using enumeration
 * var shortestBeautifulSubstring = function (s, k) {
 *   for (let m = k; m <= s.length; m++) {
 *        let ans = "";
 *        for (let i = m; i <= s.length; i++) {
 *             const t = s.slice(i - m, i);
 *             if (
 *                  (!ans || t < ans) && 
 *                  [...t].filter((c) => c === "1").length === k
 *             ) {
 *                  ans = t;
 *             }
 *        }
 *        if (ans) return ans;
 *   }
 *   return "";
 * };
 */