package com.taqui.api_mvc.controller;
import com.taqui.api_mvc.dto.AuthDTO;
import com.taqui.api_mvc.dto.RegisterDTO;
import com.taqui.api_mvc.model.Usuario;
import com.taqui.api_mvc.repository.UsuarioRepository;
import com.taqui.api_mvc.security.TokenService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login").addObject("authDTO", new AuthDTO());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(HttpSession session, @Valid @ModelAttribute AuthDTO authDTO, Model model) {
        try {
            var usuarioSenha = new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getSenha());
            var auth = authenticationManager.authenticate(usuarioSenha);

            var token = tokenService.generateToken((Usuario)auth.getPrincipal());

            Usuario usuario = (Usuario) auth.getPrincipal();
            session.setAttribute("userId", usuario.getId());

            ModelAndView mv = new ModelAndView("/templateVazio");

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", token);

            mv.addObject(responseHeaders);

            SecurityContextHolder.getContext().setAuthentication(auth);

            session.setAttribute("authToken", "Bearer " + token);

            return mv;
        } catch (Exception e) {
            model.addAttribute("error", "Login falhou. Verifique suas credenciais.");
            return new ModelAndView("login");
        }
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register").addObject("registerDTO", new RegisterDTO());
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute RegisterDTO registerDTO, Model model) {

        if (usuarioRepository.findByLogin(registerDTO.getLogin()) != null) {
            model.addAttribute("error", "Usuário já existe!");
            return new ModelAndView("register");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getSenha());

        Usuario usuario = new Usuario();
        usuario.setLogin(registerDTO.getLogin());
        usuario.setEmail(registerDTO.getEmail());
        usuario.setSenha(encryptedPassword);
        usuario.setRole(registerDTO.getRole());
        usuarioRepository.save(usuario);

        return new ModelAndView("registerSuccess");
    }

    @GetMapping("/logout")
    public RedirectView logout(Model model) {

        return new RedirectView("/auth/login");
    }

}