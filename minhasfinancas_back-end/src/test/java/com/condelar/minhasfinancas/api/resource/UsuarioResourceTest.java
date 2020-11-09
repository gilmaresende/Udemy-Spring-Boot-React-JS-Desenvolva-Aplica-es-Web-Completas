package com.condelar.minhasfinancas.api.resource;

import com.condelar.minhasfinancas.api.dto.UsuarioDTO;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.services.LancamentoService;
import com.condelar.minhasfinancas.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = UsuarioResource.class)
@AutoConfigureMockMvc
public class UsuarioResourceTest {

    static final String API = "/api/usuario";
    static final MediaType JSON = MediaType.APPLICATION_JSON;

    @Autowired
    MockMvc mvc;

    @MockBean
    UsuarioService service;

    @MockBean
    LancamentoService serviceLancamento;

    @Test
    public void deveautenticarUmUsuario() throws Exception {
        String senha = "usuario@teste.com";
        String email = "senha";
        //cenario
        UsuarioDTO dto = UsuarioDTO.builder().email(email).senha(senha).build();
        Usuario usuario = Usuario.builder().id(1l).email(email).senha(senha).build();

        Mockito.when(service.autenticar(email, senha)).thenReturn(usuario);

        String json = new ObjectMapper().writeValueAsString(dto);
        //execucao e verificacao

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/autenticar")).accept(JSON)
                .contentType(JSON).content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(usuario.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(usuario.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(usuario.getEmail()));

    }

}
