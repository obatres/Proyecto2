/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Tipo;
import java.util.ArrayList;
/**
 *
 * @author obatres_
 */
public class Simbolo {

    private final String id;
    private Tipo t;
    private Object valor;
    
    /**
     * @param t the t to set
     */
    public void setT(Tipo t) {
        this.t = t;
    }

    /**
     * @return the t
     */
    public Tipo getT() {
        return t;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }



    /**
     *
     * @param id
     * @param tipo
     */
    public Simbolo(String id, Tipo t){
        this.t=t;
        this.id=id;
        this.valor="null";
    }
    
}
