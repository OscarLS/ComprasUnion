/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.repository.ModeloRepository;

import dsos.compras.tec.ComprasV2.service.ModeloService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase implementa Service para los modelos
 *
 * @author Oscar
 */
@Service
public class ModeloServiceImplement implements ModeloService {

    //Varibles de la clase
    private final Log LOG = LogFactory.getLog(ModeloService.class);

    @Autowired
    private ModeloRepository modeloRepository;

    /**
     * Constructor de la clase 
     * @param modeloRepository Repositorio de los modelos
     */
    public ModeloServiceImplement(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }//Cierre del constructor

    /**
     * Método guarda un modelo 
     * @param modelo Modelo a guardar
     */
    @Override
    public void createModelo(ModeloModel modelo) {
        modeloRepository.save(modelo);
    }
    
    /**
     * Método que borra un modelo 
     * @param id Id del modelo a borrar
     */
    @Override
    public void delete(Integer id) {
        modeloRepository.deleteById(id);
    }

    /**
     * Método que actualiza un modelo 
     * @param modeloUpdate Modelo con los datos a actualizar 
     * @param id Id del modelo a actualizar
     */
    @Override
    public void update(ModeloModel modeloUpdate, Integer id) {
        ModeloModel modeloModel = modeloRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("El modelo no existe"));
        if(modeloUpdate.getNombreModelo()!=null)
        modeloModel.setNombreModelo(modeloUpdate.getNombreModelo());
        modeloRepository.save(modeloModel);
    }

    /**
     * Método que obtiene un modelo con el id especifico 
     * @param id Id del modelo a buscar
     * @return Optional con el resultado de la busqueda 
     */
    @Override
    public Optional<ModeloModel> getById(Integer id) {
        return modeloRepository.findById(id);
    }

    /**
     * Método que obtiene todos los modelos
     * @return  List con todos los modelos 
     */
    @Override
    public List<ModeloModel> getAll() {
        return modeloRepository.findAll();
    }

    /**
     * Método que obtiene todos los modelos que tengan la marca especifica 
     * @param marca Marca a buscar
     * @return Collection con el resultado de la busqueda 
     */
    @Override
    public Collection<ModeloModel> getAllMarca(MarcaModel marca) {
        return modeloRepository.findByMarca(marca);
    }
    
    /**
     * Método que guarda un modelo
     * @param nuevoModelo Modelo a guardar 
     */
    @Override
    public void save(ModeloModel nuevoModelo) {
        modeloRepository.save(nuevoModelo);
    }
}
