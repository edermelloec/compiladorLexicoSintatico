/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analisador;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Eder
 */
public class LerPrograma {
    
    public String lerPrograma(BufferedReader lerArq) throws IOException{
        String programa = "";
        
        while(lerArq.ready()){
            programa = programa  +" "+ lerArq.readLine();
        }
        
        return programa;
        
    }

    
}
