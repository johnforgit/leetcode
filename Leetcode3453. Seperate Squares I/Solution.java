class Solution {

    public double separateSquares(int[][] squares) {
        double max_y = 0;
        double total_area = 0;
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            total_area += (double) l * l;
            max_y = Math.max(max_y, (double) (y + l));
        }

        double lo = 0;
        double hi = max_y;
        double eps = 1e-5;
        while (Math.abs(hi - lo) > eps) {
            double mid = (hi + lo) / 2;
            if (check(mid, squares, total_area)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return hi;
    }

    private Boolean check(double limit_y, int[][] squares, double total_area) {
        double area = 0;
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            if (y < limit_y) {
                area += (double) l * Math.min(limit_y - y, (double) l);
            }
        }
        return area >= total_area / 2;
    }
}

// runtime - 84 ms
class Solution {
    public double separateSquares(int[][] squares) {
        double maxY = 0; 
        double minY = Integer.MAX_VALUE;
        double totalArea = 0;

        for (int i = 0; i < squares.length; i++) {
            int[] sq = squares[i];
            double topY = sq[1] + sq[2];
            maxY = Math.max(maxY, topY);
            minY = Math.min(minY, sq[1]);
            totalArea += (double)sq[2] * (double)sq[2];
        }
        // System.out.println("totalARea: " + totalArea);

        double lo = minY;
        double hi = maxY;
        double precision = Math.pow(10,-5);
        // System.out.println("precision: " + precision);

        while ( lo < hi ) {
            // System.out.println("\nhi: " + hi + " lo: " + lo + " diff: " + (hi-lo) );
            if (hi - lo < precision) {
                break;
            }

            double mid = lo+(hi-lo)/2.0;
            // System.out.println("mid: " + mid);
            double topArea = getTop(squares, mid);
            double bottomArea = totalArea - topArea;

            // System.out.println("top area: " + topArea + " bottomArea: " + bottomArea);

            if (topArea <= bottomArea) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return lo;
    }

    public double getTop(int[][] squares, double line) {
        double area = 0.0;

        for (int[] square : squares) {
            double y = square[1];
            double width = square[2];

            if (y >= line) {
                area += width*width;
            } else {
                if (y + width >= line) {
                    double actualHeight = y+width-line;
                    area += actualHeight*width;
                }
            }
        }

        return area;
    }
}

/**

We have an array of squares which tells us x,y coordinate of the bottom left point, as well as its length

We need to find the minimum y-coordinate of a line that separates all the squares areas into top/bottom sections
ssuch that the total area occupied by squares are equals on both sides.

constraints / edge cases
- squares may overlap -> must count them both
- assume that is always a possible answer

all coordinates positive -> so can start from 0,0 to inf,inf

maximum y would just be max(squares[i][2]) + squares[i][3]

pick mid, 
find all areas above mid (this is the expensive operation)
find all areas below mid compare
if top > bottom, adjust line up, else adjust line down.


given a value y, how to find all areas above y??

FFFFFFTTTTTT
 */

// understandable solution but takes a lot of time
import java.util.*;

class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double targetArea = totalArea / 2.0;

        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2.0;
            double currentArea = 0;

            for (int[] s : squares) {
                double y = s[1];
                double l = s[2];
                
                double hBelow = Math.max(0, Math.min(l, mid - y));
                currentArea += hBelow * l;
            }

            if (currentArea < targetArea) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }
}

// runtime - 87 ms
class Solution {
    public double separateSquares(int[][] squares) {
        double EPS = 1e-5;
        double bottom = squares[0][1];
        double top = 0;

        for(int[] square : squares) {
            top = Math.max(top, square[1] + square[2]);
            bottom = Math.min(bottom, square[1]);
        }

        while(bottom + EPS <= top) {
            double mid = (bottom + top) / 2.0;
            if(check(squares, mid) > 0) bottom = mid;
            else top = mid;
        }

        return bottom;
    }

    public double check(int[][] squares, double line) {
        double above = 0;
        double below = 0;

        for(int[] square : squares) {
            int y = square[1];
            double l = (double) square[2];

            if (line <= y) {
                above += l * l;
            } else if (line >= y + l) {
                below += l * l;
            } else {
                above += l * (y + l - line);
                below += l * (line - y);
            }
        }

        return above - below;
    }
}

// runtime - 90 ms
class Solution {
    public double separateSquares(int[][] squares) {
        int n=squares.length;
        int m=squares[0].length;
        double low=Double.MAX_VALUE;
        double high=Double.MIN_VALUE;
        double totalArea=0;
        for(int[] square:squares)
        {
            double l=square[2];
            totalArea+=l*l;
            low=Math.min(low, square[1]);
            high=Math.max(high, square[1]+l);
        }
        double target=totalArea/2.0;
        while(high-low>1e-6)
        {
            double mid=(low+high)/2;
            double areaBelow=areaBelow(mid, squares);
            if(areaBelow<target)
            low=mid;
            else high=mid;            
        }
        return low;
    }
    private double areaBelow(double yline, int[][] squares)
    {
        double area=0.0;
        for(int[] sq:squares)
        {
            double y=sq[1];
            double len=sq[2];
            if(y>=yline)
            continue;
            else if(yline>=y+len)
            area+=len*len;
            else 
            area+=(yline -y)*(len);
        }
        return area;
    }
}

// runtime - 93 ms
class Solution {
    public double separateSquares(int[][] squares) {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        // Step 1: find search space
        for (int[] square : squares) {
            double y = square[1];
            double l = square[2];
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y + l);
        }

        double low = minY;
        double high = maxY;
        double eps = 1e-6; // precision

        // Step 2: binary search
        while (high - low > eps) {
            double mid = (low + high) / 2;
            double areaBelow = 0.0;
            double areaAbove = 0.0;

            for (int[] square : squares) {
                double y = square[1];
                double l = square[2];

                if (mid <= y) {
                    // square completely above the line
                    areaAbove += l * l;
                } else if (mid >= y + l) {
                    // square completely below the line
                    areaBelow += l * l;
                } else {
                    // square partially crosses the line
                    areaBelow += (mid - y) * l;
                    areaAbove += (y + l - mid) * l;
                }
            }

            if (areaAbove > areaBelow) {
                low = mid; // line too low, move up
            } else {
                high = mid; // line too high, move down
            }
        }

        return (low + high) / 2;
    }
}
