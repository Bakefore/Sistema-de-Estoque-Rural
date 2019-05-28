package app;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import business.EstadoBusiness;
import business.FuncionarioBusiness;
import controller.ControleDesktop;
import controller.ControleEntrar;
import controller.ControleMensagem;
import dao.FuncionarioDAO;
import model.CampoVazioException;
import model.Estado;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import view.DesktopPrincipal;
import view.Entrar;
import view.Mensagem;

public class App {
	public static Funcionario funcionarioLogado;
	public static void main(String[] args) {
		DesktopPrincipal desktopPrincipal = new DesktopPrincipal();
		ControleDesktop controleDesktop = new ControleDesktop(desktopPrincipal);	
						
		try {
			System.setProperty("file.encoding","UTF-8");
			Field charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null,null);
		} catch (IllegalArgumentException e) {
			Mensagem mensagem = new Mensagem("Impossível definir charset padrão para UTF-8 (IllegalArgumentException)",550);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (IllegalAccessException e) {
			Mensagem mensagem = new Mensagem("Impossível definir charset padrão para UTF-8 (IllegalAccessException)",550);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (NoSuchFieldException e) {
			Mensagem mensagem = new Mensagem("Impossível definir charset padrão para UTF-8 (NoSuchFieldException)",550);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (SecurityException e) {
			Mensagem mensagem = new Mensagem("Impossível definir charset padrão para UTF-8 (SecurityException)",550);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}		
	}
}
