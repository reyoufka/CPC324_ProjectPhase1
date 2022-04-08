public class MinHeap {

    //The max value of the heap capacity 
    int capacity;

    //Heap current size
    int currentSize;

    //Array contains the values of the min heap
    HeapNode[] MinHeapArr;

    //Array that will be used to decrease the key
    int[] indexes; 

    /**
     * Constructor 
     * @param capacity = the max value 
     */
    public MinHeap(int capacity) {
        this.capacity = capacity;
        MinHeapArr = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        MinHeapArr[0] = new HeapNode();
        MinHeapArr[0].key = Integer.MIN_VALUE;
        MinHeapArr[0].vertex = -1;
        currentSize = 0;
    }

    /**
     * Prints min heap values 
     */
    public void display() {
        for (int i = 0; i <= currentSize; i++) {
            System.out.println(" " + MinHeapArr[i].vertex + "   key   " + MinHeapArr[i].key);
        }
        System.out.println("________________________");
    }

    /**
     * Add nodes to the min heap 
     * @param Node 
     */
    public void insert(HeapNode Node) {
        currentSize++;
        int Index = currentSize;
        MinHeapArr[Index] = Node;
        indexes[Node.vertex] = Index;
        bubbleUp(Index);
    }

    /**
     * Moves the last value added to the correct position in the heap
     * @param Number = current index
     */
    public void bubbleUp(int Number) {
        int parentIndex = Number / 2;
        int currentIndex = Number;
        while (currentIndex > 0 && MinHeapArr[parentIndex].key > MinHeapArr[currentIndex].key) {
            HeapNode currentNode = MinHeapArr[currentIndex];
            HeapNode parentNode = MinHeapArr[parentIndex];

            //swap the positions
            indexes[currentNode.vertex] = parentIndex;
            indexes[parentNode.vertex] = currentIndex;
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    /**
     * Update the indexes[] and move the last node to the top and remove it
     * @return min node 
     */
    public HeapNode extractMin() {
        HeapNode min = MinHeapArr[1];
        HeapNode lastNode = MinHeapArr[currentSize];
        indexes[lastNode.vertex] = 1;
        MinHeapArr[1] = lastNode;
        MinHeapArr[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    /**
     * To compare the values in the min heap to make sure they are in correct position 
     * @param k
     */
    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIndex = 2 * k;
        int rightChildIndex = 2 * k + 1;
        if (leftChildIndex < heapSize() && MinHeapArr[smallest].key > MinHeapArr[leftChildIndex].key) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heapSize() && MinHeapArr[smallest].key > MinHeapArr[rightChildIndex].key) {
            smallest = rightChildIndex;
        }
        if (smallest != k) {

            HeapNode smallestNode = MinHeapArr[smallest];
            HeapNode kNode = MinHeapArr[k];

            //Swap the positions
            indexes[smallestNode.vertex] = k;
            indexes[kNode.vertex] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    /**
     * Swap to elements places 
     * @param a = the k value
     * @param b = the smallest value
     */
    public void swap(int a, int b) {
        HeapNode temp = MinHeapArr[a];
        MinHeapArr[a] = MinHeapArr[b];
        MinHeapArr[b] = temp;
    }

    /**
     * Check if the currentSize equal to zero then it means that the min heap is empty
     * @return either true or false 
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    //@return heap Current size 
    public int heapSize() {
        return currentSize;
    }
}
