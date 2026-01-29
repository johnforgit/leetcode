class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original,
                          vector<char>& changed, vector<int>& cost) {
        // Initialize result to store the total minimum cost
        long long totalCost = 0;

        // Initialize a 2D vector to store the minimum transformation cost
        // between any two characters
        vector<vector<long long>> minCost(26, vector<long long>(26, INT_MAX));

        // Fill the initial transformation costs from the given original,
        // changed, and cost arrays
        for (int i = 0; i < original.size(); ++i) {
            int startChar = original[i] - 'a';
            int endChar = changed[i] - 'a';
            minCost[startChar][endChar] =
                min(minCost[startChar][endChar], (long long)cost[i]);
        }

        // Use Floyd-Warshall algorithm to find the shortest path between any
        // two characters
        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    minCost[i][j] =
                        min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                }
            }
        }

        // Calculate the total minimum cost to transform the source string to
        // the target string
        for (int i = 0; i < source.size(); ++i) {
            if (source[i] == target[i]) {
                continue;
            }
            int sourceChar = source[i] - 'a';
            int targetChar = target[i] - 'a';

            // If the transformation is not possible, return -1
            if (minCost[sourceChar][targetChar] >= INT_MAX) {
                return -1;
            }
            totalCost += minCost[sourceChar][targetChar];
        }

        return totalCost;
    }
};


// runtime - 4ms
class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original, vector<char>& changed, vector<int>& cost) {
        const long long mx = 1e14;
        vector<vector<long long>> cc(26, vector<long long>(26, mx));
        int sz = original.size();
        for(int i=0;i<26;i++)cc[i][i]=0;
        for(int i=0;i<sz;i++){
            int a = original[i]-'a', b = changed[i]-'a', c = cost[i];
            cc[a][b] = min(cc[a][b], (long long)c);
        }

        for(int k=0;k<26;k++){
            for(int i=0;i<26;i++){
                for(int j=0;j<26;j++){
                    if(cc[i][k] == mx || cc[k][j]==mx)continue;
                    cc[i][j] = min(cc[i][j], cc[i][k]+cc[k][j]);
                }
            }
        }

        long long ans = 0;
        sz = source.size();
        for(int i=0;i<sz;i++){
            int a = source[i]-'a', b = target[i]-'a';
            if(cc[a][b]==mx)return -1;
            ans += cc[a][b];
        }
        return ans;
    }
};

auto init = atexit([](){ofstream("display_runtime.txt")<<"0";});


// runtime - 13ms
class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original, vector<char>& changed, vector<int>& cost) {
        int n = source.size();
        int m = cost.size();
        vector<vector<int>>graph(26,vector<int>(26,INT_MAX));
        for(int i=0;i<26;i++){
            graph[i][i] = 0;
        }
        long long ans = 0;
        for(int i=0;i<m;i++){
                int from = original[i] - 'a';
                int to   = changed[i] - 'a';
                graph[from][to] = min(graph[from][to],cost[i]);
            
            
        }

        for(int k=0;k<26;k++){
            for(int i=0;i<26;i++){
                if(graph[i][k]!=INT_MAX){
                    for(int j=0;j<26;j++){
                        if(graph[k][j]!=INT_MAX){
                            graph[i][j] = min(graph[i][j],graph[i][k]+graph[k][j]);
                        }
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            if(source[i]==target[i]) continue;
            else {
                int from = source[i]-'a';
                int to = target[i]-'a';
                if(graph[from][to]==INT_MAX) return -1;
                ans +=graph[from][to];
            }
        }
        return ans;



    }
};


// runtime - 21ms
class Solution {
public:
    void FloydWarshall(vector<vector<long long>>& adjmatrix,
                       vector<char>& original,
                       vector<char>& changed,
                       vector<int>& cost) {
        
        // Initialize direct transformation costs
        for (int i = 0; i < original.size(); i++) {
            int s = original[i] - 'a';
            int t = changed[i] - 'a';
            adjmatrix[s][t] = min(adjmatrix[s][t], (long long)cost[i]);
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (adjmatrix[i][k] < 1e18 && adjmatrix[k][j] < 1e18) {
                        adjmatrix[i][j] = min(adjmatrix[i][j],
                                                adjmatrix[i][k] + adjmatrix[k][j]);
                    }
                }
            }
        }
    }

    long long minimumCost(string source, string target,
                          vector<char>& original,
                          vector<char>& changed,
                          vector<int>& cost) {
        
        const long long INF = 1e18;
        vector<vector<long long>> adjmatrix(26, vector<long long>(26, INF));

        // Distance from a character to itself is 0
        for (int i = 0; i < 26; i++) {
            adjmatrix[i][i] = 0;
        }

        FloydWarshall(adjmatrix, original, changed, cost);

        long long ans = 0;
        for (int i = 0; i < source.size(); i++) {
            if (source[i] == target[i]) continue;
            long long d = adjmatrix[source[i] - 'a'][target[i] - 'a'];
            if (d == INF) return -1;
            ans += d;
        }
        return ans;
    }
};


// runtime - 30ms
class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original, vector<char>& changed, vector<int>& cost) {
        vector<vector<long long>> min_size(26,vector<long long>(26,INT_MAX));
        int n=original.size();

        for(int i=0;i<n;i++){
            int u=original[i]-'a';
            int v=changed[i]-'a';
            int w=cost[i];

            if(w<min_size[u][v]){
                min_size[u][v]=w;
            }
        }

        for(int via=0;via<26;via++){
            for(int i=0;i<26;i++){
                for(int j=0;j<26;j++){
                    if(min_size[i][via]!= INT_MAX && min_size[via][j]!= INT_MAX){
                        if(min_size[i][via]+min_size[via][j]<min_size[i][j]){
                            min_size[i][j]=min_size[i][via]+min_size[via][j];
                        }
                    }
                }
            }
        }

        int size=source.size();
        long long total_cost=0;

        for(int i=0;i<size;i++){
            if(source[i]!=target[i] ){
                long long cost=min_size[source[i]-'a'][target[i]-'a'];
                if(cost==INT_MAX){
                    return -1;
                }
                total_cost+=cost;
            }
        }

        return total_cost;

    }
};
