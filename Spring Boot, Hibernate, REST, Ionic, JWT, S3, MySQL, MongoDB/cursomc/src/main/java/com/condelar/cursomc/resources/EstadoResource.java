package com.condelar.cursomc.resources;

import com.condelar.cursomc.domain.Cidade;
import com.condelar.cursomc.domain.Estado;
import com.condelar.cursomc.dto.CidadeDTO;
import com.condelar.cursomc.dto.EstadoDTO;
import com.condelar.cursomc.services.CidadeService;
import com.condelar.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDTO = list.stream().map(m -> new EstadoDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> list = cidadeService.findAll(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(m -> new CidadeDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
