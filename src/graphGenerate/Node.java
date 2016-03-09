package graphGenerate;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
	private int x,y;
	private List<Node> children;
	
	public Node(int x, int y) {
		this.setX(x);
		this.setY(y);
		children = new LinkedList<Node>();
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
	
	public void addChild(Node x) {
		children.add(x);
	}
	
	public List<Node> getChildren() {
		return children;
	}
	
	public void findChildren(byte[][] world) {
		
		if(x == world.length-1) return;
		else {
			if(world[y][x+1] == 0 && world[y+1][x+1] == 0) {
				Node n = new Node(x+1,y);
				n.findChildren(world);
				addChild(n); 
			}
		}
	}
	
	@Override
	public String toString() {
		return "Node (x = "+x+", y = "+y+")";
	}
	
}
