package com.nttdata.product.repository;

import com.nttdata.product.domain.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    Producto guardar(Producto producto);

    List<Producto> listarTodos();

    Optional<Producto> buscarPorId(String id);
}
