package UndirectedWeightedGraph;
import java.util.List;

public interface UndirectedWeightedGraph {
    boolean isConnected();
    List<Integer> reachable(int node);
    List<Edge> mst();
    List<Integer> shortestPath(int start, int end);
}