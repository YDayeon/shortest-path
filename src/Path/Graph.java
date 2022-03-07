package Path;
import java.util.ArrayList;

public class Graph {
	   private ArrayList<Node> Nlist;
	   private ArrayList<Edge> Elist;
	   
	   public Graph() {
	      Nlist = new ArrayList<Node>();
	      Elist = new ArrayList<Edge>();
	      
	   }

	   public void addNlist(int a, int b) {
	      Node n = new Node(a, b);
	      Nlist.add(n);
	   }
	   public ArrayList<Node> getNlist() {
	         return Nlist;
	   }
	   public ArrayList<Edge> getElist() {
	      return Elist;
	   }
	   public Node findNode(int mx, int my) {
	      for(int k=0; k<Nlist.size(); k++) {
	         Node n = Nlist.get(k);
	         int nx = n.getX();
	         int ny = n.getY();
	         if((mx>nx-25)&&(mx<nx+25)&&(my>ny-25)&&(my<ny+25)) {
	            return n;
	         }
	      }
	      return (Node) null;
	   }
	   public void addElist(Edge e) {
	      Elist.add(e);
	   }

	   public Edge findEdge(Node a, Node b) {
	      for(int k=0; k<Elist.size(); k++) {
	         Edge fe = Elist.get(k);
	         Node n1 = fe.getN1();
	         Node n2 = fe.getN2();
	         if(((n1==a)&&(n2==b))||((n1==b)&&(n2==a))) {
	            return fe;
	         }
	      }
	      return null;
	   }
	}