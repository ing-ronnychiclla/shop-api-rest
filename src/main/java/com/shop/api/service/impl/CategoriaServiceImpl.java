package com.shop.api.service.impl;

import com.shop.api.dto.CategoriaDTO;
import com.shop.api.entity.Categoria;
import com.shop.api.exception.ResourceNotFoundException;
import com.shop.api.repository.CategoriaRepository;
import com.shop.api.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO dto) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));

        categoriaExistente.setNombre(dto.getNombre());
        Categoria actualizada = categoriaRepository.save(categoriaExistente);

        return CategoriaDTO.builder()
                .id(actualizada.getId())
                .nombre(actualizada.getNombre())
                .build();
    }
}
