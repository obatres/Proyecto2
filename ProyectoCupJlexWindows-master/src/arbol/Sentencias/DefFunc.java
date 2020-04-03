/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class DefFunc extends Nodo {

    /**
     * @return the InstruccionesFuncion
     */
    public LinkedList<Nodo> getInstruccionesFuncion() {
        return InstruccionesFuncion;
    }
    
    private LinkedList<Nodo> InstruccionesFuncion;

    public DefFunc(LinkedList<Nodo> InstruccionesFuncion) {
        this.InstruccionesFuncion = InstruccionesFuncion;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"DefFunc\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        String nodo1 = "nodo" + ++cont;
        builder.append(nodo1).append(" [label=\"Instrucciones\"];\n");
        builder.append(nodo).append(" -> ").append(nodo1).append(";\n");
        
        for (Nodo nodop : InstruccionesFuncion) {
           cont=nodop.Dibujar(builder, nodo1, cont);
        }

        return cont;
    }

    
}
