/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.model.DetalleCompraModel;
import dsos.compras.tec.ComprasV2.repository.DetalleCompraRepository;
import dsos.compras.tec.ComprasV2.service.DetalleCompraService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *Esta clase es el service de los detalles compras
 * @author Oscar
 */
@Service
public class DetalleCompraImplement implements DetalleCompraService {

    //Varibles de la clase
    private final Log LOG = LogFactory.getLog(DetalleCompraService.class);

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    /**
     * Constructor de la clase DetalleCompraImplement
     * @param detalleCompraRepository Repositorio de los detalles compras 
     */
    public DetalleCompraImplement(DetalleCompraRepository detalleCompraRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
    }//Cierre del constructor

    /**
     * Método que guarda un detalle compra
     * @param nuevoDetalle Detalle compra a guardar
     */
    @Override
    public void save(DetalleCompraModel nuevoDetalle) {
        detalleCompraRepository.save(nuevoDetalle);
    }

    /**
     * Método que borra un detalle compra
     * @param id Id del detalle compra 
     */
    @Override
    public void delete(Integer id) {
        detalleCompraRepository.deleteById(id);
    }

    /**
     * Método que obtiene un detalle compra con un id dado (id)
     * @param id Id del detalle compra
     * @return Optional con el detalle compra obtenido
     */
    @Override
    public Optional<DetalleCompraModel> getById(Integer id) {
        return detalleCompraRepository.findById(id);
    }

    /**
     * Método que obtien todos los detalles compras 
     * @return  List con todos los detalles compras 
     */
    @Override
    public List<DetalleCompraModel> getAll() {
        return detalleCompraRepository.findAll();
    }
    
    /**
     * Método que ontiene todos los detalles compras que tengan una compra dada
     * @param compra Compra a buscar 
     * @return 
     */
    @Override
    public Collection<DetalleCompraModel> getAllCompra(CompraModel compra) {
        return detalleCompraRepository.findByModeloMarca( compra);
    }
    
    
    

}
