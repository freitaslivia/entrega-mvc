package com.taqui.api_mvc.service;

import com.taqui.api_mvc.dto.ProdutoRequest;
import com.taqui.api_mvc.dto.ProdutoResponse;
import com.taqui.api_mvc.model.Produto;
import com.taqui.api_mvc.model.Usuario;
import com.taqui.api_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Produto requestToProduto(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setDescricao(produtoRequest.getDescricao());
        produto.setPreco(produtoRequest.getPreco());

        Usuario usuario = usuarioRepository.findById(produtoRequest.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        produto.setUsuario(usuario);

        return produto;
    }

    public ProdutoResponse produtoToResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getUsuario().getId());
    }

    public ProdutoRequest produtoToRequest(Produto produto) {
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNome(produto.getNome());
        produtoRequest.setDescricao(produto.getDescricao());
        produtoRequest.setPreco(produto.getPreco());
        produtoRequest.setIdUsuario(produto.getUsuario().getId());
        return produtoRequest;
    }
}
