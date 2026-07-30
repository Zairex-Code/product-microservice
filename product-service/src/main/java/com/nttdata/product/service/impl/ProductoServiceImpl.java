package com.nttdata.product.service.impl;

import com.nttdata.product.domain.Producto;
import com.nttdata.product.exception.ProductoNoEncontradoException;
import com.nttdata.product.mapper.ProductoMapper;
import com.nttdata.product.model.ProductoRequest;
import com.nttdata.product.model.ProductoResponse;
import com.nttdata.product.repository.ProductoRepository;
import com.nttdata.product.service.ProductoService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;
    private final AtomicLong secuencia = new AtomicLong(1);

    public ProductoServiceImpl(ProductoRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductoResponse registrar(ProductoRequest request) {
        Producto producto = mapper.aDominio(request);
        producto.setId(generarSku());
        producto.setFechaCreacion(OffsetDateTime.now());
        return mapper.aResponse(repository.guardar(producto));
    }

    @Override
    public List<ProductoResponse> listar() {
        return repository.listarTodos().stream()
                .map(mapper::aResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse obtenerPorId(String id) {
        return repository.buscarPorId(id)
                .map(mapper::aResponse)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    private String generarSku() {
        return String.format("SKU-%05d", secuencia.getAndIncrement());
    }
}
