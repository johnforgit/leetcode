class Solution {
    public boolean judgeCircle(String moves) {
        int x=0, y=0;
        for(char move : moves.toCharArray()) {
            if(move == 'U') y--;
            else if (move == 'D') y++;
            else if (move == 'L') x--;
            else if(move == 'R') x++;
        }
        return x==0 && y==0;
    }
}


// runtime - 2ms
class Solution {
    public boolean judgeCircle(String moves) {
        int[] ch = new int[26];
        for (char move : moves.toCharArray()){
            ch[move - 'A']++;
        }
        return ch['U' - 'A'] == ch['D' - 'A'] && 
                ch['L' - 'A'] == ch['R' - 'A'];
    }
}


// runtime - 3ms
class Solution {
    public boolean judgeCircle(String moves) {
        int[] ch = new int[26];
        for (char move : moves.toCharArray()){
            ch[move - 'A']++;
        }
        return ch['U' - 'A'] == ch['D' - 'A'] && 
                ch['L' - 'A'] == ch['R' - 'A'];
    }
}


// runtime - 4ms
class Solution {
    public boolean judgeCircle(String moves) {
        int right=0;
        int up=0;
        for(final char c:moves.toCharArray()) {
            switch(c) {
                case 'R':
                    right++;
                    break;
                case 'L':
                    right--;
                    break;
                case 'U':
                    up++;
                    break;
                case 'D':
                    up--;
                    break;
            }
        }
        return right==0 && up==0;
    }
}
