package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrameConfig extends JFrame {
	
	public FrameConfig(FramePrincipal princ) {
		
		super("Configurações");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(216, 119);
		setLocation((int) princ.getSize().getWidth() + (int) princ.getLocation().getX() + 15,
					(int) princ.getLocation().getY());
		
		JLabel lblPontos = new JLabel("Pontos");
		lblPontos.setBounds(15, 15, 50, 15);
		
		JSpinner spiPontos = new JSpinner();
		spiPontos.setBounds(80, 10, 105, 25);
		spiPontos.setValue(princ.getPontos());
		spiPontos.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				princ.setPontos((Integer) spiPontos.getValue());
			}
		});
		
		JLabel lblRaio = new JLabel("Raio");
		lblRaio.setBounds(15, 45, 50, 15);
		
		JSpinner spiRaio = new JSpinner();
		spiRaio.setBounds(80, 40, 105, 25);
		spiRaio.setValue(princ.getRaio());
		((SpinnerNumberModel) spiRaio.getModel()).setStepSize(2);
		spiRaio.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				princ.setRaio((Integer)spiRaio.getValue());
			}
		});
		
		add(lblPontos);
		add(lblRaio);
		add(spiPontos);
		add(spiRaio);
		
		setVisible(true);
	}
	
}
