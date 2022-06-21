/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.service;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Interface Service Marca
 *
 * @author Oscar
 */
@Service
public interface MarcaService {

    /**
     * Método para crear una nueva marca
     *
     * @param marca Marca a guardar
     */
    public void createMarca(MarcaModel marca);

    /**
     * Método para borrar una marca con el id especifico
     *
     * @param id Id a borrar
     */
    public void delete(Integer id);

    /**
     * Método para actualizar una marca con los datos especificos
     *
     * @param marcaUpdate Marca con los datos a actualziar
     * @param id Id de la marca a actualizar
     */
    public void update(MarcaModel marcaUpdate, Integer id);

    /**
     * Método para obtener una marca con el id especifico
     *
     * @param id Id de la marca a buscar
     * @return Optional con el resultado de la busqueda
     */
    public Optional<MarcaModel> getById(Integer id);

    /**
     * Método para obtener todas las marcas
     *
     * @return List con todas las marcas
     */
    public List<MarcaModel> getAll();

    /**
     * Método para obtner una marca con el nombre especifico
     *
     * @param nombre Nombre de la marca a buscar
     * @return Optional con el resultado de la compra
     */
    public Optional<MarcaModel> getByNombre(String nombre);

    /**
     * Método para guardar una marca
     *
     * @param nuevaMarca Marca a guardar
     */
    public void save(MarcaModel nuevaMarca);
}
