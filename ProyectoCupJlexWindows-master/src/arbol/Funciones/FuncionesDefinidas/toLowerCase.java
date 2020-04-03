/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

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
public class toLowerCase extends Expresion{
    
    private Expresion exp;
    String cadena;
    public toLowerCase(Expresion exp) {
        this.exp = exp;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {

        if(exp.GetTipo(ts).isString()){
            if(exp.ejecutar(ts) instanceof ArrayList){
                cadena=((Expresion)((ArrayList)exp.ejecutar(ts)).get(0)).ejecutar(ts).toString();
            }else if(exp.ejecutar(ts) instanceof Single){
                cadena=((Expresion)exp.ejecutar(ts)).ejecutar(ts).toString();
            }else if(exp.ejecutar(ts) instanceof String){
                cadena=exp.ejecutar(ts).toString();
            }else{
                cadena="null";
                                    ErrorARIT e=new ErrorARIT("Semantico", exp.toString(), " tipo de parametro invalido, se usara NULL", 0, 0);
                    ListaErrores.Add(e);
            }
            //System.out.println(cadena.toLowerCase());
            return cadena.toLowerCase();
        }else{
            System.out.println("tipo de parametro invalido");
                                                ErrorARIT e=new ErrorARIT("Semantico", exp.toString(), " tipo de parametro invalido, se usara NULL", 0, 0);
                    ListaErrores.Add(e);
            
        }
        return null;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"toLowerCase\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        cont=exp.Dibujar(builder, nodoOp, cont);
        return cont;
    }
    
    
}
