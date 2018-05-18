package Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Velocidade {

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
		
		return lbl;
	}
	
}
