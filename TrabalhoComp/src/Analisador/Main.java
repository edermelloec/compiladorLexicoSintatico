/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analisador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eder
 */
public class Main {
    public static void main(String[] args) {
        LerPrograma lerPrograma = new LerPrograma();
        FileReader arq;
        BufferedReader lerArq = null;
        String prog="";
        try {
            //transformando todo arquivo em uma String para fazer analise
            arq = new FileReader("prog.txt");
            lerArq = new BufferedReader(arq);
            prog = lerPrograma.lerPrograma(lerArq);
        } catch (FileNotFoundException ex) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Lexico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Sintatico sintatico = new Sintatico();
        sintatico.sintatico(prog);
        
    }
}
