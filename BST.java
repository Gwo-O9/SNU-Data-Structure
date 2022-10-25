public class BST { // Binary Search Tree implementation

	private Node root;
	int Scount = 0, Sfreq = 0, Sprobe = 0; //S means Sum

	public BST() {
		this.root = null;
	}

	public void insert(String key) {
		Node Node = new Node(key); //if Node is null 
		if (root == null) {
			root = Node;
			Sfreq = 1;
			Scount = 1;
			return;
		}
		Node cur = root;
		Node up = null;

		while (true) {
			up = cur;
			if (key.compareTo(cur.data) < 0) { //key < cur.data;
				cur = cur.left;
				if (cur == null) {
					up.left = Node;
					Sfreq++;
					Scount++;
					return;
				}
			} else if (key.compareTo(cur.data) > 0) { // key > cur.data
				cur = cur.right;
				if (cur == null) {
					up.right = Node;
					Sfreq++;
					Scount++;
					return;
				}
			} else { // key == cur.data
				cur.freq++;
				Sfreq++;
				return;
			}
		}
	}

	public boolean find(String key) {
		Node cur = root;
		while (cur != null) {
			cur.probe++;
			Sprobe++;
			if (key.compareTo(cur.data) == 0) // if key == cur.data, return ture
				return true;
			else if (key.compareTo(cur.data) < 0)
				cur = cur.left;
			else
				cur = cur.right;
		}
		return false; // if the key isn't in the tree, reurn false

	}

	public int size() { //Return the number of nodes in the tree
		return Scount;
	}

	public int sumFreq() { //Return sum of all node's frequency
		return Sfreq;
	}

	public int sumProbes() { //Return sum of all node's probes
		return Sprobe;
	}

	public void Resetfunc(Node Node) { // Function of Reset all node's freq and probe
		if (Node == null)
			return;
		Resetfunc(Node.left);
		Resetfunc(Node.right);
		Node.freq = 1;
		Node.probe = 0;
	}

	public void resetCounters() {
		Resetfunc(root);
	}

	public void printfunc(Node root) { //print output with given form. [ : : ]
		if (root != null) {
			printfunc(root.left);
			System.out.println("[" + root.data + ":" + root.freq + ":" + root.probe + "]");
			printfunc(root.right);
		}
	}

	public void print() {
		printfunc(root);
	}
}
