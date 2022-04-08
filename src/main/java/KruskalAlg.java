import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalAlg extends MSTAlgorithm{

    private Graph graph;

    /**
     * Make set- creating a new element with a parent pointer to itself.
     *
     * @param parent = chain of parent pointers
     */
    public void makeSet(int[] parent) {
        for (int i = 0; i < graph.verticesNo; i++) {
            parent[i] = i;
        }
    }

    /**
     * Chain of parent pointers from x upwards through the tree until an element
     * is reached whose parent is itself
     *
     * @param parent = chain of parent pointers
     * @param vertex = wanted vertex
     * @return the searched vertex
     */
    public int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        };
        return vertex;
    }

    public void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //Make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    public void kruskal() {
        //Start time
        double StartTime = System.currentTimeMillis();
        LinkedList<Edge>[] allEdges = graph.adjlist.clone(); // modified data type from ArrayList to LinkedList
        PriorityQueue<Edge> priorityQueueVar = new PriorityQueue<>(graph.edgesNo, Comparator.comparingInt(o -> o.weight));

        //Add all the edges to priority queue, //sort the edges on weights
        for (int i = 0; i < allEdges.length; i++) {
            for (int j = 0; j < allEdges[i].size(); j++) {
                priorityQueueVar.add(allEdges[i].get(j));
            }
        }
        //Create a parent array
        int[] parent = new int[graph.verticesNo];

        //Makeset
        makeSet(parent);

        LinkedList<Edge> MinSpanTree = new LinkedList<>();

        //Process vertices - 1 edges
        int index = 0;
        while (index < graph.verticesNo - 1 && !priorityQueueVar.isEmpty()) {
            Edge edge = priorityQueueVar.remove();
            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.source.label);
            int y_set = find(parent, edge.target.label);

            if (x_set == y_set) {
                //Ignore, will create cycle
            } else {
                //Add it to our final result
                MinSpanTree.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
        }

        //Finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //Print the total time consumed by the algorithm
        System.out.println("Total runtime of Kruskal's Algorithm: " + (FinishTime - StartTime) + " ms.");
        //Print minimum spanning tree
        graph.printGraph(MinSpanTree);
    }

    @Override
    public void displayResultingMST(Graph graph) {
        this.graph = graph;
        kruskal();
    }

}
