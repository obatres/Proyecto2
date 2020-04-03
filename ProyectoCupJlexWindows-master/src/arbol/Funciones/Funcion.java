/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;


import arbol.Expresion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class Funcion extends Nodo{

    /**
     * @return the identificadorFuncion
     */
    public String getIdentificadorFuncion() {
        return identificadorFuncion;
    }

    /**
     * @return the ParametrosFuncion
     */
    public ArrayList<Parametro> getParametrosFuncion() {
        return ParametrosFuncion;
    }

    /**
     * @return the InstruccionesFuncion
     */
    public LinkedList<Nodo> getInstruccionesFuncion() {
        return InstruccionesFuncion;
    }

    /**
     * @return the ValordeRetorno
     */
    public Expresion getValordeRetorno() {
        return ValordeRetorno;
    }
    
    private String identificadorFuncion;
    
    private ArrayList<Parametro> ParametrosFuncion;
    
    private LinkedList<Nodo> InstruccionesFuncion;
    
    private Expresion ValordeRetorno;

    public Funcion(String identificadorFuncion, ArrayList<Parametro> ParametrosFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.identificadorFuncion = identificadorFuncion;
        this.ParametrosFuncion = ParametrosFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }
    
    public Funcion(String identificadorFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.identificadorFuncion = identificadorFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Funcion\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        String nodo1 = "nodo" + ++cont;
        builder.append(nodo1).append(" [label=\""+identificadorFuncion+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodo1).append(";\n");
        if(ParametrosFuncion!=null){
            String nodoP = "nodo" + ++cont;
            builder.append(nodoP).append(" [label=\""+"Parametros"+"\"];\n");
            builder.append(nodo).append(" -> ").append(nodoP).append(";\n");
            for (Parametro parametro : ParametrosFuncion) {
                cont=parametro.Dibujar(builder, nodoP, cont);
            }            
        }

        
        String nodoI = "nodo" + ++cont;
        builder.append(nodoI).append(" [label=\""+"instrucciones"+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodoI).append(";\n");
        for (Nodo nodop : InstruccionesFuncion) {
           cont=nodop.Dibujar(builder, nodoI, cont);
        }

        return cont;
    }

}
