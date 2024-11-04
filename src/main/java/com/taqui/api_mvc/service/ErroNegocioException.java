package com.taqui.api_mvc.service;

public class ErroNegocioException extends RuntimeException{

    public ErroNegocioException(String mensagem) {
        super(mensagem);
    }
}
