class Solution {
    public int[] sumZero(int n) {
        if(n==1) {
            return new int[] {0};
        }
        List<Integer> Res = new ArrayList<>();
        int s=0;
        for(int i=1; i<n; i++) {
            Res.add(-i);
            s -= i;
        }
        Res.add(-s);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Res.get(i);
        }
        return res;
    }
}

// runtime - 0ms
class Solution {
    public int[] sumZero(int n) {
        int[] l = new int[n];
        int index = 0;

        int pairs = n / 2;
        for (int i = 1; i <= pairs; i++) {
            l[index++] = i;
            l[index++] = -i;
        }

        if (n % 2 == 1) {
            l[index++] = 0;
        }

        return l;
    }
}
