/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.If;

import InterfazGrafica.VentanaPrincipal;
import TabladeSimbolos.ReporteTabla;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.Operacion;
import arbol.SwitchCase.Break;
import arbol.TablaDeSimbolos;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class IfElse extends Instruccion{
    private final Operacion Operacion1;
    private final Operacion Operacion2;
    
    private final LinkedList<Nodo> ListaDeInstrucciones;
    
    private LinkedList<Nodo> ListaDeInstruccionesElse;

    public IfElse(Operacion Operacion1, Operacion Operacion2, LinkedList<Nodo> ListaDeInstrucciones, LinkedList<Nodo> ListaDeInstruccionesElse) {
        this.Operacion1 = Operacion1;
        this.Operacion2 = Operacion2;
        this.ListaDeInstrucciones = ListaDeInstrucciones;
        this.ListaDeInstruccionesElse = ListaDeInstruccionesElse;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if((Boolean)Operacion1.ejecutar(ts)){
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            for(Nodo in: ListaDeInstrucciones){
                if (in instanceof Instruccion){
                    ((Instruccion) in).ejecutar(tablalocal);
                }else if(in instanceof Expresion){
                    ((Expresion) in).GetTipo(tablalocal);
                    return ((Expresion) in).ejecutar(tablalocal);
                }else if (in instanceof Break){
                    return null;
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", in.toString(), "error de tipo de instruccion", 0, 0);
                    ListaErrores.Add(e);  
                }
            }
            VentanaPrincipal.RTS.add(new ReporteTabla(tablalocal, VentanaPrincipal.ambito++));
        }else if((Boolean)Operacion2.ejecutar(ts)){
            if(ListaDeInstruccionesElse!=null){
                TablaDeSimbolos tablalocal = new TablaDeSimbolos();
                tablalocal.addAll(ts);
                for(Nodo in: ListaDeInstruccionesElse){
                    if(in instanceof Instruccion){
                        ((Instruccion)in).ejecutar(tablalocal);
                    }else if(in instanceof Expresion){
                        ((Expresion) in).GetTipo(tablalocal);
                        return ((Expresion) in).ejecutar(tablalocal);
                    }else if(in instanceof Break){
                        return null;
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
        builder.append(nodo).append(" [label=\"If-Else\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoIf = "nodo" + ++cont;
        builder.append(nodoIf).append(" [label=\"If\"];\n");
        builder.append(nodo).append(" -> ").append(nodoIf).append(";\n");

        for(Nodo in: ListaDeInstrucciones){
            if (in instanceof Instruccion){
                cont = ((Instruccion)in).Dibujar(builder, nodoIf, cont);
            }
        }  
        
        if (ListaDeInstruccionesElse!=null){
        String nodoElse = "nodo" + ++cont;
        builder.append(nodoElse).append(" [label=\"Else\"];\n");
        builder.append(nodo).append(" -> ").append(nodoElse).append(";\n");


        for(Nodo in: ListaDeInstruccionesElse){
            if (in instanceof Instruccion){
                cont = ((Instruccion)in).Dibujar(builder, nodoElse, cont);
            }
        }  
        }
        return cont;  
    }
    
}
