/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.SwitchCase;

import InterfazGrafica.VentanaPrincipal;
import TabladeSimbolos.ReporteTabla;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.Nodo;
import arbol.SwitchCase.NodoCase;
import arbol.TablaDeSimbolos;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class SwitchCase extends Instruccion{
    /**
     * ListaDeCase es una lista que contiene elementos de tipo NodoCase que cada uno contiene una expresion y una lista de instrucciones,
     * esta expresion antes mencionada es la encargada de ser comparada con la expresion del switch.
     */
    private LinkedList<NodoCase> ListaDeCase;
    /**
     * Parametro expComp es la expresion que trae el switch y es la que se va a comparar con cada caso
     */
    private Expresion expComp;

    public SwitchCase(LinkedList<NodoCase> ListaDeCase, Expresion expComp) {
        this.ListaDeCase = ListaDeCase;
        this.expComp = expComp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        for(NodoCase nc:ListaDeCase){
            if(nc.getExp()!=null){
                if(expComp.ejecutar(ts).equals(nc.getExp().ejecutar(ts))){
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for (Nodo in:nc.getInstruccionesDeCase()){
                        if(in instanceof Instruccion){
                            ((Instruccion) in).ejecutar(tablalocal);
                        }else if(in instanceof Break){   
                            System.out.println(((Break) in).getTipoInstruccion());
                            return null;
                        }else if(in instanceof Expresion){
                            ((Expresion) in).GetTipo(tablalocal);
                            return ((Expresion) in).ejecutar(tablalocal);
                        } else{
                    ErrorARIT e=new ErrorARIT("Semantico", in.toString(), "error de tipo de instruccion", 0, 0);
                    ListaErrores.Add(e);
                        }
                    }
                                VentanaPrincipal.RTS.add(new ReporteTabla(tablalocal, VentanaPrincipal.ambito++));
                }

            }else{
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for (Nodo in:nc.getInstruccionesDeCase()){
                    if(in instanceof Instruccion){
                        ((Instruccion) in).ejecutar(tablalocal);
                    }else if(in instanceof Break){   
                        System.out.println(((Break) in).getTipoInstruccion());
                        return null;
                    }else if (in instanceof Expresion){
                        ((Expresion) in).GetTipo(tablalocal);
                        return ((Expresion) in).ejecutar(tablalocal);
                    }else{
                    ErrorARIT e=new ErrorARIT("Semantico", in.toString(), "error de tipo de instruccion", 0, 0);
                    ListaErrores.Add(e);
                    }  
                }
                                VentanaPrincipal.RTS.add(new ReporteTabla(tablalocal, VentanaPrincipal.ambito++));
            }

        }
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Switch-case\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        for (NodoCase nodoCase : ListaDeCase) {
                
            cont = nodoCase.Dibujar(builder, nodo, cont);
                }           
        
        return cont;
    }
    
}
