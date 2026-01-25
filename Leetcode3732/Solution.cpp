#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        long long ans = 0;
        sort(nums.begin(), nums.end());
        long long scale = 100000; // 10^5

        // iterating forwards
        for (int i = 0; i < nums.size() - 1; i++) {
            long long p = 1LL * nums[i] * nums.back();
            ans = max(ans, p * scale);
            ans = max(ans, p * (-scale));
        }

        // iterating backwards
        for (int i = 1; i < nums.size(); i++) {
            long long p = 1LL * nums[i] * nums[0];
            ans = max(ans, p * scale);
            ans = max(ans, p * (-scale));
        }

        return ans;
    }
};



// runtime - 0ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        long long max1=INT_MIN, max2=INT_MIN;
        for(int i=0; i<nums.size(); i++){
            if((abs(nums[i]))>max1){
                max2=max1;
                max1=abs(nums[i]);
            }
            else if((abs(nums[i]))>max2){max2=abs(nums[i]);} 
        }
        return max2*max1*100000;
        
    }
};

// runtime - 2ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        for (int i = 0; i < nums.size(); i++) nums[i] = (long long)abs(nums[i]);
        int m1 = -1e5;
        int m2 = -1e5;

        for (int num : nums) {
            if (num > m1) {
                m2 = m1;
                m1 = num;
            }
            else if (num > m2) {
                m2 = num;
            }
        }

        return (long long)m1 * (long long)m2 * 1e5;
    }
};


// runtime - 3ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        int mx1 = 0, mx2 = 0;
        for (int num : nums) {
            num = std::abs(num);
            if (num >= mx1) {
                mx2 = mx1;
                mx1 = num;
            }
            else if (num > mx2) {
                mx2 = num;
            }
        }

        return 1e5 * mx1 * mx2;
    }
};

// runtime - 5ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        nth_element(nums.begin(), nums.begin() + 2, nums.end(), [](auto &a, auto &b) {
            return abs(a) > abs(b);
        });
        
        long long r = (long long) nums[0] * (long long) nums[1];
        r *= (r < 0) ? -100000 : 100000;
        return r;
    }
};


// runtime - 6ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        //three largest modulus number to be found 
        //then replace the smallest by largest number
        int n=nums.size();

        long long ans=1;
        int x = 100000;
        int largest=INT_MIN;
        int second=INT_MIN;
        for(int i=0;i<n;i++){
            
            if(largest<abs(nums[i])){
                second=largest;
                largest=abs(nums[i]);
        
            }
            else if(abs(nums[i])>second) second =abs(nums[i]);

        }
        if(1LL*largest*second>0) return 1LL*largest*second*x;
        else return 1LL*largest*second*(-x);

        
    }
};

// runtime - 9ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        vector<long long> temp = {abs(nums[0]), abs(nums[1]), abs(nums[2])};
        sort(temp.begin(), temp.end());
        long long ans;
        for (int i = 3 ; i < nums.size() ; i++) {
            if (temp[0] < abs(nums[i])) {
                temp[0] = abs(nums[i]);
                sort(temp.begin(), temp.end());
            }
        }
        ans = pow(10, 5)*temp[1]*temp[2];
        return ans;
    }
};

// runtime - 11ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        long long max_possible_product = INT_MIN;

        vector<int> all_positive;
        
        for (int i = 0; i < nums.size(); ++i) {
            all_positive.push_back(abs(nums.at(i)));
        }

        // find two largest numbers in nums
        int first_index = 0;
        long long first_max = INT_MIN;
        
        for (int i = 0; i < all_positive.size(); ++i) {
            if (all_positive.at(i) > first_max) {
                first_index = i;
                first_max = all_positive.at(i);
            }
        }

        long long second_max = 0;
        
        for (int i = 0; i < all_positive.size(); ++i) {
            if (all_positive.at(i) > second_max && i != first_index) {
                second_max = all_positive.at(i);
            }
        }

        return first_max * second_max * pow(10, 5);
    }
};

// runtime - 13ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        priority_queue<long long>pq;
        for(int i=0;i<nums.size();i++){
            pq.push(abs(nums[i]));
        }
        long long a=pq.top();
        pq.pop();
        long long b=pq.top();
        pq.pop();
        if(a*b<=0)return a*b*(-100000);
        return a*b*100000;
    }
};

// runtime - 16ms
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        priority_queue<int> pq;
        for(int num: nums) {
            pq.push(-1*abs(num));
            if (pq.size()>=3)
                pq.pop();
        }
        long long res = pq.top();
        pq.pop();
        res *= pq.top();
        res *= 1e5;
        return abs(res);
    }
};
