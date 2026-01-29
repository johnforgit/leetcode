// floyd - warshall algorithm
class Solution:
    def minimumCost(
        self,
        source: str,
        target: str,
        original: List[str],
        changed: List[str],
        cost: List[int],
    ) -> int:
        # Initialize result to store the total minimum cost
        total_cost = 0

        # Initialize a 2D list to store the minimum transformation cost
        # between any two characters
        min_cost = [[float("inf")] * 26 for _ in range(26)]

        # Fill the initial transformation costs from the given original,
        # changed, and cost arrays
        for orig, chg, cst in zip(original, changed, cost):
            start_char = ord(orig) - ord("a")
            end_char = ord(chg) - ord("a")
            min_cost[start_char][end_char] = min(
                min_cost[start_char][end_char], cst
            )

        # Use Floyd-Warshall algorithm to find the shortest path between any
        # two characters
        for k in range(26):
            for i in range(26):
                for j in range(26):
                    min_cost[i][j] = min(
                        min_cost[i][j], min_cost[i][k] + min_cost[k][j]
                    )

        # Calculate the total minimum cost to transform the source string to
        # the target string
        for src, tgt in zip(source, target):
            if src == tgt:
                continue
            source_char = ord(src) - ord("a")
            target_char = ord(tgt) - ord("a")

            # If the transformation is not possible, return -1
            if min_cost[source_char][target_char] == float("inf"):
                return -1
            total_cost += min_cost[source_char][target_char]

        return total_cost

// djisktra's algorithm

class Solution:
    def minimumCost(
        self,
        source: str,
        target: str,
        original: List[str],
        changed: List[str],
        cost: List[int],
    ) -> int:
        # Create a graph representation of character conversions
        adjacency_list = [[] for _ in range(26)]

        # Populate the adjacency list with character conversions
        conversion_count = len(original)
        for i in range(conversion_count):
            adjacency_list[ord(original[i]) - ord("a")].append(
                (ord(changed[i]) - ord("a"), cost[i])
            )

        # Calculate shortest paths for all possible character conversions
        min_conversion_costs = [
            self._dijkstra(i, adjacency_list) for i in range(26)
        ]

        # Calculate the total cost of converting source to target
        total_cost = 0
        for s, t in zip(source, target):
            if s != t:
                char_conversion_cost = min_conversion_costs[ord(s) - ord("a")][
                    ord(t) - ord("a")
                ]
                if char_conversion_cost == float("inf"):
                    return -1  # Conversion not possible
                total_cost += char_conversion_cost

        return total_cost

    def _dijkstra(
        self, start_char: int, adjacency_list: List[List[tuple]]
    ) -> List[int]:
        # Priority queue to store characters with their conversion cost, sorted by cost
        priority_queue = [(0, start_char)]

        # List to store the minimum conversion cost to each character
        min_costs = [float("inf")] * 26

        while priority_queue:
            current_cost, current_char = heapq.heappop(priority_queue)

            if min_costs[current_char] != float("inf"):
                continue

            min_costs[current_char] = current_cost

            # Explore all possible conversions from the current character
            for target_char, conversion_cost in adjacency_list[current_char]:
                new_total_cost = current_cost + conversion_cost

                # If we found a cheaper conversion, update its cost
                if min_costs[target_char] == float("inf"):
                    heapq.heappush(
                        priority_queue, (new_total_cost, target_char)
                    )

        # Return the list of minimum conversion costs from the starting character to all others
        return min_costs

# runtime - 263ms
class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        INF = 1 << 25
        orda = ord('a')

        # Build adjacency list for 26 letters
        graph = [[] for _ in range(26)]
        for a, b, c in zip(original, changed, cost):
            ai = ord(a) - orda
            bi = ord(b) - orda
            graph[ai].append((bi, c))

        # Run Dijkstra from each letter
        def dijkstra(start):
            dist = [INF] * 26
            dist[start] = 0
            pq = [(0, start)]

            while pq:
                d, u = heappop(pq)
                if d > dist[u]:
                    continue
                for v, w in graph[u]:
                    nd = d + w
                    if nd < dist[v]:
                        dist[v] = nd
                        heappush(pq, (nd, v))
            return dist

        # Compute all-pairs shortest paths
        all_dist = [dijkstra(i) for i in range(26)]

        # Compute total transformation cost
        total = 0
        for s, t in zip(source, target):
            si = ord(s) - orda
            ti = ord(t) - orda
            d = all_dist[si][ti]
            if d >= INF:
                return -1
            total += d

        return total


# runtime - 313ms
class Solution:
    def minimumCost(self, source, target, original, changed, cost):
        INF = 10**15

        # dist[u][v] = min cost to convert char u -> v
        dist = [[INF] * 26 for _ in range(26)]
        for i in range(26):
            dist[i][i] = 0

        for o, ch, c in zip(original, changed, cost):
            u = ord(o) - ord('a')
            v = ord(ch) - ord('a')
            dist[u][v] = min(dist[u][v], c)   # in case of multiple edges

        # Floyd–Warshall: all-pairs shortest paths
        for k in range(26):
            for i in range(26):
                if dist[i][k] == INF:
                    continue
                for j in range(26):
                    if dist[k][j] == INF:
                        continue
                    nd = dist[i][k] + dist[k][j]
                    if nd < dist[i][j]:
                        dist[i][j] = nd

        # Now sum over characters
        total = 0
        for s, t in zip(source, target):
            if s == t:
                continue
            u = ord(s) - ord('a')
            v = ord(t) - ord('a')
            if dist[u][v] == INF:
                return -1
            total += dist[u][v]

        return total

# runtime - 362ms
class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:

        # Build graph
        graph = defaultdict(list)
        for o, c, w in zip(original, changed, cost):
            u = ord(o) - ord('a')
            v = ord(c) - ord('a')
            graph[u].append((v, w))

        # minCost[i][j] = min cost to convert char i → char j
        minCost = [[inf] * 26 for _ in range(26)]

        # Run Dijkstra from each character
        for start in range(26):
            minCost[start][start] = 0
            h = [(0, start)]

            while h:
                curCost, u = heapq.heappop(h)
                if curCost > minCost[start][u]:
                    continue

                for v, w in graph[u]:
                    if minCost[start][v] > curCost + w:
                        minCost[start][v] = curCost + w
                        heapq.heappush(h, (curCost + w, v))

        # Compute answer
        ans = 0
        for s, t in zip(source, target):
            if s == t:
                continue
            u = ord(s) - ord('a')
            v = ord(t) - ord('a')
            if minCost[u][v] == inf:
                return -1
            ans += minCost[u][v]

        return ans

# runtime - 411ms
class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:

        letterNum = {}
        count = 0
        for l in range(len(original)):
            if original[l] not in letterNum:
                letterNum[original[l]] = count
                count += 1
            if changed[l] not in letterNum:
                letterNum[changed[l]] = count
                count += 1


        n = len(letterNum)
        dist = [[float('inf')] * n for _ in range(n)]

        for i in range(n):
            dist[i][i] = 0

        for o in range(len(original)):
            i = letterNum[original[o]]
            j = letterNum[changed[o]]
            w = cost[o]
            dist[i][j] = min(dist[i][j], w)

        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if dist[i][k] + dist[k][j] < dist[i][j]:
                        dist[i][j] = dist[i][k] + dist[k][j]
        
        totalCost = 0
        for s in range(len(source)):
            if source[s] not in letterNum or target[s] not in letterNum:
                return -1
            i = letterNum[source[s]]
            j = letterNum[target[s]]
            
            if dist[i][j] != float('inf'):
                totalCost += dist[i][j]
            else:
                return -1

        return totalCost

