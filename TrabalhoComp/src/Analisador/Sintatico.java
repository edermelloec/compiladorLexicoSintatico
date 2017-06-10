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
public class Sintatico {

    Lexico lexico = new Lexico();
    Token token = new Token();
    int numToken = 1;
    Erro erro = new Erro();
    Boolean e = false;
    int aux = 0;
    int contBegin = 0;

    public void sintatico(String prog) {

        do {

            if (e == false) {
                token = lexico.analisadorLexico(prog);

            } else {
                tokenChave(prog);
                numToken = 3;
            }

            //program
            if (numToken == 1) {
                if (token.getValor().equals("program")) {
                    token = lexico.analisadorLexico(prog);

                    if (token.tipo.equals("id")) {
                        token = lexico.analisadorLexico(prog);

                        if (token.getValor().equals(";")) {
                            //program declarado corretamente
                            token = lexico.analisadorLexico(prog);
                            numToken = 2;
                            System.out.println("program");
                        } else {
                            erro.erro("Esperava encontrar ponto e vingula");
                            e = true;
                        }
                    } else {
                        erro.erro("Esperava encontrar identificador");
                        e = true;
                    }
                } else {
                    erro.erro("Esperava encontrar program");
                    e = true;
                }
            }
            //declaracao de variaveis
            if (numToken == 2) {
                int maisVar = 0;
                //quando for mais de um VAR
                while (maisVar != 1) {
                    System.out.println(token.getValor());
                    if (token.getValor().equals("var")) {
                        int para = 0;

                        token = lexico.analisadorLexico(prog);
                        while (para != 1) {
                            if (token.getTipo().equals("id")) {
                                token = lexico.analisadorLexico(prog);

                                if (token.getValor().equals(":")) {//acabou as variaveis 
                                    para = 1;
                                } else if (token.getValor().equals(",")) {
                                    //declaracao de variaveis feita corretamente
                                    token = lexico.analisadorLexico(prog);

                                } else {

                                    para = 1;
                                    erro.erro("Esperava encontrar ,");
                                    e = true;
                                    break;
                                }
                            } else {
                                para = 1;
                                erro.erro("Esperava encontrar identificador");
                                e = true;
                                break;
                            }
                        }

                        if (token.getValor().equals(":")) {

                            token = lexico.analisadorLexico(prog);
                            //tipo_var
                            if (token.getValor().equals("integer") || token.getValor().equals("real")) {
                                token = lexico.analisadorLexico(prog);
                                if (token.getValor().equals(";")) {
                                    token = lexico.analisadorLexico(prog);
                                    if (token.getValor().equals("var")) {
                                        //entrou no if, tem mais var
                                    } else if (token.getValor().equals("begin")) {
                                        //entou no else if, acabou var comeca begin
                                        System.out.println("begin");
                                        contBegin++;
                                        token = lexico.analisadorLexico(prog);
                                        numToken = 3;
                                        maisVar = 1;

                                    } else {
                                        erro.erro("Esperava encontrar begin");
                                        e = true;
                                        break;
                                    }

                                } else {
                                    erro.erro("Esperava encontrar ;");
                                    e = true;
                                    break;
                                }
                            } else {
                                erro.erro("Esperava encontrar tipo_var");
                                e = true;
                                break;
                            }
                        } else {
                            erro.erro("Esperava encontrar :");
                            e = true;
                            break;
                        }

                    } else {
                        erro.erro("Esperava encontrar var");
                        e = true;
                        break;
                    }
                }
            }
            //CMD
            if (numToken == 3) {

                
                if (token.getValor().equals("begin")) {
                    System.out.println("begin");
                    contBegin++;
                } else if (token.getValor().equals("end")) {
                    contBegin--;
                    if (contBegin < 0) {
                        System.out.println("end sem par begin");
                    }

                }

                if (token.getValor().equals("read") || token.getValor().equals("write")) {
                    numToken = 4;
                    System.out.println("read");
                } else if (token.getValor().equals("while")) {
                    System.out.println("while");
                    numToken = 5;
                } else if (token.getValor().equals("if")) {
                    System.out.println("if");
                    numToken = 6;
                } else if (token.getTipo().equals("id")) {
                    System.out.println("id");
                    numToken = 7;
                } else if (token.getValor().equals("else")) {
                    System.out.println("else");
                    numToken = 3;
                }
            }

            if (numToken == 4) {
                //comandos
                //read & write
                int para = 0;

                if (token.getValor().equals("read") || token.getValor().equals("write")) {
                    token = lexico.analisadorLexico(prog);
                    if (token.getValor().equals("(")) {
                        token = lexico.analisadorLexico(prog);
                        //consumindo todas variaveis
                        while (para != 1) {
                            if (token.getTipo().equals("id")) {
                                token = lexico.analisadorLexico(prog);

                                if (token.getValor().equals(")")) {//acabou as variaveis 
                                    para = 1;
                                } else if (token.getValor().equals(",")) {
                                    //declaracao de variaveis feita corretamente
                                    token = lexico.analisadorLexico(prog);

                                } else {

                                    para = 1;
                                    erro.erro("Esperava encontrar ,");
                                    e = true;
                                }
                            } else {
                                para = 1;
                                erro.erro("Esperava encontrar variavel");
                                e = true;
                            }
                        }

                        if (token.getValor().equals(")")) {
                            token = lexico.analisadorLexico(prog);
                            if (token.getValor().equals(";")) {
                                numToken = 3;
                            } else {
                                erro.erro("Esperava encontrar ;");
                                e = true;
                            }
                        } else {
                            erro.erro("Esperava encontrar )");
                            e = true;
                        }
                    } else {
                        erro.erro("Esperava encontrar (");
                        e = true;
                    }
                }

            }
            //WHILE
            if (numToken == 5) {

                if (token.getValor().equals("while")) {
                    token = lexico.analisadorLexico(prog);
                    if (token.getTipo().equals("id")) {
                        token = lexico.analisadorLexico(prog);
                        //fazer metado pra resolver qual é
                        if (token.getValor().equals("=") || token.getValor().equals("<>") || token.getValor().equals(">=") || token.getValor().equals("<=") || token.getValor().equals(">") || token.getValor().equals("<")) {
                            token = lexico.analisadorLexico(prog);
                            if (token.getTipo().equals("id")) {
                                token = lexico.analisadorLexico(prog);
                                if (token.getValor().equals("do")) {
                                    numToken = 3;
                                    //CHAMAR COMANDO cmd

                                } else {
                                    erro.erro("Esperava encontrar do");
                                    e = true;
                                }
                            } else {
                                erro.erro("Esperava encontrar identificador");
                                e = true;
                            }
                        } else {
                            erro.erro("Esperava encontrar relacao");
                            e = true;
                        }
                    } else {
                        erro.erro("Esperava encontrar identificador");
                        e = true;
                    }
                } else {
                    erro.erro("Esperava encontrar while");
                    e = true;
                }
            }
            //IF
            if (numToken == 6) {

                if (token.getValor().equals("if")) {
                    token = lexico.analisadorLexico(prog);
                    int para = 0;
                    while (para != 1) {
                        if (token.getTipo().equals("id")) {
                            token = lexico.analisadorLexico(prog);
                            if (token.getValor().equals("then")) {
                                numToken = 3;
                                para = 1;
                                //CHAMAR COMANDOS cmd
                            } else if (token.getValor().equals("/") || token.getValor().equals("*") || token.getValor().equals("-") || token.getValor().equals("+") || token.getValor().equals("=") || token.getValor().equals("<>") || token.getValor().equals(">=") || token.getValor().equals("<=") || token.getValor().equals(">") || token.getValor().equals("<")) {
                                token = lexico.analisadorLexico(prog);

                            } else {
                                erro.erro("Esperava encontrar then / relacao");
                                e = true;
                                break;
                            }
                        } else {
                            erro.erro("Esperava encontrar identificador");
                            e = true;
                            break;
                        }
                    }

                } else {
                    erro.erro("Esperava encontrar if");
                    e = true;
                }
            }

            //atribuicao
            if (numToken == 7) {
                if (token.getTipo().equals("id")) {
                    token = lexico.analisadorLexico(prog);
                    if (token.getValor().equals(":=")) {
                        token = lexico.analisadorLexico(prog);
                        int para = 0;
                        while (para != 1) {
                            if (token.getTipo().equals("id")) {
                                token = lexico.analisadorLexico(prog);
                                if (token.getValor().equals(";")) {//acabou as variaveis 
                                    para = 1;
                                } else if (token.getValor().equals("+") || token.getValor().equals("-") || token.getValor().equals("*") || token.getValor().equals("/")) {
                                    //declaracao de variaveis feita corretamente
                                    token = lexico.analisadorLexico(prog);
                                } else {
                                    para = 1;
                                    erro.erro("Esperava encontrar op_ad ou op_mult");
                                    e = true;
                                    break;
                                }
                            } else {
                                para = 1;
                                erro.erro("Esperava encontrar variavel");
                                e = true;
                                break;
                            }
                        }

                        if (token.getValor().equals(";")) {
                            numToken = 3;
                        } else {
                            erro.erro("Esperava encontrar ;");
                            e = true;
                        }

                    } else {
                        erro.erro("Esperava encontrar atribuicao");
                        e = true;
                    }
                } else {
                    erro.erro("Esperava encontrar identificador");
                    e = true;
                }
            }

            if (token.valor.equals(".")) {
                aux = 1;
            }
        } while (aux != 1);

    }

    private void readWrite(String prog) {
//        if (token.getValor().equals("begin")) {
//                    token = lexico.analisadorLexico(prog);
//                    System.out.println("TEST: "+token.getValor());
        //comandos
        //read & write
        int para = 0;
        if (token.getValor().equals("read") || token.getValor().equals("write")) {
            token = lexico.analisadorLexico(prog);
            if (token.getValor().equals("(")) {
                token = lexico.analisadorLexico(prog);
                //consumindo todas variaveis
                while (para != 1) {
                    if (token.getTipo().equals("id")) {
                        token = lexico.analisadorLexico(prog);

                        if (token.getValor().equals(")")) {//acabou as variaveis 
                            para = 1;
                        } else if (token.getValor().equals(",")) {
                            //declaracao de variaveis feita corretamente
                            token = lexico.analisadorLexico(prog);

                        } else {

                            para = 1;
                            erro.erro("Esperava encontrar ,");
                            e = true;
                        }
                    } else {
                        para = 1;
                        erro.erro("Esperava encontrar variavel");
                        e = true;
                    }
                }

                if (token.getValor().equals(")")) {
                    token = lexico.analisadorLexico(prog);
                    if (token.getValor().equals(";")) {
                        token = lexico.analisadorLexico(prog);
                        //numToken++;
                    } else {
                        erro.erro("Esperava encontrar ;");
                        e = true;
                    }
                } else {
                    erro.erro("Esperava encontrar )");
                    e = true;
                }
            } else {
                erro.erro("Esperava encontrar (");
                e = true;
            }
        }

//                } else {
//                    erro.erro("Esperava encontrar begin");
//                    e = true;
//                }
    }

    private void tokenChave(String prog) {
        //token chave
        //read
        //write
        //while
        //if
        //begin
        //.
        //var
        do {
            token = lexico.analisadorLexico(prog);
            if (token.getValor().equals("read") || token.getValor().equals("write") || token.getValor().equals("while")
                    || token.getValor().equals("if") || token.getValor().equals("begin") || token.getValor().equals(".") || token.getValor().equals("var")) {
                e = false;
                break;
            }

            if (token.valor.equals(".")) {
                aux = 1;
            }
        } while (aux != 1);
    }

}
