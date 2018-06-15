package it.nextre.academy.realspring.services.impl;

import it.nextre.academy.realspring.controllers.FilmController;
import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("mockFilm")
public class FilmServiceMock implements FilmService {

    Logger log = Logger.getLogger(FilmController.class);

    private List<Film> videoteca = new ArrayList<>();

    {
        videoteca.add(new Film(1, "300", "Zack Snyder", 2007));
        videoteca.add(new Film(2, "Pacific Rim", "Guillermo del Toro", 2013));
        videoteca.add(new Film(3, "Dunkirk", "Christopher Nolan", 2017));
        videoteca.add(new Film(4, "Saw", "James Wan", 2004));
        videoteca.add(new Film(5, "Il Padrino", "Francis Ford Coppola", 1972));
        videoteca.add(new Film(6, "Arancia Meccanica", "Stanley Kubrick", 1971));
        videoteca.add(new Film(7, "Shining", "Stanley Kubrick", 1980));
        videoteca.add(new Film(8, "Italiano medio", "Marcello Macchia", 2015));
    }

    public List<Film> getAll() {
        //List<Film>tmp = new ArrayList<>();
        log.debug("FilmService -> getAll() called");
        return videoteca;
    }

    public Film findById(long id) {
        log.debug("FilmService -> findById() called with id: " + id);
        return videoteca.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Film> findByTitolo(String titolo) {
        log.debug("FilmService -> findByTitolo() called with id: " + titolo);
        return videoteca.stream()
                .filter(f -> f.getTitolo().contains(titolo))
                .collect(Collectors.toList());
    }

    public List<Film> findByRegista(String regista) {
        log.debug("FilmService -> findByRegista() called with id: " + regista);
        return videoteca.stream()
                .filter(f -> f.getRegista().contains(regista))
                .collect(Collectors.toList());
    }

    public List<Film> findByAnno(int anno) {
        log.debug("FilmService -> findByAnno() called with anno: " + anno);
        return videoteca.stream()
                .filter(f -> f.getAnno() == anno)
                .collect(Collectors.toList());
    }

    public Film add(Film f) throws Exception {
        if (f != null && f.getId() == 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            long id = videoteca.stream()
                    .max((f1, f2) -> (int) (f1.getId() - f2.getId())).get().getId();
            f.setId(++id);
            log.debug(f);
            //ora memorizzo il nuovo film
            videoteca.add(f);
            return f;
        } else {
            throw new Exception("Malformed film data");
        }
    }

    public Film save(Film f) throws Exception {
        if (f != null && f.getId() > 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            Optional<Film> v = videoteca.stream()
                    .filter(f1 -> f1.getId() == f.getId()).findFirst();
            if (v.isPresent()) {
                v.get().setAnno(f.getAnno());
                v.get().setTitolo(f.getTitolo());
                v.get().setRegista(f.getRegista());

                log.debug(v.get());
                return v.get();
            }
        } else {
            throw new Exception("Malformed film data");
        }
        return new Film();
    }

    public boolean delete(Film f) throws Exception{
        if (f != null && f.getId() > 0) {
            log.debug("FilmService -> delete() called with film: " + f);
            return videoteca.removeIf(f1 -> f1.getId() == f.getId());
        }else{
            throw new Exception("Malformed film data");
        }
        //return false;
    }
}
