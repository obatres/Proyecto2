/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Expresion;
import arbol.Nodo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Llamada extends Nodo{

    /**
     * @return the ParametrosLlamada
     */
    public ArrayList<Expresion> getParametrosLlamada() {
        return ParametrosLlamada;
    }
    private ArrayList<Expresion> ParametrosLlamada;

    public Llamada(ArrayList<Expresion> ParametrosLlamada) {
        this.ParametrosLlamada = ParametrosLlamada;
    }

    public Llamada() {
        this.ParametrosLlamada = null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Llamada\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        if(ParametrosLlamada!=null){
          for (Expresion expresion : ParametrosLlamada) {
            cont=expresion.Dibujar(builder, nodo, cont);
        }          
        }

        return cont;
    }
    
    
}
