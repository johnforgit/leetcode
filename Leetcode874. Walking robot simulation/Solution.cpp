class Solution {
private:
    static const long long HASH_MULTIPLIER =
        60013;  // Slightly larger than 2 * max coordinate value

    // Hash function to convert (x, y) coordinates to a unique integer value
    long long hashCoordinates(long long x, long long y) {
        return x + HASH_MULTIPLIER * y;
    }

public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        // Store obstacles in an unordered_set for efficient lookup
        unordered_set<long long> obstacleSet;
        for (auto& obstacle : obstacles) {
            obstacleSet.insert(hashCoordinates(obstacle[0], obstacle[1]));
        }

        // Define direction vectors: North, East, South, West
        vector<vector<int>> directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        vector<int> currentPosition = {0, 0};
        int maxDistanceSquared = 0;
        int currentDirection = 0;  // 0: North, 1: East, 2: South, 3: West

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
            vector<int> direction = directions[currentDirection];
            for (int step = 0; step < command; step++) {
                int nextX = currentPosition[0] + direction[0];
                int nextY = currentPosition[1] + direction[1];
                if (obstacleSet.contains(hashCoordinates(nextX, nextY))) {
                    break;
                }
                currentPosition[0] = nextX;
                currentPosition[1] = nextY;
            }

            maxDistanceSquared =
                max(maxDistanceSquared,
                    currentPosition[0] * currentPosition[0] +
                        currentPosition[1] * currentPosition[1]);
        }

        return maxDistanceSquared;
    }
};


// runtime - 1ms
#pragma GCC optimize("Ofast,unroll-loops,inline")
#pragma GCC target("avx2,bmi,bmi2")

static const int _ = []() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

// Используем огромный разреженный массив как хэш-таблицу.
// 65536 — это степень двойки, маска будет летать.
long long ht[65536]; 
const long long EMPTY = -1e15;

class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        // Быстрое зануление через memset — это SIMD уровень.
        for(int i = 0; i < 65536; ++i) ht[i] = EMPTY;

        for (const auto& obs : obstacles) {
            long long key = ((long long)obs[0] << 32) | (obs[1] & 0xFFFFFFFFLL);
            int h = (unsigned int)(key ^ (key >> 32)) & 65535; // Маска вместо %
            while (ht[h] != EMPTY) h = (h + 1) & 65535;
            ht[h] = key;
        }

        // Направления в константах, чтобы не лезть в память лишний раз
        int x = 0, y = 0, dir = 0;
        int maxDistSq = 0;

        for (int cmd : commands) {
            if (cmd < 0) {
                // Магия: (dir + 1) & 3 — это поворот вправо, (dir + 3) & 3 — влево.
                if (cmd == -1) dir = (dir + 1) & 3;
                else dir = (dir + 3) & 3;
            } else {
                // Кэшируем смещения прямо здесь
                int dx = (dir == 1) ? 1 : (dir == 3 ? -1 : 0);
                int dy = (dir == 0) ? 1 : (dir == 2 ? -1 : 0);
                
                while (cmd--) {
                    int nx = x + dx;
                    int ny = y + dy;
                    long long key = ((long long)nx << 32) | (ny & 0xFFFFFFFFLL);
                    
                    // Ультра-быстрый поиск
                    int h = (unsigned int)(key ^ (key >> 32)) & 65535;
                    bool hit = false;
                    while (ht[h] != EMPTY) {
                        if (ht[h] == key) { hit = true; break; }
                        h = (h + 1) & 65535;
                    }
                    
                    if (hit) break;
                    x = nx; y = ny;
                }
                int cur = x * x + y * y;
                if (cur > maxDistSq) maxDistSq = cur;
            }
        }
        return maxDistSq;
    }
};



// runtime - 4ms
#include <memory_resource>
pmr::unsynchronized_pool_resource pool;
class Solution {
public:
    static int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        const long long M=60001, lb=-30000;
        pmr::unordered_set<long long> obSet(&pool);
        obSet.reserve(obstacles.size());
        for(auto& ob: obstacles){
            const long long x=ob[0]-lb, y=ob[1]-lb;
            obSet.insert(x*M+y);
        }

        const int dir[4][2]={{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int x=0, y=0, dx=0, dy=1, face=0, maxD2=0;
        for(int c: commands){
            switch(c){
                case -2: face=(face+1)%4; dx=dir[face][0]; dy=dir[face][1]; break;
                case -1: face=(face+3)%4; dx=dir[face][0]; dy=dir[face][1]; break;
                default:
                //    cout<<"\nc="<<c<<" face="<<face<<":";
                    for(int i=0; i<c; i++){
                        x+=dx, y+=dy;
                        if (obSet.count((x-lb)*M+y-lb)) {
                            x-=dx;  // previous move
                            y-=dy;
                            break;
                        }
                    //    cout<<"("<<x<<","<<y<<"),";
                        maxD2=max(maxD2, x*x+y*y);
                    }
            }
        }
        return maxD2;
    }
};



// runtime - 17ms
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        // lambda functions

        auto f = [](int x, int y) {
            return x * 60010 + y;
        };

        unordered_set<int> st;
        for(auto &obs : obstacles){
            st.insert( f(obs[0], obs[1]) ) ;
        }

        vector<pair<int,int>> dir = { {0,1} , {1,0} , {0,-1}, {-1,0}};
        //                            north    east    south   west

        int x = 0 , y =0;
        int d = 0; //north
        long long max_dist = 0;

        for(int cmd : commands){
            if(cmd == -2){
                d = (d+3)%4 ;
            } else if (cmd == -1){
                d = (d+1)%4 ;
            } else {
                for(int i = 0; i<cmd; i++){
                    int nx = x + dir[d].first;
                    int ny = y + dir[d].second;

                    int key = f(nx , ny);

                    if(st.find(key) != st.end()){
                        break;
                    }

                    x = nx;
                    y = ny;

                    max_dist = max( max_dist, 1LL*x*x + 1LL* y*y);
                
                }
            }
        }
        
        return max_dist;
    }
};



// runtime - 20ms
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        reverse(commands.begin(),commands.end());
        std::set<pair<int,int>> set;
        for(auto& n:obstacles)set.insert({n[0],n[1]});
        vector<pair<int,int>> moveMap{{0,1},{1,0},{0,-1},{-1,0}};
        int dir=0;
        int posX=0;
        int posY=0;
        int ans=0;
        while(commands.size()){
            if(commands.back()==0){
                commands.pop_back();
            }else if(commands.back()==-1){
                dir++;
                dir%=4;
                commands.pop_back();
            }else if(commands.back()==-2){
                dir+=3;
                dir%=4;
                commands.pop_back();
            }else{
                auto[moveX,moveY]=moveMap[dir];
                if(set.contains({moveX+posX,moveY+posY})){
                    commands.pop_back();                
                }else{
                    posX+=moveX;
                    posY+=moveY;
                    commands.back()--;
                }
            }
            ans=max(ans,posX*posX+posY*posY);
        }
        return ans;
    }
};


