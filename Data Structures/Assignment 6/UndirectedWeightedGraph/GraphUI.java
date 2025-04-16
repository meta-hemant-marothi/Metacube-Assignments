package UndirectedWeightedGraph;
import java.util.*;

public class GraphUI {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numNodes = 0;
            
            // Input validation for the number of nodes
            while (true) {
                try {
                    System.out.print("Enter the number of nodes in the graph: ");
                    numNodes = Integer.parseInt(scanner.nextLine());
                    if (numNodes <= 0) {
                        throw new IllegalArgumentException("Number of nodes must be greater than zero.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            Graph graph = new Graph(numNodes);
            boolean exit = false;
            
            while (!exit) {
                System.out.println("\n--- Graph Operations ---");
                System.out.println("1. Add Edge");
                System.out.println("2. Check Connectivity");
                System.out.println("3. Find Reachable Nodes");
                System.out.println("4. Compute Minimum Spanning Tree (MST)");
                System.out.println("5. Find Shortest Path");
                System.out.println("6. Exit");
                
                int choice = -1;
                while (true) {
                    try {
                        System.out.print("Choose an option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        if (choice < 1 || choice > 6) {
                            throw new IllegalArgumentException("Please choose a valid option (1-6).");
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                
                try {
                    switch (choice) {
                        case 1:
                            int src, dest, weight;
                            try {
                                System.out.print("Enter source node: ");
                                src = Integer.parseInt(scanner.nextLine());
                                validateNode(src, numNodes);
                                
                                System.out.print("Enter destination node: ");
                                dest = Integer.parseInt(scanner.nextLine());
                                validateNode(dest, numNodes);
                                
                                System.out.print("Enter weight of the edge: ");
                                weight = Integer.parseInt(scanner.nextLine());
                                if (weight <= 0) {
                                    throw new IllegalArgumentException("Weight must be a positive integer.");
                                }
                                
                                graph.addEdge(src, dest, weight);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter integers for nodes and weight.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                            
                        case 2:
                            System.out.println("The graph is " + (graph.isConnected() ? "connected." : "not connected."));
                            break;
                            
                        case 3:
                            System.out.print("Enter the node to find reachable nodes from: ");
                            try {
                                int node = Integer.parseInt(scanner.nextLine());
                                validateNode(node, numNodes);
                                List<Integer> reachableNodes = graph.reachable(node);
                                System.out.println("Reachable nodes: " + reachableNodes);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid node number.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                            
                        case 4:
                            List<Edge> mstEdges = graph.mst();
                            System.out.println("Edges in the Minimum Spanning Tree:");
                            for (Edge edge : mstEdges) {
                                System.out.println(edge.src + " -> " + edge.dest + " (Weight: " + edge.weight + ")");
                            }
                            break;
                            
                        case 5:
                            int start, end;
                            try {
                                System.out.print("Enter the start node: ");
                                start = Integer.parseInt(scanner.nextLine());
                                validateNode(start, numNodes);
                                
                                System.out.print("Enter the end node: ");
                                end = Integer.parseInt(scanner.nextLine());
                                validateNode(end, numNodes);
                                
                                List<Integer> path = graph.shortestPath(start, end);
                                if (path.isEmpty()) {
                                    System.out.println("No path exists between " + start + " and " + end);
                                } else {
                                    System.out.println("Shortest path: " + path);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter valid integers for nodes.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                            
                        case 6:
                            exit = true;
                            System.out.println("Exiting...");
                            break;
                            
                        default:
                            System.out.println("Unexpected error occurred.");
                    }
                } catch (Error e) {
                    System.out.println("Exception: " + e);
                }
            }
        }
    }

    // Helper method to validate node indices
    private static void validateNode(int node, int numNodes) throws IllegalArgumentException {
        if (node < 0 || node >= numNodes) {
            throw new IllegalArgumentException("Node index must be between 0 and " + (numNodes - 1) + ".");
        }
    }
}