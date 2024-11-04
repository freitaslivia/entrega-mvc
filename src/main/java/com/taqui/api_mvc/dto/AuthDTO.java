package com.taqui.api_mvc.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @NotBlank(message = "O campo não pode estar vazio")
    String login;
    @NotBlank(message = "O campo não pode estar vazio")
    String senha;


}