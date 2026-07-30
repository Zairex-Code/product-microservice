package com.nttdata.product.service;

import com.nttdata.product.model.ProductoRequest;
import com.nttdata.product.model.ProductoResponse;

import java.util.List;

public interface ProductoService {

    ProductoResponse registrar(ProductoRequest request);

    List<ProductoResponse> listar();

    ProductoResponse obtenerPorId(String id);
}
