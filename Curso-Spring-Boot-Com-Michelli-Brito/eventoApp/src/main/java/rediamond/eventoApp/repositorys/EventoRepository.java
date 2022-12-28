package rediamond.eventoApp.repositorys;

import org.springframework.data.repository.CrudRepository;
import rediamond.eventoApp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
    Evento findByCodigo(long codigo);
}
