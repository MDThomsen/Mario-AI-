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
	
	private int x,y;
	private List<Edge> neighbours;
	
	public Node(int x, int y) {
		this.setX(x);
		this.setY(y);
		neighbours = new LinkedList<Edge>();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void addEdge(Node x) {
		neighbours.add(new Edge(x));
	}
	
	public List<Edge> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public String toString() {
		return "Node (x = "+x+", y = "+y+")";
	}
	
}

public class Graph {
	
	List<Node> nodes;
	
	public Graph() {
		nodes = new LinkedList<Node>();
	}
	
	public void findNeighbours(Node n, byte[][] world) {
		
		if(n.getX() == world.length-1) return;
		else {
			if(world[n.getY()][n.getX()+1] == 0 && world[n.getY()+1][n.getX()+1] == 0) {
				Node x = new Node(n.getX()+1,n.getY());
				findNeighbours(x,world);
				n.addEdge(x);  
			}
		}
	}
	
	public void addNode(Node n) {
		nodes.add(n);
	}
}
