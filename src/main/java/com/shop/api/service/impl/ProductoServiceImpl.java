package com.shop.api.service.impl;

import com.shop.api.dto.ProductoRequestDTO;
import com.shop.api.dto.ProductoResponseDTO;
import com.shop.api.entity.Categoria;
import com.shop.api.entity.Producto;
import com.shop.api.exception.ResourceNotFoundException;
import com.shop.api.mapper.ProductoMapper;
import com.shop.api.repository.CategoriaRepository;
import com.shop.api.repository.ProductoRepository;
import com.shop.api.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO dto) {
        if (dto.getCategoriaId() == null) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio");
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Producto producto = productoMapper.toEntity(dto, categoria);
        Producto guardado = productoRepository.save(producto);

        return productoMapper.toResponseDTO(guardado);
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        return productoMapper.toResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        if (dto.getCategoriaId() == null) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio");
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setStock(dto.getStock());
        productoExistente.setCategoria(categoria);

        Producto actualizado = productoRepository.save(productoExistente);
        return productoMapper.toResponseDTO(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
