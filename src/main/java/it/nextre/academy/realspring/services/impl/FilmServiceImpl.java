package it.nextre.academy.realspring.services.impl;

import it.nextre.academy.realspring.controllers.api.FilmController;
import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.repositories.FilmRepository;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("implFilm")
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    Logger log = Logger.getLogger(FilmController.class);

    public List<Film> getAll() {
        //List<Film>tmp = new ArrayList<>();
        log.debug("FilmService -> getAll() called");
        return (List<Film>) filmRepository.findAll();
    }

    public Film findById(long id) {
        log.debug("FilmService -> findById() called with id: " + id);
        return filmRepository.findOne(id);
    }

    public List<Film> findByTitolo(String titolo) {
        log.debug("FilmService -> findByTitolo() called with titolo: " + titolo);
        return filmRepository.findAllByTitoloContains(titolo);
    }

    public List<Film> findByRegista(String regista) {
        log.debug("FilmService -> findByRegista() called with regista: " + regista);
        return filmRepository.findAllByRegistaLike(regista);
    }

    public List<Film> findByAnno(int anno) {
        log.debug("FilmService -> findByAnno() called with anno: " + anno);
        return filmRepository.findAllByAnno(anno);
    }

    public Film add(Film f) throws Exception {
        if (f != null && f.getId() == 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            System.out.println("aggiungo" + f);
            Film tmp = filmRepository.save(f);
            log.debug(tmp);
            return tmp;
        } else {
            throw new Exception("Malformed film data");
        }
    }

    public Film save(Film f) throws Exception {
        if (f != null && f.getId() > 0 && f.getTitolo() != null && f.getTitolo().length() > 0) {
            Film tmp = filmRepository.save(f);
            log.debug(tmp);
            return tmp;
        } else {
            throw new Exception("Malformed film data");
        }
    }

    public boolean delete(Film f) throws Exception {
        log.debug("FilmService -> delete() called with film: " + f);
        if (f != null && f.getId() > 0) {
            filmRepository.delete(f.getId());
            return true;
        } else {
            throw new Exception("Malformed film data");
        }
    }
}
