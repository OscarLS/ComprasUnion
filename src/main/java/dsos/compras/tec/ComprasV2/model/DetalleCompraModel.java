package dsos.compras.tec.ComprasV2.model;

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
 *
 * @author Oscar
 */
@Entity
@Table(name = "DetalleCompraModel")
public class DetalleCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDetalleCompra", nullable = false, updatable = false, length = 10)
    private Integer idDetalleCompra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "compra", nullable = false)
    private CompraModel compra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "producto" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductoModel producto;

    @Column(name = "cantidad", nullable = false, length = 10)
    private Integer cantidad;

    public DetalleCompraModel() {
    }

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
