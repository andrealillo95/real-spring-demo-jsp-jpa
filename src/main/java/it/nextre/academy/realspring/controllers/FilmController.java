package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.models.Film;
import it.nextre.academy.realspring.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController   //serve per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

    @Autowired    //cerca il filmService all'interno della sua libreria
    FilmService filmService;

    Logger log = Logger.getLogger(FilmController.class);

    @GetMapping("/")
    public List<Film> getAll(){
        log.debug("getAll Film called");
        return filmService.getAll();
    }

    @GetMapping("/{idFilm}")
    public Film getFilmById(@PathVariable("idFilm") Long id){
        log.debug("getFilmById Film called");
        if(id != null){
            return filmService.findById(id);
        }
        return null;
    }

    @GetMapping("/titolo/{taitol}")    //title troppo banale
    public List<Film> getFilmByTitolo(@PathVariable("taitol") String titolo){
        log.debug("getFilmByTitolo Film called");
        if(titolo != null && titolo.length() > 0){
            return filmService.findByTitolo(titolo);
        }
        return null;
    }

    @GetMapping("/regista/{regista}")
    public List<Film> getFilmByRegista(@PathVariable("regista") String regista){
        log.debug("getFilmByRegista Film called");
        if(regista != null && regista.length() > 0){
            return filmService.findByRegista(regista);
        }
        return null;
    }

    @PostMapping("/")
    public Film addFilm(@RequestBody Film f1){
        log.debug("addFilm() called with film: " + f1);
        return filmService.add(f1);
    }
}
