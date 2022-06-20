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
 *
 * @author Oscar
 */
@Service
public interface CompraService {

    public void save(CompraModel nuevaCompra);

    public CompraModel save2(CompraModel nuevaCompra);
    
    public void delete(Integer id);

    public void update(CompraModel compraUpdate, Integer id);
    
    public Optional<CompraModel> getById(Integer id);

    public List<CompraModel> getAll();

    public Optional<CompraModel> getByCompra(Double total,LocalDateTime fechaAdquirido);
}
