import java.util.*;

class solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, List<Integer> sub, List<List<Integer>> res) {
        res.add(new ArrayList<>(sub));

        for (int i = start; i < nums.length; i++) {
            sub.add(nums[i]);
            dfs(nums, i + 1, sub, res);
            sub.remove(sub.size() - 1); // backtrack
        }
    }
}
