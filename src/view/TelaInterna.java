package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class TelaInterna extends JInternalFrame{
	
	private DesktopPrincipal pertenceDesktop;
	private Dimension dimensaoTela;
	private Font fonteNormal, fonteNegrito;
	
	public TelaInterna(String titulo, int largura, int altura, boolean resizeable, boolean closeable, 
			boolean maximizable, Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		
		this.pertenceDesktop = pertenceDesktop;
		this.dimensaoTela = dimensaoTela;
		
		setTitle(titulo);
		setSize(largura, altura);
		setResizable(resizeable);
		setClosable(closeable);
		setMaximizable(maximizable);
		
				
		//Pega a dimensão atual da tela e centraliza a tela		
		setLocation((dimensaoTela.width - largura)/2, (dimensaoTela.height- altura)/2);
		
		fonteNormal = new Font("Arial", Font.ROMAN_BASELINE, 16);
		fonteNegrito = new Font("Arial", Font.ROMAN_BASELINE | Font.BOLD, 16);
	}

	public DesktopPrincipal getPertenceDesktop() {
		return pertenceDesktop;
	}

	public Dimension getDimensaoTela() {
		return dimensaoTela;
	}	
	
	public Font getFonteNormal(){
		return fonteNormal;
	}
	
	public Font getFonteNegrito(){
		return fonteNegrito;
	}
}
