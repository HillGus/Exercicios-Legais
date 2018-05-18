package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Triangulos {

	private int[][] coords = new int[3][2];
	private int tempX, tempY, tempX1, tempY1;
	private int i = 0;
	
	public Triangulos() {
		
		JFrame frame = newFrame();
		
		JLabel lbl = newLabel(frame);
		
		frame.add(lbl);
		
		frame.setVisible(true);
	}
	
	public JFrame newFrame() {
		
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		return frame;
	}
	
	public JLabel newLabel(JFrame frame) {
		
		JLabel lbl = new JLabel();
		lbl.setBounds(0, 0, 10000, 10000);
		
		lbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {

				coords[i][0] = e.getX();
				coords[i][1] = e.getY();
				
				Graphics2D g = (Graphics2D) lbl.getGraphics();
				g.setStroke(new BasicStroke(5));
				
				if (i == 0) {
					tempX = e.getX();
					tempY = e.getY();
				}
				
				if (i == 1) {
					
					g.drawLine(coords[0][0], coords[0][1], coords[1][0], coords[1][1]);
				} else if (i == 2) {
					
					g.drawLine(coords[1][0], coords[1][1], coords[2][0], coords[2][1]);
					g.drawLine(coords[0][0], coords[0][1], coords[2][0], coords[2][1]);
					
					i = -1;
					
					JOptionPane.showMessageDialog(null, descobrirTipo());
				}
				
				i++;
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		lbl.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
				Graphics2D g = (Graphics2D) lbl.getGraphics();
				g.setStroke(new BasicStroke(5));
				
				if (i > 0) {			
					
					tempX1 = tempX;
					tempY1 = tempY;
					
					g.setColor(frame.getContentPane().getBackground());
					g.drawLine(coords[i-1][0], coords[i-1][1], tempX1, tempY1);
				
					tempX = e.getX();
					tempY = e.getY();
					
					g.setColor(Color.BLACK);
					g.drawLine(coords[i-1][0], coords[i-1][1], tempX, tempY);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return lbl;
	}
	
	public double getSize(int i, int i2) {
		
		int x1 = coords[i][0], y1 = coords[i][1];
		int x2 = coords[i2][0], y2 = coords[i2][1];
		
		int v1 = x1 > x2 ? x1 - x2 : x2 - x1;
		int v2 = y1 > y2 ? y1 - y2 : y2 - y1;
		
		return Math.hypot((double)v1, (double)v2);
	}

	public String descobrirTipo() {
		
		String tipo = "";
		
		double l1 = getSize(0, 1), l2 = getSize(1, 2), l3 = getSize(0, 2);
		int margemDeErro = 3;
		
		if ((l1 < l2 + l3) && (l2 < l1 + l3) && (l3 < l1 + l2)) {
			
			if (((l1 < l2 + margemDeErro) && (l1 > l2 - margemDeErro)) && 
			   ((l1 < l3 + margemDeErro) && (l1 > l3 - margemDeErro))) {
				tipo = "Esse triângulo é equilátero";
			} else if ((((l1 < l2 + margemDeErro) && (l1 > l2 - margemDeErro)) && ((l1 > l3 + margemDeErro) || (l1 < l3 - margemDeErro))) || 
					  (((l1 < 13 + margemDeErro) && (l1 > l3 - margemDeErro)) && ((l2 > l3 + margemDeErro) || (l2 < 13 - margemDeErro))) || 
					  (((l2 < l3 + margemDeErro) && (l2 > l3 - margemDeErro)) && ((l2 > l1 + margemDeErro) || (l2 < l1 - margemDeErro)))) {
				tipo = "Esse triângulo é isósceles";
			} else {
				tipo = "Esse triângulo é escaleno";
			}
		} else {
			tipo = "Isso não é um triângulo";
		}
		
		return tipo;
	}
}
