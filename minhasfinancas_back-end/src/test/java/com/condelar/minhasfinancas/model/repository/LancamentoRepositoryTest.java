package com.condelar.minhasfinancas.model.repository;

import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.enums.StatusLancamento;
import com.condelar.minhasfinancas.model.enums.TipoLancamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LancamentoRepositoryTest {

    @Autowired
    LancamentoRepository repository;

    @Autowired
    TestEntityManager entityManeger;

    @Test
    public void deveSalvarUmLancamento() {
        Lancamento lancamento = newLancamento();

        repository.save(lancamento);

        assertThat(lancamento.getId()).isNotNull();
    }

    @Test
    public void deveDeletarUmLancamento() {
        Lancamento lancamento = criarEPersistirLancamento();

        lancamento = entityManeger.find(Lancamento.class, lancamento.getId());

        repository.delete(lancamento);

        Lancamento lancamentoInexistente = entityManeger.find(Lancamento.class, lancamento.getId());

        assertThat(lancamentoInexistente).isNull();
    }

    @Test
    public void deveAtualizarUmLancamento() {
        Lancamento lancamento = criarEPersistirLancamento();
        lancamento.setAno(2018);
        lancamento.setDescricao("teste atualizado");
        lancamento.setStatus(StatusLancamento.CANCELADO);

        repository.save(lancamento);

        Lancamento lancamentoatualizado = entityManeger.find(Lancamento.class, lancamento.getId());

        assertThat(lancamentoatualizado.getAno()).isEqualTo(2018);
        assertThat(lancamentoatualizado.getDescricao()).isEqualTo("teste atualizado");
        assertThat(lancamentoatualizado.getStatus()).isEqualTo(StatusLancamento.CANCELADO);
    }

    @Test
    public void deveBuscarLancamentoPorId() {
        Lancamento lancamento = criarEPersistirLancamento();

        Optional<Lancamento> lancamentoSalvo = repository.findById(lancamento.getId());

        assertThat(lancamentoSalvo.isPresent()).isTrue();
    }

    private Lancamento criarEPersistirLancamento() {
        Lancamento lancamento = newLancamento();
        entityManeger.persist(lancamento);
        return lancamento;
    }

    private Lancamento newLancamento() {
        Lancamento lancamento = new Lancamento().builder().
                mes(1).
                ano(2019).
                descricao("teste").
                valor(BigDecimal.valueOf(10)).
                tipo(TipoLancamento.RECEITA).
                dataCadastro(LocalDate.now()).build();
        return lancamento;
    }
}
