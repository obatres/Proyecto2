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
import arbol.Simbolo;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author obatres_
 */
public class BarPlot extends Instruccion{

    private ArrayList<Object> ParametrosBarPlot;

    ArrayList<Object> Numericos = new ArrayList<>();
    ArrayList<Object> Labels    = new ArrayList<>();
    String main;
    String xlab;
    String ylab;
    public BarPlot(ArrayList<Object> ParametrosBarPlot) {
        this.ParametrosBarPlot = ParametrosBarPlot;
    }  
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {

        if (ParametrosBarPlot.size()==5){

            if(((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosBarPlot.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)||((Expresion)ParametrosBarPlot.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.INT))
                    Numericos = (ArrayList) ((Expresion)ParametrosBarPlot.get(0)).ejecutar(ts);
            }  else{
                //Parametro incorrecto, se esperaba un listado de numeros
                    ErrorARIT e=new ErrorARIT("Semantico", ParametrosBarPlot.get(0).toString(), " Parametro incorrecto, se esperaba un listado de numeros", 0, 0);
                    ListaErrores.Add(e);
            }
            if(((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts) instanceof String){
                xlab = ((Expresion)ParametrosBarPlot.get(1)).ejecutar(ts).toString();
            }else{
                //SE ESPERABA UN LABEL PARA EL EJE X
                                    ErrorARIT e=new ErrorARIT("Semantico", ParametrosBarPlot.get(1).toString(), " SE ESPERABA UN LABEL PARA EL EJE X", 0, 0);
                    ListaErrores.Add(e);
            }
            if(((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts) instanceof String){
                ylab=((Expresion)ParametrosBarPlot.get(2)).ejecutar(ts).toString();
            }else{
                //SE ESPERABA UN LABEL PARA EL EJE Y
                    ErrorARIT e=new ErrorARIT("Semantico", ParametrosBarPlot.get(2).toString(), " SE ESPERABA UN LABEL PARA EL EJE Y", 0, 0);
                    ListaErrores.Add(e);
            }
            if(((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts) instanceof String){
                main = ((Expresion)ParametrosBarPlot.get(3)).ejecutar(ts).toString();
            }else{
                //SE ESPERABA EL NOMBRE DE LA GRAFICA
                    ErrorARIT e=new ErrorARIT("Semantico", ParametrosBarPlot.get(3).toString(), " SE ESPERABA EL NOMBRE DE LA GRAFICA", 0, 0);
                    ListaErrores.Add(e);          
            }
            if(((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosBarPlot.get(4)).GetTipo(ts).tp.equals(Tipo.tipo.STRING)){
                    Labels = (ArrayList) ((Expresion)ParametrosBarPlot.get(4)).ejecutar(ts);
                }else{
                    ErrorARIT e=new ErrorARIT("Semantico", "Labels", " Error de tipo en los labes", 0, 0);
                    ListaErrores.Add(e);
                }
                
            }
        }else{
            //NUMERO DE PARAMETROS INCORRECTOS PARA HACER LA GRAFICA
                                ErrorARIT e=new ErrorARIT("Semantico", "Barplot", " NUMERO DE PARAMETROS INCORRECTOS PARA HACER LA GRAFICA", 0, 0);
                    ListaErrores.Add(e);
        }
         
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(Numericos.size()==Labels.size()){
            for (int i = 0; i < Numericos.size(); i++) {
                dataset.setValue(Double.parseDouble((Numericos.get(i)).toString()), main,(Labels.get(i)).toString());
            }              
        }else if (Numericos.size()>Labels.size()){
            //REPORTAR ERROR DE TAMAÑO DE VECTORES
                                ErrorARIT e=new ErrorARIT("Semantico", "Barplot", " ERROR DE TAMAÑO DE VECTORES", 0, 0);
                    ListaErrores.Add(e);
            for (int i = 0; i < Labels.size(); i++) {
                dataset.setValue(Double.parseDouble(Numericos.get(i).toString()), main,(Labels.get(i).toString()));
            }
            for (int i = Labels.size(); i < Numericos.size(); i++) {
                dataset.setValue(Double.parseDouble((Numericos.get(i)).toString()), main,"Desconocido"+i);
            }
        }
  
        JFreeChart chart =  ChartFactory.createBarChart3D(main,xlab, ylab, dataset, PlotOrientation.VERTICAL,true,true, false);
        ChartPanel panel = new ChartPanel(chart);
        JFrame ventana = new JFrame("grafica");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"BarPlot\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp = "nodo" + ++cont;
        builder.append(nodoOp).append(" [label=\"" + "Contenido" + "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp).append(";\n");

        for (int i = 0; i < ParametrosBarPlot.size(); i++) {
            cont=((Expresion)ParametrosBarPlot.get(i)).Dibujar(builder, nodoOp, cont);
        }
        return cont; 
    }
    
}
