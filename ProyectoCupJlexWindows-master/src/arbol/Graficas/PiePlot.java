/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Graficas;

import arbol.Errores.ErroSemantico.ErrorARIT;
import arbol.Errores.ErroSemantico.ListaErrores;
import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import java.lang.NullPointerException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author obatres_
 */
public class PiePlot extends Instruccion{
    private ArrayList<Object> ParametrosPie;

    ArrayList<Object> Numericos = new ArrayList<>();
    ArrayList<Object> Labels    = new ArrayList<>();
    String main;
    
    public PiePlot(ArrayList<Object> ParametrosPie) {
        this.ParametrosPie = ParametrosPie;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(ParametrosPie.size()==3){
            if(((Expresion)ParametrosPie.get(0)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosPie.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)||((Expresion)ParametrosPie.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.INT)){
                    Numericos = (ArrayList<Object>) ((Expresion)ParametrosPie.get(0)).ejecutar(ts);
                                    //System.out.println(Numericos);
                }else{
                    //Parametro incorrecto, se esperaba un listado de numeros
                                ErrorARIT e=new ErrorARIT("Semantico", "PiePlot", " Parametro incorrecto, se esperaba un listado de numeros", 0, 0);
                    ListaErrores.Add(e);             
                }
            }
            if(((Expresion)ParametrosPie.get(1)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosPie.get(1)).GetTipo(ts).tp.equals(Tipo.tipo.STRING)){
                    Labels = (ArrayList<Object>) ((Expresion)ParametrosPie.get(1)).ejecutar(ts);
                    //System.out.println(Labels);
                }else{
                    //Parametro incorrecto, se esperaba un listado de labels
                                ErrorARIT e=new ErrorARIT("Semantico", "PiePlot", " Parametro incorrecto, se esperaba un listado de labels", 0, 0);
                    ListaErrores.Add(e);  
                }
            }
            if(((Expresion)ParametrosPie.get(2)).ejecutar(ts) instanceof String){
                main=((Expresion)ParametrosPie.get(2)).ejecutar(ts).toString();
                //System.out.println(main);
            }else{
                //Parametro incorrecto, se esperaba una cadena
                                ErrorARIT e=new ErrorARIT("Semantico", "PiePlot", " Parametro incorrecto, se esperaba una cadena", 0, 0);
                    ListaErrores.Add(e); 
            }
        }else{
            //CANTIDAD DE PARAMETROS INCORRECTOS
                                ErrorARIT e=new ErrorARIT("Semantico", "PiePlot", " NUMERO DE PARAMETROS INCORRECTOS PARA HACER LA GRAFICA", 0, 0);
                    ListaErrores.Add(e);
        }
        
        DefaultPieDataset data = new DefaultPieDataset();
        if(Numericos.size()==Labels.size()){
            for (int i = 0; i < Numericos.size(); i++) {
                //System.out.println(Labels.get(i).getClass());
                data.setValue(Labels.get(i).toString(),Double.parseDouble(Numericos.get(i).toString()));
            }            
        }else if(Numericos.size()>Labels.size()){
            //REPORTAR ERROR DE TAMAÑO DE VECTORES
                                ErrorARIT e=new ErrorARIT("Semantico", "PiePlot", " ERROR DE TAMAÑO DE VECTORES", 0, 0);
                    ListaErrores.Add(e);
            for (int i = 0; i < Labels.size(); i++) {
                data.setValue((Labels.get(i)).toString(),Double.parseDouble(Numericos.get(i).toString()));   
            }
            for (int i = Labels.size(); i < Numericos.size(); i++) {
                data.setValue("Desconocido"+i,Double.parseDouble(Numericos.get(i).toString()));
            }
        }

        
        JFreeChart chart2 = ChartFactory.createPieChart(main, data,true,true,false);
        ChartPanel panel2 = new ChartPanel(chart2);
        JFrame ventana2 = new JFrame("grafica");
        ventana2.getContentPane().add(panel2);
        ventana2.pack();
        ventana2.setVisible(true);
        ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"PiePlot\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");

        for (int i = 0; i < ParametrosPie.size(); i++) {
            cont=((Expresion)ParametrosPie.get(i)).Dibujar(builder, nodoOp, cont);
        }
        return cont; 
    }
    
    
    
    
}
