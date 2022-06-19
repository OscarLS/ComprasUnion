package dsos.compras.tec.ComprasV2.repository;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.model.ProductoModel;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {

    @Query("select s from ProductoModel s where s.marca = ?1")
    Collection<ProductoModel> findByMarca(MarcaModel marca);

    @Query("select s from ProductoModel s where s.modelo = ?1")
    Collection<ProductoModel> findByModelo(ModeloModel modelo);

    @Query("select s from ProductoModel s where s.modelo = ?1 and s.marca = ?2")
    Collection<ProductoModel> findByModeloMarca(ModeloModel modelo, MarcaModel marca);

    @Query("select s from ProductoModel s where s.talla = ?1 and s.color = ?2 and s.modelo = ?3 and s.marca = ?4")
    Optional<ProductoModel> findByDatos(Double talla, String color, ModeloModel modelo, MarcaModel marca);

}
