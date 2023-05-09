package com.condelar.cursomc;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.domain.Produto;
import com.condelar.cursomc.repositories.CategoriaRepository;
import com.condelar.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Gamer");

        Produto p1 = new Produto(null, "Computador", 2000D);
        Produto p2 = new Produto(null, "Impressora", 800D);
        Produto p3 = new Produto(null, "Mouse", 80D);
        Produto p4 = new Produto(null, "Manete", 120D);
        Produto p5 = new Produto(null, "HeadSet", 100D);

        cat1.getProdutos().addAll(Arrays.asList(p1, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        cat3.getProdutos().addAll(Arrays.asList(p4, p5));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        p4.getCategorias().addAll(Arrays.asList(cat1, cat3));
        p5.getCategorias().addAll(Arrays.asList(cat1, cat3));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

    }
}
