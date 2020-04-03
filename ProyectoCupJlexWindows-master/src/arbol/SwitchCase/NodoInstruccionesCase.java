/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.SwitchCase;

import arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class NodoInstruccionesCase {
    
    private LinkedList<Nodo> InstruccionesDeCase;
    
    private String Break;

    public NodoInstruccionesCase(LinkedList<Nodo> InstruccionesCase) {
        this.InstruccionesDeCase=InstruccionesCase;
    }
    
    public NodoInstruccionesCase(String Break){
        this.Break=Break;
    }
    
    
}
