package com.example.sistemausuario.controller;


import com.example.sistemausuario.dto.DadosAtualizacaoUsuario;
import com.example.sistemausuario.dto.DadosCadastrarUsuario;
import com.example.sistemausuario.dto.DadosListagemUsuarios;
import com.example.sistemausuario.dto.DetalhamentoUsuario;
import com.example.sistemausuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DetalhamentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastrarUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        DetalhamentoUsuario usuario = usuarioService.criarUsuario(dados);
        var uri = uriComponentsBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.id())
                .toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarios>> listar(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listar(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhamentoUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
