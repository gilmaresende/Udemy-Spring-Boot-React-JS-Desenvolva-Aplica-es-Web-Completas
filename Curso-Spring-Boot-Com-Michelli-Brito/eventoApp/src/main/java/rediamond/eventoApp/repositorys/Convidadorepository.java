package rediamond.eventoApp.repositorys;

import org.springframework.data.repository.CrudRepository;
import rediamond.eventoApp.models.Convidado;
import rediamond.eventoApp.models.Evento;

public interface Convidadorepository extends CrudRepository<Convidado, String> {
    Iterable<Convidado> findByEvento(Evento evento);

    Convidado findByRg(String rg);
}
