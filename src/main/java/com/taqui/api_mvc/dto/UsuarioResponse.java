package com.taqui.api_mvc.dto;

import com.taqui.api_mvc.model.Usuario;

public record UsuarioResponse(

        String id,

        String login,

        String email
) {

    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getEmail());
    }
}