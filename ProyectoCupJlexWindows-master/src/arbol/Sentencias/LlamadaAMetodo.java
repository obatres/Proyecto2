/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Nodo;

/**
 *
 * @author obatres_
 */
public class LlamadaAMetodo extends Nodo {

    /**
     * @return the Identificador
     */
    public String getIdentificador() {
        return Identificador;
    }

    /**
     * @return the llamada
     */
    public Llamada getLlamada() {
        return llamada;
    }
    private String Identificador;
    private Llamada llamada;

    public LlamadaAMetodo(String Identificador, Llamada llamada) {
        this.Identificador = Identificador;
        this.llamada = llamada;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Llamada a Metodo\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoI = "nodoI" + ++cont;
        builder.append(nodoI).append(" [label=\""+Identificador+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodoI).append(";\n");
        
        if(llamada!=null){
        cont=llamada.Dibujar(builder, nodo, cont);            
        }

        
        return cont;
    }
    
    
}
