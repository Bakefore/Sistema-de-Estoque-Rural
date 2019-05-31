package view;

import java.awt.Font;

import javax.swing.JFrame;

public class Tela extends JFrame{
	
	private Font fonteNormal, fonteNegrito;
	
	public Tela(String titulo, int largura, int altura, boolean resizeable) {
		setTitle(titulo);
		setSize(largura, altura);
		setResizable(resizeable);
		setLocationRelativeTo(null);
		
		fonteNormal = new Font("Arial", Font.ROMAN_BASELINE, 16);
		fonteNegrito = new Font("Arial", Font.ROMAN_BASELINE | Font.BOLD, 16);
	}
	
	public Font getFonteNormal(){
		return fonteNormal;
	}
	
	public Font getFonteNegrito(){
		return fonteNegrito;
	}
}
