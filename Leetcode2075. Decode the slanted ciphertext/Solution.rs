impl Solution {
    pub fn decode_ciphertext(s: String, rows: i32) -> String {
        if s.is_empty() {
            return s;
        }

        let rows = rows as usize;
        let len = s.len();
        let cols = (len + rows - 1) / rows; // ceil

        let chars: Vec<char> = s.chars().collect();
        let mut res = String::new();

        for start in 0..cols {
            let mut r = 0;
            let mut c = start;

            while r < rows && c < cols {
                let idx = r * cols + c;
                if idx < len {
                    res.push(chars[idx]);
                }
                r += 1;
                c += 1;
            }
        }

        res.trim_end().to_string()
    }
}
