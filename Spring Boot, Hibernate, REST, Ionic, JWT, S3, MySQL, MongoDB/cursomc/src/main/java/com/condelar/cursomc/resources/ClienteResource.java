package com.condelar.cursomc.resources;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.dto.CategoriaDTO;
import com.condelar.cursomc.dto.ClienteDTO;
import com.condelar.cursomc.dto.ClienteNewDTO;
import com.condelar.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente ob = service.find(id);
        return ResponseEntity.ok().body(ob);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email) {
        Cliente ob = service.findByEmail(email);
        return ResponseEntity.ok().body(ob);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO dto) {
        Cliente ob = service.fromDTO(dto);
        ob = service.insert(ob);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ob.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ClienteDTO dto) {
        Cliente obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> list = service.findAll().stream().map(m -> new ClienteDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    ///page?linesPerPage=5&page=0&direction=DESC
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<ClienteDTO> list = service.findPage(
                page,
                linesPerPage,
                orderBy,
                direction).map(m -> new ClienteDTO(m));
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = service.updateProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
