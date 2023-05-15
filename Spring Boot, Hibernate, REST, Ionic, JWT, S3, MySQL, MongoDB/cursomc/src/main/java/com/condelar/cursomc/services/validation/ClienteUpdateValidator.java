package com.condelar.cursomc.services.validation;

import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.dto.ClienteDTO;
import com.condelar.cursomc.dto.ClienteNewDTO;
import com.condelar.cursomc.repositories.ClienteRepository;
import com.condelar.cursomc.resources.exceptions.FieldMessage;
import com.condelar.cursomc.services.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente ob = repo.findByEmail(dto.getEmail());
        if (ob != null && !ob.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "e-mail já existente!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}