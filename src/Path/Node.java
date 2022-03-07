package Path;

import java.awt.Color;
import java.awt.Graphics;

public class Node {
	   private int x, y, path;
	   private String color;
	   private boolean visited;
	   
	   public Node(int a, int b) {
	      x = a;
	      y = b;
	      visited = false;
	      path = 999;
	   }
	   public int getX() {
	      return x;
	   }
	   public int getY() {
	      return y;
	   }
	   public void move(int a, int b) {
	      x = a;
	      y = b;
	   }
	   public int getPath() {
		   return path;
	   }
	   public void setPath(int a) {
		   path = a;
	   }
	   public boolean getVisited() {
	      return visited;
	   }
	   public void setVisited() {
	      visited = true;
	   }
	   
	   public void setstColor() {
	      color = "start";
	   }
	   public void setendColor() {
	      color = "end";
	   }
	   public void paint(Graphics g) {
	      g.setColor(Color.pink);
	      g.fillOval(x-25, y-25, 50, 50);
	      if(color=="start") {
	         g.setColor(Color.red);
	         g.fillOval(x-25, y-25, 50, 50);
	      }
	      else if(color=="end") {
	         g.setColor(Color.blue);
	         g.fillOval(x-25, y-25, 50, 50);
	      }
	   }

	}