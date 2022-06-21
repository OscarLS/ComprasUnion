package dsos.compras.tec.ComprasV2.repository;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.model.ProductoModel;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface repository ProductoModel
 *
 * @author Oscar
 */
public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {

    /**
     * Consulta Select de la tabla ProductoModel donde la marca sea la
     * especificada
     *
     * @param marca Marca a buscar
     * @return Collection resultado de la compra
     */
    @Query("select s from ProductoModel s where s.marca = ?1")
    Collection<ProductoModel> findByMarca(MarcaModel marca);

    /**
     * Consulta Select de la tabla ProductoModel donde el modelo sea el
     * especificado
     *
     * @param modelo Modelo a buscar
     * @return Collection con el resultado de la busqueda
     */
    @Query("select s from ProductoModel s where s.modelo = ?1")
    Collection<ProductoModel> findByModelo(ModeloModel modelo);

    /**
     * Consulta Select de la tabla ProductoModel donde el modelo y la marca sea
     * el especificado
     *
     * @param modelo Modelo a buscar
     * @param marca Marca a buscar
     * @return
     */
    @Query("select s from ProductoModel s where s.modelo = ?1 and s.marca = ?2")
    Collection<ProductoModel> findByModeloMarca(ModeloModel modelo, MarcaModel marca);

    /**
     * Consulta Select de la tabla ProductoModel donde la talla, color, modelo y
     * la marca sean los especificados
     *
     * @param talla Talla del producto
     * @param color Color del producto
     * @param modelo Modelo del producto
     * @param marca MAarca del producto
     * @return
     */
    @Query("select s from ProductoModel s where s.talla = ?1 and s.color = ?2 and s.modelo = ?3 and s.marca = ?4")
    Optional<ProductoModel> findByDatos(Double talla, String color, ModeloModel modelo, MarcaModel marca);

}
