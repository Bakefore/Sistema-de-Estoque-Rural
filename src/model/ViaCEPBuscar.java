package model;

import java.util.Scanner;

import view.Mensagem;

/**
 * Testa a classe ViaCEP
 * @author Pablo Alexander da Rocha Gonçalves
 */
public class ViaCEPBuscar implements ViaCEPEvents {

    /**
     * @param args the command line arguments
     */

    public ViaCEP run(String cep) {
        ViaCEP viaCEP = new ViaCEP(this);
        try {
            viaCEP.buscar(cep);
        } catch (ViaCEPException ex) {}
        
        return viaCEP;
    }

    public void onCEPSuccess(ViaCEP cep) {}

    public void onCEPError(String cep) {}
}
