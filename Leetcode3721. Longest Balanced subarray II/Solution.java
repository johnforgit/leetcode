class LazyTag {

    int toAdd;

    LazyTag() {
        this.toAdd = 0;
    }

    LazyTag add(LazyTag other) {
        this.toAdd += other.toAdd;
        return this;
    }

    boolean hasTag() {
        return this.toAdd != 0;
    }

    void clear() {
        this.toAdd = 0;
    }
}

class SegmentTreeNode {

    int minValue;
    int maxValue;
    LazyTag lazyTag;

    SegmentTreeNode() {
        this.minValue = 0;
        this.maxValue = 0;
        this.lazyTag = new LazyTag();
    }
}

class SegmentTree {

    private int n;
    private SegmentTreeNode[] tree;

    SegmentTree(int[] data) {
        this.n = data.length;
        this.tree = new SegmentTreeNode[this.n * 4 + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new SegmentTreeNode();
        }
        build(data, 1, this.n, 1);
    }

    void add(int l, int r, int val) {
        LazyTag tag = new LazyTag();
        tag.toAdd = val;
        update(l, r, tag, 1, this.n, 1);
    }

    int findLast(int start, int val) {
        if (start > this.n) {
            return -1;
        }
        return find(start, this.n, val, 1, this.n, 1);
    }

    private void applyTag(int i, LazyTag tag) {
        tree[i].minValue += tag.toAdd;
        tree[i].maxValue += tag.toAdd;
        tree[i].lazyTag.add(tag);
    }

    private void pushdown(int i) {
        if (tree[i].lazyTag.hasTag()) {
            LazyTag tag = new LazyTag();
            tag.toAdd = tree[i].lazyTag.toAdd;
            applyTag(i << 1, tag);
            applyTag((i << 1) | 1, tag);
            tree[i].lazyTag.clear();
        }
    }

    private void pushup(int i) {
        tree[i].minValue = Math.min(
            tree[i << 1].minValue,
            tree[(i << 1) | 1].minValue
        );
        tree[i].maxValue = Math.max(
            tree[i << 1].maxValue,
            tree[(i << 1) | 1].maxValue
        );
    }

    private void build(int[] data, int l, int r, int i) {
        if (l == r) {
            tree[i].minValue = tree[i].maxValue = data[l - 1];
            return;
        }

        int mid = l + ((r - l) >> 1);
        build(data, l, mid, i << 1);
        build(data, mid + 1, r, (i << 1) | 1);
        pushup(i);
    }

    private void update(
        int targetL,
        int targetR,
        LazyTag tag,
        int l,
        int r,
        int i
    ) {
        if (targetL <= l && r <= targetR) {
            applyTag(i, tag);
            return;
        }

        pushdown(i);
        int mid = l + ((r - l) >> 1);
        if (targetL <= mid) update(targetL, targetR, tag, l, mid, i << 1);
        if (targetR > mid) update(
            targetL,
            targetR,
            tag,
            mid + 1,
            r,
            (i << 1) | 1
        );
        pushup(i);
    }

    private int find(int targetL, int targetR, int val, int l, int r, int i) {
        if (tree[i].minValue > val || tree[i].maxValue < val) {
            return -1;
        }

        if (l == r) {
            return l;
        }

        pushdown(i);
        int mid = l + ((r - l) >> 1);

        if (targetR >= mid + 1) {
            int res = find(targetL, targetR, val, mid + 1, r, (i << 1) | 1);
            if (res != -1) return res;
        }

        if (l <= targetR && mid >= targetL) {
            return find(targetL, targetR, val, l, mid, i << 1);
        }

        return -1;
    }
}

class Solution {

    public int longestBalanced(int[] nums) {
        Map<Integer, Queue<Integer>> occurrences = new HashMap<>();

        int len = 0;
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = sgn(nums[0]);
        occurrences.computeIfAbsent(nums[0], k -> new LinkedList<>()).add(1);

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1];
            Queue<Integer> occ = occurrences.computeIfAbsent(nums[i], k ->
                new LinkedList<>()
            );
            if (occ.isEmpty()) {
                prefixSum[i] += sgn(nums[i]);
            }
            occ.add(i + 1);
        }

        SegmentTree seg = new SegmentTree(prefixSum);

        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, seg.findLast(i + len, 0) - i);

            int nextPos = nums.length + 1;
            occurrences.get(nums[i]).poll();
            if (!occurrences.get(nums[i]).isEmpty()) {
                nextPos = occurrences.get(nums[i]).peek();
            }

            seg.add(i + 1, nextPos - 1, -sgn(nums[i]));
        }

        return len;
    }

    private int sgn(int x) {
        return (x % 2) == 0 ? 1 : -1;
    }
}


// runtime - 12ms
// semicolons : 1
class Solution {
    public int longestBalanced(int[] nums) {
        return CompletableFuture.supplyAsync(() -> Optional
                .of(new Object[] { new int[3][4 * nums.length], new HashMap<Integer, Integer>(), null })
                .map(s -> Optional.of((BiFunction<Object, int[], Integer>) (f, a) -> a[0] == 0
                        ? (((int[][]) s[0])[2][a[1]] == 0 ? 0
                                : (((int[][]) s[0])[0][a[1]] += ((int[][]) s[0])[2][a[1]]) * 0
                                        + (((int[][]) s[0])[1][a[1]] += ((int[][]) s[0])[2][a[1]]) * 0 + (a[2] == a[3]
                                                ? 0
                                                : (((int[][]) s[0])[2][2 * a[1]] += ((int[][]) s[0])[2][a[1]]) * 0
                                                        + (((int[][]) s[0])[2][2 * a[1]
                                                                + 1] += ((int[][]) s[0])[2][a[1]]) * 0)
                                        + (((int[][]) s[0])[2][a[1]] = 0) * 0)
                        : a[0] == 1 ? ((BiFunction<Object, int[], Integer>) f).apply(f,
                                new int[] { 0, a[1], a[2], a[3] }) * 0
                                + (a[2] > a[5] || a[3] < a[4] ? 0
                                        : a[4] <= a[2] && a[3] <= a[5]
                                                ? (((int[][]) s[0])[2][a[1]] += a[6]) * 0
                                                        + ((BiFunction<Object, int[], Integer>) f).apply(f,
                                                                new int[] { 0, a[1], a[2], a[3] })
                                                : ((BiFunction<Object, int[], Integer>) f).apply(f,
                                                        new int[] { 1, 2 * a[1], a[2], (a[2] + a[3]) / 2, a[4], a[5],
                                                                a[6] })
                                                        * 0
                                                        + ((BiFunction<Object, int[], Integer>) f).apply(f,
                                                                new int[] { 1, 2 * a[1] + 1, (a[2] + a[3]) / 2 + 1,
                                                                        a[3], a[4], a[5], a[6] })
                                                                * 0
                                                        + (((int[][]) s[0])[0][a[1]] = Math.min(
                                                                ((int[][]) s[0])[0][2 * a[1]],
                                                                ((int[][]) s[0])[0][2 * a[1] + 1])) * 0
                                                        + (((int[][]) s[0])[1][a[1]] = Math.max(
                                                                ((int[][]) s[0])[1][2 * a[1]],
                                                                ((int[][]) s[0])[1][2 * a[1] + 1])) * 0)
                                : ((BiFunction<Object, int[], Integer>) f).apply(f, new int[] { 0, a[1], a[2], a[3] })
                                        * 0
                                        + (((int[][]) s[0])[0][a[1]] > 0 || ((int[][]) s[0])[1][a[1]] < 0 ? -1
                                                : a[2] == a[3] ? (((int[][]) s[0])[0][a[1]] == 0 ? a[2] : -1)
                                                        : Optional
                                                                .of(((BiFunction<Object, int[], Integer>) f).apply(f,
                                                                        new int[] { 2, 2 * a[1], a[2],
                                                                                (a[2] + a[3]) / 2 }))
                                                                .map(r -> r != -1 ? r
                                                                        : ((BiFunction<Object, int[], Integer>) f)
                                                                                .apply(f, new int[] { 2, 2 * a[1] + 1,
                                                                                        (a[2] + a[3]) / 2 + 1, a[3] }))
                                                                .get()))
                        .map(func -> ((s[2] = func) == null ? s : s)).map(
                                state -> IntStream.range(0, nums.length).boxed().reduce(0,
                                        (max, r) -> Optional.of(nums[r] % 2 == 0 ? 1 : -1)
                                                .map(val -> (state[1] != null && ((Map) state[1]).containsKey(nums[r])
                                                        ? ((BiFunction<Object, int[], Integer>) state[2]).apply(
                                                                state[2],
                                                                new int[] { 1, 1, 0, nums.length - 1, 0,
                                                                        (int) ((Map) state[1]).get(nums[r]), -val })
                                                        : 0) * 0
                                                        + ((BiFunction<Object, int[], Integer>) state[2])
                                                                .apply(state[2],
                                                                        new int[] { 1, 1, 0, nums.length - 1, 0, r,
                                                                                val })
                                                                * 0
                                                        + (((Map) state[1]).put(nums[r], r) == null
                                                                ? 0
                                                                : 0) * 0
                                                        + Optional
                                                                .of(((BiFunction<Object, int[], Integer>) state[2])
                                                                        .apply(state[2],
                                                                                new int[] { 2, 1, 0, nums.length - 1 }))
                                                                .filter(l -> l != -1 && l <= r)
                                                                .map(l -> Math.max(max, r - l + 1)).orElse(max))
                                                .get(),
                                        (a, b) -> b))
                        .get())
                .orElse(0)).join();
    }
}


// runtime - 16ms
class Solution {
    
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        
        int max = 0;
        for(int x:nums)
            max = Math.max(max, x);
        
        boolean[] seen = new boolean[max + 1];
        int[] count = new int[2];

        for(int x:nums){
            if(seen[x]) continue;
            seen[x] = true;
            count[x & 1]++;
        }

        if(count[0] == count[1]) return n;
        if(count[0] == 0 || count[1] == 0) return 0;

        int[] first = new int[max + 1];
        Arrays.fill(first, -1);

        SegmentTree segTree = new SegmentTree(n);

        int result = 0;
        for (int i = n - 1; i >= 0; --i) {
            int x = nums[i];
            int j = first[x];
            if (j != -1)
                segTree.update(j, 0);

            first[x] = i;
            segTree.update(i, 2 * (x & 1) - 1);

            int right = segTree.query(0);
            if (right >= i)
                result = Math.max(result, right - i + 1);
        }

        return result;
    }
}

class SegmentTree {
    int[] stack = new int[20];
    int[] min, max, sum;
    int n;

    SegmentTree(int n) {
        int size = 4 * n;
        min  = new int[size];
        max  = new int[size];
        sum = new int[size];
        this.n = n;
    }

    void update(int index, int x) {
        
        int node = 1, left = 0, right = n - 1;
        int top = -1;
        
        while (left < right) {
            stack[++top] = node;
            int mid = left + right >> 1;
            if (mid >= index) {
                right = mid;
                node = node << 1;
            } else {
                left = mid + 1; 
                node = node << 1 | 1;
            }
        }

        min[node] = x;
        max[node] = x;
        sum[node] = x;
        
        while (top > -1) 
            apply(stack[top--]);
    }

    void apply(int index) {

        int l = index << 1, r = index << 1 | 1;
        min[index] = Math.min(min[l], sum[l] + min[r]);
        max[index] = Math.max(max[l], sum[l] + max[r]);
        
        sum[index] = sum[l] + sum[r];
    }

    int query(int target) {
        
        int node = 1;
        if (target < min[node] || target > max[node])
            return -1;
        
        int left = 0, right = n - 1;
        
        int curSum = 0;
        while (left < right) {
            int mid = left + right >> 1;
            int lchild = node << 1, rchild = node << 1|1;

            // Check right half first
            int diff = target - sum[lchild];

            if (min[rchild] <= diff && diff <= max[rchild]) {
                node = rchild;
                left = mid + 1;
                target -= sum[lchild];
            } else {
                node = lchild;
                right = mid;
            }
        }

        return left;
    }
}


// runtime - 21ms

class Solution {
    
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        
        int max = 0;
        for(int x:nums)
            max = Math.max(max, x);
        
        boolean[] seen = new boolean[max + 1];
        int[] count = new int[2];

        for(int x:nums){
            if(seen[x]) continue;
            seen[x] = true;
            count[x & 1]++;
        }

        if(count[0] == count[1]) return n;
        if(count[0] == 0 || count[1] == 0) return 0;

        int[] first = new int[max + 1];
        Arrays.fill(first, -1);

        SegmentTree segTree = new SegmentTree(n);

        int result = 0;
        for (int i = n - 1; i >= 0; --i) {
            int x = nums[i];
            int j = first[x];
            if (j != -1)
                segTree.update(j, 0);

            first[x] = i;
            segTree.update(i, 2 * (x & 1) - 1);

            int right = segTree.find_rightmost_prefix(0);
            if (right >= i)
                result = Math.max(result, right - i + 1);
        }

        return result;
    }
}

class SegmentTree {
    public int n;
    public int size;
    public int[] sum, min, max;
    int[] stack = new int[32];

    SegmentTree(int n_) {
        n = n_;
        size = 4 * n_;
        sum = new int[size];
        min  = new int[size];
        max  = new int[size];
    }

    void update(int index, int x) {
        int node = 1, left = 0, right = n - 1;
        int top = -1;

        while (left != right) {
            stack[++top] = node;
            int mid = left + right >> 1;
            if (index <= mid) {
                node = node << 1;
                right = mid;
            } else {
                node = node << 1 | 1;
                left = mid + 1;
            }
        }

        sum[node] = x;
        min[node] = x;
        max[node] = x;

        while (top > -1) 
            apply(stack[top--]);
    }

    void apply(int index) {

        int l = index << 1, r = index << 1 | 1;
        sum[index] = sum[l] + sum[r];
        min[index] = Math.min(min[l], sum[l] + min[r]);
        max[index] = Math.max(max[l], sum[l] + max[r]);
    }

    int find_rightmost_prefix(int target) {
        int node = 1,  sum_before = 0;

        if (!(min[node] <= target - sum_before && target - sum_before <= max[node]))
            return -1;
        
        int left = 0, right = n - 1;
        while (left != right) {
            int mid = left + right >> 1;
            int lchild = node * 2, rchild = node * 2 + 1;

            // Check right half first
            int sum_before_right = sum[lchild] + sum_before;
            int need_right = target - sum_before_right;

            if (min[rchild] <= need_right && need_right <= max[rchild]) {
                node = rchild;
                left = mid + 1;
                sum_before = sum_before_right;
            } else {
                node = lchild;
                right = mid;
            }
        }

        return left;
    }
}


// runtime - 25ms
class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        
        int max = 0;
        for(int x:nums)
            max = Math.max(max, x);
            
        boolean[] seen = new boolean[max + 1];
        int[] count = new int[2];
        for (int x : nums) {
            if(seen[x]) continue;
            seen[x] = true;
            count[x & 1]++;
        }

        if (count[0] == count[1]) return n;
        if (count[0] == 0|| count[1] == 0) return 0;

        int[] last = new int[max + 1];
        Arrays.fill(last, -1);

        LazySegmentTree segTree = new LazySegmentTree(n + 1);

        int ans = 0;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int v = 2 * (x & 1) - 1;
            Integer j = last[x];
            if (j == -1) { // 首次遇到 x
                sum += v;
                segTree.update(i, n, v); // sum 的 [i,n] 增加 v
            } else { // 再次遇到 x
                segTree.update(j, i - 1, -v); // 撤销之前对 sum 的 [j, i-1] 的增加
            }
            
            last[x] = i;

            // 把 i-1 优化成 i-1-ans，因为在下标 > i-1-ans 中搜索是没有意义的，不会把答案变大
            int l = segTree.findFirst(0, i - 1 - ans, sum);
            if (l >= 0) 
                ans = i - l;
        }
        return ans;        
    }
}

class LazySegmentTree {
    Node[] tree;
    int n;
     
    class Node {
        int min, max, lazy;
    }
   
    public LazySegmentTree(int n) {
        this.n = n;
        int N = 1;
        while(N <= n) N <<= 1;
        tree = new Node[N * 2];
        
        Arrays.setAll(tree, e-> new Node());
    }

    // 用 v 更新 [ql, qr] 中的每个 sum[i]
    // 0 <= ql <= qr <= n-1
    // 时间复杂度 O(log n)
    public void update(int ql, int qr, int v) {
        update(1, 0, n - 1, ql, qr, v);
    }

    // 查询 [ql, qr] 内第一个等于 target 的元素下标
    // 找不到返回 -1
    // 0 <= ql <= qr <= n-1
    // 时间复杂度 O(log n)
    public int findFirst(int ql, int qr, int target) {
        return findFirst(1, 0, n - 1, ql, qr, target);
    }

    private void update(int i, int l, int r, int ql, int qr, int v) {
        if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
            apply(i, v);
            return;
        }
        
        spread(i);
        int mid = l + r  >> 1;
        if (ql <= mid)  // 更新左子树
            update(i << 1, l, mid, ql, qr, v);
        
        if (qr > mid) // 更新右子树
            update(i << 1|1, mid + 1, r, ql, qr, v);
    
        tree[i].min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
        tree[i].max = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);      
    }

    private int findFirst(int i, int l, int r, int ql, int qr, int target) {
        if (l > qr || r < ql || target < tree[i].min || target > tree[i].max) 
            return -1;
        
        if (l == r) 
            return l;
        
        spread(i);
        int mid = l + r >> 1;
        int result = findFirst(i << 1, l, mid, ql, qr, target);
        if (result < 0) 
            result = findFirst(i << 1 |1, mid + 1, r, ql, qr, target);
        
        return result;
    }
    
    // 把当前节点的懒标记下传给左右儿子
    private void spread(int i) {
        int x = tree[i].lazy;
        if (x == 0) // 没有需要下传的信息
            return;
        
        apply(i << 1, x);
        apply(i << 1 | 1, x);
        tree[i].lazy = 0; // 下传完毕
    }
        
    // 把懒标记作用到 i 子树
    private void apply(int i, int todo) {
        Node cur = tree[i];
        cur.min += todo;
        cur.max += todo;
        cur.lazy += todo;
    }    
}
