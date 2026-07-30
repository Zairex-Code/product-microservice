package com.nttdata.product.service;

import com.nttdata.product.exception.ProductoNoEncontradoException;
import com.nttdata.product.mapper.ProductoMapper;
import com.nttdata.product.model.ProductoRequest;
import com.nttdata.product.model.ProductoResponse;
import com.nttdata.product.repository.ProductoRepositoryInMemory;
import com.nttdata.product.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductoServiceImplTest {

    @Test
    void registrar_asignaSkuYFechaCreacion() {
        var repo = new ProductoRepositoryInMemory();
        var service = new ProductoServiceImpl(repo, new ProductoMapper());

        var request = new ProductoRequest();
        request.setNombre("Mouse");
        request.setPrecio(50.0);
        request.setCategoria("Tecnología");

        ProductoResponse response = service.registrar(request);

        assertEquals("SKU-00001", response.getId());
        assertNotNull(response.getFechaCreacion());
    }

    @Test
    void obtenerPorId_inexistente_lanzaExcepcion() {
        var service = new ProductoServiceImpl(new ProductoRepositoryInMemory(), new ProductoMapper());
        assertThrows(ProductoNoEncontradoException.class, () -> service.obtenerPorId("SKU-XXXXX"));
    }
}
