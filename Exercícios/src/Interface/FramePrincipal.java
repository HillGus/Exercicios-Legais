package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private int dimen = 500, raio = 200, pontos;
	
	private JLabel lbl;
	
	public FramePrincipal() {
		
		super("Desenho");
		
		setSize(516, 539);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl = new JLabel();
		lbl.setBounds(0, 0, 500, 500);
		
		add(lbl);
		
		setVisible(true);
	}
	
	private void desenharLinhas() {
		
		ArrayList<int[]> coords = addPontos(this.pontos);

		try {
			Thread.sleep(100);
		} catch (Exception e) {}
		
		Graphics2D g = (Graphics2D) lbl.getGraphics();
		
		g.setColor(getContentPane().getBackground());
		g.fillRect(0, 0, dimen, dimen);
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < coords.size(); i++) {
			
			int x1 = coords.get(i)[0];
			int y1 = coords.get(i)[1];
			
			g.fillOval(x1 - 2, y1 - 2, 5, 5);
			
			for (int j = i + 1; j < coords.size(); j++) {
				
				int x2 = coords.get(j)[0];
				int y2 = coords.get(j)[1];
				
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	private ArrayList<int[]> addPontos(int qnt) {
		
		ArrayList<int[]> coords = new ArrayList<>();
		
		double ang = 360f / qnt;
		
		for (int i = 0; i < qnt; i++) {
			
			int quad = (int) quadrante((i + 1) * ang)[0];
			double angulo = quadrante((i + 1) * ang)[1];
			
			int difX = getDif(quad, angulo)[0];
			int difY = getDif(quad, angulo)[1];
			
			coords.add(new int[] {dimen / 2 + difX, dimen / 2 + difY});
		}
		
		return coords;
	}
	
	private double[] quadrante(double ang) {
		
		int quad = 0;
		double a = 0;
		
		if (ang < 90) {
			
			quad = 1;
			a = ang;
		} else if (ang < 180) {
			
			quad = 2;
			a = ang - 90;
		} else if (ang < 270) {
			
			quad = 3;
			a = ang - 180;
		} else {
			
			quad = 4;
			a = ang - 270;
		}
		
		return new double[] {quad, a};
	}
	
	private int[] getDif(int quad, double ang) {
		
		switch (quad) {
		
			case 1: {
				
				return new int[] {getCO(raio, ang), getCA(raio, ang) * -1};
			}
			
			case 2: {
				
				return new int[] {getCA(raio, ang), getCO(raio, ang)};
			}
			
			case 3: {
				
				return new int[] {getCO(raio, ang) * -1, getCA(raio, ang)};
			}
			
			case 4: {
				
				return new int[] {getCA(raio, ang) * -1, getCO(raio, ang) * -1};
			}
		}
		
		return new int[] {0, 0};
	}
	
	private int getCO(int hypot, double ang) {
		
		return (int) (hypot * Math.sin(Math.toRadians(ang)));
	}
	
	private int getCA(int hypot, double ang) {
		
		return (int) (hypot * Math.cos(Math.toRadians(ang)));
	}

	public void setPontos(int qnt) {
		
		this.pontos = qnt;
		
		desenharLinhas();
	}
	
	public int getPontos() {
		
		return this.pontos;
	}
	
	public void setRaio(int raio) {
		
		this.raio = raio;
		
		desenharLinhas();
	}
	
	public int getRaio() {
		
		return this.raio;
	}
	
	public void setDimen(int dimen) {
		
		this.dimen = dimen;
		setSize(dimen + 16, dimen + 39);
	}
	
	public int getDimen() {
		
		return this.dimen;
	}
		
}