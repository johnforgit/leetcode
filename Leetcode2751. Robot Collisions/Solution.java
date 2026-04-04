class Solution {

    public List<Integer> survivedRobotsHealths(
        int[] positions,
        int[] healths,
        String directions
    ) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        // keep a copy of the original indices array
        for(int idx=0; idx<n; ++idx) {
            indices[idx] = idx;
        }

        Arrays.sort(
            indices,
            (lhs, rhs) -> Integer.compare(positions[lhs], positions[rhs])
        );

        for(int curIdx : indices) {
            // add the right moving robots to the stack
            if(directions.charAt(curIdx) == 'R') {
                stack.push(curIdx);
            } else {
                while ((!stack.isEmpty()) && (healths[curIdx] > 0)) {
                    // pop the top robot from the stack for collision check
                    int topIdx = stack.pop();

                    // top robot survives, current robot is destroyed
                    if(healths[topIdx] > healths[curIdx]) {
                        healths[topIdx] -= 1;
                        healths[curIdx] = 0;
                        stack.push(topIdx);
                    } else if (healths[topIdx] < healths[curIdx]) {
                        // current robot survives, top robot is destroyed
                        healths[curIdx] -= 1;
                        healths[topIdx] = 0;
                    } else {
                        // both robots are destroyed
                        healths[curIdx] = 0;
                        healths[topIdx] = 0;
                    }
                }
            }
        }

        // collect the surviving robots
        for(int index=0; index < n; ++index) {
            if(healths[index] > 0) {
                res.add(healths[index]);
            }
        }

        return res;
    }
}


// runtime - 14ms

class Solution{
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        // Optimization: Pack (position, index) into a single long to avoid Integer boxing
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            // Upper 32 bits: position, Lower 32 bits: original index
            packed[i] = ((long) positions[i] << 32) | i;
        }

        // Primitive sort is much faster than object-based sort
        Arrays.sort(packed);

        // Manual stack with a primitive array
        int[] stack = new int[n];
        int top = -1;

        for (long p : packed) {
            int i = (int) (p & 0xFFFFFFFFL); // Extract original index
            
            if (directions.charAt(i) == 'R') {
                stack[++top] = i;
            } else {
                while (top != -1 && healths[i] > 0) {
                    int j = stack[top];
                    if (healths[j] > healths[i]) {
                        healths[j]--;
                        healths[i] = 0;
                    } else if (healths[j] < healths[i]) {
                        healths[i]--;
                        healths[j] = 0;
                        top--;
                    } else {
                        healths[i] = 0;
                        healths[j] = 0;
                        top--;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) result.add(h);
        }
        return result;
    }
}


// runtime - 15ms
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        char[] dirs = directions.toCharArray(); // avoid repeated charAt

        // Primitive index sort — no Integer boxing
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        // Counting sort would be O(n) but positions go up to 1e8, so use comparison sort
        // Trick: pack position + index into a long to sort primitively
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) packed[i] = ((long) positions[i] << 20) | i;
        Arrays.sort(packed); // sorts by position, no comparator lambda, no boxing

        // Primitive int[] stack — no Deque, no autoboxing
        int[] stack = new int[n];
        int top = -1;

        for (long p : packed) {
            int i = (int)(p & 0xFFFFF); // extract original index

            if (dirs[i] == 'R') {
                stack[++top] = i;
            } else {
                // Left-moving robot: resolve collisions
                while (top >= 0 && healths[i] > 0) {
                    int j = stack[top];
                    if (healths[j] > healths[i]) {
                        healths[j]--;
                        healths[i] = 0;
                    } else if (healths[j] < healths[i]) {
                        healths[i]--;
                        healths[j] = 0;
                        top--;
                    } else {
                        healths[j] = 0;
                        healths[i] = 0;
                        top--;
                    }
                }
            }
        }

        // Collect survivors in original order — pre-sized ArrayList
        List<Integer> result = new ArrayList<>(top + 2);
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) result.add(healths[i]);
        }
        return result;
    }
}


// runtime - 16ms
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
    int n=positions.length;
    long[]packed=new long[n];
    for(int i=0;i<n;i++){
    packed[i]=((long)positions[i]<<32)|i;
        }
        Arrays.sort(packed);
        int[]st=new int[n];
        int top=-1;
        for(long p:packed){
            int i=(int)(p&0xFFFFFFFFL);
    if(directions.charAt(i)=='R'){
        st[++top]=i;
    }else{
    while(top>=0&&healths[i]>0){
        int j=st[top];
        if(healths[j]>healths[i]){
            healths[j]--;
            healths[i]=0;
        }else if(healths[j]<healths[i]){
            healths[i]--;
            healths[j]=0;
            top--;
        }else{
            healths[i]=0;
            healths[j]=0;
            top--;
        }
    }
    }
        }
        List<Integer>ans=new ArrayList<>();
        for(int h:healths){
            if(h>0)
                ans.add(h);
        }
        return ans;
    }
}


// runtime - 17ms
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        final int  POS_SHIFT =       21;
        final long DIR_LEFT_MASK =   0x10_0000L;
        final int  INDEX_MASK =      0xF_FFFF;
        final int  DEAD = -1_000_000;
        
        int n = positions.length;
        byte[] dirs = directions.getBytes();

        // Build array of robots, and sort it by position.
        long[] robots = new long[n];
        for (int i = 0; i < n; i++)
            robots[i] = ((long)positions[i] << POS_SHIFT) + 
                        ((dirs[i] == 'L') ? DIR_LEFT_MASK : 0) + i;
        Arrays.sort(robots);
        
        // Using the sorted array of robots, remove any robots 
        // from the ends of the array which are headed outward 
        // from the center, that have nothing to collide with.
        int rightEnd = n - 1;
        int leftEnd = 0;
        while (leftEnd < rightEnd && (robots[leftEnd] & DIR_LEFT_MASK) != 0) 
            leftEnd++;
        while (leftEnd < rightEnd && (robots[rightEnd] & DIR_LEFT_MASK) == 0) 
            rightEnd--;

        // Repeatedly get two robots going opposite directions 
        // with no other robot between that pair.  If there are 
        // multiple rightward moving robots without any leftward 
        // robot to collide with, then save the extra rightward 
        // moving robots on a stack for later, until a leftward 
        // robot is found to collide with, if any still exist.
        if (leftEnd < rightEnd) {
            int[] stk = new int[rightEnd - leftEnd + 1];
            int stkIdx = 0;
            int idx = leftEnd;
            while (idx <= rightEnd) {
                if ((robots[idx] & DIR_LEFT_MASK) == 0)
                    stk[stkIdx++] = (int)robots[idx++] & INDEX_MASK;
                else if (stkIdx == 0)
                    idx++;
                else {
                    int goRightIdx = stk[stkIdx - 1];
                    int goLeftIdx = (int)robots[idx] & INDEX_MASK;
                    int goRightHealth = healths[goRightIdx];
                    int goLeftHealth = healths[goLeftIdx];
                    if (goRightHealth == goLeftHealth) {
                        healths[goRightIdx] = DEAD;
                        healths[goLeftIdx] = DEAD;
                        stkIdx--;
                        idx++;
                    } else if (goRightHealth >= goLeftHealth) {
                        healths[goRightIdx]--;
                        healths[goLeftIdx] = DEAD;
                        idx++;
                    } else {
                        healths[goRightIdx] = DEAD;
                        healths[goLeftIdx]--;
                        stkIdx--;
                    }
                }
            }
        }
        
        // Scan through the healths[] array to output the health 
        // value of any robots that are still alive.  Return the 
        // surviving health values as the answer to this leetcode 
        // problem.
        List<Integer> result = new ArrayList();
        for (int health : healths)
            if (health > DEAD)
                result.add(health);
        return result;
    }
}


// runtime - 21ms
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++)
            indices[i] = i;

        Arrays.sort(indices, (a, b) -> positions[a] - positions[b]);

        int[] st = new int[n];
        int top = -1;

        for (int idx : indices) {
            if (directions.charAt(idx) == 'R') {
                st[++top] = idx; // push right-moving robot
            } else {
                while (top >= 0 && healths[idx] > 0) {
                    int rightRobot = st[top]; // peek top
                    if (healths[rightRobot] < healths[idx]) {
                        healths[rightRobot] = 0;
                        healths[idx]--;
                        top--; // pop
                    } else if (healths[rightRobot] > healths[idx]) {
                        healths[idx] = 0;
                        healths[rightRobot]--;
                    } else {
                        healths[rightRobot] = 0;
                        healths[idx] = 0;
                        top--; // pop
                    }
                }
            }
        }

        // Collect survivors from healths array
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0)
                result.add(healths[i]);
        }
        return result;
    }
}
