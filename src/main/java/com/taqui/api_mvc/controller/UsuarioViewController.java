package com.taqui.api_mvc.controller;

import com.taqui.api_mvc.dto.UsuarioResponse;
import com.taqui.api_mvc.model.Usuario;
import com.taqui.api_mvc.repository.ProdutoRepository;
import com.taqui.api_mvc.repository.UsuarioRepository;
import com.taqui.api_mvc.service.ErroNegocioException;
import com.taqui.api_mvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioViewController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listaUsuarios")
    public ModelAndView listaUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosResponse.add(usuarioService.usuarioToResponse(usuario));
        }
        ModelAndView mv = new ModelAndView("usuarios");
        mv.addObject("listaUsuarios", listaUsuariosResponse);
        return mv;
    }

    @GetMapping("/template")
    public String template() {
        return "templateVazio";
    }


    @GetMapping("/listaUsuariosTemplate")
    public ModelAndView listaUsuariosTemplate() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResponse> listaUsuariosResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosResponse.add(usuarioService.usuarioToResponse(usuario));
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "usuarios");
        mv.addObject("content", "listaUsuarios");
        mv.addObject("listaUsuarios", listaUsuariosResponse);
        return mv;
    }

    @GetMapping("/deletarUsuario/{id}")
    public ModelAndView deletarUsuario(@PathVariable String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new ErroNegocioException("Usuario não encontrado.");
        }
        usuarioService.deletarUsuarioEProdutos(id);
        return listaUsuariosTemplate();
    }


}
