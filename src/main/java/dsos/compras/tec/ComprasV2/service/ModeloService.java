/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.service;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Interface service modelo
 *
 * @author Oscar
 */
@Service
public interface ModeloService {

    /**
     * Método para crear un nuevo modelo
     *
     * @param modelo Modelo a guardar
     */
    public void createModelo(ModeloModel modelo);

    /**
     * Método para borrar un modelo
     *
     * @param id Id del modelo a borrar
     */
    public void delete(Integer id);

    /**
     * Método para actualizar un modelo
     *
     * @param modeloUpdate Modelo con los datos a actualizar
     * @param id Id del modelo a actualizar
     */
    public void update(ModeloModel modeloUpdate, Integer id);

    /**
     * Método para obtenr un modelo con el id dado
     *
     * @param id Id del modelo a buscar
     * @return Optional con el resultado de la busqueda
     */
    public Optional<ModeloModel> getById(Integer id);

    /**
     * Método para obtenr todos los modelos
     *
     * @return List con todos los modelos
     */
    public List<ModeloModel> getAll();

    /**
     * Método para obtener todos los modelos que tengan la marca dada
     *
     * @param marca Marca a buscar
     * @return Collection con el resultado de la busqueda
     */
    public Collection<ModeloModel> getAllMarca(MarcaModel marca);

    public void save(ModeloModel nuevoModelo);
}
