/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.model;

import javax.persistence.*;

/**
 * Clase de la tabla ModeloModel
 *
 * @author Oscar
 */
@Entity
@Table(name = "ModeloModel")
public class ModeloModel {

    //Varibles de la clase
    //Id de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idModelo", nullable = false, updatable = false, length = 10)
    private Integer idModelo;

    //Nombre del modelo 
    @Column(name = "nombreModelo", nullable = false, length = 20)
    private String nombreModelo;

    //Relacion muchos a uno con la tabla MarcaModel
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "marca", nullable = false)
    private MarcaModel marca;

    /**
     * Constructor de la clase ModeloModel
     */
    public ModeloModel() {
    }

    //Metodos Get y Set de las variables de la clase
    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String numeroModelo) {
        this.nombreModelo = numeroModelo;
    }

    public MarcaModel getMarca() {
        return marca;
    }

    public void setMarca(MarcaModel marca) {
        this.marca = marca;
    }

}
