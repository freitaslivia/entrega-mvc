package com.taqui.api_mvc.repository;

import com.taqui.api_mvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    void deleteByUsuarioId(String id);
}
