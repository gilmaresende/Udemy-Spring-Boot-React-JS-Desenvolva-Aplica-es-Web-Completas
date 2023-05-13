package com.condelar.cursomc.resources;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.dto.CategoriaDTO;
import com.condelar.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria ob = service.find(id);
        return ResponseEntity.ok().body(ob);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @RequestBody Categoria obj) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = service.findAll().stream().map(m -> new CategoriaDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    ///page?linesPerPage=5&page=0&direction=DESC
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<CategoriaDTO> list = service.findPage(
                page,
                linesPerPage,
                orderBy,
                direction).map(m -> new CategoriaDTO(m));
        return ResponseEntity.ok().body(list);
    }
}
