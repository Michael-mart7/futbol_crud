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
public class JugadorControlador {

    @Autowired
    private ClubRepositorio clubRepo;
    @Autowired
    private JugadorRepositorio jugadorRepo;

    // GET /api/clubes/{clubId}/jugadores → listar jugadores de un club
    @GetMapping("/{clubId}/jugadores")
    public ResponseEntity<?> listarJugadores(@PathVariable String clubId) {
        Club club = clubRepo.findById(clubId).orElse(null);
        if (club == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado.");
        }
        return ResponseEntity.ok(club.getJugadores());
    }

    // POST /api/clubes/{clubId}/jugadores → agregar jugador a un club
    @PostMapping("/{clubId}/jugadores")
    public ResponseEntity<?> guardarJugador(@PathVariable String clubId, @RequestBody Jugador jugador) {
        Club club = clubRepo.findById(clubId).orElse(null);
        if (club == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado.");
        }
        jugador.setClub(club);
        Jugador guardado = jugadorRepo.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // DELETE /api/clubes/{clubId}/jugadores/{jugadorId} → eliminar jugador
    @DeleteMapping("/{clubId}/jugadores/{jugadorId}")
    public ResponseEntity<?> eliminarJugador(@PathVariable String clubId, @PathVariable String jugadorId) {
        if (!clubRepo.existsById(clubId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado.");
        }
        if (!jugadorRepo.existsById(jugadorId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jugador no encontrado.");
        }
        jugadorRepo.deleteById(jugadorId);
        return ResponseEntity.ok("Jugador eliminado correctamente.");
    }
}