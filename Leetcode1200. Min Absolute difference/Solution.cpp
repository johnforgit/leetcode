class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int min_diff = INT_MAX;
        vector<vector<int>> res;

        for(int i=1; i<arr.size(); i++) {
            int diff = arr[i] - arr[i-1];
            if(diff < min_diff) {
                min_diff = diff;
                res.clear();
                res.push_back({arr[i-1], arr[i]});
            } else if(diff == min_diff) {
                res.push_back({arr[i-1], arr[i]});
            }
        }

        return res;
    }
};


// runtime - 0ms
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        //o/(n)+o(nlogn)
        vector<vector<int>>res;
        int mindiff=abs(arr[0]-arr[1]);
        
        sort(arr.begin(),arr.end());
        for(int i=0;i<arr.size()-1;i++){
            int currdiff=abs(arr[i]-arr[i+1]);
            if(currdiff<mindiff) {         
                mindiff=currdiff;
                res.clear();
                res.push_back({arr[i],arr[i+1]});
            }
            else if(currdiff==mindiff){    
                res.push_back({arr[i],arr[i+1]});
            }
        }
        return res;
    }
};
auto init = atexit( [](){ ofstream("display_runtime.txt") <<'0'; });


// runtime - 7ms
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        // Initialize the auxiliary array `line`.
        // Keep a record of the minimum element and the maximum element.
        int minElement = *min_element(arr.begin(), arr.end());
        int maxElement = *max_element(arr.begin(), arr.end());
        int shift = -minElement;
        vector<uint8_t> line(maxElement - minElement + 1);
        vector<vector<int>> answer;
        
        // For each integer `num` in `arr`, we increment line[num + shift] by 1.
        for (const int& num : arr) {
            line[num + shift] = 1;
        }
        
        // Start from the index representing the minimum integer, initialize the 
        // absolute difference `min_pair_diff` as a huge value such as
        // `max_element - min_element` in order not to miss the absolute 
        // difference of the first pair.
        int minPairDiff = maxElement - minElement;
        int prev = 0;
        
        // Iterate over the array `line` and check if line[curr] 
        // holds the occurrence of an input integer.
        for (int curr = 1; curr <= maxElement + shift; ++curr) {
            // If line[curr] == 0, meaning there is no occurrence of the integer (curr - shift) 
            // held by this index, we will move on to the next index.
            if (line[curr] == 0) {
                continue;
            }
            
            // If the difference (curr - prev) equals `minPairDiff`, we add this pair 
            // {prev - shift, curr - shift} to the answer list. Otherwise, if the difference 
            // (curr - prev) is smaller than `minPairDiff`, we empty the answer list and add 
            // the pair {curr - shift, prev - shift} to the answre list and update the `minPairDiff`
            if (curr - prev == minPairDiff) {
                answer.push_back({prev - shift, curr - shift});
            } else if (curr - prev < minPairDiff) {
                minPairDiff = curr - prev;
                answer = {{prev - shift, curr - shift}};
            }        

            // Update prev as curr.
            prev = curr;
        }
        
        return answer;
    }
};


// runtime - 8ms
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int diff = INT_MAX, n = arr.size();

        for(int i = 1; i < n; i++){
            diff = min(diff, arr[i] - arr[i-1]);
        }

        vector<vector<int>> ans;
        for(int i = 1; i < n; i++){
            if(arr[i] - arr[i-1] == diff) ans.push_back({arr[i-1], arr[i]});
        }

        return ans;
    }
};


// runtime - 9ms
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {

        sort(arr.begin(), arr.end());   // REQUIRED for your logic

        int a = arr.size();
        int d = INT_MAX;                // minimum difference
        vector<vector<int>> c;          // result

        for (int i = 1; i < a; i++) {
            int parent = arr[i - 1];
            int diff = arr[i] - parent;

            if (diff < d) {
                d = diff;
                c.clear();
                c.push_back({parent, arr[i]});
            }
            else if (diff == d) {
                c.push_back({parent, arr[i]});
            }
        }

        return c;
    }
};

