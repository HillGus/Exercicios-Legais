package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TeoPit {

	private int[][] coords = new int[3][2];
	private int i = 0;
	
	JFrame frame;
	
	public TeoPit() {
		
		frame = newFrame();
		
		JLabel lbl = newLabel();
		
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
	
	public JLabel newLabel() {
		
		JLabel lbl = new JLabel();
		lbl.setBounds(0, 0, 2000, 2000);
		
		lbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				coords[i][0] = e.getX();
				coords[i][1] = e.getY();
				
				if (i == 1) {
					
					Graphics2D g = (Graphics2D) lbl.getGraphics();
					
					g.setStroke(new BasicStroke(3));
					
					g.drawLine(coords[0][0], coords[0][1], coords[1][0], coords[1][1]);
					
					desenharTeorema(g);
				}
				
				i++;
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		return lbl;
	}

	public void desenharTeorema(Graphics2D g) {
		
		int x = coords[0][0] > coords[1][0] ? coords[0][0] : coords[1][0];
		int y = coords[0][1] > coords[1][1] ? coords[0][1] : coords[1][1];
		
		coords[2][0] = x;
		coords[2][1] = y;
		
		g.drawLine(x, y, coords[0][0], coords[0][1]);
		g.drawLine(x, y, coords[1][0], coords[1][1]);
		
		double[] bounds = getValores();
		
		JLabel lbl = new JLabel(String.valueOf(bounds[0]));
		lbl.setLocation(coords[2][0], coords[2][1] + 15);
		lbl.setOpaque(true);
		lbl.setBackground(Color.BLACK);
		
		frame.add(lbl);
		
		frame.setVisible(false);
		frame.setVisible(true);
	}

	public double[] getValores() {
	
		double[] valores = new double[5];
		
		int x = coords[0][0] < coords[1][0] ? coords[0][0] : coords[0][1];
		int y = coords[0][1] < coords[1][1] ? coords[0][1] : coords[1][1];
		
		int x1 = coords[2][0], y1 = coords[2][1];
		
		int c1 = x > x1 ? x - x1 : x1 - x;
		int c2 = y > y1 ? y - y1 : y1 - y;
		
		valores[0] = c1;
		valores[1] = c2;
		valores[2] = Math.hypot(c1, c2);
		
		valores[3] = x;
		valores[4] = y;
		
		return valores;
	}
	
}
