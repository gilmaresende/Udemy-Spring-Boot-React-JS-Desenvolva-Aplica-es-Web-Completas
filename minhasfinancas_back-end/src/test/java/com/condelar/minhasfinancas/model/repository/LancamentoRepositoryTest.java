package com.condelar.minhasfinancas.model.repository;

import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.enums.TipoLancamento;
import org.assertj.core.api.Assertions;
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

        Assertions.assertThat(lancamento.getId()).isNotNull();
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
