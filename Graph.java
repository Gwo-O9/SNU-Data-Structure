class Graph{
    public String parent;
    public int dist;
    public int p;
    public int h;
    public Graph(String parent, int h, int dist, int p){
        this.parent = parent;
        this.dist = dist;
        this.p = p;
        this.h = h;
    }
}