import java.util.*;

class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;

        for (String s : arr) {
            int mask = 0;
            boolean dup = false;

            for (char c : s.toCharArray()) {
                int bit = 1 << (c - 'a');
                if ((mask & bit) != 0) {
                    dup = true;
                    break;
                }
                mask |= bit;
            }

            if (dup) continue;

            int size = dp.size();
            for (int i = size - 1; i >= 0; i--) {
                if ((dp.get(i) & mask) != 0) continue;
                int newMask = dp.get(i) | mask;
                dp.add(newMask);
                res = Math.max(res, Integer.bitCount(newMask));
            }
        }
        return res;
    }
}

// runtime - 1ms
class Solution {
    List<Integer> masks = new ArrayList<>();

    int result = 0;

    int[] suffixOr;

    public int maxLength(List<String> arr) {

        for (String str : arr) {
            int mask = 0;
            boolean pick = true;
            for (char c : str.toCharArray()) {
                int bit = 1 << (c - 'a');
                if ((mask & bit) != 0) {
                    pick = false;
                    break;
                }
                mask |= bit;
            }
            if (pick) masks.add(mask);
        }

        int n = masks.size();

        suffixOr = new int[n + 1];

        suffixOr[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            suffixOr[i] = suffixOr[i + 1] | masks.get(i);
        }

        backtrack(0, 0);

        return result;
    }

    public void backtrack(int index, int mask) {
        int curCount = Integer.bitCount(mask);

        int possibleCount = Integer.bitCount(suffixOr[index] & ~mask);

        if (curCount + possibleCount <= result) return;

        if (index == masks.size()) {
            result = Math.max(result, curCount);
            return;
        }

        if ((mask & masks.get(index)) == 0) {
            backtrack(index + 1, mask | masks.get(index));
        }

        backtrack(index + 1, mask);
    }
}

// runtime - 2 ms
class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String str : arr) {
            int mask = 0;
            boolean valid = true;
            for (char ch : str.toCharArray()) {
                int n = ch - 'a';
                if ((mask & (1 << n)) != 0) {
                    valid = false;
                    break;
                }
                mask |= (1 << n);
            }
            if (valid) {
                masks.add(mask);
            }
        }

        int[] max = new int[1];
        dfs(masks, 0, 0, max);
        return max[0];
    }

    private void dfs(List<Integer> masks, int index, int mask, int[]max) {
        max[0] = Math.max(max[0], Integer.bitCount(mask));
        for (int i = index; i < masks.size(); i++) {
            int m = masks.get(i);
            if ((mask & m) == 0) {
                dfs(masks, i + 1, mask | m, max);
            }
        }
    }
}

# runtime - 3ms
  class Solution {
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, 0);
    }

    int dfs(List<String> arr, int idx, int mask) {
        int res = 0;

        for (int i = idx; i < arr.size(); i++) {
            int currMask = 0;
            boolean ok = true;

            for (char c : arr.get(i).toCharArray()) {
                int bit = 1 << (c - 'a');
                if ((currMask & bit) != 0 || (mask & bit) != 0) {
                    ok = false;
                    break;
                }
                currMask |= bit;
            }

            if (ok) {
                res = Math.max(res, arr.get(i).length() + dfs(arr, i + 1, mask | currMask));
            }
        }
        return res;
    }
}
