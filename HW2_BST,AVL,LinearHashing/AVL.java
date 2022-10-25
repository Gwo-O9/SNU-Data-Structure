public class AVL extends BST {
	private Node4AVL rootAVL;
	int Sprobe = 0; //S means sum

	public AVL() {
		this.rootAVL = null;
	}

	private int max(int a, int b) { //decide what is bigger one.
		return a > b ? a : b;
	}

	public Node4AVL insertfunc(String key, Node4AVL rootAVL) { //AVL insert function which inserts and makes balance at the same time.
		if (rootAVL == null) {
			rootAVL = new Node4AVL(key);
		}

		else if (key.compareTo(rootAVL.data) > 0) {
			rootAVL.right = insertfunc(key, rootAVL.right);
			if (height(rootAVL.right) - height(rootAVL.left) == 2) {
				if (key.compareTo(rootAVL.right.data) > 0)
					rootAVL = RotateR(rootAVL);
				else
					rootAVL = RotateLR(rootAVL);
			}
		}

		else if (key.compareTo(rootAVL.data) < 0) {
			rootAVL.left = insertfunc(key, rootAVL.left);
			if (height(rootAVL.left) - height(rootAVL.right) == 2) {
				if (key.compareTo(rootAVL.left.data) < 0)
					rootAVL = RotateL(rootAVL);
				else
					rootAVL = RotateRL(rootAVL);
			}
		} 
		else {
			rootAVL.freq++;
		}
		rootAVL.height = max(height(rootAVL.left), height(rootAVL.right)) + 1;
		return rootAVL;
	}

	public void insert(String key) {
		rootAVL = insertfunc(key, rootAVL);
	}

	public int sumProbes() {
		return Sprobe;
	}

	public int height(Node4AVL rootAVL) {
		if (rootAVL == null)
			return -1;
		else
			return rootAVL.height;
	}

	public Node4AVL RotateL(Node4AVL rootAVL) {
		Node4AVL temp = rootAVL;
		temp = rootAVL.left;
		rootAVL.left = temp.right;
		temp.right = rootAVL;
		rootAVL.height = max(height(rootAVL.left), height(rootAVL.right)) + 1;
		temp.height = max(height(temp.left), rootAVL.height) + 1;
		return temp;
	}

	public Node4AVL RotateR(Node4AVL rootAVL) {
		Node4AVL temp = rootAVL;
		temp = rootAVL.right;
		rootAVL.right = temp.left;
		temp.left = rootAVL;
		rootAVL.height = max(height(rootAVL.right), height(rootAVL.left)) + 1;
		temp.height = max(height(temp.right), rootAVL.height) + 1;
		return temp;
	}

	public Node4AVL RotateRL(Node4AVL rootAVL) {
		rootAVL.left = RotateR(rootAVL.left);
		return RotateL(rootAVL);
	}

	public Node4AVL RotateLR(Node4AVL rootAVL) {
		rootAVL.right = RotateL(rootAVL.right);
		return RotateR(rootAVL);
	}

	public void printfunc(Node4AVL rootAVL) {
		if (rootAVL != null) {
			printfunc(rootAVL.left);
			System.out.println("[" + rootAVL.data + ":" + rootAVL.freq + ":" + rootAVL.probe + "]");
			printfunc(rootAVL.right);
		}
	}

	public void print() {
		printfunc(rootAVL);
	}

	public void Resetfunc(Node4AVL rootAVL) {
		if (rootAVL == null)
			return;
		Resetfunc(rootAVL.left);
		rootAVL.freq = 1;
		rootAVL.probe = 0;
		Resetfunc(rootAVL.right);
	}

	public void resetCounters() {
		Resetfunc(rootAVL);
	}

	public boolean findfunc(String key, Node4AVL cur) {
		if (cur == null) return false;
		cur.probe++;
		Sprobe++;
		if (key.compareTo(cur.data) < 0) {

			return findfunc(key, cur.left);
		}

		if (key.compareTo(cur.data) > 0) {
			return findfunc(key, cur.right);
		}
		return true;
	}

	public boolean find(String key) {
		return findfunc(key, rootAVL);
	}
}
