class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[][] memo;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // make a dictionary of the allowed words
        for(String s : allowed) {
            int key = (s.charAt(0)-'A')*7 + (s.charAt(1)-'A');
            map.computeIfAbsent(key, k->new ArrayList<>()).add(s.charAt(2)-'A');
        }
        memo = new int[20][1<<7];
        return solve(bottom);
    }

    public boolean solve(String row) {
        if(row.length() == 1)
            return true;
        return buildNextRow(row, 0, new StringBuilder());
    }

    // helper to build next row character by character
    boolean buildNextRow(String row, int idx, StringBuilder nextRow) {
        if(idx==row.length()-1)
            return solve(nextRow.toString());

        // get the key of the current row of string
        int key = (row.charAt(idx)-'A')*7 + (row.charAt(idx+1)-'A');
        // if the key is not present in the dictionary return false
        if(!map.containsKey(key))
            return false;
        
        for(int val:map.get(key)) {
            nextRow.append((char)(val+'A'));
            if(buildNextRow(row, idx+1, nextRow))
                return true;
            nextRow.deleteCharAt(nextRow.length()-1);
        }
        return false;
    }
}
