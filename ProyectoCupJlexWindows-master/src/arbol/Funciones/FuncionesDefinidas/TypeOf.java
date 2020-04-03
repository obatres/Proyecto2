/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import InterfazGrafica.VentanaPrincipal;
import arbol.Expresion;
import arbol.Imprimir;
import arbol.Instruccion;

import arbol.TablaDeSimbolos;
import arbol.Tipo;

/**
 *
 * @author obatres_
 */
public class TypeOf extends Expresion{
    private Expresion exp;
    Tipo t;
    public TypeOf(Expresion exp) {
        this.exp = exp;
    }
    
    


    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        t = exp.GetTipo(ts);
        System.out.println(t.tp);
        return t.tp;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Lneght\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        cont=exp.Dibujar(builder, nodoOp, cont);
        return cont;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
       return exp.GetTipo(ts);
    }
    
}
