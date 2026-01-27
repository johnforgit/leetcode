class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];

        for(int i=0; i<n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] e:edges) {
            int x = e[0];
            int y = e[1];
            int w = e[2];
            g[x].add(new int[] {y, w});
            g[y].add(new int[] {x, 2*w}); // reversal
        }

        int[] d = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0]=0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        pq.offer(new int[] {0, 0}); // [Distance,Node]

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int x = current[1];

            if(x == n-1) return dist;
            if(visited[x])
                continue;
            visited[x] = true;

            for(int[] neighbor : g[x]) {
                int y = neighbor[0];
                int w = neighbor[1];

                if(dist+w < d[y]) {
                    d[y] = dist+w;
                    pq.offer(new int[] {d[y], y});
                }
            }
        }
        return -1;
    }
}


// runtime - 63ms
class Solution {
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public int minCost(int n, int[][] edges) {
        List<Edge>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, 2*w));
        }

        int[] dist = new int[n]; //najkraci put do svakog cvora od pocetnog
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});//dodajemo cvor sa distancom do njega
        
        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int distanceToCurrentNode = current[1];
            //if(currentNode == n - 1) return dist[currentNode];
            if(currentNode == n - 1) return distanceToCurrentNode;
            for(Edge edge : graph[currentNode]) {
                int nextNode = edge.to;
                int weight = edge.weight;
                if(dist[nextNode] > distanceToCurrentNode + weight) {
                    dist[nextNode] = distanceToCurrentNode + weight;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        //return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1] ;
        return -1;
    }
}

// runtime - 67ms
class Solution {
    public int minCost(final int n, final int[][] edges) {
        final List<int[]>[] adj = new List[n];

        for(final int[] edge : edges) {
            if(adj[edge[0]] == null)
                adj[edge[0]] = new ArrayList<>();

            if(adj[edge[1]] == null)
                adj[edge[1]] = new ArrayList<>();

            adj[edge[0]].add(new int[] { edge[1], edge[2] });
            adj[edge[1]].add(new int[] { edge[0], 2 * edge[2] });
        }


        final Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        final int[] costs = new int[n];

        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[0] = 0;
        queue.offer(new int[] { 0, 0 });

        while(!queue.isEmpty()) {
            final int[] data = queue.poll();
            final int node = data[0], total = data[1];

            if(total > costs[node])
                continue;

            if(node == n - 1)
                return total;

            final List<int[]> list = adj[node];

            if(list != null) {
                for(final int[] next : list) {
                    if(total + next[1] >= costs[next[0]])
                        continue;

                    costs[next[0]] = total + next[1];

                    queue.offer(new int[] { next[0], costs[next[0]] });
                }
            }
        }

        return -1;
    }
}


// runtime - 71ms
class Solution {
    public int minCost(int n, int[][] edges) {

        List<List<int[]>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] minCost = new int[n];
        Arrays.fill(minCost, (int)Math.pow(10,8));
        
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u    = edge[0];
            int v    = edge[1];
            int cost = edge[2];
            graph.get(u).add(new int[]{v, cost});
            graph.get(v).add(new int[]{u, 2 * cost}); // Add Switch Too
        }

        // Do Dijkstra's Algorithm
        minCost[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[1], n2[1]));
        pq.offer(new int[]{0, 0});
        while(!pq.isEmpty() && !visited[n-1]){
            int[] curr = pq.poll();
            if(visited[curr[0]]) continue;
            visited[curr[0]] = true;
            for(int[] nei : graph.get(curr[0])){
                if(visited[nei[0]]) continue;
                int newDis = nei[1] + minCost[curr[0]];
                if(minCost[nei[0]] > newDis){
                    minCost[nei[0]] = newDis;
                    pq.offer(new int[]{nei[0], newDis});
                }
            }
        }

        if(visited[n-1]) return minCost[n-1];
        return -1;
    }
}


// runtime - 75ms
class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList());
        }

        for(int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, 2*w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        minCost[0] = 0;

        pq.add(new int[]{0, 0});

        while(!pq.isEmpty()) {
            int[] edge = pq.poll();
            int from = edge[0];
            int cost = edge[1];
            
            if(from == n - 1) return cost;
            if(minCost[from] < cost) continue;

            for(int[] neighbor : adj.get(from)) {
                int to = neighbor[0];
                int toCost = neighbor[1];

                if(minCost[from] + toCost < minCost[to]) {
                    minCost[to] = minCost[from] + toCost;
                    pq.add(new int[]{to, minCost[to]});
                }
            }
        }
        return minCost[n-1] == Integer.MAX_VALUE ? -1 : minCost[n-1];
    }
}


// runtime - 78ms
class Solution {
    public int minCost(int n, int[][] edges) {

        List<List<int[]>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] minCost = new int[n];
        Arrays.fill(minCost, (int)Math.pow(10,8));
        
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u    = edge[0];
            int v    = edge[1];
            int cost = edge[2];
            graph.get(u).add(new int[]{v, cost});
            graph.get(v).add(new int[]{u, 2 * cost}); // Add Switch Too
        }

        // Do Dijkstra's Algorithm
        minCost[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[1], n2[1]));
        pq.offer(new int[]{0, 0});
        while(!pq.isEmpty() && !visited[n-1]){
            int[] curr = pq.poll();
            if(visited[curr[0]]) continue;
            visited[curr[0]] = true;
            for(int[] nei : graph.get(curr[0])){
                if(visited[nei[0]]) continue;
                int newDis = nei[1] + minCost[curr[0]];
                if(minCost[nei[0]] > newDis){
                    minCost[nei[0]] = newDis;
                    pq.offer(new int[]{nei[0], newDis});
                }
            }
        }

        if(visited[n-1]) return minCost[n-1];
        return -1;
    }
}
