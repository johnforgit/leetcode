class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions,
                                      vector<int>& healths, string directions) {
        int n = positions.size();
        vector<int> indices(n), result;
        stack<int> stack;

        for (int index = 0; index < n; ++index) {
            indices[index] = index;
        }

        sort(indices.begin(), indices.end(),
             [&](int lhs, int rhs) { return positions[lhs] < positions[rhs]; });

        for (int currentIndex : indices) {
            // Add right-moving robots to the stack
            if (directions[currentIndex] == 'R') {
                stack.push(currentIndex);
            } else {
                while (!stack.empty() && healths[currentIndex] > 0) {
                    // Pop the top robot from the stack for collision check
                    int topIndex = stack.top();
                    stack.pop();

                    // Top robot survives, current robot is destroyed
                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        stack.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        // Current robot survives, top robot is destroyed
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        // Both robots are destroyed
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        // Collect surviving robots
        for (int index = 0; index < n; ++index) {
            if (healths[index] > 0) {
                result.push_back(healths[index]);
            }
        }
        return result;
    }
};



// runtime - 10ms
class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        const size_t n = positions.size();
        //static std::array<int, 100'000> staticIdxs;
        //std::span idxs(staticIdxs.begin(), n);
        std::pair<int, int> idxs[n];
        for (int i = 0; i < n; ++i)
        {
            idxs[i].first = positions[i];
            idxs[i].second = i;
        }
        std::sort(idxs, idxs + n);
        int st[n];
        size_t j{};
        for (auto [_, idx] : idxs)
        {
            if (directions[idx] == 'R')
            {
                st[j++] = idx;
                continue;
            }
            auto& ih = healths[idx];
            while (j > 0 && ih)
            {
                auto& sh = healths[st[j - 1]];
                if (sh == ih)
                {
                    sh = 0;
                    ih = 0;
                    --j;
                    break;
                }
                if (sh < ih)
                {
                    sh = 0;
                    ih--;
                    --j;
                }
                else
                {
                    sh--;
                    ih = 0;
                    if (sh == 0)
                        --j;
                }
            }
        }
        size_t count = 0;
        for (auto h : healths)
        {
            if (h)
            {
                healths[count++] = h;
            }
        }
        healths.resize(count);
        return std::move(healths);
    }
};


// runtime - 14ms
class Solution {
public:
    using int2=pair<int, int>;// (position, idx)
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string& directions) {
        const int n=positions.size();
        int2 robot[n];
        for(int i=0; i<n; i++)// 0-indexed is fine
            robot[i]={positions[i], i};
        sort(robot, robot+n);

        int stack[n], top=-1;
        for(auto& [_, i]: robot){
            if (directions[i]=='R') stack[++top]=i;
            else{
                while(top!=-1 && healths[i]>0){
                    int j=stack[top];
                    int x=healths[j]-healths[i];
                    if (x>0) healths[j]--, healths[i]=0;
                    else if (x<0) healths[j]=0, healths[i]--, top--;
                    else healths[i]=healths[j]=0, top--;
                }
            }
        }
        vector<int> ans;
        for(int x: healths)
            if (x>0) ans.push_back(x);
        return ans;
    }
};

auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();


// runtime - 18ms
class Solution {
public:
    using int2=pair<int, int>;// (position, idx)
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string& directions) {
        const int n=positions.size();
        int2 robot[n];
        for(int i=0; i<n; i++)// 0-indexed is fine
            robot[i]={positions[i], i};
        sort(robot, robot+n);

        int stack[n], top=-1;
        for(auto& [_, i]: robot){
            if (directions[i]=='R') stack[++top]=i;
            else{
                while(top!=-1 && healths[i]>0){
                    int j=stack[top];
                    int x=healths[j]-healths[i];
                    if (x>0) healths[j]--, healths[i]=0;
                    else if (x<0) healths[j]=0, healths[i]--, top--;
                    else healths[i]=healths[j]=0, top--;
                }
            }
        }
        vector<int> ans;
        for(int x: healths)
            if (x>0) ans.push_back(x);
        return ans;
    }
};

auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();


// runtime - 23ms
class Solution {
public:
    using int2 = pair<int, int>;
    vector<int> survivedRobotsHealths(vector<int>& positions,
    vector<int>& healths, string directions) {
        const int n = positions.size();
        vector<int2> robot(n);

        for(int i = 0; i < n; i++){
            robot[i] = {positions[i], i};
        }            
        sort(robot.begin(), robot.end(), greater<>());

        vector<int> stack;
        for(auto& [pos, i]: robot){
            if(directions[i] == 'L')stack.push_back(i);
            else{
                while(!stack.empty() && healths[i] > 0){
                    int j = stack.back();
                    int x = healths[j] - healths[i];
                    if(x > 0) healths[j]--,healths[i] = 0;
                    else if(x < 0) healths[j] = 0,healths[i]--, stack.pop_back();
                    else healths[j] = healths[i] = 0, stack.pop_back();
                }
            }
        }
        vector<int> ans;
        for(int x: healths){
            if(x > 0) ans.push_back(x);
        }

        return ans;
    }
};



// runtime - 27ms
class Solution {
public:
    using int2=pair<int, int>;// (position, idx)
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string& directions) {
        const int n=positions.size();
        vector<int2> robot(n);
        for(int i=0; i<n; i++)// 0-indexed is fine
            robot[i]={positions[i], i};
        sort(robot.begin(), robot.end(), greater<>());

        vector<int> stack;
        for(auto& [pos, i]: robot){
            if (directions[i]=='L') stack.push_back(i);
            else{
                while(!stack.empty() && healths[i]>0){
                    int j=stack.back();
                    int x=healths[j]-healths[i];
                    if (x>0) healths[j]--, healths[i]=0;
                    else if (x<0) healths[j]=0, healths[i]--, stack.pop_back();
                    else healths[i]=healths[j]=0, stack.pop_back();
                }
            }
        }
        vector<int> ans;
        for(int x: healths)
            if (x>0) ans.push_back(x);
        return ans;
    }
};




auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();
