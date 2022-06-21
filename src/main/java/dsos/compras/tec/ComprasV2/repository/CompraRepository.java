/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.repository;

import dsos.compras.tec.ComprasV2.model.CompraModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *  Interface repositorio de compra
 * @author Oscar
 */
public interface CompraRepository extends JpaRepository<CompraModel, Integer>   {
    /**
     * Consulta de la tabla CompraModel buscando una compra con los datos específicos 
     * @param total Total de la compra
     * @param fechaAdquirido Fecha de la compra 
     * @return Optional con el resultado de la consulta 
     */
    @Query("select s from CompraModel s where total=?1 and fechaAdquirido=?2")
    Optional<CompraModel> findByCompra(Double total,LocalDateTime fechaAdquirido);
}
