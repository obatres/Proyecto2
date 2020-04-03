/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import InterfazGrafica.VentanaPrincipal;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Lenght  extends Expresion{
    private Expresion exp;
    
    boolean banderaVector=true;
    int contador=0;
    public Lenght(Expresion exp) {
        this.exp = exp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if( exp.ejecutar(ts) instanceof ArrayList){
            contador=((ArrayList)exp.ejecutar(ts)).size();
        }else{
                                ErrorARIT e=new ErrorARIT("Semantico", exp.ejecutar(ts).toString(), " Valor no aceptado en lenght", 0, 0);
                    ListaErrores.Add(e);  
        }
        System.out.println(contador);
        return contador;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Lenght\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        cont=exp.Dibujar(builder, nodoOp, cont);
        return cont;
    }
    
}
