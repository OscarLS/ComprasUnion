package dsos.compras.tec.ComprasV2.controller;

import dsos.compras.tec.ComprasV2.authentication.Authentication;
import dsos.compras.tec.ComprasV2.exceptions.ExternalMicroserviceException;
import dsos.compras.tec.ComprasV2.exceptions.UnauthorizedException;
import dsos.compras.tec.ComprasV2.model.CompraModel;
import dsos.compras.tec.ComprasV2.model.DetalleCompraModel;
import dsos.compras.tec.ComprasV2.model.MarcaModel;
import dsos.compras.tec.ComprasV2.model.ModeloModel;
import dsos.compras.tec.ComprasV2.model.ProductoModel;
import dsos.compras.tec.ComprasV2.service.CompraService;
import dsos.compras.tec.ComprasV2.service.DetalleCompraService;
import dsos.compras.tec.ComprasV2.service.MarcaService;
import dsos.compras.tec.ComprasV2.service.ModeloService;
import dsos.compras.tec.ComprasV2.service.ProductoService;
import dsos.compras.tec.ComprasV2.utils.CustomResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Esta clase es el controlador de todo el programa
 *
 * @author Oscar
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class ProductoController {

    //Varibles de la clase 
    HashMap<String, Object> response;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private DetalleCompraService detalleCompraService;

    @Autowired
    private Authentication authentication;

    /**
     * Método GET que devuelve todos los productos que tengan un modelo con el
     * id (idModelo)
     *
     * @param idModelo Id del modelo a buscar
     * @return
     */
    @GetMapping("/productos/modelo/{idModelo}")
    public ResponseEntity<HashMap<String, Object>> getAllProductoModel(@PathVariable String idModelo) {
        response = new HashMap<>();
        try {
            Optional<ModeloModel> modeloModel = modeloService.getById(Integer.parseInt(idModelo));
             //modeloService.getById(Integer.parseInt(idModelo)).
            if (modeloModel.isPresent()) {

                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", productoService.getAllModelo(modeloModel.get()));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Productos encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Sin productos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET que devuelve todos los productos que tengan una marca con el
     * id (idMarca)
     *
     * @param idMarca Id de la marca buscar
     * @return
     */
    @GetMapping("/productos/marca/{idMarca}")
    public ResponseEntity<HashMap<String, Object>> getAllProductoMarca(@PathVariable String idMarca) {
        response = new HashMap<>();
        try {
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(idMarca));
            if (marcaModel.isPresent()) {

                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", productoService.getAllMarca(marcaModel.get()));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Productos encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Sin productos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET que devuelve todos los productos que tengan una marca con el
     * id (idMarca) y un modelo con un id (idModelo)
     *
     * @param idMarca Id de la marca buscar
     * @param idModelo Id del modelo buscar
     * @return
     */
    @GetMapping("/productos/marca//{idMarca}/Modelo/{idModelo}")
    public ResponseEntity<HashMap<String, Object>> getAllProductoMM(@PathVariable String idMarca, @PathVariable String idModelo) {
        response = new HashMap<>();
        try {
            Optional<ModeloModel> modeloModel = modeloService.getById(Integer.parseInt(idModelo));
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(idMarca));
            if (marcaModel.isPresent() && modeloModel.isPresent()) {
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", productoService.getAllModeloMarca(modeloModel.get(), marcaModel.get()));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Productos encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Sin productos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET que devuelve todos los productos
     *
     * @return
     */
    @GetMapping("/productos/")
    public ResponseEntity<HashMap<String, Object>> getAll() {
        response = new HashMap<>();
        try {
            response.put("httpCode", HttpStatus.OK.value());
            response.put("data", productoService.getAll());
            response.put("message", HttpStatus.OK.getReasonPhrase() + ": Producto encontrado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET que devuelve un producto con el id especificado (id)
     *
     * @param id Id del producto a buscar
     * @return
     */
    @GetMapping("/productos/{id}")
    public ResponseEntity<HashMap<String, Object>> get(@PathVariable String id) {
        response = new HashMap<>();
        try {
            Optional<ProductoModel> productoModel = productoService.getById(Integer.parseInt(id));
            if (productoModel.isPresent()) {
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", productoModel);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Producto encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Id del producto a buscar
     *
     * @param producto Producto (ProductoModel) a guardar
     * @param request Token de autenticación
     * @return
     */
    @PostMapping("/productos/")
    public ResponseEntity<HashMap<String, Object>> post(@RequestBody ProductoModel producto, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            //valueResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            if (producto.getPrecioCompra() == null || producto.getStock() == null
                    || producto.getColor() == null || producto.getMarca() == null || producto.getTalla() == null || producto.getModelo() == null
                    || producto.getPrecioVenta() == null) {
                response.put("httpCode", HttpStatus.NOT_FOUND.value());
                response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                productoService.createProducto(producto);
                response.put("httpCode", HttpStatus.CREATED.value());
                response.put("data", producto);
                response.put("message", HttpStatus.CREATED.getReasonPhrase() + ": El producto se ha creado correctamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método PUT actualiza los datos de un producto
     *
     * @param producto Producto (ProductoModel) con los datos del producto a
     * actualizar
     * @param id Id del producto a actualizar
     * @param request Token de autenticación
     * @return
     */
    @Transactional
    @PutMapping("/productos/{id}")
    public ResponseEntity<HashMap<String, Object>> put(@RequestBody ProductoModel producto, @PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);

            Optional<ProductoModel> productoModel = productoService.getById(Integer.parseInt(id));
            //check if producto exists
            if (productoModel.isPresent()) {
                //check if fields are not null
                if (producto.getPrecioCompra() == null && producto.getStock() == null
                        && producto.getColor() == null && producto.getMarca() == null
                        && producto.getTalla() == null && producto.getModelo() == null
                        && producto.getPrecioVenta() == null) {
                    response.put("httpCode", 400);
                    response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                } else {
                    productoService.update(producto, Integer.parseInt(id));
                    response.put("httpCode", HttpStatus.OK.value());
                    response.put("data", productoService.getById(Integer.parseInt(id)).get());
                    response.put("message", HttpStatus.OK.getReasonPhrase() + ": El producto se ha actualizado correctamente");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método PUT actualiza el stock de un producto de acuerdo a las unidades
     * vendidas del producto
     *
     * @param id Id del producto a actualizar
     * @param unidades Id del producto a actualizar
     * @param request Token de autenticación
     * @return
     */
    @Transactional
    @PutMapping("/productos/vender/{id}/{unidades}")
    public ResponseEntity<HashMap<String, Object>> vender(@PathVariable String id, @PathVariable String unidades, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            Optional<ProductoModel> productoModel = productoService.getById(Integer.parseInt(id));
            if (productoModel.isPresent()) {
                //check for stock
                ProductoModel producto = productoModel.get();
                //if stock is not enough
                if (producto.getStock() < Integer.parseInt(unidades)) {
                    response.put("httpCode", 400);
                    response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": No hay suficiente stock");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
                //if stock is enough, update stock
                productoService.vender(Integer.parseInt(id), Integer.parseInt(unidades));
                response.put("httpCode", HttpStatus.OK.value());
                HashMap<String, Object> data = new HashMap<>();
                //put into data stock and id
                data.put("stock", producto.getStock());
                data.put("id", producto.getIdProducto());
                response.put("data", data);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Se ha vendido " + unidades + " unidades");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            //If producto does not exist
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método PUT actualiza el stock de un producto de acuerdo a las unidades
     * devueltas del producto
     *
     * @param id Id del producto a actualizar
     * @param unidades Unidades compradas
     * @param request Token de autenticación
     * @return
     */
    @Transactional
    @PutMapping("/productos/devolver/{id}/{unidades}")
    public ResponseEntity<HashMap<String, Object>> devolver(@PathVariable String id, @PathVariable String unidades, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            //check if producto exists
            Optional<ProductoModel> productoModel = productoService.getById(Integer.parseInt(id));
            if (productoModel.isPresent()) {
                ProductoModel producto = productoModel.get();
                //update stock
                productoService.devolver(Integer.parseInt(id), Integer.parseInt(String.valueOf(unidades)));
                response.put("httpCode", HttpStatus.OK.value());
                HashMap<String, Object> data = new HashMap<>();
                //put into data stock and id
                data.put("stock", producto.getStock());
                data.put("id", producto.getIdProducto());
                response.put("data", data);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Se ha devuelto " + unidades + "unidad(es) al inventario.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            //If producto does not exist
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método DELETE borra un producto de acuerdo al id dado (id)
     *
     * @param id Id del producto a eliminar
     * @param request Método DELETE borra un producto de acuerdo al id dado (id)
     * @return
     */
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<HashMap<String, Object>> delete(@PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            //check if producto exists
            Optional<ProductoModel> productoModel = productoService.getById(Integer.parseInt(id));
            if (productoModel.isPresent()) {
                productoService.delete(Integer.parseInt(id));
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", productoService.getById(Integer.parseInt(id)));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": El producto se ha eliminado correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El producto no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    //MODELO
    /**
     * Método GET obtiene todos los modelos
     *
     * @return
     */
    @GetMapping("/modelos/")
    public ResponseEntity<HashMap<String, Object>> getAllModelo() {
        response = new HashMap<>();
        try {
            response.put("httpCode", HttpStatus.OK.value());
            response.put("data", modeloService.getAll());
            response.put("message", HttpStatus.OK.getReasonPhrase() + ": Modelo encontrado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET obtiene el modelo con el id dado (id)
     *
     * @param id Id del modelo a buscar
     * @return
     */
    @GetMapping("/modelos/{id}")
    public ResponseEntity<HashMap<String, Object>> getModelo(@PathVariable String id) {
        response = new HashMap<>();
        try {
            //check if producto exists
            Optional<ModeloModel> modeloModel = modeloService.getById(Integer.parseInt(id));
            if (modeloModel.isPresent()) {
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", modeloModel);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Modelo encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El modelo no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método POST guarda un modelo
     *
     * @param modelo Modelo a guardar
     * @param request Token de autenticación
     * @return
     */
    @PostMapping("/modelos/")
    public ResponseEntity<HashMap<String, Object>> postModelo(@RequestBody ModeloModel modelo, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            if (modelo.getNombreModelo() == null) {
                response.put("httpCode", HttpStatus.NOT_FOUND.value());
                response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                modeloService.createModelo(modelo);
                response.put("httpCode", HttpStatus.CREATED.value());
                response.put("data", modelo);
                response.put("message", HttpStatus.CREATED.getReasonPhrase() + ": El modelo se ha creado correctamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método PUT actualiza los datos de un modelo
     *
     * @param modelo Modelo con los datos a actualizar
     * @param id Id del modelo a actualizar
     * @param request Token de autenticación
     * @return
     */
    @Transactional
    @PutMapping("/modelos/{id}")
    public ResponseEntity<HashMap<String, Object>> putModelo(@RequestBody ModeloModel modelo, @PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);

            Optional<ModeloModel> modeloModel = modeloService.getById(Integer.parseInt(id));
            if (modeloModel.isPresent()) {
                if (modelo.getNombreModelo() == null) {
                    response.put("httpCode", HttpStatus.NOT_FOUND.value());
                    response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                } else {
                    modeloService.update(modelo, Integer.parseInt(id));
                    response.put("httpCode", HttpStatus.OK.value());
                    response.put("data", modelo);
                    response.put("message", HttpStatus.OK.getReasonPhrase() + ": El modelo se ha actualizado correctamente");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El modelo no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método DELETE borra un modelo con el id dado
     *
     * @param id Id del modelo a borrar
     * @param request Id del modelo a borrar
     * @return
     */
    @DeleteMapping("/modelos/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteModelo(@PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            Optional<ModeloModel> modeloModel = modeloService.getById(Integer.parseInt(id));
            if (modeloModel.isPresent()) {
                modeloService.delete(Integer.parseInt(id));
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", modeloService.getById(Integer.parseInt(id)));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": El modelo se ha eliminado correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": El modelo no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET obtiene todos los modelos que tengan una marca con el id dado
     *
     * @param idMarca Id de la marca a buscar
     * @return
     */
    @GetMapping("/modelos/marca/{idMarca}")
    public ResponseEntity<HashMap<String, Object>> getAllMarcaMarca(@PathVariable String idMarca) {
        response = new HashMap<>();
        try {
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(idMarca));
            if (marcaModel.isPresent()) {

                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", modeloService.getAllMarca(marcaModel.get()));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": modelos encontrados");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Sin modelo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    //MARCA
    /**
     * Método GET obtiene todas las marcas
     *
     * @return ResponseEntity con todas las marcas
     */
    @GetMapping("/marcas/")
    public ResponseEntity<HashMap<String, Object>> getAllMarca() {
        response = new HashMap<>();
        try {
            response.put("httpCode", HttpStatus.OK.value());
            response.put("data", marcaService.getAll());
            response.put("message", HttpStatus.OK.getReasonPhrase() + ": Marca encontrada");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET obtiene la marca con el id dado
     *
     * @param id Id de la marca
     * @return
     */
    @GetMapping("/marcas/{id}")
    public ResponseEntity<HashMap<String, Object>> getMarca(@PathVariable String id) {
        response = new HashMap<>();
        try {
            //check if producto exists
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(id));
            if (marcaModel.isPresent()) {
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", marcaModel);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Marca encontrada");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": La marca no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método POST guarda una marca
     *
     * @param marca Marca a guardar
     * @param request Token de autenticación
     * @return
     */
    @PostMapping("/marcas/")
    public ResponseEntity<HashMap<String, Object>> postMarca(@RequestBody MarcaModel marca, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            Optional<MarcaModel> marcaModel = marcaService.getByNombre(marca.getNombreMarca());
            if (!marcaModel.isPresent()) {
                if (marca.getNombreMarca() == null) {
                    response.put("httpCode", 400);
                    response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                } else {
                    marcaService.createMarca(marca);
                    response.put("httpCode", HttpStatus.CREATED.value());
                    response.put("data", marca);
                    response.put("message", HttpStatus.CREATED.getReasonPhrase() + ": La marca se ha creado correctamente");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": La marca ya esta registrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método PUT actualiza los datos de una marca
     *
     * @param marca Marca con los datos a aguardar
     * @param id Id de la marca a actualizar
     * @param request Token de autenticación
     * @return
     */
    @Transactional
    @PutMapping("/marcas/{id}")
    public ResponseEntity<HashMap<String, Object>> putMarca(@RequestBody MarcaModel marca, @PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(id));
            //check if marca exists
            if (marcaModel.isPresent()) {
                //check if fields are not null
                if (marca.getNombreMarca() == null) {
                    response.put("httpCode", 400);
                    response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": Uno o más campos están vacíos");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                } else {
                    marcaService.update(marca, Integer.parseInt(id));
                    response.put("httpCode", HttpStatus.OK.value());
                    response.put("data", marca);
                    response.put("message", HttpStatus.OK.getReasonPhrase() + ": La marca se ha actualizado correctamente");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": La marca no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método DELETE borra una marca con el id dado (id)
     *
     * @param id Id de la marca a borrar
     * @param request Token de autenticación
     * @return
     */
    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteMarca(@PathVariable String id, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);
            Optional<MarcaModel> marcaModel = marcaService.getById(Integer.parseInt(id));
            if (marcaModel.isPresent()) {
                marcaService.delete(Integer.parseInt(id));
                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", marcaService.getById(Integer.parseInt(id)));
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": La marca se ha eliminado correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": La marca no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET obtiene todas las marcas
     *
     * @return
     */
    @GetMapping("/compras/")
    public ResponseEntity<HashMap<String, Object>> getAllCompras() {
        response = new HashMap<>();
        try {
            response.put("httpCode", HttpStatus.OK.value());
            response.put("data", compraService.getAll());
            response.put("message", HttpStatus.OK.getReasonPhrase() + ": Compras encontradas");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método POST crea una compra y los detalles compras de los productos dados
     *
     * @param productosLista Lista con los productos que se compraron
     * @param request Token de autenticación
     * @return
     */
    @PostMapping("/compras/new/")
    public ResponseEntity<HashMap<String, Object>> postCompras2(@RequestBody List<ProductoModel> productosLista, HttpServletRequest request) {
        response = new HashMap<>();
        try {
            authentication.auth(request);

            if (productosLista != null) {
                CompraModel compra = new CompraModel();
                compra.setFechaAdquirido(LocalDateTime.now());

                compra.setTotal(0.0);
                compraService.save(compra);
                Optional<CompraModel> compraRt = compraService.getByCompra(compra.getTotal(), compra.getFechaAdquirido());

                for (ProductoModel productoN : productosLista) {
                    int cantidad = 0;
                    if (productoN.getPrecioCompra() == null || productoN.getStock() == null
                            || productoN.getColor() == null || productoN.getMarca() == null
                            || productoN.getTalla() == null || productoN.getModelo() == null
                            || productoN.getPrecioVenta() == null) {
                        compraService.delete(compraRt.get().getIdCompra());
                        response.put("httpCode", 400);
                        response.put("message", HttpStatus.BAD_REQUEST.getReasonPhrase() + ": Uno o más campos están vacíos");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    } else {
                        cantidad = productoN.getStock();
                        compra.setTotal(compra.getTotal() + (productoN.getStock() * productoN.getPrecioCompra()));
                        Optional<ProductoModel> productoModel = productoService.getByDatos(productoN.getTalla(), productoN.getColor(), productoN.getModelo(), productoN.getMarca());
                        if (!productoModel.isPresent()) {
                            productoService.save(productoN);
                        } else {
                            productoN.setStock(productoN.getStock() + productoModel.get().getStock());
                            productoService.update(productoN, productoModel.get().getIdProducto());
                        }

                        productoModel = productoService.getByDatos(productoN.getTalla(), productoN.getColor(), productoN.getModelo(), productoN.getMarca());

                        DetalleCompraModel detalleCompra = new DetalleCompraModel();
                        detalleCompra.setCompra(compraRt.get());
                        detalleCompra.setProducto(productoModel.get());
                        detalleCompra.setCantidad(cantidad);
                        detalleCompraService.save(detalleCompra);
                    }

                }
                compraService.update(compra, compraRt.get().getIdCompra());
                Collection dc = detalleCompraService.getAllCompra(compraRt.get());

                List<Object> salida = new ArrayList<>();
                salida.add(compraRt.get());
                salida.add(dc);

                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", salida);
                response.put("message", HttpStatus.CREATED.getReasonPhrase() + ": La compra se ha creado correctamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            }
            response.put("httpCode", 400);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": No ingreso ningun producto");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (UnauthorizedException ex) {
            response.put("httpCode", HttpStatus.UNAUTHORIZED.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (ExternalMicroserviceException ex) {
            response.put("httpCode", HttpStatus.SERVICE_UNAVAILABLE.value());
            response.put("data", ex.toJSON());
            response.put("message", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método

    /**
     * Método GET obtiene todos los detalles compras que pertenecen a una compra
     * con el id dado(idCompra)
     *
     * @param idCompra Id de la compra
     * @return
     */
    @GetMapping("/compras-detalle/{idCompra}")
    public ResponseEntity<HashMap<String, Object>> getAllDetalle(@PathVariable String idCompra) {

        response = new HashMap<>();
        try {
            Optional<CompraModel> compraRt = compraService.getById(Integer.parseInt(idCompra));
            if (compraRt.isPresent()) {

                Collection dc = detalleCompraService.getAllCompra(compraRt.get());

                List<Object> salida = new ArrayList<>();
                salida.add(compraRt.get());
                salida.add(dc);

                response.put("httpCode", HttpStatus.OK.value());
                response.put("data", salida);
                response.put("message", HttpStatus.OK.getReasonPhrase() + ": Lista compra");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
            response.put("httpCode", HttpStatus.NOT_FOUND.value());
            response.put("data", null);
            response.put("message", HttpStatus.NOT_FOUND.getReasonPhrase() + ": La compra no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            response.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + "Eror detalle");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//Cierre del método
}//Cierre de la clase 
