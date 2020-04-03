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
public class Remove extends Expresion
{
    private Expresion exp1;
    private Expresion exp2;

    String cadena1;
    String cadena2;
    
    
    public Remove(Expresion exp1, Expresion exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        
        if(exp1.GetTipo(ts).isString()&& exp2.GetTipo(ts).isString()){
            
            if(exp1.ejecutar(ts) instanceof ArrayList){
                if(((ArrayList)exp1.ejecutar(ts)).size()==1){
                    cadena1= ((Expresion)((ArrayList)exp1.ejecutar(ts)).get(0)).ejecutar(ts).toString();
                }
            }else if (exp1.ejecutar(ts) instanceof Single){
                cadena1=((Expresion)exp1.ejecutar(ts)).ejecutar(ts).toString();
            }else if(exp1.ejecutar(ts) instanceof String){
                cadena1=exp1.ejecutar(ts).toString();
            }else{
                cadena1="null";
                    ErrorARIT e=new ErrorARIT("Semantico", cadena1, " Valor no aceptado en length", 0, 0);
                    ListaErrores.Add(e);  
            }
            
            if(exp2.ejecutar(ts) instanceof ArrayList){
                if(((ArrayList)exp2.ejecutar(ts)).size()==1){
                    cadena2= ((Expresion)((ArrayList)exp2.ejecutar(ts)).get(0)).ejecutar(ts).toString();
                }                
            }else if (exp2.ejecutar(ts) instanceof Single){
                cadena2=((Expresion)exp2.ejecutar(ts)).ejecutar(ts).toString();
            }else if(exp2.ejecutar(ts) instanceof String){
                cadena2 = exp2.ejecutar(ts).toString();
            }else{
                cadena2="null";
                    ErrorARIT e=new ErrorARIT("Semantico", cadena2, " Valor no aceptado en length", 0, 0);
                    ListaErrores.Add(e);  
            }
            cadena1=cadena1.replaceAll(cadena2,"");
            System.out.println(cadena1);
            return cadena1;
        }else{
            System.out.println("los parametros no son String");
                                ErrorARIT e=new ErrorARIT("Semantico", cadena1.toString() + " "+ cadena2.toString(), " Los parametros no son String", 0, 0);
                    ListaErrores.Add(e);  
        }
        return null;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp1.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Lneght\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        
        cont=exp1.Dibujar(builder, nodoOp, cont);
        cont=exp2.Dibujar(builder, nodoOp, cont);
        return cont;
    }
    
    
           
}
