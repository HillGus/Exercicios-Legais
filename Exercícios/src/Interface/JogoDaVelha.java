package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class JogoDaVelha {

	JFrame frame;
	JButton[][] btn = new JButton[3][3];
	JLabel[][] lbl = new JLabel[3][3];
	int[][] jogo = new int[3][3];
	int jogador = 0;

	public JogoDaVelha() {

		// Configura frame
		frame = new JFrame();
		frame.setSize(295, 320);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);

		// Iniciando botões
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {

				btn[x][y] = new JButton();
				lbl[x][y] = new JLabel();

				JButton button = btn[x][y];
				JLabel label = lbl[x][y];

				button.setBounds((x + 1) * 5 + x * 90, (y + 1) * 5 + y * 90, 90, 90);
				label.setBounds((x + 1) * 5 + x * 90, (y + 1) * 5 + y * 90, 90, 90);

				label.setOpaque(true);
				label.setBackground(Color.WHITE);
				label.setFont(new Font("Arial", Font.PLAIN, 50));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				
				button.addActionListener(new Listener(x, y) {

					@Override
					public void actionPerformed(ActionEvent e) {

						button.setVisible(false);
						label.setVisible(true);

						label.setText(jogador % 2 == 0 ? "X" : "O");

						jogo[x][y] = (jogador % 2) + 1;
						
						int ganhador = verificar();
						
						if (ganhador != 0) {
							
							if (ganhador == 1) {
								
								JOptionPane.showMessageDialog(null, "X ganhou");								
							} else {
								
								JOptionPane.showMessageDialog(null, "O ganhou");
							}
							
							System.exit(0);
						}
						
						jogador++;
					}
				});

				frame.add(button);
				frame.add(label);
			}
		}

		// Exibe frame
		frame.setVisible(true);
	}

	public int verificar() {

		int ganhador = 0;

		for (int k = 0; k < 3; k++) {

			// Verificando por coluna
			if ((jogo[0][k] == jogo[1][k]) && (jogo[0][k] == jogo[2][k])) {
				ganhador = jogo[0][k];
				break;
			}
			
			// Verificando por linha
			if ((jogo[k][0] == jogo[k][1]) && (jogo[k][0] == jogo[k][2])) {
				ganhador = jogo[k][0];
				break;
			}
		}

		// Verificando pela diagonal esquerda -> direita
		if ((jogo[0][0] == jogo[1][1]) && (jogo[0][0] == jogo[2][2])) {
			ganhador = jogo[0][0];
		}

		// Verificando pela diagonal direita -> esquerda
		if ((jogo[0][2] == jogo[1][1]) && (jogo[0][2] == jogo[2][0])) {
			ganhador = jogo[0][2];
		}

		return ganhador;
	}
}
