class Solution:
    def survivedRobotsHealths(
        self, positions: List[int], healths: List[int], directions: str
    ) -> List[int]:
        n = len(positions)
        indices = list(range(n))
        result = []
        stack = deque()

        # Sort indices based on their positions
        indices.sort(key=lambda x: positions[x])

        for current_index in indices:
            # Add right-moving robots to the stack
            if directions[current_index] == "R":
                stack.append(current_index)
            else:
                while stack and healths[current_index] > 0:
                    # Pop the top robot from the stack for collision check
                    top_index = stack.pop()

                    if healths[top_index] > healths[current_index]:
                        # Top robot survives, current robot is destroyed
                        healths[top_index] -= 1
                        healths[current_index] = 0
                        stack.append(top_index)
                    elif healths[top_index] < healths[current_index]:
                        # Current robot survives, top robot is destroyed
                        healths[current_index] -= 1
                        healths[top_index] = 0
                    else:
                        # Both robots are destroyed
                        healths[current_index] = 0
                        healths[top_index] = 0

        # Collect surviving robots
        for index in range(n):
            if healths[index] > 0:
                result.append(healths[index])

        return result



# runtime - 59ms
class Solution:
    def survivedRobotsHealths(self, positions: List[int], healths: List[int], directions: str) -> List[int]:
        right = deque([])
        for index in sorted(range(len(positions)), key=positions.__getitem__):
            if directions[index] == 'R':
                right.append(index)
                continue
            while right:
                ir = right[-1]
                if healths[ir] == healths[index]:
                    right.pop()
                    healths[index] = healths[ir] = 0
                    break
                if healths[ir] > healths[index]:
                    healths[ir] -= 1
                    healths[index] = 0
                    break
                healths[ir] = 0
                right.pop()
                healths[index] -= 1
        return [*filter(None, healths)]


# runtime - 61ms
class Solution:
    def survivedRobotsHealths(self, positions: List[int], healths: List[int], directions: str) -> List[int]:
        if len(set(directions)) == 1:
            return healths
        n = len(positions)
        order = sorted(range(n), key=lambda i: positions[i])
        h = healths[:]
        alive = [True]*n
        stack = []
        for idx in order:
            if directions[idx] == 'R':
                stack.append(idx)
            else:
                while stack:
                    top = stack[-1]
                    if h[top] < h[idx]:
                        alive[top] = False
                        stack.pop()
                        h[idx] -= 1
                    elif h[top] > h[idx]:
                        alive[idx] = False
                        h[top] -= 1
                        break
                    else:
                        alive[top] = False
                        alive[idx] = False
                        stack.pop()
                        break
        return [h[i] for i in range(n) if alive[i]]



# runtime - 63ms
class Solution:
    def survivedRobotsHealths(
        self, positions: List[int], h: List[int], directions: str
    ) -> List[int]:
        n = len(positions)
        ind = sorted(range(n), key=positions.__getitem__)
        stack = []
        for i in ind:
            if directions[i] == "R":
                stack.append(i)
                continue
            while stack and h[i] > 0:
                if h[stack[-1]] < h[i]:
                    h[stack.pop()] = 0
                    h[i] -= 1
                elif h[stack[-1]] > h[i]:
                    h[stack[-1]] -= 1
                    h[i] = 0
                else:
                    h[stack.pop()] = 0
                    h[i] = 0
        return [v for v in h if v > 0]



# runtime - 66ms
class Solution:
    def survivedRobotsHealths(self, positions: List[int], h: List[int], directions: str) -> List[int]:
        n = len(positions)
        ind = sorted(range(n), key=positions.__getitem__)
        stack = []
        for i in ind:
            if directions[i] == 'R':
                stack.append(i)
                continue
            while stack and h[i] > 0:
                if h[stack[-1]] < h[i]:
                    h[stack.pop()] = 0
                    h[i] -= 1
                elif h[stack[-1]] > h[i]:
                    h[stack[-1]] -= 1
                    h[i] = 0
                else:
                    h[stack.pop()] = 0
                    h[i] = 0
        return [v for v in h if v > 0]


# runtime - 84ms
class Solution:
    def survivedRobotsHealths(self, positions: List[int], healths: List[int], directions: str) -> List[int]:
        n = len(positions)
        ids = sorted(list(range(n)), key=lambda x: positions[x])

        stack = []
        for i in ids:
            if directions[i] == "R":
                stack.append(i)
            else:
                while stack and healths[i] > 0:
                    top = stack.pop()
                    if healths[top] > healths[i]:
                        healths[top] -= 1
                        healths[i] = 0
                        stack.append(top)
                    elif healths[top] < healths[i]:
                        healths[i] -= 1
                        healths[top] = 0
                    else:
                        healths[top] = 0
                        healths[i] = 0
        return [healths[i] for i in range(n) if healths[i] > 0]
