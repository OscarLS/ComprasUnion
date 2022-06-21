/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.repository.MarcaRepository;
import dsos.compras.tec.ComprasV2.service.MarcaService;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase implementa Service para las marcas
 * @author Oscar
 */
@Service
public class MarcaServiceImplement implements MarcaService {

    //Varibles de la clase
    private final Log LOG = LogFactory.getLog(MarcaService.class);

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     * Constructor de la clase MarcaServiceImplement 
     * @param marcaRepository Repositorio de marca
     */
    public MarcaServiceImplement(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }//Cierre del constructor

    /**
     * Método guarda una marca 
     * @param marca Marca a guardar
     */
    @Override
    public void createMarca(MarcaModel marca) {
        marcaRepository.save(marca);
    }

    /**
     * Método que borra una marca
     * @param id Id marca a borrar 
     */
    @Override
    public void delete(Integer id) {
        marcaRepository.deleteById(id);
    }

    /**
     * Método que actualiza una marca con el id dado
     * @param marcaUpdate Marca con los datos a actualizar 
     * @param id Id de la marca a actualziar 
     */
    @Override
    public void update(MarcaModel marcaUpdate, Integer id) {
        MarcaModel marcaModel = marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("La marca no existe"));
        marcaModel.setNombreMarca(marcaUpdate.getNombreMarca());
        marcaRepository.save(marcaModel);
    }

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public Optional<MarcaModel> getById(Integer id) {
        return marcaRepository.findById(id);
    }
    
    /**
     * Método que obtiene una marca con un nombre dado 
     * @param nombre Nombre de la marca a buscar
     * @return Optional con el resultado de la busqueda 
     */
    @Override
    public Optional<MarcaModel> getByNombre(String nombre) {
        return marcaRepository.findByNombreMarca(nombre);
    }

    /**
     * Método obtiene todas las marcas
     * @return List con todas las marcas 
     */
    @Override
    public List<MarcaModel> getAll() {
        return marcaRepository.findAll();
    }
    
    /**
     * Método guarda una nueva marca 
     * @param nuevaMarca 
     */
    @Override
    public void save(MarcaModel nuevaMarca) {
        marcaRepository.save(nuevaMarca);
    }
}
