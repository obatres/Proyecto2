/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.Variables.NodoVector;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class Declaracion extends Instruccion{

    
    int NumeroDeTipo;
    private final String id;

    Tipo tipo; //Tipo del simbolo (primitivos)
    
    private Expresion exp;
    
    ArrayList<Object> Vector ;//= new ArrayList<Object>(); //ArrayList que almacena los valores de una variable de tipo Vector
    
    ArrayList<Object> ValoresVariableVector; // variable para recibir los valores de una variable del lenguaje ARIT
    
    int TipoDeVariable;  //Tipo de variable para distinguir entre Vector, arreglo, lista y matriz
    
    
    private int tipoDeclaracion ; //Tipo de Declaracion, vector, arreglo, lista y matriz
    
    
    public Declaracion(String id, Expresion exp, int tipoDeclaracion) {
        this.id = id;
        this.exp = exp;
        this.tipoDeclaracion = tipoDeclaracion;
    }
    
    public Declaracion(String a, Tipo t) {
        id = a;
        tipo = t;
    }



    public Declaracion(String id, ArrayList<Object> ValoresVariableVector,  int tipoDeclaracion, int TipoDeVariable) {
        this.id = id;
        this.ValoresVariableVector = ValoresVariableVector;
        this.TipoDeVariable = TipoDeVariable;
        this.tipoDeclaracion = tipoDeclaracion;
    }
    
    /*
    Tipos de declaracion                                                Tipos de variables
    
     1      |  Identificador = Expresion                            
     2      |  Identificador = C( lista de expresiones);                1       |   Vector
    
    */
       
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
       if(tipoDeclaracion==1){
           Vector = new ArrayList<>();
           Vector.add(0, exp.ejecutar(ts));
        if(ts.Existe(id)){
            ts.setValor(id, Vector, exp.GetTipo(ts));           
        }else{
            ts.add(new Simbolo(id, exp.GetTipo(ts)));            
            ts.setValor(id, Vector,exp.GetTipo(ts));
            }
        }else if(tipoDeclaracion==2) {
            Vector = new ArrayList<>();
            for (int i = 0; i < ValoresVariableVector.size(); i++) {
               Vector.add(ValoresVariableVector.get(i));
            }
           if(ts.Existe(id)){
               for (Object t : Vector) {
                   if (t instanceof Expresion){
                       
                       if (((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.INT)&&NumeroDeTipo<=0){
                           NumeroDeTipo=1;
                       }else if(((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)&&NumeroDeTipo<=1){
                           NumeroDeTipo=2;
                       }else if(((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.STRING)&&NumeroDeTipo<=2){
                           NumeroDeTipo=3;
                       }
                   }
               }
               
               ArrayList<Object> NuevoVector = new ArrayList<>();
               if(NumeroDeTipo==0){
                   this.tipo = new Tipo(Tipo.tipo.BOOLEAN);
                   NuevoVector=Vector;
               }else if(NumeroDeTipo==1){
                   this.tipo = new Tipo(Tipo.tipo.INT);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("1", new Tipo (Tipo.tipo.INT)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("0", new Tipo (Tipo.tipo.INT)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(t);
                           }
                       }
                   }
               }else if(NumeroDeTipo==2){
                   this.tipo = new Tipo(Tipo.tipo.DOUBLE);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("1.0", new Tipo (Tipo.tipo.DOUBLE)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("0.0", new Tipo (Tipo.tipo.DOUBLE)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(new Single(Double.parseDouble(((Expresion)t).ejecutar(ts).toString()), new Tipo (Tipo.tipo.DOUBLE)));
                           }else if(((Expresion) t).GetTipo(ts).isDouble()){
                               NuevoVector.add(t);
                           }
                       }
                   }                   
               }else if(NumeroDeTipo==3){
                   this.tipo = new Tipo(Tipo.tipo.STRING);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("true", new Tipo (Tipo.tipo.STRING)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("false", new Tipo (Tipo.tipo.STRING)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(new Single(((Expresion)t).ejecutar(ts).toString(), new Tipo (Tipo.tipo.STRING)));
                           }else if(((Expresion) t).GetTipo(ts).isDouble()){
                               NuevoVector.add(new Single(((Expresion)t).ejecutar(ts).toString(), new Tipo (Tipo.tipo.STRING)));
                           }else if(((Expresion) t).GetTipo(ts).isString()){
                               NuevoVector.add(t);
                           }
                       }
                   }                    
               }

               ts.setValor(id, NuevoVector, tipo);
           }else{
               for (Object t : Vector) {
                   if (t instanceof Expresion){
                       
                       if (((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.INT)&&NumeroDeTipo<=0){
                           NumeroDeTipo=1;
                       }else if(((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)&&NumeroDeTipo<=1){
                           NumeroDeTipo=2;
                       }else if(((Expresion) t).GetTipo(ts).tp.equals(Tipo.tipo.STRING)&&NumeroDeTipo<=2){
                           NumeroDeTipo=3;
                       }
                   }
               }
               
                ArrayList<Object> NuevoVector = new ArrayList<>();
                if(NumeroDeTipo==0){
                    this.tipo = new Tipo(Tipo.tipo.BOOLEAN);
                    NuevoVector=Vector;
                }else if(NumeroDeTipo==1){
                    this.tipo = new Tipo(Tipo.tipo.INT);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("1", new Tipo (Tipo.tipo.INT)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("0", new Tipo (Tipo.tipo.INT)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(t);
                           }
                       }
                   }                    
                }else if(NumeroDeTipo==2){
                    this.tipo = new Tipo(Tipo.tipo.DOUBLE);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("1.0", new Tipo (Tipo.tipo.DOUBLE)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("0.0", new Tipo (Tipo.tipo.DOUBLE)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(new Single(Double.parseDouble(((Expresion)t).ejecutar(ts).toString()), new Tipo (Tipo.tipo.DOUBLE)));
                           }else if(((Expresion) t).GetTipo(ts).isDouble()){
                               NuevoVector.add(t);
                           }
                       }
                   }                      
                }else if(NumeroDeTipo==3){
                    this.tipo = new Tipo(Tipo.tipo.STRING);
                   for (Object t : Vector) {
                       if(t instanceof Expresion){
                           if(((Expresion) t).GetTipo(ts).isBoolean()){
                               if(((Expresion)t).ejecutar(ts).equals("true")){
                                   NuevoVector.add(new Single("true", new Tipo (Tipo.tipo.STRING)));
                               }else if(((Expresion)t).ejecutar(ts).equals("false")){
                                   NuevoVector.add(new Single("false", new Tipo (Tipo.tipo.STRING)));
                               }
                           }else if(((Expresion) t).GetTipo(ts).isInt()){
                               NuevoVector.add(new Single(((Expresion)t).ejecutar(ts).toString(), new Tipo (Tipo.tipo.STRING)));
                           }else if(((Expresion) t).GetTipo(ts).isDouble()){
                               NuevoVector.add(new Single(((Expresion)t).ejecutar(ts).toString(), new Tipo (Tipo.tipo.STRING)));
                           }else if(((Expresion) t).GetTipo(ts).isString()){
                               NuevoVector.add(t);
                           }
                       }
                   }                     
                }
                 
         
               ts.add(new Simbolo(id, this.tipo));

               ts.setValor(id, NuevoVector, tipo);
//               for (Object t : ValoresVariableVector) {
//                   System.out.println(((Expresion)t).ejecutar(ts));
//               }
           }
        }
       return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        if(tipoDeclaracion==1){
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Asignacion\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + id + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        

        String nodoVal = "nodo"+ ++cont;
        builder.append(nodoVal).append(" [label=\"Valor\"];\n");
        builder.append(nodo).append(" -> ").append(nodoVal).append(";\n");           
        
        cont = exp.Dibujar(builder, nodoVal, cont);            
        }


        return cont;        
    }




}
