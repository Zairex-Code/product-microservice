package com.nttdata.product.delegate;

import com.nttdata.product.api.ProductosApiDelegate;
import com.nttdata.product.model.ProductoRequest;
import com.nttdata.product.model.ProductoResponse;
import com.nttdata.product.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosApiDelegateImpl implements ProductosApiDelegate {

    private final ProductoService service;

    public ProductosApiDelegateImpl(ProductoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ProductoResponse> registrarProducto(ProductoRequest productoRequest) {
        return ResponseEntity.ok(service.registrar(productoRequest));
    }

    @Override
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> productos = service.listar();
        return productos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productos);
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}
