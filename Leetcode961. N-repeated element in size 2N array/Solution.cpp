#include <unordered_set>
#include <vector>
using namespace std;

class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        unordered_set<int> seen;

        for (int x : nums) {
            if (seen.count(x)) {
                return x;   // first duplicate
            }
            seen.insert(x);
        }
        return -1; // guaranteed not to happen

        /*
        for(int i=0;i<nums.size()-2;i++){
            if(nums[i]==nums[i+1] || nums[i]==nums[i+2]){
                return nums[i];
            }
        }
        

        return nums[nums.size()-1];
        */
    }
};


class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {

        for(int i=0;i<nums.size()-2;i++){
            if(nums[i]==nums[i+1] || nums[i]==nums[i+2]){
                return nums[i];
            }
        }
        

        return nums[nums.size()-1];
    }
};
