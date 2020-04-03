/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class TablaDeSimbolos extends LinkedList<Simbolo>{

    public TablaDeSimbolos() {
        super();
    }
    /**
     * Método que busca una variable en la tabla de símbolos y devuelve su valor.
     * @param id Identificador de la variable que quiere buscarse
     * @return Valor de la variable que se buscaba, si no existe se devuelve nulo
     */
    Object getValor(String id) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                return s.getValor();
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito."+"VALOR");
        return "Valor Desconocido";
    }  
    
    Object getTipo(String id){
        for (Simbolo s : this) {
            if(s.getId().equals(id)){
                return s.getT();
            }
        }
        System.out.println("El valor de la variable"+id+"no existe en este ambito"+"TIPO");
        return "Tipo Desconocido";
    }

    
    
    
    
    public void setValor(String id, Object valor, Tipo tipo) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                s.setValor(valor);
                s.setT(tipo);
                return;
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito, por lo "
                + "que no puede asignársele un valor.");
    } 
    
    
    
    public boolean Existe(String id){       
        for (int i = 0; i < this.size(); i++) {
            Simbolo get = this.get(i);
            if(get.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}
