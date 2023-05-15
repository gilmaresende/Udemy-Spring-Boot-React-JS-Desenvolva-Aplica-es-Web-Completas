package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.dto.CategoriaDTO;
import com.condelar.cursomc.repositories.CategoriaRepository;
import com.condelar.cursomc.services.exeption.DataIntegrityException;
import com.condelar.cursomc.services.exeption.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> op = repo.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria ob = find(obj.getId());
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

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO dto) {
        Categoria ob = new Categoria(dto.getId(), dto.getNome());
        return ob;
    }


    private void updateData(Categoria obNew, Categoria objOld) {
        obNew.setNome(objOld.getNome());
    }
}
