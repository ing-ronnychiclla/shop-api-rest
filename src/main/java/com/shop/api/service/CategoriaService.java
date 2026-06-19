package com.shop.api.service;

import com.shop.api.dto.CategoriaDTO;
import com.shop.api.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    Categoria guardarCategoria(Categoria categoria);

    CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO dto);
}
