package datastructures;

import java.util.LinkedList;
import java.util.List;

class Edge {

	private Node to;

	public Edge(Node n) {
		to = n;
	}

	public Node getNode() {
		return to;
	}

	@Override
	public String toString() {
		return "(col =" + to.getCol() + " row =" + to.getRow() + ") ";
	}
}

class Node {

	private int row, col;
	private List<Edge> edges;

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		edges = new LinkedList<Edge>();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void addEdge(Node x) {
		edges.add(new Edge(x));
	}

	public List<Edge> getNeighbours() {
		return edges;
	}

	@Override
	public String toString() {
		String s = "Node (x = " + col + ", y = " + row + ") {";
		return s;
	}

	public String neighboursToString() {
		String s = "Node (x = " + col + ", y = " + row + ") {";
		for (Edge e : edges) {
			s += e;
		}

		return s += "}\n";
	}
}

public class Graph {

	List<Node> nodes;
	Queue<Node> frontier;

	public Graph() {
		nodes = new LinkedList<Node>();
		frontier = new Queue<Node>();
	}

	public void initGraph(byte[][] world) {
		Node n = new Node(world.length / 2, world.length / 2);
		this.addNode(n);
		this.findNeighbours(n, n.getRow(), n.getCol(), world);
	}

	public void expandFrontier(byte[][] world, int distanceTraveled, int deltaHeightFromStart) {
		
		int size = frontier.getSize();
		System.out.println("frontier before loop: " + size);
		for (int i = 0; i < size; i++) {

			Node n = frontier.dequeue();
			System.out.println("dequeing: " + n);
			int row = n.getRow() - deltaHeightFromStart;
			int col = n.getCol() - distanceTraveled;

			System.out.println("row: " + row + " col: " + col + " dist: " + distanceTraveled);
			findNeighbours(n, row, col, world);
		}
		System.out.println("frontier after loop: " + size);

	}
	public void printArrayWithNodes(byte[][] world,int distanceTraveled, int deltaHeightFromStart ) {
	for (int i = 0; i < world.length; i++) {
		for (int j = 0; j < world[0].length; j++) {
			boolean node = false;
			for (Node n : nodes) {
				if (n.getRow() - deltaHeightFromStart == i && n.getCol() - distanceTraveled  == j) {
					node = true;
					break;
				}
			}
			if (node) {
				System.out.printf("%3c", 'N');
			} else {
				System.out.printf("%3d", world[i][j]);
			}
		}
		System.out.println();
	}
	}
	public void printArray(byte[][] world) {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				
					System.out.printf("%3d", world[i][j]);
				
			}
			System.out.println();
		}
	}
	public void findNeighbours(Node n, int row, int col, byte[][] world) {
		System.out.println("finding neighbours: " + n + "at postion in array: row: " + row + " , col: " + col);
		// row: row corresponding to n's position in world
		// col: column corresponding to n's position in world		 
		if (col == world.length - 1) {
			frontier.enqueue(n);
			return;
		} else if (freePassageAhead(row, col, world)) {
			Node x = new Node(n.getRow(), n.getCol() + 1);
			System.out.println("node created: " + x);
			this.addNode(x);
			findNeighbours(x, row, col + 1, world);
			n.addEdge(x);
		} /*else if (gapLength <= 10 && gapLength != 0) {
			Node x = new Node(row, col + gapLength);
			findNeighbours(x, x.getRow(), x.getCol(), world);
			n.addEdge(x);
			this.addNode(x);
		}*/ else if (jumpUpHeight(row, col,world) <= 4 && jumpUpHeight(row, col,world) != 0) {
			Node x = new Node(n.getRow()- jumpUpHeight(row, col,world), n.getCol() + 1);
			System.out.println("new node: " + x);
			findNeighbours(x, row-jumpUpHeight(row, col,world), col+1, world);
			n.addEdge(x);
			this.addNode(x);
		}

	}

	private boolean freePassageAhead(int row, int col, byte[][] world) {
		System.out.println("what is straight ahead: " + world[row][col + 1] );
		return (world[row][col + 1] == 0 && world[row - 1][col + 1] == 0);
	}

	private int gapAheadLength(Node n, byte[][] world) {
		int x = n.getCol();
		int y = n.getRow();

		for (int col = x; col < world.length; col++) {
			for (int row = y; row < world.length; row++) {
				if (world[row][col] != 0) {
					return (col - x) + 1;
				}
			}
		}
		return x;
	}

	private int jumpUpHeight(int row, int col, byte[][] world) {
		for (int i = row + 3; i >= row; i--) {
			System.out.println("world at position: "+ world[i][col + 1]);
			if (world[i][col + 1] != 0) {
				if (world[i + 1][col + 1] == 0 && world[i + 2][col + 1] == 0) {
					System.out.println("returning: "+ ((i + 1)-row));
					return (i + 1)-row;
				}
			}
		}
		return 0;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public void printGraph() {
		for (Node n : nodes) {
			System.out.println(n.neighboursToString());
		}
	}
}
