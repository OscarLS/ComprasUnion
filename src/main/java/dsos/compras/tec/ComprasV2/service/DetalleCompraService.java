/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.service;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.model.DetalleCompraModel;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public interface DetalleCompraService {

    public void save(DetalleCompraModel nuevoDetalle);

    public DetalleCompraModel save2(DetalleCompraModel nuevoDetalle);
    
    public void delete(Integer id);

    //public void update(DetalleCompraModel compraUpdate, Integer id);
    public Optional<DetalleCompraModel> getById(Integer id);

    public List<DetalleCompraModel> getAll();

    public Collection<DetalleCompraModel> getAllCompra(CompraModel compra);
}
