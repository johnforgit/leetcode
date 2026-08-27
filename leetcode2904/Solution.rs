// solution using sliding window
impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let bytes = s.as_bytes();
        if bytes.iter().filter(|&&b| b == b'1').count() < k as usize {
            return String::new();
        }
        let mut ans = s.clone();
        let (mut cnt, mut left) = (0, 0);
        for right in 0..bytes.len() {
            cnt += (bytes[right] - b'0') as i32;
            while cnt > k || bytes[left] == b'0' {
                cnt -= (bytes[left] - b'0') as i32;
                left += 1;
            }
            if cnt == k {
                let t = &s[left..=right];
                if t.len() < ans.len() || t.len() == ans.len() && t < ans.as_str() {
                    ans = t.to_string();
                }
            }
        }
        ans
    }
}


// solution using enumeration
impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        for m in k as usize..=s.len() {
            let mut ans = String::new();
            for i in m..=s.len() {
                let t = &s[i - m..i];
                if t.bytes().filter(|&b| b == b'1').count() == k as usize
                    && (ans.is_empty() || t < ans.as_str())
                {
                    ans = t.to_string();
                }
            }
            if !ans.is_empty() {
                return ans;
            }
        }
        String::new()
    }
}