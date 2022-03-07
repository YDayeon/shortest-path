package Path;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MyFrame extends Frame {
	   private int nx, ny, px, py, dx, dy, rx, ry;
	   //private int path;
	   private MyFrame me;
	   private Graph gr;
	   private Edge curEdge;
	   private Node n1, n2, cur, st, end;
	   private boolean ondrawing;
	   private PopupMenu pm;
	   private String write;
	   
	   
	   public class ExitHandler extends WindowAdapter{
	      @Override
	      public void windowClosing(WindowEvent e) {
	         System.exit(0);
	      }
	   }
	   
	   public MyFrame() {
	      me = this; 
	      gr = new Graph();
	      ondrawing = false;
	      curEdge = null;
	      write = null;
	      
	      ExitHandler eh = new ExitHandler();
	      this.addWindowListener(eh);
	      
	      NodeMouse nm = new NodeMouse(this);
	      EdgeMouse em = new EdgeMouse(this);
	      SelectMouse sm = new SelectMouse(this);
	      //
	      this.setLayout(new BorderLayout());
	      Panel p1 = new Panel();
	      this.add(p1, BorderLayout.NORTH);
	      p1.setLayout(new FlowLayout());
	      //
	      Button b1 = new Button("Node");
	      ActionListener a1 = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            me.removeMouseListener(em);
	            me.removeMouseMotionListener(em);
	            me.removeMouseListener(sm);
	            me.removeMouseMotionListener(sm);
	            
	            me.addMouseListener(nm);
	         }
	      };
	      p1.add(b1);
	      b1.addActionListener(a1);
	      b1.setBackground(Color.yellow);
	      b1.setPreferredSize(new Dimension(100, 50));
	      //
	      Button b2 = new Button("Edge");
	      ActionListener a2 = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            me.removeMouseListener(nm);
	            me.removeMouseListener(sm);
	            me.removeMouseMotionListener(sm);
	            
	            me.addMouseListener(em);
	            me.addMouseMotionListener(em);
	         }
	      };
	      p1.add(b2);
	      b2.addActionListener(a2);
	      b2.setBackground(Color.orange);
	      b2.setPreferredSize(new Dimension(100, 50));
	      //
	      Button b3 = new Button("Select");
	      ActionListener a3 = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            me.removeMouseListener(nm);
	            me.removeMouseListener(em);
	            me.removeMouseMotionListener(em);
	            
	            me.addMouseListener(sm);
	            me.addMouseMotionListener(sm);
	         }
	      };
	      p1.add(b3);
	      b3.addActionListener(a3);
	      b3.setBackground(Color.green);
	      b3.setPreferredSize(new Dimension(100, 50));
	      
	      MenuBar mb = new MenuBar();
	      this.setMenuBar(mb);
	      Menu menu = new Menu("Menu");
	      mb.add(menu);
	      MenuItem mi = new MenuItem("ShortestPath");
	      ActionListener ma = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            me.shortestPath();
	            write = "yes";
	            //System.out.println(path);
	         }
	      };
	      menu.add(mi);
	      mi.addActionListener(ma);
	   }
	   public void makePopup() {
	      pm = new PopupMenu();
	      
	      MenuItem mi1 = new MenuItem("start");
	      ActionListener ma1 = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            cur.setstColor();
	            me.repaint();
	            st = cur;
	         }
	      };
	      pm.add(mi1);
	      mi1.addActionListener(ma1);
	      MenuItem mi2 = new MenuItem("end");
	      ActionListener ma2 = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            cur.setendColor();
	            end = cur;
	         }
	      };
	      pm.add(mi2);
	      mi2.addActionListener(ma2);
	      this.add(pm);
	      
	      pm.show(this, cur.getX(), cur.getY());
	   }
	   
	   public void setCheckNode(int a, int b) {
	      cur = gr.findNode(a, b);
	      this.repaint();
	      
	   }
	   public void setDraggedSelected(int a, int b) {
	      if(cur!=null) {
	         cur.move(a, b);
	      }
	      
	      this.repaint();
	   }
	   
	   public void setPressedNode(int a, int b) {
	      nx = a;
	      ny = b;
	      gr.addNlist(nx, ny);
	      this.repaint();
	   }

	   public void setPressedEdge(int a, int b) {
	      n1 = gr.findNode(a, b); 
	      if(n1==(Node) null) {
	         return;
	      }
	      px = n1.getX();
	      py = n1.getY();
	      
	      ondrawing = true;
	   }
	   public void setDraggedEdge(int a, int b) {
	      dx = a;
	      dy = b;
	      this.repaint();
	   }   
	   public void setReleasedEdge(int a, int b) {
	      n2 = gr.findNode(a,b);
	      if(n2==(Node)null) {
	         return;
	      }
	      Edge edge=new Edge(n1, n2);
	      curEdge = edge;
	      gr.addElist(edge);

	      rx=a;
	      ry=b;
	      ondrawing = false;
	      this.repaint();
	      
	      
	      MyDialog md = new MyDialog(this);
	      int x = this.getX();
	      int y = this.getY();
	      
	      md.pack();
	      md.setLocation(x+rx, y+ry);
	      md.setVisible(true);
	      this.repaint();
	   }
	   
	   public void shortestPath() {
		   ArrayList<Node> Nlist = gr.getNlist();
		   Edge fe;
		   Node ns1, ns2;
		   st.setPath(0);
		   st.setVisited();
		   while(true) {
			   ns1 = st;
			   int min = 999;
			   //ns1.setVisited();
			   if(end.getVisited()==true) {
				   break;
			   }
			   for(int k=0; k<Nlist.size(); k++) {	
				   ns2 = Nlist.get(k);
				   boolean visited = ns2.getVisited();
				   if(!visited) {
					   fe = gr.findEdge(ns1, ns2);
					   int path = ns1.getPath();
					   //System.out.println(fe);
					   System.out.println(ns1.getX()+" "+ns1.getY()+" "+ns2.getX()+" "+ns2.getY());
					   if(fe != null) {
						   int dist = fe.getDist();
						   if(path+dist<ns2.getPath()) {
							   path = path + dist;
							   ns2.setPath(path);
							   System.out.println(path);
						   }
						   if(path<min) {
							   min = path;
							   st = ns2;
						   }
					   }
				   }
			   }
			   st.setVisited();
		   }
	   }
	   
	   public void setDist(int a) {
		   curEdge.setDist(a);
		   this.repaint();
	   }
	   //
	   @Override
	   public void paint(Graphics g) {
	      g.setColor(Color.DARK_GRAY);
	      Font fo=new Font("¸¼Àº °íµñ", Font.BOLD, 20);
	      g.setFont(fo);
	      
	      if(ondrawing == true) {
	         g.setColor(Color.GRAY);
	         g.drawLine(px, py, dx, dy);
	      }
	      ArrayList<Edge> Elist = gr.getElist();
	      for(int k=0; k<Elist.size(); k++) {
	         Edge e = Elist.get(k);
	         Node n1 = e.getN1();
	         Node n2 = e.getN2();
	         int x1 = n1.getX();
	         int y1 = n1.getY();
	         int x2 = n2.getX();
	         int y2 = n2.getY();
	         if(e!=null) {
	            g.drawLine(x1, y1, x2, y2);
	            int x = x1+(x2 - x1)/2;
	            int y = y1+(y2 - y1)/2;
	            String dd = Integer.toString(e.getDist());
	            g.drawString(dd, x, y);
	         }
	      }
	      ArrayList<Node> Nlist = gr.getNlist();
	      for(int k=0; k<Nlist.size(); k++) {
	         Node n = Nlist.get(k);
	         n.paint(g);
	         nx = n.getX();
	         ny = n.getY();
	         g.setColor(Color.black);
	         String no = Integer.toString(k+1);
	         g.drawString(no, nx-10, ny+5);
	      }
	      if(write == "yes") {
	    	  String answer = Integer.toString(end.getPath());
	         g.drawString("Shortest Path = "+answer, 200, 500);
	      }
	   }   
	}