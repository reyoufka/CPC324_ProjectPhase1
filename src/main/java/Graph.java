import java.util.LinkedList;
import java.util.Random;

public class Graph {

    /**
     * Variable to take the number of vertices from the main method depend on
     * user choice
     */
    int verticesNo;

    /**
     * Variable to take the number edges from the main method depend on user
     * choice
     */
    int edgesNo;

    /**
     * A linked list that save every vertex adjacent
     */
    
    boolean isDigraph;
    
    LinkedList<Edge>[] adjlist;

    /**
     * Constructor
     *
     * @param verticesNo = number of vertices
     * @param edgesNo = number of edges
     */
    Graph(int verticesNo, int edgesNo) {
        this.verticesNo = verticesNo;
        this.edgesNo = edgesNo;
        adjlist = new LinkedList[verticesNo];
        
        //Initialize adjacency lists for all the vertices
        for (int i = 0; i < verticesNo; i++) {
            adjlist[i] = new LinkedList<>();
        }
    }

    /**
     * this method is used in makeGraph() method to add a new edge to the graph
     *
     * @param source source vertex
     * @param target destination vertex
     * @param weight weight of the edge
     */
    public void addEdge(int source, int target, int weight) {
        Edge edge = new Edge(source, target, weight);
        adjlist[source].addFirst(edge);

        edge = new Edge(target, source, weight);
        adjlist[target].addFirst(edge); //for undirected graph

    }

    /**
     * randomly generate graph with given int weight the range is from (0,20)
     *
     * @param graph = data structure made of vertices and edges
     */
    public void makeGraph(Graph graph) {
        //Instance of Random class
        Random random = new Random();
        
        //Ensure that all vertices are connected
        for (int i = 0; i < verticesNo - 1; i++) {
            int RandomNum = random.nextInt(10) + 1;
            addEdge(i, i + 1, RandomNum);
        }

        //Generate random graph with the remaining edges
        int remaning = edgesNo - (verticesNo - 1);

        for (int i = 0; i < remaning; i++) {
            int source = random.nextInt(graph.verticesNo);
            int Destination = random.nextInt(graph.verticesNo);
            if (Destination == source || isConnected(source, Destination, graph.adjlist)) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            //Generate random weights in range 0 to 20
            int weight = random.nextInt(20) + 1;
            
            //Add edge to the graph
            addEdge(source, Destination, weight);
        }
    }

    /**
     * Checks if the edge is already existed and connected
     *
     * @param Source
     * @param Destination
     * @param allEdges
     * @return
     */
    public boolean isConnected(int Source, int Destination, LinkedList<Edge>[] allEdges) {
        for (LinkedList<Edge> i : allEdges) {
            for (Edge edge : i) {
                if ((edge.source.label == Source && edge.target.label == Destination) || (edge.source.label == Destination && edge.target.label == Source)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Calculate and display the minimum spanning tree cost and display it , called in prim's algorithm
     * @param resultSet
     */
    public void PrintMinSpanTree(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.print("Minimum Spanning Tree: ");
        for (int i = 1; i < verticesNo; i++) {
            total_min_weight += resultSet[i].weight;
        }
        System.out.println(total_min_weight);
    }
    
    /**
     * Calculate all edges weight to print the min spanning tree cost , called in kruskal's algorithm
     * @param edgeList = all edges in the graph to get the weight from 
     */
    public void printGraph(LinkedList<Edge> edgeList) {
        int cost = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            cost += edge.weight;
        }
        System.out.println("Minimum Spanning Tree = " + cost);
    }    

}
