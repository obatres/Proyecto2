/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import javax.swing.text.TabableView;

/**
 *
 * @author obatres_
 */
public abstract class Expresion extends Nodo {
    public abstract Object ejecutar(TablaDeSimbolos ts);   
    public abstract Tipo GetTipo(TablaDeSimbolos ts);
    
}
