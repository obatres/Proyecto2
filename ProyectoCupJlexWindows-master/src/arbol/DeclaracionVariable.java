/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import InterfazGrafica.VentanaPrincipal;
import TabladeSimbolos.ReporteTabla;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Funciones.FuncionesDefinidas.C;
import arbol.Funciones.FuncionesDefinidas.Lista;
import arbol.Funciones.LlamadaFuncionExp;
import arbol.Sentencias.Llamada;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class DeclaracionVariable extends Instruccion {
    private Expresion exp;
    private String identificador;
    
    public static ArrayList<Object> Vector = new ArrayList<>();
    ArrayList<Object> Valor = new ArrayList<>();
    
    public DeclaracionVariable(Expresion exp, String identificador) {
        this.exp = exp;
        this.identificador = identificador;
    }
    
    
            
           
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        VentanaPrincipal.RTS.add(new ReporteTabla(ts, VentanaPrincipal.ambito++));
        Object salVec = exp.ejecutar(ts);
        if(exp instanceof Lista){
            // <editor-fold desc="LISTA">>
            for (Object object : (ArrayList)salVec) {
                Valor.add(object);
            }
            // </editor-fold>
        }else{
            if(exp instanceof C){
                // <editor-fold desc="FUNCION C">> 
                if(exp.GetTipo(ts).isBoolean()){
                    // <editor-fold desc="VECTOR BOOLEANO">> 
                    for (Object o : Vector) {
                        Valor.add(((Expresion)o).ejecutar(ts));
                    }
                    // </editor-fold>
                }else if(exp.GetTipo(ts).isInt()){
                    // <editor-fold desc="VECTOR INT">> 
                    for (Object o : Vector) {
                        if(((Expresion)o).GetTipo(ts).isBoolean()){
                            if(((Expresion)o).ejecutar(ts).equals("true")){
                                Valor.add("1");
                            }else if(((Expresion)o).ejecutar(ts).equals("false")){
                                Valor.add("0");
                            }
                        }else{
                            Valor.add(((Expresion)o).ejecutar(ts));
                        }
                    }
                    // </editor-fold>
                }else if(exp.GetTipo(ts).isDouble()){
                    // <editor-fold desc="VECTOR DOUBLE">> 
                    for (Object o : Vector) {
                        if(((Expresion)o).GetTipo(ts).isBoolean()){
                            if(((Expresion)o).ejecutar(ts).equals("true")){
                                Valor.add("1.0");
                            }else if(((Expresion)o).ejecutar(ts).equals("false")){
                                Valor.add("0.0");
                            }
                        }else if (((Expresion)o).GetTipo(ts).isInt()){
                            Valor.add(Double.parseDouble(((Expresion)o).ejecutar(ts).toString()));
                        }else{
                            Valor.add(((Expresion)o).ejecutar(ts));
                        }
                    }   
                    // </editor-fold>
                }else if(exp.GetTipo(ts).isString()){
                    // <editor-fold desc="VECTOR STRING">> 
                    for (Object o : Vector) {
                        if(((Expresion)o).GetTipo(ts).isBoolean()){
                            if(((Expresion)o).ejecutar(ts).equals("true")){
                                Valor.add("true");
                            }else if(((Expresion)o).ejecutar(ts).equals("false")){
                                Valor.add("false");
                            }
                        }else if(((Expresion)o).GetTipo(ts).isInt()){
                            Valor.add(((Expresion)o).ejecutar(ts).toString());
                        }else if(((Expresion)o).GetTipo(ts).isDouble()){
                            Valor.add(((Expresion)o).ejecutar(ts).toString());
                        }else if(((Expresion)o).GetTipo(ts).isString()){
                            Valor.add(((Expresion)o).ejecutar(ts));
                        }
                    } 
                    // </editor-fold>
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", salVec.toString(), "error de tipo en el vector", exp.linea, exp.columna);
                    ListaErrores.Add(e); 
                }
                // </editor-fold>
            }else if(exp instanceof Single){
                Valor.add(0, salVec);
            }else if(exp instanceof LlamadaFuncionExp){
                Valor.add(0,salVec);
            }else{
                Valor.add(0, salVec);
            }   
        }

        if(ts.Existe(identificador)){
            ts.setValor(identificador, Valor, exp.GetTipo(ts));            
        }else{
            ts.add(new Simbolo(identificador,exp.GetTipo(ts)));
            ts.setValor(identificador, Valor, exp.GetTipo(ts));
        }   

        Vector.clear(); 
        VentanaPrincipal.RTS.add(new ReporteTabla(ts, VentanaPrincipal.ambito));
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {

        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Asignacion\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + identificador + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        

        String nodoVal = "nodo"+ ++cont;
        builder.append(nodoVal).append(" [label=\"Valor\"];\n");
        builder.append(nodo).append(" -> ").append(nodoVal).append(";\n");           
        
        
        cont = exp.Dibujar(builder, nodoVal, cont);            
 


        return cont; 
    }
    
}
