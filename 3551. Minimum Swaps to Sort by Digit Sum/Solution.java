import java.util.Arrays;

class Solution {

    private static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        if(nums.length==1)
            return 0;
        
        int n = nums.length;

        Integer[] b = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(b, (x, y) -> {
            int cmp = Integer.compare(digitSum(x), digitSum(y));
            return cmp == 0 ? Integer.compare(x, y) : cmp;
        });

        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            pos.put(nums[i], i);

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != b[i]) {
                int j = pos.get(b[i]);
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                pos.put(nums[i], i);
                pos.put(nums[j], j);
                res++;
            }
        }
        return res;

    }
}

/**

Approach - Permutation cycles
class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i],
                sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                return Integer.compare(digitSum[a], digitSum[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        int[] targetPos = new int[n];
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = targetPos[j];
                }
            }
        }

        return n - cycles;
    }
}

Approach - Union-Find
class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i],
                sum = 0;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            digitSum[i] = sum;
        }

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                return Integer.compare(digitSum[a], digitSum[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        int[] targetPos = new int[n];
        for (int pos = 0; pos < n; pos++) {
            targetPos[indices[pos]] = pos;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int components = n;

        for (int i = 0; i < n; i++) {
            int ri = find(parent, i);
            int rj = find(parent, targetPos[i]);
            if (ri != rj) {
                parent[ri] = rj;
                components--;
            }
        }

        return n - components;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
 */

// ------------------------------------------------------------------
// runtime - 232ms
import java.util.*;

class Solution {

    class Element {
        int number;
        int digitSum;
        int originalIndex;

        Element(int number, int digitSum, int originalIndex) {
            this.number = number;
            this.digitSum = digitSum;
            this.originalIndex = originalIndex;
        }
    }

    public int sumDigit(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {

        int n = nums.length;

        Element[] arr = new Element[n];

        // Create objects
        for (int i = 0; i < n; i++) {
            arr[i] = new Element(
                nums[i],
                sumDigit(nums[i]),
                i
            );
        }

        // Sort by digit sum, then by number
        Arrays.sort(arr, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }

            return Integer.compare(a.number, b.number);
        });

        // pos[originalIndex] = current position
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[arr[i].originalIndex] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {

            // Already correct
            if (arr[i].originalIndex == i) {
                continue;
            }

            int correctPosition = pos[i];

            // Swap current element with the element
            // that originally belongs at position i
            Element temp = arr[i];
            arr[i] = arr[correctPosition];
            arr[correctPosition] = temp;

            // Update positions
            pos[arr[correctPosition].originalIndex] = correctPosition;
            pos[arr[i].originalIndex] = i;

            swaps++;
        }

        return swaps;
    }
}
// ------------------------------------------------------------------

// ------------------------------------------------------------------
// runtime - 246ms
class Solution {
    public int minSwaps(int[] nums) {
        int n=nums.length;
        int[][]arr=new int[n][3];
        for(int i=0;i<nums.length;i++){
            arr[i][0]=digitSum(nums[i]);
            arr[i][1]=nums[i];
            arr[i][2]=i;
        }

        boolean[]visited=new boolean[n];
        Arrays.sort(arr,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }
            return a[1]-b[1];
        });
        int swaps=0;
        for(int i=0;i<n;i++){
            if(visited[i]||arr[i][2]==i){
                continue;
            }
            int j=i;
            int CycleSize=0;
            while(!visited[j]){
                visited[j]=true;
                j=arr[j][2];
                CycleSize++;
            }
            swaps+=CycleSize-1;
        }
        return swaps;
    }
    public int digitSum(int num){
        int sum=0;
        while(num>0){
            int rem=num%10;
            sum+=rem;
            num=num/10;
        }
        return sum;

    }
}
// ------------------------------------------------------------------

// ------------------------------------------------------------------
// runtime - 260ms
class Solution {
    int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
            arr[i][2] = digitSum(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            return Integer.compare(a[0], b[0]);
        });

        boolean[] visit = new boolean[n];
        int swap = 0;
        for (int i = 0; i < n; i++) {
            if (visit[i] || arr[i][1] == i) continue;

            int cycleSize = 0;
            int j = i;
            while (!visit[j]) {
                cycleSize++;
                visit[j] = true;
                j = arr[j][1];
            }
            swap += cycleSize - 1;
        }
        return swap;
    }
}
// ------------------------------------------------------------------