import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class PQPrimAlg extends MSTAlgorithm {

    Graph graph;

    /**
     * Prim's Algorithm using Priority Queue : calculate the taken time to apply
     * Prim's Algorithm using Priority Queue on different graphs
     */
    public void PrimPriorityQueue() {
        //start time
        double StartTime = System.currentTimeMillis();
        boolean[] MinSpanTree = new boolean[graph.verticesNo];
        ResultSet[] resultSet = new ResultSet[graph.verticesNo];
        int[] key = new int[graph.verticesNo];  //keys are used to store the key to know whether priority queue update is required

        //Initialize all the keys to infinity and resultSet for all the vertices
        for (int i = 0; i < graph.verticesNo; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
        }

        //Initialize priority queue
        //Override the comparator to do the sorting based keys
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(graph.verticesNo, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> point1, Map.Entry<Integer, Integer> point2) {
                //sort using key values
                int key1 = point1.getKey();
                int key2 = point2.getKey();
                return key1 - key2;
            }
        });

        //Create the pair for for the first index, 0 key 0 index
        key[0] = 0;
        Map.Entry<Integer, Integer> point0 = new AbstractMap.SimpleEntry<>(key[0], 0);
        //Add it to pq
        priorityQueue.offer((Map.Entry<Integer, Integer>) point0);
        resultSet[0] = new ResultSet();
        resultSet[0].parent.label = -1;

        //While priority queue is not empty
        while (!priorityQueue.isEmpty()) {
            //Extract the min
            Map.Entry<Integer, Integer> extractedMinPair = priorityQueue.poll();

            //extracted vertex
            Vertex extractedVertex = new Vertex();
            extractedVertex.label = extractedMinPair.getValue();
            MinSpanTree[extractedVertex.label] = true;

            //Iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = graph.adjlist[extractedVertex.label];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //Only if edge destination is not present in mst
                if (MinSpanTree[edge.target.label] == false) {
                    int destination = edge.target.label;
                    int newKey = edge.weight;
                    //Check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        //Add it to the priority queue
                        Map.Entry<Integer, Integer> NewPair = new AbstractMap.SimpleEntry<>(newKey, destination);
                        priorityQueue.offer((Map.Entry<Integer, Integer>) NewPair);
                        //Update the resultSet for destination vertex
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        //Update the key[]
                        key[destination] = newKey;
                    }
                }
            }
        }
        //Finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //Print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin PQ): " + (FinishTime - StartTime) + " ms.");
        //Print mst
        PrintMinSpanTree(resultSet);
    }

    /**
     * Calculate and display the minimum spanning tree cost and display it ,
     * called in Prim's algorithm
     *
     * @param resultSet
     */
    public void PrintMinSpanTree(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.print("Minimum Spanning Tree: ");
        for (int i = 1; i < graph.verticesNo; i++) {
            total_min_weight += resultSet[i].weight;
        }
        System.out.println(total_min_weight);
    }

    @Override
    public void displayResultingMST(Graph graph) {
        this.graph = graph;
        PrimPriorityQueue();
    }

}
