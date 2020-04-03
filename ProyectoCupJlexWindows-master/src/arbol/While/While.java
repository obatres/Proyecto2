/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.While;

import InterfazGrafica.VentanaPrincipal;
import TabladeSimbolos.ReporteTabla;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.SwitchCase.Break;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class While extends Instruccion{
    
    private Expresion exp;
    private LinkedList<Nodo> ListaDeInstrucciones;

    public While(Expresion exp, LinkedList<Nodo> ListaDeInstrucciones) {
        this.exp = exp;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
    }

    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        while ((Boolean)exp.ejecutar(ts)) {
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for (Nodo en : ListaDeInstrucciones) {
                if(en instanceof Instruccion){
                    ((Instruccion) en).ejecutar(tablalocal);
                }else if(en instanceof Expresion){
                    ((Expresion) en).GetTipo(tablalocal);
                    return ((Expresion) en).ejecutar(tablalocal);
                }else if( en instanceof Break){
                    return null;
                }  else{
                    ErrorARIT e=new ErrorARIT("Semantico", en.toString(), "error de tipo de instruccion", 0, 0);
                    ListaErrores.Add(e);
                }
            } 
                            VentanaPrincipal.RTS.add(new ReporteTabla(tablalocal, VentanaPrincipal.ambito++));
        }
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"While\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        for (Nodo n : ListaDeInstrucciones) {
            if(n instanceof Instruccion){
                cont = ((Instruccion) n).Dibujar(builder, nodo, cont);   
            }
        }
        return cont;
    }
}
