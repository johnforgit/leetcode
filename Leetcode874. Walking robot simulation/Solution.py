class Solution:
    def __init__(self):
        self.HASH_MULTIPLIER = (
            60013  # Slightly larger than 2 * max coordinate value
        )

    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        # Store obstacles in an set for efficient lookup
        obstacle_set = {self._hash_coordinates(x, y) for x, y in obstacles}

        # Define direction vectors: North, East, South, West
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        x, y = 0, 0
        max_distance_squared = 0
        current_direction = 0  # 0: North, 1: East, 2: South, 3: West

        for command in commands:
            if command == -1:  # Turn right
                current_direction = (current_direction + 1) % 4
                continue

            if command == -2:  # Turn left
                current_direction = (current_direction + 3) % 4
                continue

            # Move forward
            dx, dy = directions[current_direction]
            for _ in range(command):
                next_x, next_y = x + dx, y + dy
                if self._hash_coordinates(next_x, next_y) in obstacle_set:
                    break
                x, y = next_x, next_y

            max_distance_squared = max(max_distance_squared, x * x + y * y)

        return max_distance_squared

    # Hash function to convert (x, y) coordinates to a unique integer value
    def _hash_coordinates(self, x: int, y: int) -> int:
        return x + self.HASH_MULTIPLIER * y



# runtime - 17ms
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        best_distance = 0
        stage = 0
        my_set = set()
        for obstacle in obstacles:
            my_set.add(tuple(obstacle))
        current_x = 0
        current_y = 0
        for command in commands:
            if command == -1:
                stage = (stage + 1) % 4
            elif command == -2:
                stage = (stage - 1) % 4
            else:
                if stage == 0:
                    for x in range(command):
                        if (current_x,current_y + 1) in my_set:
                            break
                        current_y += 1
                elif stage == 1:
                    for x in range(command):
                        if (current_x + 1,current_y) in my_set:
                            break
                        current_x += 1
                elif stage == 2:
                    for x in range(command):
                        if (current_x,current_y - 1) in my_set:
                            break
                        current_y -= 1
                else:
                    for x in range(command):
                        if (current_x - 1,current_y) in my_set:
                            break
                        current_x -= 1
                best_distance = max(best_distance,current_x*current_x + current_y*current_y)
        return best_distance



# runtime - 18ms
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        obstacle_set = set(map(tuple, obstacles))
        directions = [(0,1),(1,0),(0,-1),(-1,0)]
        d = 0
        x = y = 0
        ans = 0


        for cmd in commands:
            if cmd == -2:
                d = (d + 3) % 4
            elif cmd == -1:
                d = (d + 1) % 4
            else:
                dx, dy = directions[d]
                for _ in range(cmd): #k
                    nx , ny = x + dx, y + dy
                    if (nx,ny) in obstacle_set:
                        break
                    x,y = nx,ny
                    ans = max(ans,x*x+y*y)

        return ans




# runtime - 19ms
'''
O(n) time (n = len commands)
O(m) space (m = len obstacles)

simulate
since k <= 9, we don't need to do any math, we can just iterate one at a time
keep track of the max squared euclidean dist
'''
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        obstacles = set((x, y) for x, y in obstacles)
        directions = {
            0: (0, 1), # north
            1: (1, 0), # east
            2: (0, -1), # south
            3: (-1, 0) # west
        }
        max_dist = 0
        x, y, d = 0, 0, 0
        for cmd in commands:
            if cmd == -2: # turn left
                d = (d - 1) % len(directions)
            elif cmd == -1: # turn right
                d = (d + 1) % len(directions)
            else: # go straight
                for _ in range(cmd):
                    dx, dy = directions[d]
                    if (x + dx, y + dy) in obstacles:
                        break
                    x += dx
                    y += dy
                max_dist = max(max_dist, x ** 2 + y ** 2)
        return max_dist




# runtime - 23ms
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        x, y = 0, 0
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        dir_idx = 0
        max_dist = 0
        obstacle_set = {tuple(obs) for obs in obstacles}
        
        for cmd in commands:
            if cmd == -2:
                dir_idx = (dir_idx - 1) % 4
            elif cmd == -1:
                dir_idx = (dir_idx + 1) % 4
            else:
                dx, dy = directions[dir_idx]
                for _ in range(cmd):
                    nx, ny = x + dx, y + dy
                    if (nx, ny) in obstacle_set:
                        break
                    x, y = nx, ny
                    max_dist = max(max_dist, x*x + y*y)
                    
        return max_dist
