package dsos.compras.tec.ComprasV2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase de la tabla DetalleCompraModel
 * @author Oscar
 */
@Entity
@Table(name = "DetalleCompraModel")
public class DetalleCompraModel {

    //Varibles de la clase
    //Id de la tabla 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDetalleCompra", nullable = false, updatable = false, length = 10)
    private Integer idDetalleCompra;

    //Relacion muchos a uno con la tabla (clase) CompraModel 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //Ignora este variable (No se muestra)
    @JsonIgnore
    @JoinColumn(name = "compra", nullable = false)
    private CompraModel compra;

    //Relacion muchos a uno con la tabla (clase) ProductoModel
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "producto" , nullable = false)
    private ProductoModel producto;

    //Cantidad de producto comprado 
    @Column(name = "cantidad", nullable = false, length = 10)
    private Integer cantidad;

    /**
     * Constructor de la clase DetalleCompraModel
     */
    public DetalleCompraModel() {
    }
    
    //Metodos Get y Set de las variables de la clase

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public CompraModel getCompra() {
        return compra;
    }

    public void setCompra(CompraModel compra) {
        this.compra = compra;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
