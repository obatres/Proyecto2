/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Tipo.tipo.*;
import java.util.ArrayList;
/**
 *
 * @author obatres_
 */
public class Operacion extends Expresion{

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Expresion Binaria\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");
        
        if(opderadorIzq!=null){
            cont = opderadorIzq.Dibujar(builder, nodo, cont);            
        }

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + tipo_operacion + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");

        if(operadorDer!=null){
            cont = operadorDer.Dibujar(builder, nodo, cont);           
        }
        
        if(operadorTer!=null){
            cont = operadorTer.Dibujar(builder, nodo, cont);
        }


        return cont;   
    }

    public static enum Tipo_operacion{
        SUMA,
        NUMERO,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        POTENCIA,
        MODULO,
        UMENOS,
        IGUALIGUAL,
        DESIGUAL,
        MAYOR,
        MENOR,
        MAYORIGUAL,
        MENORIGUAL,
        AND,
        OR,
        NOT,
        TERNARIO,
        IDENTIFICADOR
    }
   
    private final Tipo_operacion tipo_operacion;
    
    private Expresion opderadorIzq;
    private Expresion operadorDer;
    private Expresion operadorTer;
    public  Tipo tipo;
    private Object valor;

    public Operacion(Tipo_operacion tipo, Expresion opderadorIzq, Expresion operadorDer) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    public Operacion(Tipo_operacion tipo, Expresion opderadorIzq) {
        this.tipo_operacion = tipo;
        this.opderadorIzq = opderadorIzq;
    } 
    
    public Operacion (String a, Tipo_operacion tipo){
        this.valor = a;
        this.tipo_operacion=tipo;
    }
    
    public Operacion(Object a, Tipo tipo, Tipo_operacion to) {
        this.valor=a;
        this.tipo = tipo;
        this.tipo_operacion= to;
    }

    public Operacion(Tipo_operacion tipo,Expresion operadorTer , Expresion opderadorIzq,Expresion operadorDer ) {
        this.tipo_operacion = tipo;
        this.operadorTer = operadorTer;
        this.opderadorIzq=opderadorIzq;
        this.operadorDer = operadorDer;
    }
    
    
    

    
    @Override
    public Object ejecutar(TablaDeSimbolos ts){

        if(tipo_operacion==Tipo_operacion.SUMA){
            // <editor-fold desc="SUMA">> 
            if (opderadorIzq.GetTipo(ts).isInt()){ // EJEMPLO DE OPERADOR IZQUIERDO  1,546,100
                // <editor-fold desc="IZQUIERDO ES INT">>                 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){                    
                    // <editor-fold desc="INT+INT/DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                      
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){ 
                                
                            /*********************************************************ARRAY+ARRAY***********************************************/  
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())+Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                            }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            /*********************************************************ARRAY+STRING***********************************************/                                  
                                return Double.parseDouble(vec1.get(0).toString()) +Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                            /*********************************************************ARRAY+DOUBLE***********************************************/                                  
                                return Double.parseDouble(vec1.get(0).toString()) +Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            }         
                    }else{  
                     
                        //System.out.println(operadorDer.ejecutar(ts).getClass());
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){  
                        /*********************************************************SIMPLE+ARRAY***********************************************/                                
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1+Double.parseDouble(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                        /*********************************************************SIMPLE+STRING***********************************************/     
                            return val1+Double.parseDouble(operadorDer.ejecutar(ts).toString());                           
                        }else if(operadorDer.ejecutar(ts) instanceof Double){
                        /*********************************************************SIMPLE+DOUBLE***********************************************/                                 
                            return val1+Double.parseDouble(operadorDer.ejecutar(ts).toString());    
                        }                                         
                    }   
                    
  // </editor-fold>                  
                 
/*******************************************************INT+STRING********************************************/   
                }else if(operadorDer.GetTipo(ts).isString()){
    // <editor-fold desc="INT+STRING">>                    
                    String val1 = "";
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){   
                        vec1=((ArrayList)opderadorIzq.ejecutar(ts));   
                        if(operadorDer.ejecutar(ts)instanceof ArrayList){
                        /*********************************************************ARRAY+ARRAY***********************************************/     
                            vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                            if(vec1.size()==vec2.size()){                                  
                                for (int i = 0; i < vec1.size(); i++) {
                                    vec3.add((vec1.get(i).toString())+(vec2.get(i).toString()));
                                }    
                                return vec3;     
                            }else{
                                System.out.println("error de tamaños");
                            }                             
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                        /*********************************************************ARRAY+STRING***********************************************/     
                            return (vec1.get(0).toString()) + (operadorDer.ejecutar(ts).toString());
                        }   
                    }else{                       
                        val1=(opderadorIzq.ejecutar(ts).toString());                          
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                        /*********************************************************SIMPLE+ARRAY***********************************************/ 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1+(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){   
                        /*********************************************************SIMPLE+STRING***********************************************/     
                            return val1+(operadorDer.ejecutar(ts).toString());                           
                        }                        
                    } 
                    
                }else{
                   
                    System.out.println("error de tipos");
               }        
                
// </editor-fold>   
// </editor-fold>
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQUIERDO ES DOUBLE">>                 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){                
                    // <editor-fold desc="DOUBLE+INT/DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){   
                            /*********************************************************ARRAY+ARRAY***********************************************/                                
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())+Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                            }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            /*********************************************************ARRAY+STRING***********************************************/    
                                return Double.parseDouble(vec1.get(0).toString()) +Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                            /*********************************************************ARRAY+DOUBLE***********************************************/    
                                return Double.parseDouble(vec1.get(0).toString()) +Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                            }         
                    }else{                        
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                        /*********************************************************SIMPLE+ARRAY***********************************************/    
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1+Double.parseDouble(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                        /*********************************************************SIMPLE+STRING***********************************************/
                            return val1+Double.parseDouble(operadorDer.ejecutar(ts).toString());                           
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                        /*********************************************************SIMPLE+DOUBLE***********************************************/   
                            return val1+Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                        }                                         
                    }  
// </editor-fold>    
                }else if(operadorDer.GetTipo(ts).isString()){
                    // <editor-fold desc="DOUBLE+INT/STRING">> 
                    String val1 = "";
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){   
                        vec1=((ArrayList)opderadorIzq.ejecutar(ts));   
                        if(operadorDer.ejecutar(ts)instanceof ArrayList){
                        /*********************************************************ARRAY+ARRAY***********************************************/    
                            vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                            if(vec1.size()==vec2.size()){                                  
                                for (int i = 0; i < vec1.size(); i++) {
                                    vec3.add((vec1.get(i).toString())+(vec2.get(i).toString()));
                                }    
                                return vec3;     
                            }else{
                                System.out.println("error de tamaños");
                            }                             
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                        /*********************************************************ARRAY+STRING***********************************************/    
                            return (vec1.get(0).toString()) + (operadorDer.ejecutar(ts).toString());
                        }   
                    }else{                       
                        val1=(opderadorIzq.ejecutar(ts).toString());                          
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                        /*********************************************************SIMPLE+ARRAY***********************************************/    
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1+(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                        /*********************************************************SIMPLE+STRING***********************************************/    
                            return val1+(operadorDer.ejecutar(ts).toString());                           
                        }                        
                    }  
                }else{
                    System.out.println("error de tipos");
                }       
  // </editor-fold>            
                // </editor-fold> 
            }else if(opderadorIzq.GetTipo(ts).isString()){                 
                // <editor-fold desc="IZQUIERO ES STRING">> 

                String val1 = "";
                ArrayList<Object> vec1 = new ArrayList<>();
                ArrayList<Object> vec2 = new ArrayList<>();
                ArrayList<Object> vec3 = new ArrayList<>();
                if(opderadorIzq.ejecutar(ts) instanceof ArrayList){   
                    vec1=((ArrayList)opderadorIzq.ejecutar(ts));   
                    if(operadorDer.ejecutar(ts)instanceof ArrayList){
                        /*********************************************************ARRAY+ARRAY***********************************************/ 
                        vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                        if(vec1.size()==vec2.size()){                                  
                            for (int i = 0; i < vec1.size(); i++) {
                                vec3.add((vec1.get(i).toString())+(vec2.get(i).toString()));
                            }    
                            return vec3;     
                        }else{
                            System.out.println("error de tamaños");
                        }                             
                    }else {
                        /*********************************************************ARRAY+STRING***********************************************/ 
                        return (vec1.get(0).toString()) + (operadorDer.ejecutar(ts).toString());
                    }   
                }else{                       
                    val1=(opderadorIzq.ejecutar(ts).toString());                          
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                        /*********************************************************SIMPLE+ARRAY***********************************************/
                        vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                        return val1+(vec2.get(0).toString());                       
                    }else { 
                        /*********************************************************SIMPLE+STRING***********************************************/
                        return val1+(operadorDer.ejecutar(ts).toString());                           
                    }                        
                }
         // </editor-fold>
            }else if(opderadorIzq.GetTipo(ts).isBoolean()){
                // <editor-fold desc="IZQUIERO ES BOOL">> 
                if(operadorDer.GetTipo(ts).isString()){               
                    String val1 = "";
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){   
                        vec1=((ArrayList)opderadorIzq.ejecutar(ts));   
                        if(operadorDer.ejecutar(ts)instanceof ArrayList){
                        /*********************************************************ARRAY+ARRAY***********************************************/    
                            vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                            if(vec1.size()==vec2.size()){                                  
                                for (int i = 0; i < vec1.size(); i++) {
                                    vec3.add((vec1.get(i).toString())+(vec2.get(i).toString()));
                                }    
                                return vec3;     
                            }else{
                                System.out.println("error de tamaños");
                            }                             
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                        /*********************************************************ARRAY+ARRAY***********************************************/    
                            return (vec1.get(0).toString()) + (operadorDer.ejecutar(ts).toString());
                        }   
                    }else{                       
                        val1=(opderadorIzq.ejecutar(ts).toString());                          
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){                            
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1+(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){                          
                            return val1+(operadorDer.ejecutar(ts).toString());                           
                        }                        
                    }
                    
                    
                }else{
                    System.out.println("error de tipos");
                }
            // </editor-fold>
            }else{
                //TODO reportar error de tipo
            }
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.RESTA){
            // <editor-fold desc="RESTA">> 
            if(opderadorIzq.GetTipo(ts).isInt()){
                // <editor-fold desc="IZQUIERDO ES INT">>    
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER  INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){
                                // <editor-fold desc="DERECHO ARRAY">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())-Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){
                                // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) -Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">> 
                                return Double.parseDouble(vec1.get(0).toString()) -Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                            // </editor-fold>
                            }     
                       // </editor-fold>     
                    }else{      
                        // <editor-fold desc="IZQ SIMPLE">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1-Double.parseDouble(vec2.get(0).toString());  
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){  
                            // <editor-fold desc="DER STRING">> 
                            return val1-Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1-Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }  
                        // </editor-fold>
                    } 
                   // </editor-fold> 
                } else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);        
                }                    
                // </editor-fold>                 
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQUIERDO ES DOUBLE">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){                  
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){                              
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())-Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                            }else if(operadorDer.ejecutar(ts)instanceof String){                               
                                return Double.parseDouble(vec1.get(0).toString()) -Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                            }         
                    }else{                        
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){                          
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1-Double.parseDouble(vec2.get(0).toString());                       
                        }else if(operadorDer.ejecutar(ts)instanceof String){                         
                            return val1-Double.parseDouble(operadorDer.ejecutar(ts).toString());                           
                        }                                         
                    } 
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
            }
         // </editor-fold> 
             
        }else if(tipo_operacion==Tipo_operacion.MULTIPLICACION){
            // <editor-fold desc="MULTIPLICACION">> 
            if(opderadorIzq.GetTipo(ts).isInt()){
                // <editor-fold desc="IZQ INT">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){
                                  // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())*Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){ 
                                  // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) *Double.parseDouble(operadorDer.ejecutar(ts).toString());
// </editor-fold>                                
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                  // <editor-fold desc="DER DOUBLE">> 
                                return Double.parseDouble(vec1.get(0).toString()) *Double.parseDouble(operadorDer.ejecutar(ts).toString());
                                // </editor-fold>
                            }  
                            // </editor-fold>
                    }else{    
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){  
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1*Double.parseDouble(vec2.get(0).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            // <editor-fold desc="DER STRING">> 
                            return val1*Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1*Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }  
                        // </editor-fold>
                    } 
                     // </editor-fold>   
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQ DOUBLE">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARRAY">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())*Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tamaños en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){   
                                // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) *Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">> 
                                return Double.parseDouble(vec1.get(0).toString()) *Double.parseDouble(operadorDer.ejecutar(ts).toString());   
                                // </editor-fold>
                            } else{
                        ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);   
                            }
                            // </editor-fold>
                    }else{  
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1*Double.parseDouble(vec2.get(0).toString());    
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            // <editor-fold desc="DER STRING">> 
                            return val1*Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1*Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        } else{
                            ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tamaños en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);   
                        }
                        // </editor-fold>
                    } 
                    
                  // </editor-fold>  
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
            }  
        
// </editor-fold>             
         
        }else if(tipo_operacion==Tipo_operacion.DIVISION){
            // <editor-fold desc="DIVISION">> 
            if(opderadorIzq.GetTipo(ts).isInt()){
                // <editor-fold desc="IZQ INT">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){ 
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())/Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tamaños en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){  
                                // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) /Double.parseDouble(operadorDer.ejecutar(ts).toString());  
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">> 
                                return Double.parseDouble(vec1.get(0).toString()) /Double.parseDouble(operadorDer.ejecutar(ts).toString());
                                // </editor-fold>                                
                            }else{
ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipos en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);   
                            }
                        // </editor-fold>
                    }else{ 
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1/Double.parseDouble(vec2.get(0).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){    
                            // <editor-fold desc="DER STRING">> 
                            return val1/Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1/Double.parseDouble(operadorDer.ejecutar(ts).toString());  
                            // </editor-fold>
                        } else{
ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MULTIPLICACION, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                           }
                        // </editor-fold>
                    } 
                    // </editor-fold>
                } else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
               // </editor-fold> 
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQ DOUBLE">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){  
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())/Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){ 
                                // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) /Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">> 
                                return Double.parseDouble(vec1.get(0).toString()) /Double.parseDouble(operadorDer.ejecutar(ts).toString());                                
                                // </editor-fold>
                            } 
                        // </editor-fold>    
                    }else{
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1/Double.parseDouble(vec2.get(0).toString());
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                            // <editor-fold desc="DER STRING">> 
                            return val1/Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1/Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        }  
                        // </editor-fold>
                    } 
                // </editor-fold>  
                }else{

                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                  } 
            // </editor-fold> 
           
        }else if(tipo_operacion==Tipo_operacion.POTENCIA){
            // <editor-fold desc="POTENCIA">> 
            if(opderadorIzq.GetTipo(ts).isInt()){
                // <editor-fold desc="IZQ INT">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){                 
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Math.pow(Double.parseDouble(vec1.get(i).toString()),Double.parseDouble(vec2.get(i).toString())));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tamaños en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                                 
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){ 
                                // <editor-fold desc="DER STRING">> 
                                return Math.pow(Double.parseDouble(vec1.get(0).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString()));
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">> 
                                return Math.pow(Double.parseDouble(vec1.get(0).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                                // </editor-fold>
                            } else{
                                ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);   
                            }
                        // </editor-fold>
                    }else{                        
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return Math.pow(val1,Double.parseDouble(vec2.get(0).toString()));
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                            // <editor-fold desc="DER STRING">> 
                            return Math.pow(val1,Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return Math.pow(val1,Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                            // </editor-fold>
                        } else{
                            ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);   
                        }
                        // </editor-fold>
                    }     
                    // </editor-fold>
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQ DOUBLE">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">>    
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){ 
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Math.pow(Double.parseDouble(vec1.get(i).toString()),Double.parseDouble(vec2.get(i).toString())));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tamaños en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){  
                                // <editor-fold desc="DER STRING">> 
                                return Math.pow(Double.parseDouble(vec1.get(0).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString()));  
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">>
                                return Math.pow(Double.parseDouble(vec1.get(0).toString()),Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                                // </editor-fold>
                            }else{
                                                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);
                            }  
                        // </editor-fold>
                    }else{   
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return Math.pow(val1,Double.parseDouble(vec2.get(0).toString()));
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            // <editor-fold desc="DER STRING">> 
                            return Math.pow(val1,Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return Math.pow(val1,Double.parseDouble(operadorDer.ejecutar(ts).toString())); 
                            // </editor-fold>
                        }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);
                        }  
                        // </editor-fold>
                    }                    

                    // </editor-fold>
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }
                // </editor-fold>
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la POTENCIA, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                  }
            // </editor-fold> 
             
        }else if(tipo_operacion==Tipo_operacion.MODULO){
            // <editor-fold desc="MODULO">> 
            if(opderadorIzq.GetTipo(ts).isInt()){
                // <editor-fold desc="IZQ INT">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                            if(operadorDer.ejecutar(ts)instanceof ArrayList){  
                                // <editor-fold desc="DER ARR">> 
                                vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                                if(vec1.size()==vec2.size()){                                   
                                    for (int i = 0; i < vec1.size(); i++) {                                       
                                        vec3.add(Double.parseDouble(vec1.get(i).toString())%Double.parseDouble(vec2.get(i).toString()));                                       
                                    }                                     
                                    return vec3;                                    
                                }else{                                   
                                    System.out.println("error de tamaños");                                   
                                }
                                // </editor-fold>
                            }else if(operadorDer.ejecutar(ts)instanceof String){
                                // <editor-fold desc="DER STRING">> 
                                return Double.parseDouble(vec1.get(0).toString()) %Double.parseDouble(operadorDer.ejecutar(ts).toString());
                                // </editor-fold>                                
                            }else if(operadorDer.ejecutar(ts)instanceof Double){
                                // <editor-fold desc="DER DOUBLE">>
                                return Double.parseDouble(vec1.get(0).toString()) %Double.parseDouble(operadorDer.ejecutar(ts).toString());  
                                // </editor-fold>
                            } else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                            }  
                        // </editor-fold>    
                    }else{      
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){   
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1%Double.parseDouble(vec2.get(0).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){ 
                            // <editor-fold desc="DER STRING">> 
                            return val1%Double.parseDouble(operadorDer.ejecutar(ts).toString());  
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1%Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        } else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la resta, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                              }
                        // </editor-fold>
                    }   
                    // </editor-fold>  
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MODULO, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                }  
                // </editor-fold>
            }else if(opderadorIzq.GetTipo(ts).isDouble()){
                // <editor-fold desc="IZQ DOUBLE">> 
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    // <editor-fold desc="DER INT-DOUBLE">> 
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec2 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="IZQ ARR">> 
                        vec1=((ArrayList)opderadorIzq.ejecutar(ts)); 
                        if(operadorDer.ejecutar(ts)instanceof ArrayList){  
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts)); 
                            if(vec1.size()==vec2.size()){                                   
                                for (int i = 0; i < vec1.size(); i++) {                                       
                                    vec3.add(Double.parseDouble(vec1.get(i).toString())%Double.parseDouble(vec2.get(i).toString()));                                       
                                }                                     
                                return vec3;                                    
                            }else{                                   
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tama en la MODULO, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);      
                            }
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                            // <editor-fold desc="DER STRING">> 
                            return Double.parseDouble(vec1.get(0).toString()) %Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return Double.parseDouble(vec1.get(0).toString()) %Double.parseDouble(operadorDer.ejecutar(ts).toString());
                            // </editor-fold>
                        } 
                        // </editor-fold>
                    }else{      
                        // <editor-fold desc="IZQ SIN">> 
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                       
                        if(operadorDer.ejecutar(ts) instanceof ArrayList){ 
                            // <editor-fold desc="DER ARR">> 
                            vec2=((ArrayList)operadorDer.ejecutar(ts));                           
                            return val1%Double.parseDouble(vec2.get(0).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof String){
                            // <editor-fold desc="DER STRING">> 
                            return val1%Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        }else if(operadorDer.ejecutar(ts)instanceof Double){
                            // <editor-fold desc="DER DOUBLE">> 
                            return val1%Double.parseDouble(operadorDer.ejecutar(ts).toString()); 
                            // </editor-fold>
                        }   
                        // </editor-fold>
                    }  
                    // </editor-fold>   
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MODULO, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
                // </editor-fold>
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la MODULO, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
            } 
            // </editor-fold> 
             
        }else if(tipo_operacion==Tipo_operacion.UMENOS){
            // <editor-fold desc="UMENOS">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                    Double val1 = 0.0;
                    ArrayList<Object> vec1 = new ArrayList<>();
                    ArrayList<Object> vec3 = new ArrayList<>();
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                            vec1=((ArrayList)opderadorIzq.ejecutar(ts));                                 
                                for (int i = 0; i < vec1.size(); i++) {                                       
                                    vec3.add(-Double.parseDouble(vec1.get(i).toString()));                                       
                                }                                     
                                return vec3;                                    
                    }else{                        
                        val1=Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                                                
                            return -val1;                                           
                    }   
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString(), "error de tipo en la UMENOS, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
            }
               // </editor-fold> 
             
        }else if(tipo_operacion==Tipo_operacion.IGUALIGUAL){
            // <editor-fold desc="IGUALIGUAL">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1.equals(val2);
                }else{
                                        ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la IGUALIGUAL, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return (val1.toString().equals( val2.toString()));   
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la IGUALIGUAL, se esperaba un String", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
            }
            // </editor-fold>
                 
        }else if(tipo_operacion==Tipo_operacion.DESIGUAL){
            // <editor-fold desc="DESIGUAL">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return !val1.equals(val2);
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la DESIGUAL, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return !(val1.toString().equals( val2.toString()));                
            }else{
                ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la DESIGUAL, se esperaba un String", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(e); 
            }         
            // </editor-fold> 
        }else if(tipo_operacion==Tipo_operacion.MAYOR){
            // <editor-fold desc="MAYOR">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=Double.parseDouble(((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString());
                    }else{
                        System.out.println(opderadorIzq.ejecutar(ts).getClass());
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1>val2;
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MAYOR, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()>val2.length();
            }else{
                ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la MAYOR, se esperaba un String", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(e); 
            }      
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.MENOR){
            // <editor-fold desc="MENOR">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){ 
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1<val2;
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MENOR, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()<val2.length();               
            }else{
                ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la MENOR, se esperaba un String", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(e); 
            }   
             // </editor-fold> 
        }else if(tipo_operacion==Tipo_operacion.MAYORIGUAL){
            // <editor-fold desc="MAYORIGUAL">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1>=val2;
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MAYORIGUAL, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()>=val2.length();   
            }else{
                ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la MAYORIGUAL, se esperaba un String", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(e); 
            }   
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.MENORIGUAL){
            // <editor-fold desc="MENORIGUAL">> 
            if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    Double val1;
                    Double val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=(Double) ((ArrayList)opderadorIzq.ejecutar(ts)).get(0);
                    }else{
                        val1= Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=(Double) ((ArrayList)operadorDer.ejecutar(ts)).get(0);
                    }else{
                        val2= Double.parseDouble(operadorDer.ejecutar(ts).toString());                    
                    }                    
                    return val1<=val2;
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la MENORIGUAL, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e);                 }
            }else if(opderadorIzq.GetTipo(ts).isString()&&operadorDer.GetTipo(ts).isString()){
                    String val1;
                    String val2;
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        val1=((ArrayList)opderadorIzq.ejecutar(ts)).get(0).toString();
                    }else{
                        val1= opderadorIzq.ejecutar(ts).toString();                     
                    }
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        val2=((ArrayList)operadorDer.ejecutar(ts)).get(0).toString();
                    }else{
                        val2= operadorDer.ejecutar(ts).toString();                    
                    }                    
                    return val1.length()<=val2.length();   
            }  else{
                ErrorARIT e=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipo en la MENORIGUAL, se esperaba un String", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(e);             }
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.AND){
            // <editor-fold desc="AND">> 
            try {
                return opderadorIzq.ejecutar(ts).equals(operadorDer.ejecutar(ts));               
            } catch (Exception e) {
                ErrorARIT er=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipos en AND ", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(er); 
            }
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.OR){
            // <editor-fold desc="OR">> 
            try {
                return (boolean)opderadorIzq.ejecutar(ts)||(boolean)operadorDer.ejecutar(ts);
            } catch (Exception e) {
                ErrorARIT er=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipos en OR ", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(er); 
            }
            // </editor-fold>
        }else if(tipo_operacion == Tipo_operacion.NOT){
            // <editor-fold desc="NOT">> 
            try {
                return !(boolean) opderadorIzq.ejecutar(ts);
            } catch (Exception e) {
                                ErrorARIT er=new ErrorARIT("Semantico", opderadorIzq.ejecutar(ts).toString()+" y "+operadorDer.ejecutar(ts).toString(), "error de tipos en NOT ", operadorDer.linea, operadorDer.columna);
                ListaErrores.Add(er); 
            }
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.TERNARIO){
            // <editor-fold desc="TERNARIO">> 
            if(operadorTer.ejecutar(ts).equals(true)){
                if(opderadorIzq.GetTipo(ts).isInt()||opderadorIzq.GetTipo(ts).isDouble()){
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        ArrayList<Object>vec=new ArrayList<>();
                        for (Object object : (ArrayList)opderadorIzq.ejecutar(ts)) {
                            vec.add(object);
                        }
                        return vec;
                    }else{
                        return Double.parseDouble(opderadorIzq.ejecutar(ts).toString());                        
                    }
   
                }else if(opderadorIzq.GetTipo(ts).isString()){
                    
                    
                    if(opderadorIzq.ejecutar(ts) instanceof ArrayList){
                        ArrayList<Object>vec=new ArrayList<>();
                        for (Object object : (ArrayList)opderadorIzq.ejecutar(ts)) {
                            vec.add(object);
                        }
                        return vec;
                    }else{
                        return (opderadorIzq.ejecutar(ts).toString());                        
                    }
                    
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorDer.ejecutar(ts).toString(), "error de tipo en la IGUALIGUAL, se esperaba un numero", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
                }
            }else if(operadorTer.ejecutar(ts).equals(false)){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    
                    
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        ArrayList<Object>vec=new ArrayList<>();
                        for (Object object : (ArrayList)operadorDer.ejecutar(ts)) {
                            vec.add(object);
                        }
                        return vec;
                    }else{
                        return Double.parseDouble(operadorDer.ejecutar(ts).toString());                        
                    }
                    
                }else if(operadorDer.GetTipo(ts).isString()){
                    if(operadorDer.ejecutar(ts) instanceof ArrayList){
                        ArrayList<Object>vec=new ArrayList<>();
                        for (Object object : (ArrayList)operadorDer.ejecutar(ts)) {
                            vec.add(object);
                        }
                        return vec;
                    }else{
                        return (operadorDer.ejecutar(ts).toString());                        
                    }
                }               
            }else{
                    ErrorARIT e=new ErrorARIT("Semantico", operadorTer.ejecutar(ts).toString(), "error de tipo en TERNARIO", operadorDer.linea, operadorDer.columna);
                    ListaErrores.Add(e); 
            }
            // </editor-fold>
        }else if(tipo_operacion==Tipo_operacion.IDENTIFICADOR){
            return ts.getValor(valor.toString());
        }else if(tipo_operacion==Tipo_operacion.NUMERO){
              return this.valor;  
        }else{
        ErrorARIT e=new ErrorARIT("Semantico", tipo_operacion.toString(), "error de tipo en la OPERACION", operadorDer.linea, operadorDer.columna);
        ListaErrores.Add(e); 
        }
        return null;
    }
    
    
        @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        if (opderadorIzq.GetTipo(ts).isInt()){
            if(operadorDer!=null){
                if(operadorDer.GetTipo(ts).isInt()){
                    return new Tipo(Tipo.tipo.INT);  
                }else if(operadorDer.GetTipo(ts).isDouble()){
                    return new Tipo(Tipo.tipo.DOUBLE);
                }else if(operadorDer.GetTipo(ts).isString()){
                    return new Tipo(Tipo.tipo.STRING);
                }else{
                    //TODO reportar error de tipo
                }                
            }else{
                return new Tipo(Tipo.tipo.INT); 
            }
        }else if(opderadorIzq.GetTipo(ts).isDouble()){
            if(operadorDer!=null){
                if(operadorDer.GetTipo(ts).isInt()||operadorDer.GetTipo(ts).isDouble()){
                    return new Tipo(Tipo.tipo.DOUBLE);
                }else if(operadorDer.GetTipo(ts).isString()){
                    return new Tipo(Tipo.tipo.STRING);
                }else{

                }                
            }else{
                return new Tipo(Tipo.tipo.DOUBLE); 
            }

        }else if(opderadorIzq.GetTipo(ts).isString()){
            return new Tipo(Tipo.tipo.STRING);
        }else if(opderadorIzq.GetTipo(ts).isBoolean()){
            if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else if(opderadorIzq.GetTipo(ts).isBoolean()){
            if(operadorDer.GetTipo(ts).isBoolean()){
                return new Tipo(Tipo.tipo.BOOLEAN);
            }else if(operadorDer.GetTipo(ts).isString()){
                return new Tipo(Tipo.tipo.STRING);
            }
        }else{
            return this.tipo;
        }
        return this.tipo;
    }
        
}
