/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author obatres_
 */
public class Single extends Expresion{

    /**
     * @return the Valor
     */
    public Object getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    private Object Valor;
    private Tipo tipo;

    public Single(Object Valor, Tipo tipo) {
        this.Valor = Valor;
        this.tipo = tipo;
    }
    
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        
        return this.Valor;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return this.tipo;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Exp\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        String nodoOp1 = "nodo" + ++cont;
        builder.append(nodoOp1).append(" [label=\""+ Valor+ "\"];\n");
        builder.append(nodo).append(" -> ").append(nodoOp1).append(";\n");


        return cont;
    }
    
}
