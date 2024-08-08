package graphs;
import java.util.*;

/**
 * Graph Design: A way for you to develop a graph on your terms to satisfy certain functionality.
 */
public class Graph {

    /**
     * Feel free to develop this class however you like, with either an adjacency list or using nodes. For reference, the staff solution
     * was developed using a combination of an adjacency list and the edge class to incorporate route names. The preferred solution
     * (and potentially the most intuitive) is using the adjacency list HashMap to store the vertices/stops as values and a list of
     * that vertex's edges (i.e. the 'A-C' & 'A-B' edges for vertex 'A') as the value. The vertex can be stored as a Node or just a String!
     *
     */

    /**
     * a map to represent an adjacency list for our graph.
     */
    private Map<String, ArrayList> adjacencyList;

    /**
     * a Node class example you might like to use.
     */
    private class Node {
        String name;
        private HashMap<Node, Integer> neighbors;

        public Node(String name){
            this.name = name;
        }

        public Node(String name, HashMap<Node, Integer> neighbors) {
            this.name = name;
            this.neighbors = neighbors;
        }
    }

    /**
     * an Edge class example you might like to use. Hint: this was particularly helpful in the staff solution.
     */
    private class Edge {
        private String from;
        private String to;
        private String routeName;
        private double weight;

        Edge(String from, Double weight){
            this.from = from;
            this.weight = weight;
        }

        Edge(String from, String to, Double weight, String routeName) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.routeName = routeName;
        }
    }

    /**
     * Getting this show on the road!
     */
    public Graph(){
        adjacencyList = new HashMap<>();
    }

    /** Task 1: Add Transit Paths - add Transit Paths to the graph design you have developed.
     *
     * @param stops
     * @param routeName
     * @param travelTimes
     */
    public void addTransitRoute(List<String> stops, String routeName, List<Double> travelTimes) {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** Task 2: Get Transit Paths - get the three transit paths from a start to destination that use the least amount of transfers.
     *          Break ties using the shorter path!
     *
     * @param start
     * @param destination
     * @return a List<List<String>> of vertices and routes for the three minimized transfer paths [[A, C, D, E, F, G, 372, 556], ...].
     * The inner list should be formatted where you add Strings in the sequential order "A" then "B" and all vertices, then "32" and all bus routes etc.
     * i.e. We want an inner list of [A, B, G, 32, 1Line] since the route from A -> B is on route 32 and from B -> G is on the 1Line.
     * Ties are broken using the shorter path!
     * Note: Do not add the same route multiple times for a path! I.e. Only add route "32" once per path.
     */
    public List<List<String>> getTransitPaths(String start, String destination) {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * You can use this as a helper to return all paths from a start vertex to an end vertex.
     * Call this in getTransitPaths!
     * This method is designed to help give partial credit in the event that you are unable to finish getTransitPaths!
     *
     * @param start
     * @param destination
     * @return a List<List<String>> of containing all vertices among all paths from start to dest [[A, C, D, E, F, G], ...].
     * Do not add transit routes to this method! You should take care of that in getTransitPaths!
     */
    public List<List<String>> allPaths(String start, String destination){
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Task 3: Get Shortest Coffee Path - get the shortest path from start to destination with a coffee shop on the route.
     *
     * @param start
     * @param destination
     * @param coffeeStops
     * @return a List<String> representing the shortest path from a start to a destination with a coffee shop along the way
     * return in the form of a List where you add Strings in the sequential order "A" then "B" and all vertices, then "32" and all bus routes etc.
     * i.e. We want to return [A, B, G, 32, 1Line] since the route from A -> B is on route 32 and the route from B -> G is on the 1Line.
     * Note: Do not add the same route multiple times for a path! I.e. Only add route "32" once per path.
     */
    public List<String> getShortestCoffeePath(String start, String destination, Set<String> coffeeStops) {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * A helper method used to actually find the shortest path between any start node and destination node.
     * Call this in getShortestCoffeePaths!
     * This method is designed to help give partial credit in the event that you are unable to finish getShortestCoffeePath!
     *
     * @param start
     * @param destination
     * @return a List<String> containing all vertices along the shortest path in the form [A, C, D, E, F, G].
     * Do not add transit routes to this method! You should take care of that in getShortestCoffeePaths!
     */
    public List<String> shortestPath(String start, String destination){
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
