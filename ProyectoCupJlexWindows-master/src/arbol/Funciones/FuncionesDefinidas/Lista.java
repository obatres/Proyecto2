/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import arbol.DeclaracionVariable;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Funciones.LlamadaFuncionExp;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import arbol.id;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Lista extends Expresion{
    private ArrayList<Object> ListaExp;

    ArrayList<Object> salidatotal = new ArrayList<>();   
    
    public Lista(ArrayList<Object> ListaExp) {
        this.ListaExp = ListaExp;
    }

        
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {

        for (Object t :ListaExp) {
            if(t instanceof Single){
                // <editor-fold desc="SINGLE">> 
                salidatotal.add(((Single) t).ejecutar(ts));
                // </editor-fold>
            }else if(t instanceof id){
                // <editor-fold desc="ID">> 
                if(((id) t).GetTipo(ts).isList()){
                    // <editor-fold desc="VIENE LISTA">> 
                    salidatotal.add(((id) t).ejecutar(ts));
                    // </editor-fold>
                }else{
                    if(((id) t).ejecutar(ts)instanceof ArrayList){
                        // <editor-fold desc="VIENE VECTOR">> 
                        /*for (Object object : (ArrayList)((id) t).ejecutar(ts)) {
                            salidatotal.add(object);
                        }*/
                        salidatotal.add(((id) t).ejecutar(ts));
                        // </editor-fold>
                    }else if(((id) t).ejecutar(ts)instanceof String){
                        // <editor-fold desc="VIENE STRING-SINGLE">> 
                        salidatotal.add(((id) t).ejecutar(ts));
                        // </editor-fold>
                    }else if(((id) t).ejecutar(ts) instanceof Single){
                        // <editor-fold desc="VIENE SINGLE">> 
                        salidatotal.add(((Single) t).ejecutar(ts));
                        // </editor-fold>
                    }   
                }
                // </editor-fold>
            }else if(t instanceof LlamadaFuncionExp){               
                // <editor-fold desc="LLAMADA A FUNCION">> 
                Object salidaLlamada = ((LlamadaFuncionExp) t).ejecutar(ts);

                if(((LlamadaFuncionExp) t).GetTipo(ts).isList()){                
                    salidatotal.add(salidaLlamada);
                }else{
                    if(salidaLlamada instanceof ArrayList){
                        /*for (Object object : (ArrayList)salidaLlamada) {
                            salidatotal.add(object);
                        }*/
                        salidatotal.add(salidaLlamada);
                    }else if (salidaLlamada instanceof String){
                        salidatotal.add(salidaLlamada);
                    }else{
                        //System.out.println(DeclaracionVariable.Vector);
                    }
                }

                // </editor-fold>
            }else if(t instanceof C){
                // <editor-fold desc="C">> 
                ((C) t).ejecutar(ts);
                /*for (Object object : DeclaracionVariable.Vector) {
                    if(object instanceof Single){
                        salidatotal.add(((Single)object).ejecutar(ts));
                    }
                }*/
                salidatotal.add(DeclaracionVariable.Vector);
                // </editor-fold>
            }else if(t instanceof Lista){
                // <editor-fold desc="LISTA">> 
                salidatotal.add(((Lista) t).ejecutar(ts));
                // </editor-fold>
            }else{
                                    ErrorARIT e=new ErrorARIT("Semantico", t.toString(), " Valor no aceptado en List", 0, 0);
                    ListaErrores.Add(e);  
            }
                
        }
        return salidatotal;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return new Tipo(Tipo.tipo.LISTA);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"List\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");
        

        for (Object object : ListaExp) {
            cont=((Expresion)object).Dibujar(builder, nodoOp, cont);
        }       
        return cont; 
    }
}
