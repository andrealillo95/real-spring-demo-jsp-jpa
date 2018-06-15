package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.services.FilmService;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController   //serve per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

    @Autowired
    @Qualifier("implFilm")
    FilmService filmService;

    @Autowired
    ResponseHelper responseHelper;

    Logger log = Logger.getLogger(FilmController.class);

    @GetMapping("/")
    public ResponseEntity getAll() {
        log.debug("getAll Film called");
        return responseHelper.ok(filmService.getAll());
    }

    @GetMapping("/{idFilm}")
    public ResponseEntity getFilmById(@PathVariable("idFilm") Long id) {
        log.debug("getFilmById Film called");
        if (id != null) {
            return responseHelper.ok(filmService.findById(id));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @GetMapping("/titolo/{taitol}")    //title troppo banale
    public ResponseEntity getFilmByTitolo(@PathVariable("taitol") String titolo) {
        log.debug("getFilmByTitolo Film called");
        if (titolo != null && titolo.length() > 0) {
            return responseHelper.ok(filmService.findByTitolo(titolo));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @GetMapping("/regista/{regista}")
    public ResponseEntity getFilmByRegista(@PathVariable("regista") String regista) {
        log.debug("getFilmByRegista Film called");
        if (regista != null && regista.length() > 0) {
            return responseHelper.ok(filmService.findByRegista(regista));
        }
        return responseHelper.badRequest("Invalid input data");
    }

    @PostMapping("/")
    public ResponseEntity addFilm(@RequestBody Film f1) {
        log.debug("addFilm() called with film: " + f1);
        try {
            return responseHelper.ok(filmService.add(f1));
        } catch (Exception e) {
            return responseHelper.badRequest(e.getMessage());
        }
    }

    @PutMapping("/{idFilm}")  //per modificarlo
    public ResponseEntity updateFilm(@RequestBody Film f1, @PathVariable("idFilm") long id) {
        log.debug("updateFilm() called with film: " + f1 + " and id: " + id);
        if (f1.getId() == id) {
            try {
                return responseHelper.ok(filmService.save(f1));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid input data");
        }
    }

    @DeleteMapping("/{idFilm}")  //per modificarlo
    public ResponseEntity deleteFilm(@RequestBody Film f1, @PathVariable("idFilm") long id) {
        log.debug("deleteFilm() called with film: " + f1 + " and id: " + id);
        if (f1.getId() == id) {
            try {
                return responseHelper.ok(filmService.delete(f1));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid input data, invalid ID");
        }
    }
}
