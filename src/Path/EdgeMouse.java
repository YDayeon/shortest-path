package Path;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EdgeMouse extends MouseAdapter{
	   private MyFrame frame;

	   public EdgeMouse(MyFrame f) {
	      frame = f;
	   }
	   
	   @Override
	   public void mousePressed(MouseEvent e) {
	      int px = e.getX();
	      int py = e.getY();
	      frame.setPressedEdge(px, py);
	   }
	   @Override
	   public void mouseDragged(MouseEvent e) {
	      int dx = e.getX();
	      int dy = e.getY();
	      
	      frame.setDraggedEdge(dx, dy);
	   }
	   @Override
	   public void mouseReleased(MouseEvent e) {
	      int rx = e.getX();
	      int ry = e.getY();
	      frame.setReleasedEdge(rx, ry);
	      
	      
	   }

	}