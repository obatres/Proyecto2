/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Errores.ErroSemantico;

import InterfazGrafica.VentanaPrincipal;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class ListaErrores {

    /**
     * @return the ListadeErrores
     */
    public ArrayList<ErrorARIT> getListadeErrores() {
        return ListadeErrores;
    }
    
    private static ListaErrores Lista;
    
    private static ArrayList<ErrorARIT> ListadeErrores = new ArrayList<>();

    public ListaErrores(ArrayList<ErrorARIT> ListadeErrores) {
        this.ListadeErrores = ListadeErrores;
    }
 
    public static void Add(ErrorARIT e){
        ListadeErrores.add(e);
    }
    
    public void LimpiarErrores(){
        ListadeErrores.clear();
    }
    
    public static void VerErrores(){
        int cont = 1;
        for (ErrorARIT e : ListadeErrores) {
            System.out.println(cont+". Tipo: "+e.tipo+", Lexema: "+e.lexema+", Mensaje: "+e.mensaje);
            VentanaPrincipal.errores+=cont+". Tipo: "+e.tipo+", Lexema: "+e.lexema+", Mensaje: "+e.mensaje+"\n";
            cont++;
        }
    }
}
