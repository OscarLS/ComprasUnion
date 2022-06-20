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
 *
 * @author Oscar
 */
public interface CompraRepository extends JpaRepository<CompraModel, Integer>   {
    @Query("select s from CompraModel s where total=?1 and fechaAdquirido=?2")
    Optional<CompraModel> findByCompra(Double total,LocalDateTime fechaAdquirido);
}
