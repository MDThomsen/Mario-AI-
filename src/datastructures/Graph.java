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
}

class Node {

	private int row,col;
	private List<Edge> neighbours;

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		neighbours = new LinkedList<Edge>();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}


	public void addEdge(Node x) {
		neighbours.add(new Edge(x));
	}

	public List<Edge> getNeighbours() {
		return neighbours;
	}

	@Override
	public String toString() {
		return "Node (x = " + col + ", y = " + row + ")";
	}

}

public class Graph {

	List<Node> nodes;

	public Graph() {
		nodes = new LinkedList<Node>();
	}

	public void findNeighbours(Node n, byte[][] world) {
		int jumpHeight = jumpUpHeight(n,world);
		int gapLength = gapAheadLength(n,world);
		if (n.getCol() == world.length - 1)
			return;
		else if (world[n.getRow()][n.getCol() + 1] == 0
				&& world[n.getRow() + 1][n.getCol() + 1] == 0) {
			Node x = new Node(n.getCol() + 1, n.getRow());
			findNeighbours(x, world);
			n.addEdge(x);
		} else if (gapLength <= 10 && gapLength != 0) {
			Node x = new Node(n.getCol() + gapLength, n.getRow());
			findNeighbours(x, world);
			n.addEdge(x);
		}
		else if(jumpHeight <= 4 && jumpHeight != 0){
			Node x = new Node(n.getRow()+jumpHeight,n.getCol()+1);
			findNeighbours(x, world);
			n.addEdge(x);
		}

	}

	private int gapAheadLength(Node n, byte[][] world) {
		int x = n.getCol();
		int y = n.getRow();
	

		for (int col = x; col < world.length; col++) {
			for (int row = y; row < world.length; row++) {
				if (world[row][col] != 0) {
					return (col - x)+1;
				}
			}

		}
		return x;
	}

	private int jumpUpHeight(Node n, byte[][] world) {
		int x = n.getCol();
		for (int row = x + 3; row >= x; row--) {
			if (world[row][x + 1] != 0) {
				if(world[row+1][x+1] == 0 && world[row+2][x+1] == 0){
					return row+1;
				}
			}
		}
		return 0;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}
}
