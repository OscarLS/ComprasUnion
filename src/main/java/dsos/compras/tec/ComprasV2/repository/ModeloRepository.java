/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dsos.compras.tec.ComprasV2.repository;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Oscar
 */
public interface ModeloRepository extends JpaRepository<ModeloModel, Integer>  {
    @Query("select s from ProductoModel s where s.marca = ?1")
    Collection<ModeloModel> findByMarca(MarcaModel marca);
}
