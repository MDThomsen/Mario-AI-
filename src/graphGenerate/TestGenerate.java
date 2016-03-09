package graphGenerate;

public class TestGenerate {
	
	Node startNode;
	
	public TestGenerate(int x, int y) {
		
		startNode = new Node(x,y);
	}
	
	public void generateGraph(byte[][] world) {
		
		startNode.findChildren(world);
		
	}
	
	public Node getStartNode() {
		return startNode;
	}
	
	
	public void printGraph(Node n) {
		
		if(n != null) {
			System.out.println(n+" Children: { \n");
			for(Node x : n.getChildren()) {
				printGraph(x);
			}
			System.out.println("     }");
		}
	}

}
