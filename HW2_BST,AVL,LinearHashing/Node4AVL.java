public class Node4AVL{
	String data;
	int height;
	int freq;
	int probe;
	Node4AVL left;
	Node4AVL right;
	
	public Node4AVL(){
		left=right=null;
	}
    
	public Node4AVL(String key){
		freq=1;
		probe=0;
		left=right=null;
		this.data=key;
		this.height=0;
	}
}