package com.condelar.cursomc.resources;

import com.condelar.cursomc.domain.Produto;
import com.condelar.cursomc.dto.CategoriaDTO;
import com.condelar.cursomc.dto.ProdutoDTO;
import com.condelar.cursomc.resources.utils.URL;
import com.condelar.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto ob = service.find(id);
        return ResponseEntity.ok().body(ob);
    }

    ///page?linesPerPage=5&page=0&direction=DESC
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
//        Page<ProdutoDTO> list = service.search(URL.decodeParam(nome),
//                URL.decodeIntList(categorias),
//                page,
//                linesPerPage,
//                orderBy,
//                direction).map(m -> new ProdutoDTO(m));

        Page<ProdutoDTO> list = service.search(URL.decodeParam(nome),
                URL.decodeIntList(categorias),
                page,
                linesPerPage,
                orderBy,
                direction).map(m -> new ProdutoDTO(m));
        return ResponseEntity.ok().body(list);
    }

}
