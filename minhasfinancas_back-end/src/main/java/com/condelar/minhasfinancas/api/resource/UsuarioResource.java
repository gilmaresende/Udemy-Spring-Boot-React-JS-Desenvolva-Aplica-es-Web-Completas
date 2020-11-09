package com.condelar.minhasfinancas.api.resource;

import com.condelar.minhasfinancas.api.dto.UsuarioDTO;
import com.condelar.minhasfinancas.exception.ErroAutenticacao;
import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.services.LancamentoService;
import com.condelar.minhasfinancas.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService service;
    private final LancamentoService lancamentoService;

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/saldo")
    public ResponseEntity consultarSaldo(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.findById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        BigDecimal saldo = lancamentoService.consultarSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }

}
