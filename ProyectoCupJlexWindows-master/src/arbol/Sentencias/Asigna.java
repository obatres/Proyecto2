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
public class Asigna extends Nodo{

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }
    private String identificador;
    private Expresion exp;

    public Asigna(String identificador, Expresion exp) {
        this.identificador = identificador;
        this.exp = exp;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Asignacion\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + identificador + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        String nodoOp1 = "nodo" + ++cont;
        builder.append(nodoOp1).append(" [label=\"" + "Valor" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp1).append(";\n");
        
        
        cont=exp.Dibujar(builder, nodoOp1, cont);
        return cont;
    }
    

 
    
    
    
}
