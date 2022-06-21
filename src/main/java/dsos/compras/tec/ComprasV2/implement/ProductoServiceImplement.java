package dsos.compras.tec.ComprasV2.implement;

import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.model.ProductoModel;
import dsos.compras.tec.ComprasV2.repository.ProductoRepository;
import dsos.compras.tec.ComprasV2.service.ProductoService;
import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase implementa Service para los productos
 *
 * @author Oscar
 */
@Service
public class ProductoServiceImplement implements ProductoService {

    //Varibles de la clase
    private final Log LOG = LogFactory.getLog(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Constructor de la clase
     *
     * @param productoRepository Repositorio de los productos
     */
    public ProductoServiceImplement(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }//Cierre del constructor

    /**
     * Método que actuliza el stock de un producto de acuerdo a una cantidad
     * vendida
     *
     * @param id Id del producto a actualizar
     * @param cantidad Cantidad de producto vendido
     */
    @Override
    public void vender(Integer id, Integer cantidad) {
        Optional<ProductoModel> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            ProductoModel productoUpdate = producto.get();
            productoUpdate.setStock(productoUpdate.getStock() - cantidad);
            productoRepository.save(productoUpdate);
        }
    }

    /**
     * Método que actualiza el stock de un producto de acuerdo a una cantidad
     * devuleta
     *
     * @param parseInt Id del producto a actualizar
     * @param unidades Cantidad de unidades devueltas
     */
    @Override
    public void devolver(int parseInt, int unidades) {
        Optional<ProductoModel> producto = productoRepository.findById(parseInt);
        if (producto.isPresent()) {
            ProductoModel productoUpdate = producto.get();
            productoUpdate.setStock(productoUpdate.getStock() + unidades);
            productoRepository.save(productoUpdate);
        }
    }

    /**
     * Método que guarda un producto
     *
     * @param producto Producto a guardar
     */
    @Override
    public void createProducto(ProductoModel producto) {
        productoRepository.save(producto);
    }

    /**
     * Método que borra un producto
     *
     * @param id Id del producto a borrar
     */
    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    /**
     * Método que actualiza los datos de un producto
     *
     * @param productoUpdate Producto con los datoa a actualizar
     * @param id Id del producto a actulizar
     */
    @Override
    public void update(ProductoModel productoUpdate, Integer id) {
        ProductoModel productoModel = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("El producto no existe"));
        //Revisa que datos son los que se actulizaran 
        if (productoUpdate.getTalla() != null) {
            productoModel.setTalla(productoUpdate.getTalla());
        }
        if (productoUpdate.getModelo() != null) {
            productoModel.setModelo(productoUpdate.getModelo());
        }
        if (productoUpdate.getColor() != null) {
            productoModel.setColor(productoUpdate.getColor());
        }
        if (productoUpdate.getMarca() != null) {
            productoModel.setMarca(productoUpdate.getMarca());
        }
        if (productoUpdate.getPrecioCompra() != null) {
            productoModel.setPrecioCompra(productoUpdate.getPrecioCompra());
        }
        if (productoUpdate.getPrecioVenta() != null) {
            productoModel.setPrecioVenta(productoUpdate.getPrecioVenta());
        }
        if (productoUpdate.getStock() != null) {
            productoModel.setStock(productoUpdate.getStock());
        }

        productoRepository.save(productoModel);
    }

    /**
     * Método que obtiene un producto un producto con el id especifico 
     * @param id Id del producto 
     * @return Optional con el resultado de la busqueda 
     */
    @Override
    public Optional<ProductoModel> getById(Integer id) {
        return productoRepository.findById(id);
    }

    /**
     * Método que obtien un producto que tenga los datos especificos
     * @param talla Talla del producto a buscar
     * @param color Color del producto a buscar
     * @param modelo Modelo del producto a buscar
     * @param marca Marca del producto a buscar
     * @return Optional con el resultado de la busqueda 
     */
    @Override
    public Optional<ProductoModel> getByDatos(Double talla, String color, ModeloModel modelo, MarcaModel marca) {
        return productoRepository.findByDatos(talla, color, modelo, marca);
    }

    /**
     * Método que obtiene todos los productos 
     * @return List con todos los productos 
     */
    @Override
    public List<ProductoModel> getAll() {
        return productoRepository.findAll();
    }

    /**
     * Método que obtiene todos los prodcutos con la marca especifica 
     * @param marca Marca a buscar
     * @return  Collection con el resultado de la busqueda 
     */
    @Override
    public Collection<ProductoModel> getAllMarca(MarcaModel marca) {
        return productoRepository.findByMarca(marca);
    }

    /**
     * Método que obtiene todos los productos con el modelo especifico
     * @param modelo Modelo a buscar
     * @return Collection con el resultado de la busqueda
     */
    @Override
    public Collection<ProductoModel> getAllModelo(ModeloModel modelo) {
        return productoRepository.findByModelo(modelo);
    }

    /**
     * Método que obtiene todos los productos con la marca y el modelo especifico 
     * @param modelo Modelo a buscar
     * @param marca Marca a buscar 
     * @return Collection con el resultado de la busqueda
     */
    @Override
    public Collection<ProductoModel> getAllModeloMarca(ModeloModel modelo, MarcaModel marca) {
        return productoRepository.findByModeloMarca(modelo, marca);
    }

    /**
     * Método que guarda un producto 
     * @param nuevoProducto  Producto a guardar 
     */
    @Override
    public void save(ProductoModel nuevoProducto) {
        productoRepository.save(nuevoProducto);
    }

}
