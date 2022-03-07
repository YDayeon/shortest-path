package Path;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeMouse extends MouseAdapter {
	   
private MyFrame frame;
   
   public NodeMouse(MyFrame f) {
      frame = f;
   }
   @Override
   public void mousePressed(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      frame.setPressedNode(x, y);
   }

}