func decodeCiphertext(s string, rows int) string {
	if len(s) == 0 {
		return s
	}

	cols := int(math.Ceil(float64(len(s)) / float64(rows)))
	var res []byte

	for start := 0; start < cols; start++ {
		r, c := 0, start
		for r < rows && c < cols {
			idx := r*cols + c
			if idx < len(s) {
				res = append(res, s[idx])
			}
			r++
			c++
		}
	}

	return strings.TrimRight(string(res), " ")
}
