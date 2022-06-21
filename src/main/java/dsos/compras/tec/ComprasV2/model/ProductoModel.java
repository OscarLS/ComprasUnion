package dsos.compras.tec.ComprasV2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Clase de la tabla ProductoModel
 * @author Oscar
 */
@Entity
@Table(name = "ProductoModel")
public class ProductoModel {

    //Varibles de la clase
    //Id de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProducto", nullable = false, updatable = false, length = 10)
    private Integer idProducto;

    //Precio compra del producto
    @Column(name = "precioCompra", nullable = false, length = 10, precision = 2)
    private Double precioCompra;

    //Precio de venta del producto
    @Column(name = "precioVenta", nullable = false, length = 10, precision = 2)
    private Double precioVenta;

    //Talla del producto
    @Column(name = "talla", nullable = false, length = 2, precision = 1)
    private Double talla;

    //Stock del producto
    @Column(name = "stock", nullable = false, length = 10)
    private Integer stock;

    //Color del producto
    @Column(name = "color", nullable = false, length = 20)
    private String color;

    //Relacion muchos a uno con la tabla Marca
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "marca", nullable = false)
    private MarcaModel marca;

    //Relacion muchos a uno con la tabla Modelo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modelo", nullable = false)
    private ModeloModel modelo;

    /**
     * Constructor de la clase ProductoModel
     */
    public ProductoModel() {
    }

    /**
     * Constructor de la clase ProductoModel
     * @param idProducto id del producto
     * @param precioCompra precio de compra
     * @param precioVenta precio de venta
     * @param talla talla del producto
     * @param stock stock del producto
     * @param color color  del producto
     * @param marca marca del producto
     * @param modelo modelo del producto
     */
    public ProductoModel(Integer idProducto, Double precioCompra, Double precioVenta, Double talla, Integer stock, String color, MarcaModel marca, ModeloModel modelo) {
        this.idProducto = idProducto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.talla = talla;
        this.stock = stock;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
    }
    
    //Metodos Get y Set de las variables de la clase
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer unidades) {
        this.stock = unidades;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MarcaModel getMarca() {
        return marca;
    }

    public void setMarca(MarcaModel marca) {
        this.marca = marca;
    }

    public ModeloModel getModelo() {
        return modelo;
    }

    public void setModelo(ModeloModel modelo) {
        this.modelo = modelo;
    }

}
