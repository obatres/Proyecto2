/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class DefinicionDeFuncion extends Instruccion{

    private Funcion f;
    String id;
    boolean bandera=false;
    public DefinicionDeFuncion(Funcion f) {
        this.f = f;
    }
    ArrayList<String> Reservadas = new ArrayList<>();
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        Reservadas.add("c");
        Reservadas.add("list");
        Reservadas.add("plot");
        Reservadas.add("barplot");
        Reservadas.add("print");
        Reservadas.add("pie");
        Reservadas.add("lenght");
        Reservadas.add("Stringlenght");
        Reservadas.add("remove");
        Reservadas.add("trunk");
        Reservadas.add("round");
        Reservadas.add("typeof");
        Reservadas.add("tolowercase");
        Reservadas.add("touppercase");
       
        id=f.getIdentificadorFuncion();
        
        for (String Reservada : Reservadas) {
            if(id.equalsIgnoreCase(Reservada)){
                bandera=true;
            }
        }
        if(bandera==false){
            if(!proyectocupjlexwindows.ProyectoCupJlexWindows.tf.Existe(f.getIdentificadorFuncion())){
                proyectocupjlexwindows.ProyectoCupJlexWindows.tf.add(f);           
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", id, " Funcion ya definida", 0, 0);
                    ListaErrores.Add(e); 
            }
        }else{
            System.out.println("Esta es una funcion definidida del lenguaje y no se puede volver a definir");
                                ErrorARIT e=new ErrorARIT("Semantico", id, "error Esta es una funcion definidida del lenguaje y no se puede volver a definir", 0, 0);
                    ListaErrores.Add(e); 
        }

        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Def funcion\"]; \n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp1 = "nodo" + ++cont;
        builder.append(nodoOp1).append(" [label=\""+ f.getIdentificadorFuncion() + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp1).append(";\n");
        return cont;
    }
    
}
