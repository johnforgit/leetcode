class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        low, high, total_area = float('inf'), float('-inf'), 0

        for x, y, l in squares:
            total_area += l*l
            low = min(low, y)
            high = max(high, y+l)
        
        target_area = total_area / 2.0

        for i in range(60):
            mid = (low+high) / 2.0

            curr_area = 0
            for _, y, l in squares:
                curr_y = max(0, min(l, mid-y))
                curr_area += l*curr_y
            
            if curr_area < target_area:
                low = mid
            else:
                high = mid

        return mid


# runtime - 244 ms
class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        total_area = 0
        diff = defaultdict(int)
        for _, y, l in squares:
            total_area += l * l
            diff[y] += l
            diff[y + l] -= l
        
        area = 0
        s = 0
        for y, y2 in pairwise(sorted(diff)):
            s += diff[y]
            area += s * (y2 - y)
            if area * 2 >= total_area:
                return y2 - (area * 2 - total_area) / (s * 2)

# runtime - 435 ms
class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        total_area = 0        
        for x, y, l in squares:
            total_area += l * l
            
        events = []
        for x, y, l in squares:
            events.append((y, 1, l))
            events.append((y + l, 0, l))
        events.sort()
        opened = 0
        curr_area = 0
        prev_y = None
        for y, action, l in events:
            if prev_y != None:
                curr_area += opened * (y - prev_y)
            surplus = curr_area - total_area / 2
            if surplus == 0:
                return y
            if surplus > 0:
                return y - surplus / opened
            if action == 1:
                opened += l
            else:
                opened -= l
            prev_y = y
