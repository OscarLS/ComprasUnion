/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsos.compras.tec.ComprasV2.utils;

import java.util.LinkedList;

/**
 * clase CustomResponse 
 *
 * @author Adrian
 */
public class CustomResponse {

    //Varibles de la clase 
    private Integer httpCode;
    private Object data;
    private String message;

    /**
     * Constructor de la clase CustomResponse inicializa los datos de las variables de las clases con los datos dados
     * @param code Codigo HTTP 
     * @param data Datos del costom 
     * @param message Mensaje del costom
     */
    public CustomResponse(int code, Object data, String message) {
        this.httpCode = code;
        this.data = new LinkedList();
        setData(data);
        this.message = message;
    }//Cierre del método

    /**
     * Segundo Constructor de la clase CustomResponse inicialisa los variables, con datos pre-establecidos  
     */
    public CustomResponse() {
        this.httpCode = 200;
        this.data = new LinkedList();
        this.message = "Ok";
    }

    /**
     * Métodos GET y SET de las variables de la clase 
     */
    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
