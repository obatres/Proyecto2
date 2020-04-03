/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.Nodo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class TabladeFunciones extends LinkedList<Funcion>{

    public TabladeFunciones() {
        super();
    }
    
    Expresion getValorRetorno(String id){
        for (Funcion f : this) {
            if(f.getIdentificadorFuncion().equals(id)){
                return f.getValordeRetorno();
            }
        }
        System.out.println("El valor de retorno de la funcion: "+id+" no se ha declarado");
        return null;
    }
    
    LinkedList<Nodo> getInstrucciones(String id){
        for (Funcion f : this) {
            if(f.getIdentificadorFuncion().equals(id)){
                return f.getInstruccionesFuncion();
            }
        }
        System.out.println("Las instrucciones de la funcion: "+id+" no se ha declarado");
        return null;        
    }
    
    ArrayList<Parametro> getParametros(String id){
        for (Funcion f : this) {
            if(f.getIdentificadorFuncion().equals(id)){
                return f.getParametrosFuncion();
            }
        }
        System.out.println("Los parametros de la funcion: "+id+" no se ha declarado");
        return null;        
    }
    
    public boolean Existe(String id){    
        for (Funcion f : this) {
            if(f.getIdentificadorFuncion().equals(id)){
                return true;
            }
        }
        return false;
    }
}
