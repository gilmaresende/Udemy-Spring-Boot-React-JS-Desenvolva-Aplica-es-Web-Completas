package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Cidade;
import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.Endereco;
import com.condelar.cursomc.domain.enums.Perfil;
import com.condelar.cursomc.domain.enums.TipoCliente;
import com.condelar.cursomc.dto.ClienteDTO;
import com.condelar.cursomc.dto.ClienteNewDTO;
import com.condelar.cursomc.repositories.ClienteRepository;
import com.condelar.cursomc.repositories.EnderecoRepository;
import com.condelar.cursomc.security.UserSS;
import com.condelar.cursomc.services.exception.AuthorizationException;
import com.condelar.cursomc.services.exception.DataIntegrityException;
import com.condelar.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private S3Service s3Service;

    public Cliente find(Integer id) {

        UserSS user = UserService.authenticated();

        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso Negado");
        }

        Optional<Cliente> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    public Cliente update(Cliente obj) {
        Cliente ob = find(obj.getId());
        updateData(ob, obj);
        return repo.save(ob);
    }


    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir a categoria, possui produtos vinculados!");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
    }


    private void updateData(Cliente obNew, Cliente obOld) {
        obNew.setNome(obOld.getNome());
        obNew.setEmail(obOld.getEmail());
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return repo.save(obj);
    }

    public Cliente fromDTO(ClienteNewDTO dto) {
        Cliente ob = new Cliente();
        ob.setNome(dto.getNome());
        ob.setEmail(dto.getEmail());
        ob.setCpfOuCnpj(dto.getCpfOuCnpj());
        ob.setTipoCliente(TipoCliente.toEnum(dto.getTipoCliente()));
        ob.setSenha(passwordEncoder.encode(dto.getSenha()));
        Cidade cid = new Cidade(dto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, dto.getLogadouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cid, ob);
        ob.getEnderecos().add(end);

        ob.getTelefones().add(dto.getTelefone1());
        if (dto.getTelefone2() != null) {
            ob.getTelefones().add(dto.getTelefone2());
        }
        if (dto.getTelefone3() != null) {
            ob.getTelefones().add(dto.getTelefone3());
        }

        return ob;
    }

    public URI updateProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationException("Acesso Negado");
        }

        Optional<Cliente> op = repo.findById(user.getId());

        Cliente cliente = op.get();
        URI uri = s3Service.uploadFile(multipartFile);
        cliente.setImageUrl(uri.toString());
        repo.save(cliente);
        return uri;
    }
}
