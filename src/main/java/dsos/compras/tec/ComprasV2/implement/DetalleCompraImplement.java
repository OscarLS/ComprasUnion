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
 *
 * @author Oscar
 */
@Service
public class DetalleCompraImplement implements DetalleCompraService {

    private final Log LOG = LogFactory.getLog(DetalleCompraService.class);

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public DetalleCompraImplement(DetalleCompraRepository detalleCompraRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
    }

    @Override
    public void save(DetalleCompraModel nuevoDetalle) {
        detalleCompraRepository.save(nuevoDetalle);
    }

    @Override
    public DetalleCompraModel save2(DetalleCompraModel nuevoDetalle) {
        return detalleCompraRepository.save(nuevoDetalle);
    }
    
    @Override
    public void delete(Integer id) {
        detalleCompraRepository.deleteById(id);
    }

    /*
    @Override
    public void update(DetalleCompraModel compraUpdate, Integer id) {
        DetalleCompraModel productoModel = detalleCompraRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("La compra no existe"));
        productoModel.setFechaAdquirido(compraUpdate.getFechaAdquirido());
        productoModel.s
        detalleCompraRepository.save(productoModel);
    }
     */
    @Override
    public Optional<DetalleCompraModel> getById(Integer id) {
        return detalleCompraRepository.findById(id);
    }

    @Override
    public List<DetalleCompraModel> getAll() {
        return detalleCompraRepository.findAll();
    }
    @Override
    public Collection<DetalleCompraModel> getAllCompra(CompraModel compra) {
        return detalleCompraRepository.findByModeloMarca( compra);
    }
    
    
    

}
