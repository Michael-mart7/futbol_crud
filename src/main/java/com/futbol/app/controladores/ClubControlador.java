package com.futbol.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.futbol.app.entidades.*;
import com.futbol.app.repositorios.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubes")
public class ClubControlador {

    @Autowired
    private ClubRepositorio clubRepo;
    @Autowired
    private EntrenadorRepositorio entrenadorRepo;
    @Autowired
    private AsociacionRepositorio asociacionRepo;
    @Autowired
    private CompeticionRepositorio competicionRepo;
    @Autowired
    private JugadorRepositorio jugadorRepo;

    // GET /api/clubes → listar todos
    @GetMapping
    public ResponseEntity<List<Club>> listar() {
        return ResponseEntity.ok(clubRepo.findAll());
    }

    // GET /api/clubes/{id} → obtener uno
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable String id) {
        return clubRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/clubes/entrenadores-disponibles → para saber cuáles están libres
    @GetMapping("/entrenadores-disponibles")
    public ResponseEntity<List<Entrenador>> entrenadoresDisponibles() {
        List<Entrenador> disponibles = entrenadorRepo.findAll()
            .stream()
            .filter(e -> clubRepo.findByEntrenador(e).isEmpty())
            .toList();
        return ResponseEntity.ok(disponibles);
    }

    // GET /api/clubes/asociaciones → listar asociaciones
    @GetMapping("/asociaciones")
    public ResponseEntity<List<Asociacion>> asociaciones() {
        return ResponseEntity.ok(asociacionRepo.findAll());
    }

    // GET /api/clubes/competiciones → listar competiciones
    @GetMapping("/competiciones")
    public ResponseEntity<List<Competicion>> competiciones() {
        return ResponseEntity.ok(competicionRepo.findAll());
    }

    // POST /api/clubes → crear club
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Club club) {
        if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
            Entrenador entrenador = entrenadorRepo.findById(club.getEntrenador().getId()).orElse(null);
            club.setEntrenador(entrenador);
        } else {
            club.setEntrenador(null);
        }

        if (club.getAsociacion() != null && club.getAsociacion().getId() != null) {
            Asociacion asociacion = asociacionRepo.findById(club.getAsociacion().getId()).orElse(null);
            club.setAsociacion(asociacion);
        } else {
            club.setAsociacion(null);
        }

        if (club.getCompeticiones() != null && !club.getCompeticiones().isEmpty()) {
            List<Competicion> comps = club.getCompeticiones().stream()
                .map(c -> competicionRepo.findById(c.getId()).orElse(null))
                .filter(c -> c != null)
                .toList();
            club.setCompeticiones(comps);
        } else {
            club.setCompeticiones(null);
        }

        Club guardado = clubRepo.save(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // PUT /api/clubes/{id} → editar club
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable String id, @RequestBody Club club) {
        if (!clubRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        club.setId(id);

        if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
            Entrenador entrenador = entrenadorRepo.findById(club.getEntrenador().getId()).orElse(null);
            club.setEntrenador(entrenador);
        } else {
            club.setEntrenador(null);
        }

        if (club.getAsociacion() != null && club.getAsociacion().getId() != null) {
            Asociacion asociacion = asociacionRepo.findById(club.getAsociacion().getId()).orElse(null);
            club.setAsociacion(asociacion);
        } else {
            club.setAsociacion(null);
        }

        if (club.getCompeticiones() != null && !club.getCompeticiones().isEmpty()) {
            List<Competicion> comps = club.getCompeticiones().stream()
                .map(c -> competicionRepo.findById(c.getId()).orElse(null))
                .filter(c -> c != null)
                .toList();
            club.setCompeticiones(comps);
        } else {
            club.setCompeticiones(null);
        }

        return ResponseEntity.ok(clubRepo.save(club));
    }

    // DELETE /api/clubes/{id} → eliminar club
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        if (!clubRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clubRepo.deleteById(id);
        return ResponseEntity.ok("Club eliminado correctamente.");
    }
}