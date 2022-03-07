package Path;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectMouse extends MouseAdapter {
	   private MyFrame frame;
	   
	   public SelectMouse(MyFrame f) {
	      frame = f;
	   }
	   
	   @Override
	   public void mousePressed(MouseEvent e) {
	      int x = e.getX();
	      int y = e.getY();
	      
	      frame.setCheckNode(x, y);
	      
	      int button = e.getButton();
	      if(button==1) {
	         
	      }
	      else if(button==3) {
	         frame.makePopup();
	      }
	      
	   }
	   @Override
	   public void mouseDragged(MouseEvent e) {
	      int x = e.getX();
	      int y = e.getY();
	      
	      frame.setDraggedSelected(x, y);
	   }

	}