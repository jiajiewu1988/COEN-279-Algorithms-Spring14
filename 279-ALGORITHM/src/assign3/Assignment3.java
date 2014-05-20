package assign3;

import java.util.Arrays;

public class Assignment3 {
	
	/**
	 * convert number arguments into a int array
	 * @param args array of number arguments in String format
	 * @return arguments in int array
	 */
	public static int[] convertArguments(String[] args) {
		int[] intArgs = new int[args.length - 1];
		for (int i = 0; i < intArgs.length; i++) {
			intArgs[i] = Integer.parseInt(args[i+1]);
		}
		return intArgs;
	}
	
	/**
	 * Build a tree of the breaking string procedure
	 * @param stringLength length of the string to break
	 * @param l Array of break after positions
	 * @return the root of breaking string tree
	 */
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
	
	/**
	 * Calculated break string cost in a built tree
	 * @param current root of current tree
	 * @return cost of breaking a string
	 */
	public static int calculateCost(Node current) {
		if (current.leftChild == null && current.rightChild == null)
			return 0;
		return calculateCost(current.leftChild) + calculateCost(current.rightChild) + current.end + 1 - current.start;
	}
	
	/**
	 * Print the cost of breaking a string
	 * @param root root of the string breaking tree
	 */
	public static void printCost(Node root) {
		System.out.println("cost: " + calculateCost(root));
	}
	
	/**
	 * For part 2 of this homework, generate every permutation of 
	 * array L, and call printCost from the first step to print cost 
	 * of every permutation of L
	 * @param a array to generate permutation
	 * @param start start position to generate permutation
	 * @param end end position to generate permutation
	 * @param stringLength length of the string to break
	 */
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
	
	/**
	 * Use dynamic programming to compute the optimal cost of breaking a string problem
	 * Then, print out each subproblem and the overall optimal cost
	 * @param stringLength length of the string to break
	 * @param l array of positions to break
	 */
	public static void breakString(int stringLength, int[] l) {
		int[] L = new int[l.length+2];
		L[0] = 0;
		for (int i = 0; i < l.length; i++) {
			L[i+1] = l[i];
		}
		L[L.length-1] = stringLength;
		int m = L.length;
		Arrays.sort(L);
		int[][] cost = new int[m][m];
		int[][] brk = new int[m][m];
		for (int i = 0; i < m - 1; i ++) {
			cost[i][i] = 0;
			cost[i][i+1] = 0;
		}
		cost[m-1][m-1] = 0;
		
		for (int len = 3; len <= m; len++) {
			for (int i = 0; i < m - len + 1; i++) {
				int j = i + len - 1;
				cost[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k <= j - 1; k++) {
					if (cost[i][k] + cost[k][j] < cost[i][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
						brk[i][j] = k;
					}
				}
				
				cost[i][j] = cost[i][j] + L[j] - L[i];
				System.out.print("Optimal " + L[i+1] + ".." + L[j-1] + " (" + i + "," + (j-2) + ") ");
				System.out.print("order = ");
				print_breaks(L, brk, i, j);
				System.out.println("cost = " + cost[i][j]);
			}
		}
		
		System.out.print("Overall: order = ");
		print_breaks(L, brk, 0, m-1);
		System.out.println("cost = " + cost[0][m-1]);
	}
	
	/**
	 * Print out the break order for the optimal solution of a breaking string problem
	 * @param L array of positions to break
	 * @param brk array of optimal positions for breaking a string
	 * @param i start point of brk array
	 * @param j end point of brk array
	 */
	public static void print_breaks(int[] L, int[][] brk, int i, int j) {
		if (j - i >= 2) {
			int k = brk[i][j];
			System.out.print(L[k] + " ");
			print_breaks(L, brk, i, k);
			print_breaks(L, brk, k, j);
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
		} else if (args[0].equals("best")) {
			int[] intArgs = convertArguments(args);
			int[] l = new int[intArgs.length-1];
			int stringLength = intArgs[0];
			for (int i = 0; i < l.length; i++) l[i] = intArgs[i+1];
			breakString(stringLength, l);
		}
	}
}

/**
 * Tree Node for part 1 and part 2 of this assignment
 * @author jerry
 *
 */
class Node {
	int start;
	int end;
	Node leftChild;
	Node rightChild;
}

