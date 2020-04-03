/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Retorno;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;

/**
 *
 * @author obatres_
 */
public class Retorno extends Expresion{
    
    private Expresion exp;

    public Retorno(Expresion exp) {
        this.exp = exp;
    }

    public Retorno() {
        this.exp=null;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(exp!=null){
            return exp.ejecutar(ts);            
        }else{
            return 0;
        }
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Retorno\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "VALOR" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        cont=exp.Dibujar(builder, nodoOp, cont);
        return cont;
    }


    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if(exp!=null){
            return exp.GetTipo(ts);
        }else{
            return null;
        }
    }
    
}
