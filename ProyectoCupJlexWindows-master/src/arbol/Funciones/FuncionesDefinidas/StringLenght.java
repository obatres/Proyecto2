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
import arbol.Simbolo;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class StringLenght extends Expresion{
    private Expresion exp;
    String cadena="";

    public StringLenght(Expresion exp) {
        this.exp = exp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        
        if (exp.GetTipo(ts).isString()){
            Object j;
            Object o = exp.ejecutar(ts);
            if (o instanceof ArrayList){
                String cadena2="";
                for (Object t : (ArrayList)o) {
                    if( t instanceof Expresion){
                        cadena2+=((Expresion)t).ejecutar(ts);                        
                    }else if(t instanceof String){
                        cadena2+=t;
                    }
                }
                cadena=cadena2;
            }else if (o instanceof Single){
                j=o;
                cadena=((Expresion)j).ejecutar(ts).toString();
            }else if (o instanceof String){
                cadena=o.toString();
            }else{
                System.out.println("Parametro incorrecto");
                                    ErrorARIT e=new ErrorARIT("Semantico", cadena.toString(), " Parametro incorrecto", 0, 0);
                    ListaErrores.Add(e); 
            }
            //System.out.println(cadena.length());
            return cadena.length();
        }else{
            System.out.println("Parametro incorrecto");
                                                ErrorARIT e=new ErrorARIT("Semantico", cadena.toString(), " Parametro incorrecto", 0, 0);
                    ListaErrores.Add(e);
            
        }
        
        return null;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if (exp.GetTipo(ts).isString()){
            return exp.GetTipo(ts);
        }else{
            return null;
        }   
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
    
}
