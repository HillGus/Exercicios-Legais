package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Velocidade {

	private int[][] coords = new int[2][2];
	private long[] tempos = new long[2];
	private int i = 0;
	
	public Velocidade() {
		
		JFrame frame = newFrame();
		
		JLabel lbl = newLabel();
		
		frame.add(lbl);
		
		frame.setVisible(true);
	}
	
	public JFrame newFrame() {
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		return frame;
	}
	
	public JLabel newLabel() {
		
		JLabel lbl = new JLabel();
		lbl.setBounds(0, 0, 10000, 10000);
		
		lbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
							
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
				
				Graphics2D g = (Graphics2D) lbl.getGraphics();
				
				g.setColor(Color.GREEN);
				g.fillOval(e.getX() -10, e.getY() - 10, 20, 20);
				
				coords[i][0] = e.getX();
				coords[i][1] = e.getY();
				tempos[i] = getTime();
				
				if (i == 1) {
					
					g.setStroke(new BasicStroke(3));
					g.drawLine(coords[0][0], coords[0][1], coords[1][0], coords[1][1]);
					
					String info = "A velocidade foi de " + String.format("%.2f", calcularVelocidade()) + "p/s";
					
					JOptionPane.showMessageDialog(null, info);
					
					i = -1;
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

	public double calcularVelocidade() {
		
		long t1 = tempos[0], t2 = tempos[1];
		
		int dif = (int) (t2 - t1);
		
		return getDistance() / dif * 1000;
	}
	
	public double getDistance() {
		
		int x1 = coords[0][0], x2 = coords[1][0], y1 = coords[0][1], y2 = coords[1][1];
		int v1 = x1 > x2 ? x1 - x2 : x2 - x1;
		int v2 = y1 > y2 ? y1 - y2 : y2 - y1;
		
		return Math.hypot(v1, v2);
	}
	
	public long getTime() {
		
		return Calendar.getInstance().getTimeInMillis();
	}
}
