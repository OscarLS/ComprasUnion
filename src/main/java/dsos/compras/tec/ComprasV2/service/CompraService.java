/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.service;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Interface Service Compra
 *
 * @author Oscar
 */
@Service
public interface CompraService {

    /**
     * Método para guardar una compra
     *
     * @param nuevaCompra Compra a guardar
     */
    public void save(CompraModel nuevaCompra);

    /**
     * Método para eliminar un modelo
     *
     * @param id Id del modelo a eliminar
     */
    public void delete(Integer id);

    /**
     * Método para actulizar producto
     *
     * @param compraUpdate producto con los datos a actualizar
     * @param id Ide del prodcuto
     */
    public void update(CompraModel compraUpdate, Integer id);

    /**
     * Método para obtener una compra con el ide especifico
     *
     * @param id Id de la compra
     * @return Optional con el resultado de la busqueda
     */
    public Optional<CompraModel> getById(Integer id);

    /**
     * Método para obtener todas las compras
     *
     * @return List con todas las compras
     */
    public List<CompraModel> getAll();

    /**
     * Método para obtener una compra con un total y una fecha especifica
     *
     * @param total Total de la compra
     * @param fechaAdquirido Fecha de la compra
     * @return
     */
    public Optional<CompraModel> getByCompra(Double total, LocalDateTime fechaAdquirido);
}
