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
public class Tipo {


    
    public tipo tp;
    
    public String TipoVar;
    
    public String TipoArr;
    
    public enum tipo{
        INT, // Variables de tipo entero ej: 1 10 15 54 
        STRING, // Variables de tipo cadena ej: asdkjashd, hola, mundo
        BOOLEAN, // Variables de tipo booleano ej: true, false 
        CHAR, // Variables de tipo caracter ej: a, 1, ;, >
        DOUBLE, // Variable de tipo Decimal ej: 2.2654
        OBJETO, // Variable de tipo objeto ej: carro, casa, canvas (segun sean declaradas las clases en el lenguaje)
        NULL, // Valor Null
        LISTA, //Tipo lista
        NEW, // Instancia de un nuevo objeto ej: Carro c = new Carro
        CALL, // Llamada a un metodo o funcion ej: Motor(), Hola(sd)
        KEY, //Declaracion de una llave
        DEF
    }
      
    public Tipo(tipo tp){
        this.tp=tp;
    }

    public Tipo(String TipoVar) {
        this.TipoVar=TipoVar;
    }
    
    public boolean isString(){
        if(tp==tipo.STRING){
            return true;
        }
        return false;
    }
    
    public boolean isInt(){
        if(tp==tipo.INT){
            return true;
        }
        return false;
               
    }
    
    public boolean isDouble(){
        if(tp==tipo.DOUBLE){
            return true;
        }
        return false;
    }
    
    public boolean isBoolean(){
        if(tp==tipo.BOOLEAN){
            return true;
        }
        return false;
    }
    
    public boolean isChar(){
        if(tp==tipo.CHAR){
            return true;
        }
        return false;
    }

    public boolean isObject(){
        if(tp==tipo.OBJETO){
            return true;
        }
        return false;
    }   
    
    public boolean isCall(){
        if(tp==tipo.CALL){
            return true;
        }
        return false;
    }
    
    public boolean isKey(){
        if(tp==tipo.KEY){
            return true;
        }
        return false;
    }

    public boolean isNew(){
        if(tp==tipo.NEW){
            return true;
        }
        return false;
    }

    public boolean isNull(){
        if(tp==tipo.NULL){
            return true;
        }
        return false;
    }
    
    public boolean isDef(){
        if(tp==tipo.DEF){
            return true;
        }
        return false;
    }
    
    public boolean isList(){
        if(tp==tipo.LISTA){
            return true;
        }
        return false;
    }
}

