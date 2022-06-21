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
 * Interface repositorio ModeloModel 
 * @author Oscar
 */
public interface ModeloRepository extends JpaRepository<ModeloModel, Integer>  {
    /**
     *  Consulta Select de la tabla ModeloModel, busca modelos con la marca especifica
     * @param marca Marca a buscar 
     * @return Collection resultado de la consulta 
     */
    @Query("select s from ModeloModel s where s.marca = ?1")
    Collection<ModeloModel> findByMarca(MarcaModel marca);
}
