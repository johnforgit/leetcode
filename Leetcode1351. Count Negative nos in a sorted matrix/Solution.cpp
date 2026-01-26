class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int res = 0;
        int index = grid[0].size()-1;
        for(auto &row : grid) {
            while(index >= 0 && row[index]<0) {
                index--;
            }
            res += (grid[0].size()-1) - index;
        }
        return res;   
    }
};
