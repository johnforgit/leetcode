class Solution {
public:
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        vector<int> ans = {INT_MAX, INT_MAX};
        int bestQuality = 0;

        for (auto& t : towers) {
            int dist = abs(center[0] - t[0]) + abs(center[1] - t[1]);
            if (dist <= radius) {
                if (t[2] > bestQuality) {
                    bestQuality = t[2];
                    ans = {t[0], t[1]};
                } else if (t[2] == bestQuality) {
                    ans = min(ans, {t[0], t[1]});
                }
            }
        }

        if (ans[0] == INT_MAX) return {-1, -1};
        return ans;
    }
};


// runtime - 1ms
class Solution {
public:
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        // Initialize result for "no tower found" case
        vector<int> result = {-1, -1};
        int maxQuality = -1;
        
        for (const auto& tower : towers) {
            int x = tower[0], y = tower[1], q = tower[2];
            
            // Calculate Manhattan distance
            int distance = abs(x - center[0]) + abs(y - center[1]);
            
            // Check if tower is reachable
            if (distance <= radius) {
                // Update based on quality factor
                if (q > maxQuality) {
                    maxQuality = q;
                    result[0] = x;
                    result[1] = y;
                } 
                // Tie-breaking by lexicographically smallest coordinates
                else if (q == maxQuality) {
                    if (x < result[0] || (x == result[0] && y < result[1])) {
                        result[0] = x;
                        result[1] = y;
                    }
                }
            }
        }
        
        return result;
    }
};


// runtime - 3ms
class Solution {
public:
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        vector<int> ans(2,-1);
        int mxi=-1;
        int n=towers.size();
        for(int i=0; i<n; i++){
            int dist=abs(towers[i][0]-center[0])+abs(towers[i][1]-center[1]);
            if(dist<=radius){
                if(towers[i][2]>mxi){
                    mxi=towers[i][2];
                    ans[0]=towers[i][0];
                    ans[1]=towers[i][1];
                }else if(towers[i][2]==mxi){
                    if(towers[i][0]<ans[0]){
                        ans[0]=towers[i][0];
                        ans[1]=towers[i][1];
                    }else if(towers[i][0]==ans[0] && towers[i][1]<ans[1]){
                        ans[0]=towers[i][0];
                        ans[1]=towers[i][1];
                    }
                }
            }
        }
        return ans;
    }
};


// runtime - 6ms
class Solution {
public:
    bool func(int x,int y, int a, int b){
        pair<int,int> p1={x,y},p2={a,b};
        if(p1<p2)return true;
        return false;
    }
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        vector<int> ans(2,INT_MAX);
        int maxi=INT_MIN;
        for(int i=0;i<towers.size();i++){
            //if manhattan dist<= radius
            if(abs(towers[i][0]-center[0]) + abs(towers[i][1]-center[1])<=radius){
                // maxi <= towers[i][2]
                if(maxi<towers[i][2]){
                    maxi=towers[i][2];
                    ans[0]=towers[i][0];
                    ans[1]=towers[i][1];
                    
                }
                else if(maxi==towers[i][2]){
                    if(func(towers[i][0],towers[i][1], ans[0],ans[1])){
                        ans[0]=towers[i][0];
                        ans[1]=towers[i][1];
                    }
                }
            }
        }
        if(ans[0]==INT_MAX && ans[1]==INT_MAX)return {-1,-1};
        return ans;
        
    }
};
