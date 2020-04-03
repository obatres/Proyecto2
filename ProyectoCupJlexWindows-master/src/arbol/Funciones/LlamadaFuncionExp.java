/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import InterfazGrafica.VentanaPrincipal;
import TabladeSimbolos.ReporteTabla;
import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.Retorno.Retorno;
import arbol.Simbolo;
import arbol.SwitchCase.Break;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class LlamadaFuncionExp extends Expresion{

    private ArrayList<Expresion> ParametrosLlamada;
    private String identificadorLlamada;

    LinkedList<Funcion> TablaFunciones = proyectocupjlexwindows.ProyectoCupJlexWindows.tf;

    ArrayList<Parametro> ParametrosDeclaracion;
    
    LinkedList<Nodo> InstruccionesDeclaracion;
    
    Tipo tipoRetorno;
    public LlamadaFuncionExp(ArrayList<Expresion> ParametrosLlamada, String identificadorLlamada) {
        this.ParametrosLlamada = ParametrosLlamada;
        this.identificadorLlamada = identificadorLlamada;
    }
    
    public LlamadaFuncionExp(String identificadorLlamada) {
        this.identificadorLlamada = identificadorLlamada;   
    }
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        
        ArrayList<Object> Vector;
        if (proyectocupjlexwindows.ProyectoCupJlexWindows.tf.Existe(identificadorLlamada)){
            // <editor-fold desc="LA FUNCION SI SE DECLARO">> 
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            ParametrosDeclaracion=proyectocupjlexwindows.ProyectoCupJlexWindows.tf.getParametros(identificadorLlamada);
            InstruccionesDeclaracion=proyectocupjlexwindows.ProyectoCupJlexWindows.tf.getInstrucciones(identificadorLlamada);
            if(ParametrosLlamada==null&&ParametrosDeclaracion==null){
                //<editor-fold desc="LLAMADA SIN PARAMETROS">> 
                
                // <editor-fold desc="EJECUCION DE INSTRUCCIONES">> 
                for (Nodo n : InstruccionesDeclaracion) {
                    
                    if(n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);
                    }else if (n instanceof Expresion){
                        if(n instanceof Retorno){
                            tipoRetorno = new Tipo (((Retorno) n).GetTipo(tablalocal).tp);
                            return ((Expresion) n).ejecutar(tablalocal);    
                        }   
                    }else if(n instanceof Break){
                        return null;
                    }else{
                    ErrorARIT e=new ErrorARIT("Semantico", n.toString(), " error en la instruccion", 0, 0);
                    ListaErrores.Add(e);    
                    }
                }
                
                // </editor-fold>
                
                // </editor-fold>
            }else if(ParametrosLlamada.size()==ParametrosDeclaracion.size()){
                // <editor-fold desc="LLAMADA CON PARAMETROS">> 
                for (int i = 0; i < ParametrosLlamada.size(); i++) {
                    if(ParametrosLlamada.get(i).GetTipo(tablalocal).tp.equals(Tipo.tipo.DEF)){
                        // <editor-fold desc="PARAMETROS DEFAULT">> 
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosDeclaracion.get(i).getValorParametro().ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal));                       
                        // </editor-fold>
                    }else{
                        // <editor-fold desc="PARAMETROS GUARDADOS">> 
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosLlamada.get(i).ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosLlamada.get(i).GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosLlamada.get(i).GetTipo(tablalocal));
                        // </editor-fold>
                    }
                }
                
                // <editor-fold desc="EJECUCION DE INSTRUCCIONES">> 
                
                
                for (Nodo n : InstruccionesDeclaracion) {
                    
                    if(n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);
                    }else if (n instanceof Expresion){
                        if(n instanceof Retorno){
                            tipoRetorno = new Tipo (((Retorno) n).GetTipo(tablalocal).tp);
                            return ((Expresion) n).ejecutar(tablalocal);    
                        }   
                    }else if(n instanceof Break){
                        return null;
                    }else{
                    ErrorARIT e=new ErrorARIT("Semantico", n.toString(), " error en la instruccion", 0, 0);
                    ListaErrores.Add(e);  
                    }

             
                }
                
                
                
                
                // </editor-fold>
                
                 // </editor-fold>
            }else{
                System.out.println("cantidad de parametros no coincide");
                  ErrorARIT e=new ErrorARIT("Semantico", identificadorLlamada, " cantidad de parametros no coincide", 0, 0);
                    ListaErrores.Add(e);   
                
            }
                            VentanaPrincipal.RTS.add(new ReporteTabla(tablalocal, VentanaPrincipal.ambito++));
        // </editor-fold>
        }else{
            // <editor-fold desc="LA FUNCION NO SE DECLARO">> 
            System.out.println("Esta funcion "+identificadorLlamada+" no esta definida");
                    ErrorARIT e=new ErrorARIT("Semantico", identificadorLlamada, " Esta funcion "+identificadorLlamada+" no esta definida", 0, 0);
                    ListaErrores.Add(e);  
                        // </editor-fold>
        }
        return null;        
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {          
        return tipoRetorno;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"llamada a funcion\"]; \n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp1 = "nodo" + ++cont;
        builder.append(nodoOp1).append(" [label=\""+ identificadorLlamada + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp1).append(";\n");
        
        String nodoOp2 = "nodo" + ++cont;
        builder.append(nodoOp2).append(" [label=\""+ "contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp2).append(";\n");  
        if(ParametrosLlamada!=null){
         for (Expresion expresion : ParametrosLlamada) {
            cont=expresion.Dibujar(builder, nodoOp2, cont);
        }           
        }

        return cont;
    }
    
}
