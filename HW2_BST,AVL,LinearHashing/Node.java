public class Node {
    Node left, right;
    String data;
    int freq;
    int probe;

    public Node(String data){
        this.data = data;
        freq=1;
        probe=0;
        left= right=null;
    }
}
