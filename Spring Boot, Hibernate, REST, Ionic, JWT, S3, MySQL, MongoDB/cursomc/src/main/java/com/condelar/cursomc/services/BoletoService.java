package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preenchePagamentoComBoleto(PagamentoComBoleto pg, Date instante) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instante);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pg.setDataVencimento(cal.getTime());
    }
}
