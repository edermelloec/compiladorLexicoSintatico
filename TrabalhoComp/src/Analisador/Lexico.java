/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisador;

/**
 *
 * @author Eder
 */
public class Lexico {

    int i = 1;

    public Token analisadorLexico(String prog) {
        Token token;
        String palavra = "";
        int nLinha=1;
        
        while (!prog.substring(i, i + 1).equals(" ") && i < prog.length() - 1) {
            
            //numero das linha
            
            //tratamento de comentario
            if (prog.substring(i, i + 1).equals("{")) {
                while (!prog.substring(i, i + 1).equals("}") && i < prog.length() - 1) {
                    i++;
                }
                //compensar espaco inicio da linha
                i=i+2;
                
            }
            palavra = palavra + prog.substring(i, i + 1);
            i++;
            
            
        }
        
        if (i < prog.length() - 1) {
            //compensar espaco inicio da linha
            i++;
        } else {
            palavra = palavra + prog.substring(i, i + 1);
        }
        token = new Token();
        if (palavra.equals("program")) {
            token.setTipo("reservada");
            token.setValor("program");
        } else if (palavra.equals("begin")) {
            token.setTipo("reservada");
            token.setValor("begin");
        } else if (palavra.equals("var")) {
            token.setTipo("reservada");
            token.setValor("var");
        } else if (palavra.equals("read")) {
            token.setTipo("reservada");
            token.setValor("read");
        } else if (palavra.equals("write")) {
            token.setTipo("reservada");
            token.setValor("write");
        } else if (palavra.equals("while")) {
            token.setTipo("reservada");
            token.setValor("while");
        } else if (palavra.equals("do")) {
            token.setTipo("reservada");
            token.setValor("do");
        } else if (palavra.equals("if")) {
            token.setTipo("reservada");
            token.setValor("if");
        } else if (palavra.equals("else")) {
            token.setTipo("reservada");
            token.setValor("else");
        } else if (palavra.equals("then")) {
            token.setTipo("reservada");
            token.setValor("then");
        } else if (palavra.equals("end")) {
            token.setTipo("reservada");
            token.setValor("end");
        }else if (palavra.equals("=")) {
            token.setTipo("reservada");
            token.setValor("=");
        } else if (palavra.equals(":=")) {
            token.setTipo("reservada");
            token.setValor(":=");
        }else if (palavra.equals("<>")) {
            token.setTipo("reservada");
            token.setValor("<>");
        } else if (palavra.equals(">=")) {
            token.setTipo("reservada");
            token.setValor(">=");
        } else if (palavra.equals("<=")) {
            token.setTipo("reservada");
            token.setValor("<=");
        } else if (palavra.equals(">")) {
            token.setTipo("reservada");
            token.setValor(">");
        } else if (palavra.equals("<")) {
            token.setTipo("reservada");
            token.setValor("<");
        } else if (palavra.equals("+")) {
            token.setTipo("reservada");
            token.setValor("+");
        } else if (palavra.equals("-")) {
            token.setTipo("reservada");
            token.setValor("-");
        } else if (palavra.equals("*")) {
            token.setTipo("reservada");
            token.setValor("*");
        } else if (palavra.equals("/")) {
            token.setTipo("reservada");
            token.setValor("/");
        } else if (palavra.equals("integer")) {
            token.setTipo("reservada");
            token.setValor("integer");
        } else if (palavra.equals("real")) {
            token.setTipo("reservada");
            token.setValor("real");
        } else if (palavra.equals(";")) {
            token.setTipo("reservada");
            token.setValor(";");
        } else if (palavra.equals(",")) {
            token.setTipo("reservada");
            token.setValor(",");
        }else if (palavra.equals(":")) {
            token.setTipo("reservada");
            token.setValor(":");
        }else if (palavra.equals(".")) {
            token.setTipo("reservada");
            token.setValor(".");
        }else  {
            token.setTipo("id");
            token.setValor(palavra);
        }
        
        return token;

    }
}
