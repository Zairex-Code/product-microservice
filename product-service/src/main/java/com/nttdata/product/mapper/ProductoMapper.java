package com.nttdata.product.mapper;

import com.nttdata.product.domain.Producto;
import com.nttdata.product.model.ProductoRequest;
import com.nttdata.product.model.ProductoResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto aDominio(ProductoRequest request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        p.setDescripcion(request.getDescripcion());
        p.setPrecio(request.getPrecio());
        p.setCategoria(request.getCategoria());
        p.setStock(request.getStock());
        return p;
    }

    public ProductoResponse aResponse(Producto p) {
        ProductoResponse r = new ProductoResponse();
        r.setId(p.getId());
        r.setNombre(p.getNombre());
        r.setDescripcion(p.getDescripcion());
        r.setPrecio(p.getPrecio());
        r.setCategoria(p.getCategoria());
        r.setStock(p.getStock());
        r.setFechaCreacion(p.getFechaCreacion());
        return r;
    }
}
