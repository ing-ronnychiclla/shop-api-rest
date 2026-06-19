package com.shop.api.service;

import com.shop.api.dto.ProductoRequestDTO;
import com.shop.api.dto.ProductoResponseDTO;
import com.shop.api.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> listarProductos();
    ProductoResponseDTO guardarProducto(ProductoRequestDTO productoDTO);
    ProductoResponseDTO buscarPorId(Long id);
    ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO productoDTO);
    void eliminarProducto(Long id);
}
