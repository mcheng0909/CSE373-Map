package graphs;
import java.util.*;

/**
 * Graph Design: A way for you to develop a graph on your terms to satisfy certain functionality.
 */
public class Graph {

    /*
     * Feel free to develop this class however you like, with either an adjacency list or using nodes. For reference, the staff solution
     * was developed using a combination of an adjacency list and the edge class to incorporate route names. The preferred solution
     * (and potentially the most intuitive) is using the adjacency list HashMap to store the vertices/stops as values and a list of
     * that vertex's edges (i.e. the 'A-C' & 'A-B' edges for vertex 'A') as the value. The vertex can be stored as a Node or just a String!
     *
     */

    /**
     * a map to represent an adjacency list for our graph.
     */
    private Map<String, ArrayList<Edge>> adjacencyList;

    /**
     * a Node class example you might like to use.
     */
    private class Node {
        private List<String> path;
        private double totalTime;
        private int routeChanges;

        public Node(List<String> path, double totalTime, int routeChanges) {
            this.path = path;
            this.totalTime = totalTime;
            this.routeChanges = routeChanges;
        }

        public List<String> getPath() {
            return path;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public int getRouteChanges() {
            return routeChanges;
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
        //throw new UnsupportedOperationException("Not implemented yet");
        for(int i =0; i< stops.size()-1;i++){
            String from = stops.get(i);
            String to = stops.get(i+1);
            Double weight = travelTimes.get(i);

            Edge edgefrom = new Edge(from, to , weight, routeName);
            Edge edgeto = new Edge(to, from , weight, routeName);

            if(!adjacencyList.containsKey(from)){
                adjacencyList.put(from,new ArrayList<>());
            }
            adjacencyList.get(from).add(edgefrom);

            if(!adjacencyList.containsKey(to)){
                adjacencyList.put(to,new ArrayList<>());
            }
            adjacencyList.get(to).add(edgeto);

        }

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
        //throw new UnsupportedOperationException("Not implemented yet");
        List<List<String>> allPaths = allPaths(start, destination);
        List<Node> pathsWithCount = new ArrayList<>();
        Set<List<String>> seenPaths = new HashSet<>();

        for (List<String> path : allPaths){
            //List<String> routesPath = getRoutesName(path);

            if (!seenPaths.contains(path)) {
                double time = getDistance(path);
                int count = getTransferCount(path);
                pathsWithCount.add(new Node(path, time, count));
                seenPaths.add(path);
            }
        }
        pathsWithCount.sort(Comparator.comparingInt(Node::getRouteChanges)
                .thenComparingDouble(Node::getTotalTime));

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(getRoutesName(pathsWithCount.get(i).getPath()));
        }
        return result;
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
       // throw new UnsupportedOperationException("Not implemented yet");
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        dfs(start, destination, visited, path, paths);
        return paths;
    }
    private void dfs(String current, String destination, Set<String> visited, List<String> path, List<List<String>> paths) {
        visited.add(current);
        path.add(current);
        if(current.equals(destination)) {
            paths.add(new ArrayList<>(path));
        } else {
            for (Edge edge : adjacencyList.get(current)) {
                String neighbor = edge.to;
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, destination, visited, path, paths);
                }
            }
        }
        path.remove(path.size()-1);
        visited.remove(current);
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
        //throw new UnsupportedOperationException("Not implemented yet");
        List<String> overallShortest = null;
        double mindist = Double.POSITIVE_INFINITY;
        for (String coffestop : coffeeStops){
            List<String> shortestcoffeepath = shortestPath(start,coffestop);
            List<String> coffeetodest = shortestPath(coffestop,destination);
            shortestcoffeepath.addAll(coffeetodest.subList(1,coffeetodest.size()));

            double totalDist = getDistance(shortestcoffeepath);

            if(totalDist<mindist){
                mindist = totalDist;
                overallShortest = shortestcoffeepath;
            }
        }

        return getRoutesName(overallShortest);
    }

    private double getDistance(List<String> path){
        double totalDist =0.0;
        for(int i = 0; i< path.size()-1; i++){
            String from = path.get(i);
            String To = path.get(i+1);
            for(Edge edge : adjacencyList.get(from)){
                if(edge.to.equals(To)){
                    totalDist += edge.weight;
                    break;
                }
            }
        }
        return totalDist;
    }
    /*
    private List<String> getRoutesName(List<String> path){
        List<String> result = new ArrayList<>(path);
        String lastRoute = "";
        for(int i = 0; i< path.size()-1; i++){
            String from = path.get(i);
            String to = path.get(i+1);

            for(Edge edge : adjacencyList.get(from)){
                if(edge.to.equals(to)){
                    if(!edge.routeName.equals(lastRoute)){
                        result.add(edge.routeName);
                        lastRoute = edge.routeName;
                    }
                    break;
                }
            }
        }
        return result;
    }

     */

    private List<String> getRoutesName(List<String> path) {
        List<String> formattedPath = new ArrayList<>(path); // Start with all vertices
        String lastRoute = "";
        Set<String> routes = new LinkedHashSet<>();

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);

            String preferredRoute = null;
            boolean routeContinued = false;

            for (Edge edge : adjacencyList.get(from)) {
                if (edge.to.equals(to)) {
                    if (edge.routeName.equals(lastRoute)) {
                        preferredRoute = edge.routeName;
                        routeContinued = true;
                        break;  // Continue on the same route
                    }
                    if (preferredRoute == null) {
                        preferredRoute = edge.routeName;
                    }
                }
            }

            // If we had to switch routes, or this is the first route, add the new route
            if (!routeContinued || i == 0) {
                routes.add(preferredRoute);
                lastRoute = preferredRoute;
            }
        }

        formattedPath.addAll(routes); // Append the unique route names in order

        return formattedPath;
    }


    private int getTransferCount (List<String> path) {
        String lastRoute = "";
        int routeChanges = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);

            String preferredRoute = null;
            boolean routeContinued = false;

            for (Edge edge : adjacencyList.get(from)) {
                if (edge.to.equals(to)) {
                    if (edge.routeName.equals(lastRoute)) {
                        preferredRoute = edge.routeName;
                        routeContinued = true;
                        break;  // Continue on the same route without a transfer
                    }
                    if (preferredRoute == null) {
                        preferredRoute = edge.routeName;  // Select a new route if necessary
                    }
                }
            }

            if (!routeContinued) {
                routeChanges++;
                lastRoute = preferredRoute;
            }
        }

        return routeChanges;
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
        //throw new UnsupportedOperationException("Not implemented yet");
        Map<String,Edge> edgeTo = new HashMap<>();
        Map<String, Double> distTo = new HashMap<>();
        PriorityQueue<String> perimeter = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        perimeter.add(start);
        edgeTo.put(start, null);
        distTo.put(start, 0.0);
        while (!perimeter.isEmpty()) {
            String from = perimeter.poll();

            for (Edge edge : adjacencyList.get(from)) {
                String to = edge.to;
                double oldDist = distTo.getOrDefault(to, Double.POSITIVE_INFINITY);
                double newDist = distTo.get(from) + edge.weight;
                if (newDist < oldDist) {
                    edgeTo.put(to, edge);
                    distTo.put(to, newDist);
                    perimeter.add(to);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String curr = destination;
        path.add(curr);
        while (edgeTo.get(curr) != null) {
            curr = edgeTo.get(curr).from;
            path.add(curr);
        }
        Collections.reverse(path);
        return path;


    }
}
