package com.nttdata.product.repository;

import com.nttdata.product.domain.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductoRepositoryInMemory implements ProductoRepository {

    private final Map<String, Producto> products = new ConcurrentHashMap<>();

    @Override
    public Producto guardar(Producto producto) {
        products.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Producto> buscarPorId(String id) {
        return Optional.ofNullable(products.get(id));
    }
}
