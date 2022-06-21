/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase de la tabla CompraModel
 * @author Oscar
 */
@Entity
@Table(name = "CompraModel")
public class CompraModel {

    //Varibles de la clase
    
    //Id de la tabla compra 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCompra", nullable = false, updatable = false, length = 10)
    private Integer idCompra;

    //total de la compra 
    @Column(name = "total", nullable = false, length = 10, precision = 2)
    private Double total;

    //Fecha de la compra 
    @Column(name = "fechaAdquirido", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime fechaAdquirido;
    
    
    /**
     * Constructor de la clase compra 
     */
    public CompraModel() {
    }
     
    //Get y Set de las variables de la clase 
    
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getFechaAdquirido() {
        return fechaAdquirido;
    }

    public void setFechaAdquirido(LocalDateTime fechaAdquirido) {
        this.fechaAdquirido = fechaAdquirido;
    }
}
