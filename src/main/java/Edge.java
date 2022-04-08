public class Edge {
    
    //Parent vertex
    Vertex parent;
    
    //Source vertex
    Vertex source;

    //Destination vertex
    Vertex target;

    //Edge's weight
    int weight;

    /*
     * constructor
     * @param source = vertex
     * @param destination = vertex
     * @param weight = int value has a edge weight
     */
    public Edge(int source, int destination, int weight) {
        this.parent = new Vertex();
        this.source = new Vertex();
        this.target = new Vertex();
        this.source.label = source;
        this.target.label = destination;
        this.weight = weight;
    }

    //toString method
    public String toString() {
        return source + "-" + target + ": " + weight;
    }

}
