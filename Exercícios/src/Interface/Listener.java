package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	int x, y;
	String txt;

	public Listener(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public Listener(String txt) {
		
		this.txt = txt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
