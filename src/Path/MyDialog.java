package Path;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyDialog extends Dialog {
	   private MyFrame frame;
	   private MyDialog me;
	   
	   private class ExitHandler extends WindowAdapter{
	      @Override
	      public void windowClosing(WindowEvent e) {
	         me.dispose();
	      }
	   }
	   
	   public MyDialog (MyFrame f) {
	      super(f, "MyDialog", true); //super은 첫문장에
	      frame = f;
	      me = this;
	      
	      ExitHandler eh = new ExitHandler();
	      this.addWindowListener(eh);
	      
	      this.setLayout(new BorderLayout());
	      
	      Label lb = new Label("Distance");
	      this.add(lb, BorderLayout.NORTH);
	      TextField tf = new TextField();
	      this.add(tf, BorderLayout.CENTER);
	      tf.setText("");
	      
	      Button b1 = new Button("Ok");
	      ActionListener al = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            int dist = Integer.parseInt(tf.getText());
	            
	            frame.setDist(dist);
	            me.dispose();
	         }
	      };
	      this.add(b1, BorderLayout.SOUTH);
	      b1.addActionListener(al);
	   }
	}