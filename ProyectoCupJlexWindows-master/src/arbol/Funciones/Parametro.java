/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.Nodo;
import arbol.id;

/**
 *
 * @author obatres_
 */
public class Parametro extends Nodo {

    /**
     * @return the ValorParametro
     */
    public Expresion getValorParametro() {
        return ValorParametro;
    }

    /**
     * @param ValorParametro the ValorParametro to set
     */
    public void setValorParametro(Expresion ValorParametro) {
        this.ValorParametro = ValorParametro;
    }

    /**
     * @param IdParametro the IdParametro to set
     */
    public void setIdParametro(String IdParametro) {
        this.IdParametro = IdParametro;
    }

    /**
     * @return the IdParametro
     */
    public String getIdParametro() {
        return IdParametro;
    }
    
    private Expresion ValorParametro;
    private String IdParametro;

    public Parametro(Expresion ValorParametro, String IdParametro) {
        this.ValorParametro = ValorParametro;
        this.IdParametro = IdParametro;
    }

    public Parametro(String IdParametro) {
        this.IdParametro=IdParametro;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Parametro\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        String nodo1 = "nodo" + ++cont;
        builder.append(nodo1).append(" [label=\""+IdParametro+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodo1).append(";\n");
        
        if(ValorParametro!=null){
        String nodoP = "nodo" + ++cont;
        builder.append(nodoP).append(" [label=\""+"Valor"+"\"];\n");
        builder.append(nodo).append(" -> ").append(nodoP).append(";\n");
        
        
          cont=ValorParametro.Dibujar(builder, nodoP, cont);          
        }

        return cont;

    }
    
    
    
}
