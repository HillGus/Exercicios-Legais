package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Calculadora {

	public Calculadora() {
		
		//Janela
		JFrame frame = newFrame();
		
		//Visor
		JLabel visor = newVisor();
		frame.add(visor);
		
		//Botões de números
		adicionarBotoes(frame, visor);
		
		//Botão Calcular
		JButton btnCalc = newBtnCalcular(visor);
		frame.add(btnCalc);
		
		frame.setVisible(true);
	}
	
	private JFrame newFrame() {
		
		JFrame frame = new JFrame("Calculadora");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(290, 450);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		
		return frame;
	}
	
	private JLabel newVisor() {
		
		JLabel visor = new JLabel();
		visor.setBounds(15, 15, 255, 60);
		visor.setBackground(Color.WHITE);
		visor.setOpaque(true);
		visor.setFont(new Font("Arial", Font.PLAIN, 30));
		visor.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Border borda = visor.getBorder();
		Border novaBorda = new EmptyBorder(5, 15, 5, 15);
		visor.setBorder(new CompoundBorder(borda, novaBorda));
		
		return visor;
	}
	
	private JButton newBtnCalcular(JLabel visor) {
		
		JButton btn = new JButton("CALCULAR");
		btn.setFont(new Font("Arial", Font.PLAIN, 20));
		btn.setBounds(15, 345, 255, 60);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] partes = visor.getText().split(" ");
				double result = 0;
				
				switch (partes[1]) {
				
					case "+": {
						result = Double.parseDouble(partes[0]) + Double.parseDouble(partes[2]);
						break;
					}
					case "-": {
						result = Double.parseDouble(partes[0]) - Double.parseDouble(partes[2]);
						break;
					}
					case "*": {
						result = Double.parseDouble(partes[0]) * Double.parseDouble(partes[2]);
						break;
					}
					case "/": {
						result = Double.parseDouble(partes[0]) / Double.parseDouble(partes[2]);
						break;
					}
				}
				
				if (result == (int)result) {
					visor.setText(String.valueOf((int)result));
				} else {
					visor.setText(String.valueOf(result));
				}
				
			}
			
		});
		
		return btn;
	}
	
	private void adicionarBotoes(JFrame frame, JLabel visor) {
		
		JButton[][] num = new JButton[4][4];
		String sinais = " + , - , * , / ";
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				
				String txt = String.valueOf((j + 1) + i * 4);
				
				switch (Integer.parseInt(txt)) {

					case 14: {
						txt = "0";
						break;
					}
					default: {
						
						int div = Integer.parseInt(txt) % 4;
						if (div == 0) {
							txt = sinais.split(",")[(Integer.parseInt(txt) / 4) - 1];
							break;
						}
						
						txt = String.valueOf(Integer.parseInt(txt) - i * 1);
						if ((Integer.parseInt(txt) == 10) || (Integer.parseInt(txt) == 12)) {
							continue;
						}
					}
				}
				
				num[i][j] = new JButton(txt);
				num[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
				num[i][j].setBounds(15 + j * 65, 85 + i * 65, 60, 60);
				
				num[i][j].addActionListener(new Listener(txt) {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						for (int i = 0; i < 4; i++) {
							if ((visor.getText().contains(sinais.split(",")[i])) && (sinais.contains(txt))) {
								return;
							}
						}
						
						visor.setText(visor.getText() + txt);
						
					}
					
				});
				
				frame.add(num[i][j]);
			}
		}
	}

}