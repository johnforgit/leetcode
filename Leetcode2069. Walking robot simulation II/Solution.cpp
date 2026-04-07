class Robot {
private:
    bool moved = false;
    int idx = 0;
    vector<pair<int, int>> pos;
    vector<int> dir;
    unordered_map<int, string> to_dir = {
        {0, "East"}, {1, "North"}, {2, "West"}, {3, "South"}};

public:
    Robot(int width, int height) {
        for (int i = 0; i < width; ++i) {
            pos.emplace_back(i, 0);
            dir.emplace_back(0);
        }
        for (int i = 1; i < height; ++i) {
            pos.emplace_back(width - 1, i);
            dir.emplace_back(1);
        }
        for (int i = width - 2; i >= 0; --i) {
            pos.emplace_back(i, height - 1);
            dir.emplace_back(2);
        }
        for (int i = height - 2; i > 0; --i) {
            pos.emplace_back(0, i);
            dir.emplace_back(3);
        }
        dir[0] = 3;
    }

    void step(int num) {
        moved = true;
        idx = (idx + num) % pos.size();
    }

    vector<int> getPos() { return {pos[idx].first, pos[idx].second}; }

    string getDir() {
        if (!moved) {
            return "East";
        }
        return to_dir[dir[idx]];
    }
};


// runtime - 10ms
#pragma GCC optimize("Ofast,unroll-loops,inline")
#include <vector>
#include <string>

static const int speedup = []() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    return 0;
}();

class Robot {
    int w, h, p, curr;
    bool moved;
    // Используем массив строк, чтобы обращаться по индексу (0-3) без if-else
    const std::string dirs[4] = {"East", "North", "West", "South"};

public:
    Robot(int width, int height) : w(width), h(height), curr(0), moved(false) {
        p = (w + h - 2) << 1;
    }
    
    inline void step(int num) {
        moved = true;
        curr = (curr + num) % p;
    }
    
    inline std::vector<int> getPos() {
        if (curr < w) return {curr, 0};
        if (curr < w + h - 1) return {w - 1, curr - w + 1};
        if (curr < (w << 1) + h - 2) return { (w << 1) + h - 3 - curr, h - 1};
        return {0, p - curr};
    }
    
    inline std::string getDir() {
        if (!moved) return dirs[0];
        if (curr == 0) return dirs[3]; // Робот вернулся в начало — всегда South
        
        // Математическое определение направления без тяжелых веток
        if (curr < w) return dirs[0];
        if (curr < w + h - 1) return dirs[1];
        if (curr < (w << 1) + h - 2) return dirs[2];
        return dirs[3];
    }
};


// runtime - 11ms
class Robot {
public:
  Robot(int width, int height) : width(width), height(height) {}
  
  void step(int num) {
    // cout << "stepping: " << num << " at perimeter_index: " << perimeter_index << '\n';
    perimeter_index = (perimeter_index + num) % perimeter_cycle_length();
    // auto pos = getPos();
    // cout << "new perimeter_index: " << perimeter_index << " (" << pos[0] << ", " << pos[1] << ")\n";
    if (perimeter_index == ll_index() || perimeter_index > ul_index()) {
      dir_index = south;
    } else if (perimeter_index <= lr_index()) {
      dir_index = east;
    } else if (perimeter_index <= ur_index()) {
      dir_index = north;
    } else {
      dir_index = west;
    }
  }
  
  vector<int> getPos() {
    if (perimeter_index <= lr_index()) {
      return {perimeter_index, 0};
    } else if (perimeter_index <= ur_index()) {
      auto offset = perimeter_index - lr_index();
      return {width - 1, offset};
    } else if (perimeter_index <= ul_index()) {
      auto offset = perimeter_index - ur_index();
      return {width - 1 - offset, height - 1};
    } else {
      auto offset = perimeter_index - ul_index();
      return {0, height - 1 - offset};
    }
  }
  
  string getDir() {
    return dirs[dir_index];
  }

private:
  int width;
  int height;
  int perimeter_index = 0;
  int dir_index = 0;
  static constexpr inline int east = 0;
  static constexpr inline int north = 1;
  static constexpr inline int west = 2;
  static constexpr inline int south = 3;
  static constexpr inline array<string, 4> dirs = {
      "East", "North", "West", "South"
  };

  int perimeter_cycle_length() {
     return (width + height - 2) << 1;
  }

  int ll_index() {
    return 0;
  }

  int lr_index() {
    return width - 1;
  }

  int ur_index() {
    return lr_index() + height - 1;
  }

  int ul_index() {
    return ur_index() + width - 1;
  }

};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */


// runtime - 12ms
class Robot {
private:
    vector<vector<int>> dirs = {{1,0},{0,1},{-1,0},{0,-1}};
    vector<string> stringdir = {"East", "North", "West", "South"};
    int currdir;
    int posx, posy;
    int height, width;
    int perimeter;

public:
    Robot(int width, int height) : currdir(0), posx(0), posy(0), height(height), width(width) {
        perimeter = 2*height+2*width-4;
    }
    
    void step(int num) {
        num = num % (perimeter);
        if (num == 0) num = perimeter;

        int dx = dirs[currdir][0];
        int dy = dirs[currdir][1];
        int newposx = posx + dx*num;
        int newposy = posy + dy*num;

        if (newposx < 0){
            posx = 0;
            currdir = (currdir+1)%4;
            step(-newposx);
            return;
        }
        else if (newposx > width-1){
            posx = width - 1;
            currdir = (currdir+1)%4;
            step(newposx-posx);
            return;
        }
        else if (newposy < 0){
            posy = 0;
            currdir = (currdir+1)%4;
            step(-newposy);
            return;
        }
        else if (newposy > height-1){
            posy = height - 1;
            currdir = (currdir+1)%4;
            step(newposy-posy);
            return;
        }
        posx = newposx;
        posy = newposy;
    }
    
    vector<int> getPos() {
        return {posx, posy};
    }
    
    string getDir() {
        return stringdir[currdir];
    }
};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */


// runtime - 14ms
class Robot {
public:
    int dir = 0; //0 = east, 1 = north, 2 = west, 3 = south
    int stepp = 0;
    int x = 0, y = 0, w, h, xx;

    Robot(int width, int height) {
        w = width;
        h = height;
        xx = 2*((w-1) + (h-1));
    }
    
    void step(int num) {
        stepp+=num;
    }
    
    vector<int> getPos() {
        int z = stepp%xx;
        if(z<w){
            return {z, 0};
        }else if(z<(w+h-1)){
            return {w-1, z-w+1};
        }else if(z<(2*w+h-2)){
            return {2*w-z+h-3, h-1};
        }else{
            return {0, 2*h+2*w -z-4};
        }
    }
    
    string getDir() {
        if(stepp==0) return "East";
        int z = stepp%xx;

        if(z>0 && z<w) return "East";
        if(z>0 && z<(w+h-1)) return "North";
        else if(z>0 && z<(2*w+h-2)) return "West";
        else return "South";
    }
};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */


// runtime - 17ms
#pragma GCC optimize("Ofast,unroll-loops,inline")
#include <vector>
#include <string>

static const int speedup = []() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);
    return 0;
}();

class Robot {
    int w, h, p;
    int curr;
    bool moved;
    // Статический вектор, чтобы не выделять память в куче при каждом вызове getPos
    std::vector<int> pos_res;

public:
    Robot(int width, int height) : w(width), h(height), curr(0), moved(false), pos_res(2) {
        p = (w + h - 2) << 1;
    }
    
    // Используем inline для исключения затрат на вызов функции
    inline void step(int num) {
        moved = true;
        curr = (curr + num) % p;
    }
    
    inline std::vector<int> getPos() {
        if (curr < w) {
            pos_res[0] = curr; pos_res[1] = 0;
        } else if (curr < w + h - 1) {
            pos_res[0] = w - 1; pos_res[1] = curr - w + 1;
        } else if (curr < (w << 1) + h - 2) {
            pos_res[0] = (w << 1) + h - 3 - curr; pos_res[1] = h - 1;
        } else {
            pos_res[0] = 0; pos_res[1] = p - curr;
        }
        return pos_res;
    }
    
    inline std::string getDir() {
        if (!moved) return "East";
        if (curr == 0) return "South";
        if (curr < w) return "East";
        if (curr < w + h - 1) return "North";
        if (curr < (w << 1) + h - 2) return "West";
        return "South";
    }
};
