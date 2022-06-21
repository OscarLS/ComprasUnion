/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.repository.CompraRepository;
import dsos.compras.tec.ComprasV2.service.CompraService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase es el service de las compras
 *
 * @author Oscar
 */
@Service
public class CompraServiceImplement implements CompraService {

    //Varibles de la clase
    private final Log LOG = LogFactory.getLog(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    /**
     * Constructor de la clase CompraServiceImplement
     *
     * @param compraRepository Repositorio de la compra
     */
    public CompraServiceImplement(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }//Cierre del constructor

    /**
     * Método que guarda una compra
     *
     * @param nuevaCompra Compra a guardar
     */
    @Override
    public void save(CompraModel nuevaCompra) {
        compraRepository.save(nuevaCompra);
    }

    /**
     * Método que borra una compra
     *
     * @param id Id de la compra a borrar
     */
    @Override
    public void delete(Integer id) {
        compraRepository.deleteById(id);
    }

    /**
     * Método de actualiza una compra
     * @param compraUpdate Compra con los datos a actulizar 
     * @param id Id de la compra a actualizar 
     */
    @Override
    public void update(CompraModel compraUpdate, Integer id) {
        CompraModel productoModel = compraRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("La compra no existe"));
        if (compraUpdate.getTotal() != null) {
            productoModel.setTotal(compraUpdate.getTotal());
        }

        compraRepository.save(compraUpdate);
    }

    /**
     * Método que obtiene una compra con un id dado    
     * @param id Id de la compra a actualizar
     * @return Optional con la compra que tenga el id dado
     */
    @Override
    public Optional<CompraModel> getById(Integer id) {
        return compraRepository.findById(id);
    }

    /**
     * Método que obtiene todas las compras
     * @return List con todas las compras 
     */
    @Override
    public List<CompraModel> getAll() {
        return compraRepository.findAll();
    }

    /**
     * Método que obtiene una compra que concida con los datos dados
     * @param total Total de la compra
     * @param fechaAdquirido Fecha en que se realizo la compra 
     * @return Optional con la compra 
     */
    @Override
    public Optional<CompraModel> getByCompra(Double total, LocalDateTime fechaAdquirido) {
        return compraRepository.findByCompra(total, fechaAdquirido);
    }

}
