package com.condelar.cursomc.services.validation;

import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.dto.ClienteNewDTO;
import com.condelar.cursomc.resources.exceptions.FieldMessage;
import com.condelar.cursomc.services.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista

        if (dto.getTipoCliente() == null) {
            list.add(new FieldMessage("tipo", "Tipo não pode ser nulo"));
        } else if (dto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF invalido!"));
        } else if (dto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(dto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}