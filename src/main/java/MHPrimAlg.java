import java.util.LinkedList;

public class MHPrimAlg extends MSTAlgorithm{

    Graph graph;
    
     /**
     * Remove the min value from the heap 
     * @param minHeap = All min heap values 
     * @param newKey = Index
     * @param vertex = wanted vertex
     */
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

        //Get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];

        //Get the node and update its value
        HeapNode node = minHeap.MinHeapArr[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }
    
    public void PrimMinHeap() {
        //Start time
        double StartTime = System.currentTimeMillis();
        boolean[] inHeap = new boolean[graph.verticesNo];
        ResultSet[] resultSet = new ResultSet[graph.verticesNo];
        //Keys[] used to store the key to know whether min hea update is required
        int[] key = new int[graph.verticesNo];
        //Create heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[graph.verticesNo];
        for (int i = 0; i < graph.verticesNo; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
            resultSet[i].parent.label = -1;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //Decrease the key for the first index
        heapNodes[0].key = 0;

        //Add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(graph.verticesNo);
        //Add all the vertices to priority queue
        for (int i = 0; i < graph.verticesNo; i++) {
            minHeap.insert(heapNodes[i]);
        }

        //While minHeap is not empty
        while (!minHeap.isEmpty()) {
            //Extract the min
            HeapNode extractedMinNode = minHeap.extractMin();

            //Extracted vertex
            int extractedVertex = extractedMinNode.vertex;
            inHeap[extractedVertex] = false;

            //Iterate through all the adjacent vertices
            LinkedList<Edge> list = graph.adjlist[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //Only if edge destination is present in heap
                if (inHeap[edge.target.label]) {
                    int destination = edge.target.label;
                    int newKey = edge.weight;
                    //Check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        decreaseKey(minHeap, newKey, destination);
                        //Update the parent node for destination
                        resultSet[destination].parent.label = extractedVertex;
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }
        //Finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //Print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin Min Heap): " + (FinishTime - StartTime) + " ms.");
        //Print minimum spanning tree
        graph.PrintMinSpanTree(resultSet);
    }
    
    @Override
    public void displayResultingMST(Graph graph) {
        this.graph = graph;
        PrimMinHeap();
    }
    
}
    