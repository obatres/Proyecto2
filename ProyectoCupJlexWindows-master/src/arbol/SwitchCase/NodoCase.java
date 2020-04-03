/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.SwitchCase;

import arbol.Expresion;
import arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class NodoCase  extends Nodo{


    
    private Expresion exp;
    private LinkedList<Nodo> InstruccionesDeCase;

    public NodoCase(Expresion exp, LinkedList<Nodo> InstruccionesDeCase) {
        this.exp = exp;
        this.InstruccionesDeCase = InstruccionesDeCase;
    }
    
    public NodoCase(LinkedList<Nodo> InstruccionesDeCase){
        this.InstruccionesDeCase = InstruccionesDeCase;
        this.exp=null;
    }
    
        /**
     * @return the InstruccionesDeCase
     */
    public LinkedList<Nodo> getInstruccionesDeCase() {
        return InstruccionesDeCase;
    }

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"CASE\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        String nodoE = "nodo" + ++cont;
        builder.append(nodoE).append(" [label=\""+exp+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodoE).append(";\n");

        String nodoI = "nodo" + ++cont;
        builder.append(nodoI).append(" [label=\"Instrucciones\"];\n");
        builder.append(nodo).append(" -> ").append(nodoI).append(";\n");        
        
        for (Nodo nodo1 : InstruccionesDeCase) {
            cont = nodo1.Dibujar(builder, nodoI, cont);
        }
        return cont;
    }
       
}
