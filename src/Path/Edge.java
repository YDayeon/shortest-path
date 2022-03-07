package Path;

public class Edge {
	   private Node n1, n2;
	   private int dist;
	   
	   public Edge() {
	   }
	   
	   public Edge(Node a, Node b) {
	      n1 = a;
	      n2 = b;
	      dist=0;
	      
	   }
	   public Node getN1() {
	      return n1;
	   }
	   public Node getN2() {
	      return n2;
	   }
	   public void setDist(int a) {
	      dist = a;
	   }
	   public int getDist() {
	      return dist;
	   }

	}