/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.repository;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.model.DetalleCompraModel;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface repositorio detalle compra 
 * @author Oscar
 */
public interface DetalleCompraRepository extends JpaRepository<DetalleCompraModel, Integer> {
    /**
     * Comsulta Select de la tabla DetalleCompraModel que tenga la compra especifia 
     * @param compra Compra a buscar
     * @return Collection con el resultado de la consulta 
     */
    @Query("select s from DetalleCompraModel s where s.compra = ?1")
    Collection<DetalleCompraModel> findByModeloMarca(CompraModel compra);
}
