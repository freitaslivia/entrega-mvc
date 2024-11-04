package com.taqui.api_mvc.repository;

import com.taqui.api_mvc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByEmail(String email);

    UserDetails findByLogin(String login);
}
