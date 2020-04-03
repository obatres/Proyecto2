/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Errores.ErroSemantico;

/**
 *
 * @author obatres_
 */
public class ErrorARIT {
    String tipo, lexema, mensaje;
    int linea, columna;

    public ErrorARIT(String tipo, String lexema, String mensaje, int linea, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
    }
    
    
}
