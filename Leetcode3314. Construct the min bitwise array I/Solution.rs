impl Solution {
    pub fn min_bitwise_array(nums:Vec<i32>) -> Vec<i32> {
        let mut nums = nums.clone();
        for x in nums.iter_mut() {
            let mut res = -1;
            let mut d = 1;
            let val = *x;
            while (val & d) != 0 {
                res = val - d;
                d <<= 1;
            }
            *x = res;
        }
        nums.clone()
    }
}
