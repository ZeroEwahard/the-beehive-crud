package com.example.sistemausuario.service;

import com.example.sistemausuario.domain.Role;
import com.example.sistemausuario.domain.Usuario;
import com.example.sistemausuario.dto.DadosAtualizacaoUsuario;
import com.example.sistemausuario.dto.DadosCadastrarUsuario;
import com.example.sistemausuario.dto.DadosListagemUsuarios;
import com.example.sistemausuario.dto.DetalhamentoUsuario;
import com.example.sistemausuario.exception.TiposErros;
import com.example.sistemausuario.repository.UsuarioRepository;
import com.example.sistemausuario.security.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final SecurityUtils securityUtils;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder, SecurityUtils securityUtils) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.securityUtils = securityUtils;
    }

    public DetalhamentoUsuario criarUsuario(DadosCadastrarUsuario dados) {
        if (dados.email() != null && usuarioRepository.existsByEmail(dados.email())) {
            throw new TiposErros.EmailCadastradoException("Este Email já existe! Tente outro nome");
        }
        Usuario usuario = new Usuario(
                dados.nome(),
                dados.email(),
                encoder.encode(dados.senha()),
                Role.ROLE_USER
        );
        usuarioRepository.save(usuario);

        return new DetalhamentoUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemUsuarios> listar(Pageable pageable) {
        Usuario usuarioLogado = securityUtils.usuarioLogado();
        if (!usuarioLogado.getRole().equals(Role.ROLE_ADMIN)) {
            throw new TiposErros.SemPermissaoException("Você não possui autoridade para usar essa função");
        }
        return usuarioRepository.findAll(pageable).map(DadosListagemUsuarios::new);
    }

    public DetalhamentoUsuario atualizarUsuario(Long id, DadosAtualizacaoUsuario dados) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new TiposErros.RecursoNaoEncontradoException("Usuário não encontrado"));
        Usuario usuarioLogado = securityUtils.usuarioLogado();

        if (!usuarioLogado.getRole().equals(Role.ROLE_ADMIN) && !usuarioLogado.getId().equals(id)) {
            throw new TiposErros.SemPermissaoException("Você não tem permissão para alterar esse usuário");
        }

        if (dados.email() != null &&
        usuarioRepository.existsByEmail(dados.email()) &&
        !usuarioLogado.getEmail().equals(dados.email())) {

            throw new TiposErros.EmailCadastradoException("Email já cadastrado");
        }

        if (dados.nome() != null) {
            usuario.setNome(dados.nome());
        }
        if (dados.email() != null) {
            usuario.setEmail(dados.email());
        }
        if (dados.senha() != null) {
            usuario.setSenha(encoder.encode(dados.senha()));
        }

        return new DetalhamentoUsuario(usuario);
    }


    public void deletar(Long id) {
        Usuario usuarioLogado = securityUtils.usuarioLogado();
        if (!usuarioLogado.getRole().equals(Role.ROLE_ADMIN) && !usuarioLogado.getId().equals(id)) {
            throw new TiposErros.SemPermissaoException("Você não tem permissão para excluir este Usuário");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new TiposErros.RecursoNaoEncontradoException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}
