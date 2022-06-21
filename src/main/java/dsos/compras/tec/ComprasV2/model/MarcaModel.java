/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.model;

import javax.persistence.*;

/**
 * Clase de la tabla MarcaModel
 *
 * @author Oscar
 */
@Entity
@Table(name = "MarcaModel")
public class MarcaModel {

    //Varibles de la clase
    //Id de la tabla 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMarca", nullable = false, updatable = false, length = 10)
    private Integer idMarca;

    //Nombre de la marca
    @Column(name = "nombreMarca", nullable = false, length = 20)
    private String nombreMarca;

    /**
     * Constructor de la clase MarcaModel
     */
    public MarcaModel() {
    }

    //Metodos Get y Set de las variables de la clase
    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

}
