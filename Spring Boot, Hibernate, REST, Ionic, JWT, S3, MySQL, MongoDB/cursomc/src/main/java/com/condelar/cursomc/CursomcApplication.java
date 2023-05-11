package com.condelar.cursomc;

import com.condelar.cursomc.domain.*;
import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.repositories.*;
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

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000D);
        Produto p2 = new Produto(null, "Impressora", 800D);
        Produto p3 = new Produto(null, "Mouse", 80D);


        cat1.getProdutos().addAll(Arrays.asList(p1, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        est1.getCidades().add(c1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);
        est2.getCidades().add(c2);
        est2.getCidades().add(c3);

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "ms@gmail.com", "12345678901", TipoCliente.PESSOA_FISICA);
        cli1.getTelefones().addAll(Arrays.asList("31-35740001", "31-35740002"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardom ", "38220834", c1, cli1);
        Endereco e2 = new Endereco(null, "Av. Matos", "105", "Sala 800", "Centro ", "38777012", c2, cli1);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));

        enderecoRepository.saveAll(Arrays.asList(e1, e2));

    }
}
