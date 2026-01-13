#include <vector>
#include <algorithm>
#include <iostream>
#include <iomanip>

using namespace std;

class Solution {
public:
    double separateSquares(vector<vector<int>>& squares) {
        double totalArea = 0;
        double low = 2e9;
        double high = 0;

        for (auto& s : squares) {
            double y = s[1];
            double l = s[2];
            totalArea += l * l;
            low = min(low, y);
            high = max(high, y + l);
        }

        double targetArea = totalArea / 2.0;

        for (int i = 0; i < 100; ++i) {
            double mid = low + (high - low) / 2.0;
            double currentArea = 0;

            for (auto& s : squares) {
                double y = s[1];
                double l = s[2];
                double h_below = max(0.0, min(l, mid - y));
                currentArea += h_below * l;
            }

            if (currentArea < targetArea) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }
};

// runtime - 31 ms
class Solution {
public:
    double check_diff(double t_y, vector<vector<int>>& squares)
    {
        double lower=0, higher=0, zero=0;
        for (auto& s:squares)
        {
            double y=s[1], l=s[2];
            higher += max(zero, (y+l - max(y, t_y))*l);
            lower += max(zero, (min(y+l, t_y) - y)*l);
        }
        return higher-lower;
    }
    double separateSquares(vector<vector<int>>& squares) {
        double low=0, high=1e9;
        double e=0.00001;
        while (low+e < high)
        {
            double mid=(high+low)/2;
            double diff=check_diff(mid, squares);
            if (diff > 0) low=mid;
            else high=mid;
        }
        return high;
    }
};

// runtime - 39 ms
class Solution {
public:
    double separateSquares(vector<vector<int>>& squares) {
        int n = squares.size();
        if(!n)
            return 0;
        int y_min = INT_MAX, y_max = INT_MIN;
        for(const auto&cur:squares){
            int y0 = cur[1], len = cur[2], y1 = y0+len;
            y_min = min(y0, y_min);
            y_max = max(y1, y_max);
        }
        double l = y_min*1.0, r = y_max*1.0, delta = 1e-5;
        while(r-l>delta){
            auto mid = l+(r-l)/2;
            if(validate(squares, mid))
                r = mid;
            else
                l = mid;
        }
        return l;
    }

    bool validate(const vector<vector<int>>& squares, double y){
        double up = 0, down = 0;
        for(const auto&cur:squares){
            double y0 = cur[1]*1.0, len = cur[2]*1.0, y1 = y0+len;
            down += max(0.0, (min(y1, y)-y0)*len);
            up += max(0.0, (y1-max(y, y0))*len);
        }
        return up <= down;
    }
};
