package com.condelar.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.condelar.cursomc.domain.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.domain.Cidade;
import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.Endereco;
import com.condelar.cursomc.domain.Estado;
import com.condelar.cursomc.domain.ItemPedido;
import com.condelar.cursomc.domain.Pagamento;
import com.condelar.cursomc.domain.PagamentoComBoleto;
import com.condelar.cursomc.domain.PagamentoComCartao;
import com.condelar.cursomc.domain.Pedido;
import com.condelar.cursomc.domain.Produto;
import com.condelar.cursomc.domain.enums.EstadoPagamento;
import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.repositories.CategoriaRepository;
import com.condelar.cursomc.repositories.CidadeRepository;
import com.condelar.cursomc.repositories.ClienteRepository;
import com.condelar.cursomc.repositories.EnderecoRepository;
import com.condelar.cursomc.repositories.EstadoRepository;
import com.condelar.cursomc.repositories.ItemPedidoRepository;
import com.condelar.cursomc.repositories.PagamentoRepository;
import com.condelar.cursomc.repositories.PedidoRepository;
import com.condelar.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
        Categoria cat4 = new Categoria(null, "Eletronicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");


        Produto p1 = new Produto(null, "Computador", 2000D);
        Produto p2 = new Produto(null, "Impressora", 800D);
        Produto p3 = new Produto(null, "Mouse", 80D);

        Produto p4 = new Produto(null, "Mesa de Escritorio", 300D);
        Produto p5 = new Produto(null, "Toalha", 50D);
        Produto p6 = new Produto(null, "Coucha", 200D);
        Produto p7 = new Produto(null, "TV true color", 1200D);
        Produto p8 = new Produto(null, "Rochadeira", 800D);
        Produto p9 = new Produto(null, "Abajour", 100D);
        Produto p10 = new Produto(null, "Pendente", 180D);
        Produto p11 = new Produto(null, "Shampoo", 90D);


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

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

        Cliente cli1 = new Cliente(null, "Maria Silva", "gilmaresende@gmail.com", "12345678901", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("31-35740001", "31-35740002"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "gilmaresende2@gmail.com", "10118105493", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
        cli2.addPerfil(Perfil.ADMIN);
        cli2.getTelefones().addAll(Arrays.asList("31-35740003", "31-35740004"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardom ", "38220834", c1, cli1);
        Endereco e2 = new Endereco(null, "Av. Matos", "105", "Sala 800", "Centro ", "38777012", c2, cli1);


        Endereco e3 = new Endereco(null, "Rua Dom Runs", "750", "Apt 32", "Blue Dow ", "38220837", c1, cli2);
        Endereco e4 = new Endereco(null, "Av. Coleano Tadeu", "95", "Sala 90", "Centro ", "38777092", c2, cli2);


        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        cli2.getEnderecos().addAll(Arrays.asList(e3, e4));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));

        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);
        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido it1 = new ItemPedido(ped1, p1, 0D, 1, 2000D);

        ItemPedido it2 = new ItemPedido(ped1, p3, 0D, 2, 80D);

        ItemPedido it3 = new ItemPedido(ped2, p2, 100D, 1, 800D);

        ped1.getItens().addAll(Arrays.asList(it1, it2));
        ped2.getItens().addAll(Arrays.asList(it3));

        p1.getItens().addAll(Arrays.asList(it1));
        p2.getItens().addAll(Arrays.asList(it3));
        p3.getItens().addAll(Arrays.asList(it2));

        itemPedidoRepository.saveAll(Arrays.asList(it1, it2, it3));

        Produto p12 = new Produto(null, "Produto 12", 10.00);
        Produto p13 = new Produto(null, "Produto 13", 10.00);
        Produto p14 = new Produto(null, "Produto 14", 10.00);
        Produto p15 = new Produto(null, "Produto 15", 10.00);
        Produto p16 = new Produto(null, "Produto 16", 10.00);
        Produto p17 = new Produto(null, "Produto 17", 10.00);
        Produto p18 = new Produto(null, "Produto 18", 10.00);
        Produto p19 = new Produto(null, "Produto 19", 10.00);
        Produto p20 = new Produto(null, "Produto 20", 10.00);
        Produto p21 = new Produto(null, "Produto 21", 10.00);
        Produto p22 = new Produto(null, "Produto 22", 10.00);
        Produto p23 = new Produto(null, "Produto 23", 10.00);
        Produto p24 = new Produto(null, "Produto 24", 10.00);
        Produto p25 = new Produto(null, "Produto 25", 10.00);
        Produto p26 = new Produto(null, "Produto 26", 10.00);
        Produto p27 = new Produto(null, "Produto 27", 10.00);
        Produto p28 = new Produto(null, "Produto 28", 10.00);
        Produto p29 = new Produto(null, "Produto 29", 10.00);
        Produto p30 = new Produto(null, "Produto 30", 10.00);
        Produto p31 = new Produto(null, "Produto 31", 10.00);
        Produto p32 = new Produto(null, "Produto 32", 10.00);
        Produto p33 = new Produto(null, "Produto 33", 10.00);
        Produto p34 = new Produto(null, "Produto 34", 10.00);
        Produto p35 = new Produto(null, "Produto 35", 10.00);
        Produto p36 = new Produto(null, "Produto 36", 10.00);
        Produto p37 = new Produto(null, "Produto 37", 10.00);
        Produto p38 = new Produto(null, "Produto 38", 10.00);
        Produto p39 = new Produto(null, "Produto 39", 10.00);
        Produto p40 = new Produto(null, "Produto 40", 10.00);
        Produto p41 = new Produto(null, "Produto 41", 10.00);
        Produto p42 = new Produto(null, "Produto 42", 10.00);
        Produto p43 = new Produto(null, "Produto 43", 10.00);
        Produto p44 = new Produto(null, "Produto 44", 10.00);
        Produto p45 = new Produto(null, "Produto 45", 10.00);
        Produto p46 = new Produto(null, "Produto 46", 10.00);
        Produto p47 = new Produto(null, "Produto 47", 10.00);
        Produto p48 = new Produto(null, "Produto 48", 10.00);
        Produto p49 = new Produto(null, "Produto 49", 10.00);
        Produto p50 = new Produto(null, "Produto 50", 10.00);
        cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);

        produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

    }

}
