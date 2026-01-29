// floyd - warshall algorithm

class Solution {

    public long minimumCost(
        String source,
        String target,
        char[] original,
        char[] changed,
        int[] cost
    ) {
        // Initialize result to store the total minimum cost
        long totalCost = 0;

        // Initialize a 2D array to store the minimum conversion cost
        // between any two characters
        long[][] minCost = new long[26][26];
        for (long[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Fill the initial conversion costs from the given original,
        // changed, and cost arrays
        for (int i = 0; i < original.length; ++i) {
            int startChar = original[i] - 'a';
            int endChar = changed[i] - 'a';
            minCost[startChar][endChar] = Math.min(
                minCost[startChar][endChar],
                (long) cost[i]
            );
        }

        // Use Floyd-Warshall algorithm to find the shortest path between any
        // two characters
        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    minCost[i][j] = Math.min(
                        minCost[i][j],
                        minCost[i][k] + minCost[k][j]
                    );
                }
            }
        }

        // Calculate the total minimum cost to transform the source string to
        // the target string
        for (int i = 0; i < source.length(); ++i) {
            if (source.charAt(i) == target.charAt(i)) {
                continue;
            }
            int sourceChar = source.charAt(i) - 'a';
            int targetChar = target.charAt(i) - 'a';

            // If the transformation is not possible, return -1
            if (minCost[sourceChar][targetChar] >= Integer.MAX_VALUE) {
                return -1;
            }
            totalCost += minCost[sourceChar][targetChar];
        }

        return totalCost;
    }
}

// djisktra's algorithms

class Solution {

    public long minimumCost(
        String source,
        String target,
        char[] original,
        char[] changed,
        int[] cost
    ) {
        // Create a graph representation of character conversions
        List<int[]>[] adjacencyList = new List[26];
        for (int i = 0; i < 26; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Populate the adjacency list with character conversions
        int conversionCount = original.length;
        for (int i = 0; i < conversionCount; i++) {
            adjacencyList[original[i] - 'a'].add(
                    new int[] { changed[i] - 'a', cost[i] }
                );
        }

        // Calculate shortest paths for all possible character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minConversionCosts[i] = dijkstra(i, adjacencyList);
        }

        // Calculate the total cost of converting source to target
        long totalCost = 0;
        int stringLength = source.length();
        for (int i = 0; i < stringLength; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long charConversionCost =
                    minConversionCosts[source.charAt(i) - 'a'][target.charAt(
                            i
                        ) -
                        'a'];
                if (charConversionCost == -1) {
                    return -1; // Conversion not possible
                }
                totalCost += charConversionCost;
            }
        }
        return totalCost;
    }

    // Find minimum conversion costs from a starting character to all other characters
    private long[] dijkstra(int startChar, List<int[]>[] adjacencyList) {
        // Priority queue to store characters with their conversion cost, sorted by cost
        PriorityQueue<Pair<Long, Integer>> priorityQueue = new PriorityQueue<>(
            (e1, e2) -> Long.compare(e1.getKey(), e2.getKey())
        );

        // Initialize the starting character with cost 0
        priorityQueue.add(new Pair<>(0L, startChar));

        // Array to store the minimum conversion cost to each character
        long[] minCosts = new long[26];
        // Initialize all costs to -1 (unreachable)
        Arrays.fill(minCosts, -1L);

        while (!priorityQueue.isEmpty()) {
            Pair<Long, Integer> currentPair = priorityQueue.poll();
            long currentCost = currentPair.getKey();
            int currentChar = currentPair.getValue();

            if (
                minCosts[currentChar] != -1L &&
                minCosts[currentChar] < currentCost
            ) continue;

            // Explore all possible conversions from the current character
            for (int[] conversion : adjacencyList[currentChar]) {
                int targetChar = conversion[0];
                long conversionCost = conversion[1];
                long newTotalCost = currentCost + conversionCost;

                // If we found a cheaper conversion, update its cost
                if (
                    minCosts[targetChar] == -1L ||
                    newTotalCost < minCosts[targetChar]
                ) {
                    minCosts[targetChar] = newTotalCost;
                    // Add the updated conversion to the queue for further exploration
                    priorityQueue.add(new Pair<>(newTotalCost, targetChar));
                }
            }
        }
        // Return the array of minimum conversion costs from the starting character to all others
        return minCosts;
    }
}


// runtime - 12ms
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] dis = new int[26][26];
        for(int i = 0 ; i < 26 ;i++){
            Arrays.fill(dis[i] , Integer.MAX_VALUE);
            dis[i][i] = 0 ;
        }
        
        for(int i = 0 ; i < cost.length ; i++){
            dis[original[i] - 'a'][changed[i] - 'a'] = Math.min(dis[original[i] - 'a'][changed[i] - 'a'],cost[i]);
        }

        for(int k = 0 ; k < 26 ; k++){
            for(int i = 0 ; i < 26 ; i++){
                if(dis[i][k] < Integer.MAX_VALUE){
                    for(int j = 0 ; j < 26 ; j++){
                        if(dis[k][j] < Integer.MAX_VALUE){
                            dis[i][j] = Math.min(dis[i][j] , dis[i][k] + dis[k][j]);
                        }
                    }
                }
            }
        }

        long ans = 0L ;
        for(int i = 0 ; i <source.length() ;i++){
            int c1 = source.charAt(i) - 'a';
            int c2 = target.charAt(i) - 'a';
            if(dis[c1][c2] == Integer.MAX_VALUE){
                return -1L;
            }
            ans += (long)dis[c1][c2];
        }

        return ans ;
    }
}


// runtime - 13ms
import java.util.*;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        final long INF = (long) 1e18;

        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Fill edges (take min cost for duplicates)
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }

        // Floyd-Warshall
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < 26; j++) {
                    if (dist[k][j] == INF) continue;
                    long nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }

        long ans = 0;
        for (int idx = 0; idx < source.length(); idx++) {
            int s = source.charAt(idx) - 'a';
            int t = target.charAt(idx) - 'a';
            if (s == t) continue;

            long best = dist[s][t];
            if (best >= INF / 2) return -1; // unreachable
            ans += best;
        }

        return ans;
    }
}


// runtime - 14ms
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // From the original, changed and cost array, we can create a weighted directed graph 
        // Now, for every pair, we can calculate minimum cost using Floyd Warshall algorithm
        // because we want min distance of every node to every other node
        // Then checking source and target string, we can check the conversion cost and sum up to give ans
        // Since they all are lowercase letters, ascii = (char - 97) will help

        // Take 26x26 matrix as graph
        int n = 26;
        long[][] graph = new long[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++){
                if(i != j) {
                    graph[i][j] = Long.MAX_VALUE;
                }
            }
        }

        // Initailise matrix with given edges
        for(int i=0; i<original.length; i++){
            int u = (int) original[i] - 97;
            int v = (int) changed[i] - 97;
            int wt = cost[i];

            if(wt < graph[u][v]){
                graph[u][v] = wt;
            }
        }

        // Apply Floyd Warshall
        for (int via=0; via<n; via++){
            for(int i=0; i<n; i++){
                if(graph[i][via] == Long.MAX_VALUE) continue;
                for(int j=0; j<n; j++){
                    if(graph[via][j] == Long.MAX_VALUE) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][via] + graph[via][j]);
                }
            }
        }

        long ans = 0;
        for(int i=0; i<source.length(); i++){
            int src = (int) source.charAt(i) - 97;
            int dst = (int) target.charAt(i) - 97;
            if(graph[src][dst] == Long.MAX_VALUE) return -1;
            ans += graph[src][dst];
        }

        return ans;
    }
}
