/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.repository.CompraRepository;
import dsos.compras.tec.ComprasV2.service.CompraService;
import java.time.LocalDate;
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
public class CompraServiceImplement implements CompraService {

    private final Log LOG = LogFactory.getLog(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    public CompraServiceImplement(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public void save(CompraModel nuevaCompra) {
        compraRepository.save(nuevaCompra);
    }
    
    @Override
    public CompraModel save2(CompraModel com){
        return compraRepository.save(com);
    }

    @Override
    public void delete(Integer id) {
        compraRepository.deleteById(id);
    }

    /*
    @Override
    public void update(CompraModel compraUpdate, Integer id) {
        CompraModel productoModel = compraRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("La compra no existe"));
        productoModel.setFechaAdquirido(compraUpdate.getFechaAdquirido());
        productoModel.s
        compraRepository.save(productoModel);
    }
     */
    @Override
    public Optional<CompraModel> getById(Integer id) {
        return compraRepository.findById(id);
    }

    @Override
    public List<CompraModel> getAll() {
        return compraRepository.findAll();
    }
    @Override
    public Optional<CompraModel> getByCompra(Double total,LocalDate fechaAdquirido) {
        return compraRepository.findByCompra(total, fechaAdquirido);
    }
    
}
