/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author obatres_
 */
public abstract class Instruccion extends Nodo{
    
    public abstract Object ejecutar(TablaDeSimbolos ts);
    
}
