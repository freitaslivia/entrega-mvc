package com.taqui.api_mvc.dto;

import com.taqui.api_mvc.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "O campo login não pode estar vazio")
    @Size(max = 50, message = "O login pode ter no máximo 50 caracteres")
    String login;
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(max = 50, message = "O campo senha pode ter no máximo 50 caracteres")
    String senha;
    @NotBlank(message = "O campo email não pode estar vazio")
    @Size(max = 50, message = "O campo email pode ter no máximo 50 caracteres")
    String email;
    
    UserRole role;

}
