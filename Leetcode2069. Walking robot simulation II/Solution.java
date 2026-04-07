class Robot {

    private boolean moved = false;
    private int idx = 0;
    private List<int[]> pos = new ArrayList<>();
    private List<Integer> dir = new ArrayList<>();
    private Map<Integer, String> toDir = new HashMap<>();

    public Robot(int width, int height) {
        toDir.put(0, "East");
        toDir.put(1, "North");
        toDir.put(2, "West");
        toDir.put(3, "South");

        for (int i = 0; i < width; ++i) {
            pos.add(new int[] { i, 0 });
            dir.add(0);
        }
        for (int i = 1; i < height; ++i) {
            pos.add(new int[] { width - 1, i });
            dir.add(1);
        }
        for (int i = width - 2; i >= 0; --i) {
            pos.add(new int[] { i, height - 1 });
            dir.add(2);
        }
        for (int i = height - 2; i > 0; --i) {
            pos.add(new int[] { 0, i });
            dir.add(3);
        }
        dir.set(0, 3);
    }

    public void step(int num) {
        moved = true;
        idx = (idx + num) % pos.size();
    }

    public int[] getPos() {
        return pos.get(idx);
    }

    public String getDir() {
        if (!moved) {
            return "East";
        }
        return toDir.get(dir.get(idx));
    }
}



// runtime - 56ms
class Robot {

    int direction;
    String[] directionName;
    int width, height;
    int[] position;
    int[][] stepMovement;
    int num;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new int[2];
        this.position[0] = 0;
        this.position[1] = 0;
        this.direction = 0;
        this.directionName = new String[]{"East", "North", "West", "South"};
        this.stepMovement = new int[][]{{1,0},  {0,1}, {-1, 0}, {0, -1}};
        this.num = 0;

    }
    
    private void lazyStep(int num){
        num = num%(2*(this.width + this.height) - 4);
        if(num == 0){
            num = 2*(this.width + this.height) - 4;
        }
        while(num > 0){
            switch(direction){
                case 0:
                    if(this.position[0] + num > this.width - 1){
                        num = num - (this.width - this.position[0] - 1);
                        this.direction = (this.direction + 1)%4;
                        this.position[0] = this.width-1;
                    }
                    else{
                        this.position[0] = this.position[0] + num;
                        num = 0;
                    }
                    break;
                case 2:
                    if(this.position[0] - num < 0){
                        num = num - (this.position[0]);
                        this.direction = (this.direction + 1)%4;
                        this.position[0] = 0;
                    }
                    else{
                        this.position[0] = this.position[0] - num;
                        num = 0;
                    }
                    break;
                case 1:
                    if(this.position[1] + num > this.height - 1){
                        num = num - (this.height - this.position[1] - 1);
                        this.direction = (this.direction + 1)%4;
                        this.position[1] = this.height - 1;
                    }
                    else{
                        this.position[1] = this.position[1] + num;
                        num = 0;
                    }
                    break;
                case 3:
                    if(this.position[1] - num < 0){
                        num = num - (this.position[1]);
                        this.direction = (this.direction + 1)%4;
                        this.position[1] = 0;
                    }
                    else{
                        this.position[1] = this.position[1] - num;
                        num = 0;
                    }
                    break;
            }
        }
        this.num = 0;
    }
    public void step(int num) {
        this.num += num;
    }
    
    public int[] getPos() {
        if(this.num > 0)
            this.lazyStep(this.num);
        return this.position;
    }
    
    public String getDir() {
        if(this.num > 0)
            this.lazyStep(this.num);
        return this.directionName[this.direction];
    }
}


// runtime - 57ms
class Robot {

    int width;
    int height;
    int total;

    int curSteps;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        curSteps = 0;

        total = (height + width) * 2 - 4;
        
    }
    
    public void step(int num) {
        curSteps += num;
    }
    
    public int[] getPos() {
        int step = curSteps % total;

        if (step <= width - 1) {
            return new int[]{step, 0};
        }
        step -= width - 1;

        if (step <= height - 1) {
            return new int[]{width-1, step};
        }

        step -= height - 1;

        if (step <= width - 1) {
            return new int[]{width - 1 - step, height - 1};
        }

        step -= width - 1;

        return new int[]{0, height - 1 - step};
    }
    
    public String getDir() {
        if (curSteps == 0) {
            return "East";
        }

        int step = curSteps % total;

        if (step == 0) {
            return "South";
        }

        if (step <= width - 1) {
            return "East";
        }
        step -= width - 1;

        if (step <= height - 1) {
           return "North";
        }

        step -= height - 1;

        if (step <= width - 1) {
           return "West";
        }

        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */


// runtime - 58ms
class Robot {
    public enum DIR {
        UP("North"),
        DOWN("South"),
        LEFT("West"),
        RIGHT("East");

        private final String s;

        DIR(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }
    int maxX;
    int maxY;
    int x;
    int y;
    DIR dir;
    int fullCycle;
    public Robot(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.maxX = width-1;
        this.maxY = height-1;
        this.dir = DIR.RIGHT;
        this.fullCycle = (2 * (width + height)) - 4;
    }
    public void step(int num) {
        this._step(num % fullCycle);
        if(num > fullCycle) { // full cycle and lands at corner (edge case)
            if(x == 0    && y == 0   ) dir = DIR.DOWN;
            if(x == maxX && y == 0   ) dir = DIR.RIGHT;
            if(x == 0    && y == maxY) dir = DIR.LEFT;
            if(x == maxX && y == maxY) dir = DIR.UP;
        }
    }
    private void _step(int num) {
        if(num == 0) return;
        int oldX = this.x;
        int oldY = this.y;
        int dx = 0;
        int dy = 0;
        switch(dir) {
            case DIR.UP   : dy =  1; break;
            case DIR.DOWN : dy = -1; break;
            case DIR.LEFT : dx = -1; break;
            case DIR.RIGHT: dx =  1; break;
            default: throw new Error("beep boop");
        }
        this.x += dx * num;
        this.y += dy * num;
        if(outOfBounds()) {
            int rem = reposition(); //steps taken out of bounds
            this.turnLeft();
            _step(rem);
        }
    }
    private int reposition() {
        if(x < 0) {
            int diff = Math.abs(x);
            this.x = 0;
            return diff;
        }
        if(x > maxX) {
            int diff = this.x - maxX;
            this.x = maxX;
            return diff;
        }
        if(y < 0) {
            int diff = Math.abs(y);
            this.y = 0;
            return diff;
        }
        if(y > maxY) {
            int diff = this.y - maxY;
            this.y = maxY;
            return diff;
        }
        return 0;
    }
    private boolean outOfBounds() {
        return (this.x < 0 || this.x > maxX || this.y < 0 || this.y > maxY);
    }
    public int[] getPos() {
        return new int[] {x, y};
    }
    public String getDir() {
        return this.dir.toString();
    }
    public void turnLeft() {
        switch(this.dir) {
            case DIR.UP    : this.dir = DIR.LEFT;  break;
            case DIR.DOWN  : this.dir = DIR.RIGHT; break;
            case DIR.RIGHT : this.dir = DIR.UP;    break;
            case DIR.LEFT  : this.dir = DIR.DOWN;  break;
            default : throw new Error("unhandled dir");
        }
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */



// runtime - 59ms
class Robot {
    int W;
    int H;
    int x;
    int y;
    int dirX;
    int dirY;

    public Robot(int width, int height) {
        W = width;
        H = height;
        dirX = 1;
        dirY = 0;
        x = 0;
        y = 0;
    }
    
    private void rotate() {
        if (dirX != 0) {
            dirY = dirX;
            dirX = 0;
        } else {
            dirX = -dirY;
            dirY = 0;
        }
    }

    public void step(int num) {
        while (num > 0) {
            if (x == 0 && y == 0 && num / (2 * W + 2 * H - 4) > 0) {
                num = num % (2 * W + 2 * H - 4);
                if (num > 0) {
                    dirX = 1;
                    dirY = 0;
                } else {
                    dirY = -1;
                    dirX = 0;
                }
            }

            int maxNum = num;
            if (dirX > 0) {
                // num == W - 1 - x
                maxNum = Math.min(maxNum, W - 1 - x);
            } else if (dirX < 0) {
                // num = x
                maxNum = Math.min(maxNum, x);
            } else if (dirY > 0) {
                // num == H - 1 - y
                maxNum = Math.min(maxNum, H - 1 - y);
            } else if (dirY < 0) {
                // num = y
                maxNum = Math.min(maxNum, y);
            }
            x = x + maxNum * dirX;
            y = y + maxNum * dirY;
            if (maxNum < num) {
                rotate();
            }
            num -= maxNum;
        }
    }
    
    public int[] getPos() {
        return new int[] {x, y};
    }
    
    public String getDir() {
        if (dirX > 0) {
            return "East";
        }
        if (dirX < 0) {
            return "West";
        }
        if (dirY > 0) {
            return "North";
        }
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */



// runtime - 60ms
class Robot {
    int rows,cols;
    int x , y;
    int dir = 0;
    public Robot(int width, int height) {
        rows = height;
        cols = width;
        x = 0;
        y = 0;
        dir = 0;
        // 0 - east
        // 1 - north
        // 2 - west
        // 3 - south
    }
    private boolean canStep(){        
        if(dir == 0){
            if(x + 1 < cols)
                return true;
            return false;
        } else if(dir == 1){
            if(y+1 < rows)
                return true;
            return false;
        } else if (dir == 2){
            if(x-1 >=0)
                return true;
            return false;
        } else{
            if(y-1 >= 0)
                return true;
            return false;
        }
    }
    private int stepMulti(int num){
        if(dir == 0){
            if(x + num < cols){
                x += num;
                return num;
            } else{
                int step = cols - 1 - x;
                x = cols -1;
                return step;
            }
        } else if(dir == 1){
            if(y + num < rows){
                y += num;
                return num;
            } else{
                int step = rows - 1 - y;
                y = rows - 1;
                return step;
            }
        } else if (dir == 2){
            if(x - num >=0){
                x = x - num;
                return num;
            } else{
                int step = x;
                x = 0;
                return step; 
            }
        } else{
            if(y - num >=0){
                y = y - num;
                return num;
            } else{
                int step = y;
                y = 0;
                return step;
            }
        }
        // System.out.printf("%d, %d\n", x, y);
    }
    private void turn(){
        dir++;
        dir = dir%4;
    }
    public void step(int num) {
        // System.out.printf("actual : %d ", num);
        num = num%(rows-1+cols-1+rows-1+cols-1);
        // System.out.printf("walk: %d\n", num);
        // System.out.printf("Need to walk : %d\n", num);
        while(num != 0){
            if(canStep()){                
                num -= stepMulti(num);
                // System.out.printf("\t%d: took a step: %s, %d,%d\n", num, getDir(), x, y);
            } else{
                // System.out.printf("\told dir: %s ", getDir());
                turn();
                // System.out.printf("new dir: %s\n", getDir());
            }                
        }
        if(x == 0 && y == 0)
            dir = 3;
        if(x == cols-1 && y == 0)
            dir = 0;
        if(x == cols-1 && y == rows-1)
            dir = 1;
        if(x == 0 && y == rows -1)
            dir = 2;
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        if(dir == 0)
            return "East";
        else if (dir == 1)
            return "North";
        else if (dir == 2)
            return "West";
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
