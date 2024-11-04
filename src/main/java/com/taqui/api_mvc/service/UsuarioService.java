package com.taqui.api_mvc.service;

import com.taqui.api_mvc.dto.UsuarioResponse;
import com.taqui.api_mvc.model.Usuario;
import com.taqui.api_mvc.repository.ProdutoRepository;
import com.taqui.api_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;



    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getLogin(), usuario.getEmail());
    }


    @Transactional
    public void deletarUsuarioEProdutos(String id) {

        produtoRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }

}