class Solution:
    def largestSquareArea(self, bottomLeft: List[List[int]], topRight: List[List[int]]) -> int:
        max_size=0
        for(bottom_left_i, top_right_i), (bottom_left_j, top_right_j) in combinations(zip(bottomLeft, topRight),2):
            w = min(top_right_i[0], top_right_j[0]) - max(bottom_left_i[0], bottom_left_j[0])
            h = min(top_right_i[1], top_right_j[1]) - max(bottom_left_i[1], bottom_left_j[1])

            max_size = max(max_size, min(w, h))

        return max_size**2

# runtime - 35ms
class Solution:
    def largestSquareArea(self, bottomLeft: List[List[int]], topRight: List[List[int]]) -> int:
        maximal_size = 0 

        for i in range(len(bottomLeft)): 
            x10, y10 = bottomLeft[i]
            x11, y11 = topRight[i]
            if y11-y10<=maximal_size or x11-x10<=maximal_size: 
                continue

            for j in range(i+1, len(bottomLeft)): 
                x20, y20 = bottomLeft[j]
                x21, y21 = topRight[j]
                if y21-y20<=maximal_size or x21-x20<=maximal_size: 
                    continue

                if x21 <= x10 or y21<=y10 or x20>=x11 or y20>=y11: 
                    continue 
                size_x_intersect = min(x11, x21) - max(x10, x20)
                if size_x_intersect < maximal_size: 
                    continue 
                size_y_intersect = min(y11, y21) - max(y10, y20)
                if size_y_intersect < maximal_size:
                    continue 
                maximal_size = min(size_x_intersect, size_y_intersect)
                


        return maximal_size**2

# runtime - 1423 ms
class Solution:
    def largestSquareArea(self, bottomLeft, topRight):
        n = len(bottomLeft)
        max_side = 0
        ax = [p[0] for p in bottomLeft]
        ay = [p[1] for p in bottomLeft]
        cx = [p[0] for p in topRight]
        dy = [p[1] for p in topRight]

        for i in range(n):
            xi1 = ax[i]
            yi1 = ay[i]
            xi2 = cx[i]
            yi2 = dy[i]
            for j in range(i + 1, n):
                x1 = xi1 if xi1 > ax[j] else ax[j]          # max(ax[i], ax[j])
                y1 = yi1 if yi1 > ay[j] else ay[j]          # max(ay[i], ay[j])
                x2 = xi2 if xi2 < cx[j] else cx[j]          # min(cx[i], cx[j])
                y2 = yi2 if yi2 < dy[j] else dy[j]          # min(dy[i], dy[j])

                dx = x2 - x1
                if dx <= 0:
                    continue
                dy_len = y2 - y1
                if dy_len <= 0:
                    continue

                side = dx if dx < dy_len else dy_len
                if side > max_side:
                    max_side = side

        return max_side * max_side

# runtime - 2559 ms
class Solution:

    def check(self,a,b,c,d):
        if(a[0]>=d[0] or b[0]<=c[0]):return 0
        if(a[1]>=d[1] or b[1]<=c[1]):return 0
        ax=max(a[0],c[0])
        ay=max(a[1],c[1])
        bx=min(b[0],d[0])
        by=min(b[1],d[1])
        # print(ax,ay,bx,by)
        l=min(by-ay,bx-ax)
        return l*l
    def largestSquareArea(self, bottomLeft: List[List[int]], topRight: List[List[int]]) -> int:
        n=len(bottomLeft)
        ans=0
        for i in range(n):
            for j in range(i+1,n):
                ans=max(ans,self.check(bottomLeft[i],topRight[i],bottomLeft[j],topRight[j]))
        return ans
