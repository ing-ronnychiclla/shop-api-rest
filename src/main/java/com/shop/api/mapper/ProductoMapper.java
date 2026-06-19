package com.shop.api.mapper;

import com.shop.api.dto.ProductoRequestDTO;
import com.shop.api.dto.ProductoResponseDTO;
import com.shop.api.entity.Categoria;
import com.shop.api.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO toResponseDTO(Producto producto) {
        if (producto == null) return null;

        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaNombre(producto.getCategoria() != null ? producto.getCategoria().getNombre() : "Sin categoría")
                .build();
    }

    public Producto toEntity(ProductoRequestDTO dto, Categoria categoria) {
        if (dto == null) return null;

        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .categoria(categoria)
                .build();
    }
}
