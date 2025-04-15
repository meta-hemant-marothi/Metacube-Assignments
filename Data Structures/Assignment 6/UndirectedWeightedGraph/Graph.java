package UndirectedWeightedGraph;
import java.util.*;

public class Graph implements UndirectedWeightedGraph {
    List<Edge> edges;
    int numNodes;
    int[] parent;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        this.edges = new ArrayList<>();
        this.parent = new int[numNodes];
        for (int i = 0; i < numNodes; i++) parent[i] = i;
    }

    public void addEdge(int src, int dest, int weight) {
        for (Edge edge : edges) {
            if ((edge.src == src && edge.dest == dest) || (edge.src == dest && edge.dest == src)) {
                System.out.println("Error: Duplicate or ambiguous edge detected between " + src + " and " + dest);
                return;
            }
        }
        edges.add(new Edge(src, dest, weight));
        System.out.println("Edge added: " + src + " -> " + dest + " (Weight: " + weight + ")");
    }

    // Helper function to find the root of a node
    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    // Helper function to union two sets
    private void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        parent[root1] = root2;
    }

    @Override
    public boolean isConnected() {
        boolean[] visited = new boolean[numNodes];
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Start DFS from node 0
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                for (Edge edge : edges) {
                    if (edge.src == node && !visited[edge.dest]) {
                        stack.push(edge.dest);
                    } else if (edge.dest == node && !visited[edge.src]) {
                        stack.push(edge.src);
                    }
                }
            }
        }
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    @Override
    public List<Integer> reachable(int node) {
        List<Integer> reachableNodes = new ArrayList<>();
        boolean[] visited = new boolean[numNodes];
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                reachableNodes.add(current);
                for (Edge edge : edges) {
                    if (edge.src == current && !visited[edge.dest]) {
                        stack.push(edge.dest);
                    } else if (edge.dest == current && !visited[edge.src]) {
                        stack.push(edge.src);
                    }
                }
            }
        }
        return reachableNodes;
    }

    @Override
    public List<Edge> mst() {
        edges.sort(Comparator.comparingInt(e -> e.weight)); // Sort edges by weight
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            if (find(edge.src) != find(edge.dest)) {
                mstEdges.add(edge);
                union(edge.src, edge.dest);
            }
        }
        return mstEdges;
    }

    @Override
    public List<Integer> shortestPath(int start, int end) {
        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int d = current[1];

            if (node == end) break;

            for (Edge edge : edges) {
                if (edge.src == node && d + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = d + edge.weight;
                    pq.offer(new int[]{edge.dest, dist[edge.dest]});
                } else if (edge.dest == node && d + edge.weight < dist[edge.src]) {
                    dist[edge.src] = d + edge.weight;
                    pq.offer(new int[]{edge.src, dist[edge.src]});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (dist[end] == Integer.MAX_VALUE) return path;

        int current = end;
        while (current != start) {
            path.add(current);
            for (Edge edge : edges) {
                if ((edge.dest == current && dist[edge.src] + edge.weight == dist[current]) ||
                    (edge.src == current && dist[edge.dest] + edge.weight == dist[current])) {
                    current = edge.src == current ? edge.dest : edge.src;
                    break;
                }
            }
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}