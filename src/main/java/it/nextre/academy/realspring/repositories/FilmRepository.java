package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
    Film findByTitoloLikeOrderByTitoloAsc(String titolo);
    Film findByTitoloLike(String titolo);
}
