package com.condelar.cursomc.resources;

import com.condelar.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {

        Categoria c1 = new Categoria(1, "Informatica");
        Categoria c2 = new Categoria(2, "Escritorio");

        List<Categoria> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        return list;
    }
}
