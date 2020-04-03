/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.SwitchCase;

import arbol.Nodo;

/**
 *
 * @author obatres_
 */
public class Break extends Nodo{

    /**
     * @return the TipoInstruccion
     */
    public String getTipoInstruccion() {
        return TipoInstruccion;
    }

    private String TipoInstruccion; 

    public Break(String TipoInstruccion) {
        this.TipoInstruccion = TipoInstruccion;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Break\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");        
        return cont;
    }
    
    
    
}
