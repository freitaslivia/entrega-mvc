package com.taqui.api_mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequest {
    private Long id;

    @NotBlank(message = "O campo não pode estar vazio")
    @Size(max = 50, message = "O campo pode ter no máximo 50 caracteres")
    private String nome;


    @NotBlank(message = "O campo não pode estar vazio")
    @Size(max = 200, message = "O campo pode ter no máximo 50 caracteres")
    private String descricao;

    @NotNull(message = "O campo não pode ser nulo")
    private float preco;



    private  String idUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}