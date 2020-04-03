/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Funciones.Parametro;
import arbol.Nodo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class DefFuncP extends Nodo{

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
        private ArrayList<Parametro> ParametrosFuncion;
    
        private LinkedList<Nodo> InstruccionesFuncion;

    public DefFuncP(ArrayList<Parametro> ParametrosFuncion, LinkedList<Nodo> InstruccionesFuncion) {
        this.ParametrosFuncion = ParametrosFuncion;
        this.InstruccionesFuncion = InstruccionesFuncion;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"DefFuncP\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        String nodo1 = "nodo" + ++cont;
        builder.append(nodo1).append(" [label=\"Instrucciones\"];\n");
        builder.append(nodo).append(" -> ").append(nodo1).append(";\n");
        
        for (Nodo nodop : InstruccionesFuncion) {
           cont=nodop.Dibujar(builder, nodo1, cont);
        }
        
        String nodoP = "nodo" + ++cont;
        builder.append(nodoP).append(" [label=\"Parametros\"];\n");
        builder.append(nodo).append(" -> ").append(nodoP).append(";\n");
        
        for (Nodo nodop : ParametrosFuncion) {
           cont=nodop.Dibujar(builder, nodoP, cont);
        }

        return cont;
    }
        
        
}
