

package proyectocupjlexwindows;

import InterfazGrafica.VentanaPrincipal;
import arbol.Expresion;
import arbol.Funciones.Funcion;
import arbol.Funciones.TabladeFunciones;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.io.FileInputStream;
import java.util.LinkedList;


public class ProyectoCupJlexWindows {

    /**
     * @param args argumentos de la linea de comando
     */
            public static TabladeFunciones tf = new TabladeFunciones();
    public static void main(String[] args) {
        //interpretar("entrada.arit");
        VentanaPrincipal v = new VentanaPrincipal();
        v.setVisible(true);
    }
    /**
     * Método que interpreta el contenido del archivo que se encuentra en el path
     * que recibe como parámentro
     * @param path ruta del archivo a interpretar
     */
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        LinkedList<Nodo> AST_arbolSintaxisAbstracta = null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();      
            AST_arbolSintaxisAbstracta = pars.getAST();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
        DibujarArbol(AST_arbolSintaxisAbstracta);
        ejecutarAST(AST_arbolSintaxisAbstracta);
    }
    
        /**
     * Recibe una lista de instrucciones y la ejecuta
     * @param ast lista de instrucciones
     */
    private static void ejecutarAST(LinkedList<Nodo> ast) {
        if(ast==null){
            System.out.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        //Se crea una tabla de símbolos global para ejecutar las instrucciones.
        TablaDeSimbolos ts=new TablaDeSimbolos();
        //public static TabladeFunciones tf = new TabladeFunciones();
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        for(Nodo ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null)
                if(ins instanceof Expresion)//Para todas las instacias de la clase Expresion se ejecuta el metodo "ejecutar()" 
                {                           //que recibe como parametro la tabla de simbolos de la misma instancia dentro del arbol
                    ((Expresion)ins).ejecutar(ts); //y devuelve valores de operaciones aritmeticas y logicas
                }else if(ins instanceof Instruccion)
                {                           //Para todas las instancias de la clase Instruccion se ejecuta el metodo "ejecutar()"
                    ((Instruccion)ins).ejecutar(ts);//que recibe como parametro la tabla de simbolos de la misma instancia dentro del arbol
                }else{                              //y devuelve la ejecucion de instrucciones como condicionales o ciclos
   
                    System.out.println("Error al ejecutar un nivel en el arbol AST");  //reportar error
                }
 
        }
    }
    
    public static void DibujarArbol (LinkedList<Nodo> arbol){
        StringBuilder builder = new StringBuilder();
        int cont = 1;
        String root = "nodo" + cont;
        builder.append("digraph lab5 {\n");
        builder.append(root).append(" [label=\"ARIT\"];\n");
        
        for (Nodo nodo : arbol) {
                cont = nodo.Dibujar(builder, root, cont);
        }
        builder.append("}");
        
        System.out.println(builder.toString());
        
    }
}