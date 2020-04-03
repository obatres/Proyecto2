/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TabladeSimbolos;
import arbol.TablaDeSimbolos;
/**
 *
 * @author obatres_
 */
public class ReporteTabla {

    /**
     * @return the tabla
     */
    public TablaDeSimbolos getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(TablaDeSimbolos tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the ambito
     */
    public int getAmbito() {
        return ambito;
    }

    /**
     * @param ambito the ambito to set
     */
    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }
    
    private TablaDeSimbolos tabla;
    private int ambito;

    public ReporteTabla(TablaDeSimbolos tabla, int ambito) {
        this.tabla = tabla;
        this.ambito = ambito;
    }
    
    
}
