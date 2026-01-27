class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for x, y, w in edges:
            g[x].append((y, w))
            g[y].append((x, 2 * w))

        dist = [inf] * n
        visited = [False] * n
        dist[0] = 0
        heap = [(0, 0)]  # (Distance, Node)

        while heap:
            cur_dist, x = heapq.heappop(heap)

            if x == n - 1:
                return cur_dist

            # already processed
            if visited[x]:
                continue
            visited[x] = True

            # relaxing neighbors
            for y, w in g[x]:
                new_dist = cur_dist + w
                if new_dist < dist[y]:
                    dist[y] = new_dist
                    heapq.heappush(heap, (new_dist, y))

        return -1


# runtime - 395ms
from collections import defaultdict
import heapq
from typing import List

class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        graph = defaultdict(list)

        # build graph
        for u, v, w in edges:
            graph[u].append((v, w))        # normal direction
            graph[v].append((u, 2 * w))    # reversed direction

        dist = [float('inf')] * n
        dist[0] = 0

        pq = [(0, 0)]  # (cost, node)

        while pq:
            cost, u = heapq.heappop(pq)

            # stale entry
            if cost > dist[u]:
                continue

            # early exit
            if u == n - 1:
                return cost

            for v, w in graph[u]:
                new_cost = cost + w
                if new_cost < dist[v]:
                    dist[v] = new_cost
                    heapq.heappush(pq, (new_cost, v))

        return -1

# runtime - 431ms
class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        graph = [[] for _ in range(n)]
        for u, v, w in edges:
            graph[u].append((v, w))
            graph[v].append((u, 2 * w))

        dist = [float("inf")] * n
        visited = [False] * n
        dist[0] = 0
        heap = [(0, 0)]
        while heap:
            cur_dist, u = heapq.heappop(heap)
            if u == n - 1:
                return cur_dist

            if visited[u]:
                continue
            visited[u] = True

            for v, w in graph[u]:
                new_dist = cur_dist + w
                if new_dist < dist[v]:
                    dist[v] = new_dist
                    heapq.heappush(heap, (new_dist, v))

        return -1
        # m = the number of edges
        # n = the number of vertices
        # time: O(n + mlogm)
        # space: O(n + m)

