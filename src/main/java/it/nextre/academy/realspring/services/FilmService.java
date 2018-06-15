package it.nextre.academy.realspring.services;

import it.nextre.academy.realspring.entities.Film;

import java.util.List;

public interface FilmService {

    List<Film> getAll();
    Film findById(long id);
    List<Film> findByTitolo(String titolo);
    List<Film> findByRegista(String regista);
    List<Film> findByAnno(int anno);
    Film add(Film f) throws Exception;
    Film save(Film f) throws Exception;
    boolean delete(Film f) throws Exception;
}
