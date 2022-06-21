package dsos.compras.tec.ComprasV2.service;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.model.ProductoModel;
import java.util.Collection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Interface Service Producto
 *
 * @author Oscar
 */
@Service
public interface ProductoService {

    /**
     * Método para crear un producto
     *
     * @param producto Producto a guardar
     */
    public void createProducto(ProductoModel producto);

    /**
     * Método para borrar un producto
     *
     * @param id Id del modelo a borrar
     */
    public void delete(Integer id);

    /**
     * Método para actualizar un producto
     *
     * @param productoUpdate Producto con los datos a actualizar
     * @param id Id del prodcuto a actualizar
     */
    public void update(ProductoModel productoUpdate, Integer id);

    /**
     * Método para obtener un producto con el id dado
     *
     * @param id Id del producto a buscar
     * @return Optional con el resultado de la busqueda
     */
    public Optional<ProductoModel> getById(Integer id);

    /**
     * Método para obtener todos los productos
     *
     * @return List con todos los productos
     */
    public List<ProductoModel> getAll();

    /**
     * Método para actualizar el stock del producto restando la cantidad vendida
     *
     * @param id Id del producto
     * @param cantidad Cantidad de unidades vendidas
     */
    public void vender(Integer id, Integer cantidad);

    /**
     * Método para actualizar el stock del producto sumando la cantidad de
     * unidades devueltas
     *
     * @param id Id del prodcuto
     * @param unidades Cantidad de unidades devueltas
     */
    public void devolver(int id, int unidades);

    /**
     * Método para obtener todos los productos con la marca dada
     *
     * @param marca Id de la marca a buscar
     * @return Collection con el resultado de la busqueda
     */
    public Collection<ProductoModel> getAllMarca(MarcaModel marca);

    /**
     * Método para obtner todos los prodcutos que tengan el modelo dado
     *
     * @param modelo Modelo a buscar
     * @return Collection con el resultado de la busqueda
     */
    public Collection<ProductoModel> getAllModelo(ModeloModel modelo);

    /**
     * Método para obtner todos los productos que tengan como modelo y marca los datos dados 
     * @param modelo Modelo a buscar 
     * @param marca Marca a buscar 
     * @return Collection con el resultado obtenido 
     */
    public Collection<ProductoModel> getAllModeloMarca(ModeloModel modelo, MarcaModel marca);

    /**
     * Método para obtener un producto que tenga los datos dados en los parametros 
     * @param talla Talla del producto a buscar
     * @param color Color del producto a buscar
     * @param modelo Modelo del producto a buscar
     * @param marca Marca del producto a buscar
     * @return 
     */
    public Optional<ProductoModel> getByDatos(Double talla, String color, ModeloModel modelo, MarcaModel marca);

    /**
     * Método para guardar un producto 
     * @param nuevoProducto Producto a guardar 
     */
    public void save(ProductoModel nuevoProducto);

}
