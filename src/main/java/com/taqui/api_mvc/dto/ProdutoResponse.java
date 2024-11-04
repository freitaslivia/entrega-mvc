package com.taqui.api_mvc.dto;

import com.taqui.api_mvc.model.Produto;

public record ProdutoResponse(

        Long id,

        String nome,

        String descricao,

        float preco,

        String idUsuario
) {

    public ProdutoResponse(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getUsuario().getId());
    }
}