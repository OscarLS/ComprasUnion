/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "CompraModel")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCompra", nullable = false, updatable = false, length = 10)
    private Integer idCompra;

    @Column(name = "total", nullable = false, length = 10, precision = 2)
    private Double total;

    @Column(name = "fechaAdquirido", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaAdquirido;


    public CompraModel() {
        
    }

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

    public LocalDate getFechaAdquirido() {
        return fechaAdquirido;
    }

    public void setFechaAdquirido(LocalDate fechaAdquirido) {
        this.fechaAdquirido = fechaAdquirido;
    }

}
