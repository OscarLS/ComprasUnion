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
 * Interface Service Detalle Compra
 *
 * @author Oscar
 */
@Service
public interface DetalleCompraService {

    /**
     * Método para guardar un detalle compra
     *
     * @param nuevoDetalle DetalleComora a guardar
     */
    public void save(DetalleCompraModel nuevoDetalle);

    /**
     * Método para elimianr un detalle compra
     *
     * @param id Id del detalle compra a eliminar
     */
    public void delete(Integer id);

    /**
     * Método para obtner un Detalle compra con el id especifico
     *
     * @param id Id del detalle compra a buscar
     * @return Optional con el resultado de la busqueda
     */
    public Optional<DetalleCompraModel> getById(Integer id);

    /**
     * Método para obtener todos los detalles compras
     *
     * @return Lisr con todos los detalles compras
     */
    public List<DetalleCompraModel> getAll();

    /**
     * Método para obtner todos los detalles compras que tengan la compra
     * especificada
     *
     * @param compra Compra a buscar
     * @return Collection con el resultado de la busqueda
     */
    public Collection<DetalleCompraModel> getAllCompra(CompraModel compra);
}
