class Solution {

    private static final long HASH_MULTIPLIER = 60013; // Slightly larger than 2 * max coordinate value

    public int robotSim(int[] commands, int[][] obstacles) {
        // Store obstacles in an HashSet for efficient lookup
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(hashCoordinates(obstacle[0], obstacle[1]));
        }

        // Define direction vectors: North, East, South, West
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        int[] currentPosition = { 0, 0 };
        int maxDistanceSquared = 0;
        int currentDirection = 0; // 0: North, 1: East, 2: South, 3: West

        for (int command : commands) {
            if (command == -1) {
                // Turn right
                currentDirection = (currentDirection + 1) % 4;
                continue;
            }
            if (command == -2) {
                // Turn left
                currentDirection = (currentDirection + 3) % 4;
                continue;
            }

            // Move forward
            int[] direction = directions[currentDirection];
            for (int step = 0; step < command; step++) {
                int nextX = currentPosition[0] + direction[0];
                int nextY = currentPosition[1] + direction[1];
                if (obstacleSet.contains(hashCoordinates(nextX, nextY))) {
                    break;
                }
                currentPosition[0] = nextX;
                currentPosition[1] = nextY;
            }

            maxDistanceSquared = Math.max(
                maxDistanceSquared,
                currentPosition[0] * currentPosition[0] +
                currentPosition[1] * currentPosition[1]
            );
        }

        return maxDistanceSquared;
    }

    // Hash function to convert (x, y) coordinates to a unique integer value
    private long hashCoordinates(long x, long y) {
        return x + HASH_MULTIPLIER * y;
    }
}



// runtime - 9ms
class Solution {
    private static final class Coord {
        private int x, y;
        private Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int getDist() {
            return x * x + y * y;
        }

        @Override
        public boolean equals(Object other) {
            if(!(other instanceof Coord coord)) return false;
            return x == coord.x && y == coord.y;
        }
        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
    private static final int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    public int robotSim(int[] commands, int[][] obstacles) {
        int dir = 0, max = 0;
        Set<Coord> invalid = new HashSet<>(obstacles.length, 1.0f);
        for(int[] x : obstacles) invalid.add(new Coord(x[0], x[1]));
        Coord current = new Coord(0, 0);
        for(int command : commands) {
            if(command == -1) dir = dir == 3 ? 0 : dir + 1;
            else if(command == -2) dir = dir == 0 ? 3 : dir - 1;
            else {
                for(int i = 0; i < command; i++) {
                    current.x += dx[dir];
                    current.y += dy[dir];
                    if(invalid.contains(current)) {
                        current.x -= dx[dir];
                        current.y -= dy[dir];
                        break;
                    }
                }
                max = Math.max(max, current.getDist());
            }
        }
        return max;
    }
}



// runtime - 11ms
class Solution {
    static int[][] dirs = { {0,1}, {1,0}, {0,-1}, {-1,0} };

    public int robotSim(int[] commands, int[][] obstacles) {
        int dirIdx = 0;
        int result = 0;

        Set<Long> set = new HashSet<>();

        for (int[] obs : obstacles ) {
            set.add( ((long)obs[0] << 32L) | (obs[1]&0x7fffffff) );
        }

        int xx = 0;
        int yy = 0;
        int[] dir = dirs[dirIdx];
        for ( int com : commands ) {
            if ( com == -1 ) { dirIdx = (dirIdx + 1) % 4; dir = dirs[dirIdx]; }
            else if ( com == -2 ) { dirIdx = (dirIdx + 3) % 4; dir = dirs[dirIdx]; }
            else {
                for ( int ii = 1; ii <= com; ii++ ) {
                    xx += dir[0];
                    yy += dir[1];
                    if ( set.contains( ((long)xx<<32L) | (yy&0x7fffffff) ) ) {
                        xx -= dir[0];
                        yy -= dir[1];
                        break;
                    }
                }
                result = Math.max(result, xx*xx + yy*yy);
            }
// System.out.printf("xx=%d, yy=%d, dir=%s\n", xx, yy, Arrays.toString(dir));
        }

        return result;
    }
}



// runtime - 12ms
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> blocked = new HashSet<>(obstacles.length * 2 + 1);

        for (int[] obstacle : obstacles) {
            blocked.add(encode(obstacle[0], obstacle[1]));
        }

        // North, East, South, West
        int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int dir = 0;
        int x = 0;
        int y = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                dir = (dir + 1) % 4;
            } else if (cmd == -2) {
                dir = (dir + 3) % 4;
            } else {
                for (int step = 0; step < cmd; step++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];

                    if (blocked.contains(encode(nx, ny))) {
                        break;
                    }

                    x = nx;
                    y = ny;

                    int dist = x * x + y * y;
                    if (dist > maxDist) {
                        maxDist = dist;
                    }
                }
            }
        }

        return maxDist;
    }

    private long encode(int x, int y) {
        return ((long) x << 32) | (y & 0xffffffffL);
    }
}


