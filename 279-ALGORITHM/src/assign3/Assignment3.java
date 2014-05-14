package assign3;

public class Assignment3 {
	public static int[] convertArguments(String[] args) {
		int[] intArgs = new int[args.length - 1];
		for (int i = 0; i < intArgs.length; i++) {
			intArgs[i] = Integer.parseInt(args[i+1]);
		}
		return intArgs;
	}
	
	public static Node buildTree(int stringLength ,int[] l) {
		Node root = new Node();
		root.end = stringLength;
		root.start = 1;
		for (int i = 0; i < l.length; i++) {
			Node current = new Node();
			current.start = root.start;
			current.end = root.end;
			current.leftChild = root.leftChild;
			current.rightChild = root.rightChild;
			if (current.leftChild == null && current.rightChild == null) {
				current.leftChild = new Node();
				current.rightChild = new Node();
				current.leftChild.start = current.start;
				current.leftChild.end = l[i];
				current.rightChild.start = l[i] + 1;
				current.rightChild.end = current.end;
				root = current;
			} else {
				while (current.leftChild != null && current.rightChild != null) {
					if (current.leftChild.end > l[i]) {
						current = current.leftChild;
					} else { 
						current = current.rightChild;
					}
				}
					current.leftChild = new Node();
					current.rightChild = new Node();
					current.leftChild.start = current.start;
					current.leftChild.end = l[i];
					current.rightChild.start = l[i] + 1;
					current.rightChild.end = current.end;
				
			}
		}
		return root;
	}
	
	public static int calculateCost(Node current) {
		if (current.leftChild == null && current.rightChild == null)
			return 0;
		return calculateCost(current.leftChild) + calculateCost(current.rightChild) + current.end + 1 - current.start;
	}
	
	public static void printCost(Node root) {
		System.out.println("Cost: " + calculateCost(root));
	}
	
	public static void printPermute(int[] a, int start, int end, int stringLength) {
		if (start == end) {
			System.out.print("order = ");
			for (int i = 0; i < a.length; i++) {
				if (i < a.length - 1)
					System.out.print(a[i] + " ");
				else 
					System.out.print(a[i] + ", ");
			}
			Node root = buildTree(stringLength, a);
			printCost(root);
		} else {
			for (int j = start; j <= end; j++) {
				int temp = a[start];
				a[start] = a[j];
				a[j] = temp;
				printPermute(a, start + 1, end, stringLength);
				temp = a[start];
				a[start] = a[j];
				a[j] = temp;
			}
		}
	}
	
	
	public static void main(String[] args) {
		if (args[0].equals("cost")) {
			int[] intArgs = convertArguments(args);
			int[] l = new int[intArgs.length-1];
			int stringLength = intArgs[0];
			for (int i = 0; i < l.length; i++) l[i] = intArgs[i+1];
			Node root = buildTree(stringLength, l);
			printCost(root);
			System.out.println();
		} else if (args[0].equals("all")) {
			int[] intArgs = convertArguments(args);
			int[] l = new int[intArgs.length-1];
			int stringLength = intArgs[0];
			for (int i = 0; i < l.length; i++) l[i] = intArgs[i+1];
			printPermute(l, 0, l.length - 1, stringLength);
			System.out.println();
		}
	}
}

class Node {
	int start;
	int end;
	Node leftChild;
	Node rightChild;
}

