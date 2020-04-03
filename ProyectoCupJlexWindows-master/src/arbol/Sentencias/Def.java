/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Expresion;
import arbol.Nodo;

/**
 *
 * @author obatres_
 */
public class Def extends Nodo{

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }
    private Expresion exp;

    public Def(Expresion exp) {
        this.exp = exp;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Def\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        cont = exp.Dibujar(builder, nodo, cont);
        return cont;
    }
    
    
}
