package com.futbol.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.futbol.app.entidades.*;
import com.futbol.app.repositorios.*;

@Controller
public class JugadorVistaControlador {

    @Autowired
    private ClubRepositorio clubRepo;
    @Autowired
    private JugadorRepositorio jugadorRepo;

    @GetMapping("/club/{clubId}/jugadores")
    public String listarJugadores(@PathVariable String clubId, Model model, RedirectAttributes ra) {
        Club club = clubRepo.findById(clubId).orElse(null);
        if (club == null) {
            ra.addFlashAttribute("success", "Club no encontrado.");
            return "redirect:/listar";
        }
        List<Jugador> jugadores = jugadorRepo.findByClub(club);
        model.addAttribute("club", club);
        model.addAttribute("jugadores", jugadores);
        return "jugadores";
    }

    @PostMapping("/club/{clubId}/jugadores/guardar")
    public String guardarJugador(@PathVariable String clubId, @ModelAttribute Jugador jugador, RedirectAttributes ra) {
        Club club = clubRepo.findById(clubId).orElse(null);
        if (club == null) {
            ra.addFlashAttribute("success", "Club no encontrado.");
            return "redirect:/listar";
        }
        jugador.setClub(club);
        jugadorRepo.save(jugador);
        ra.addFlashAttribute("success", "Jugador agregado correctamente al club " + club.getNombre() + ".");
        return "redirect:/club/" + clubId + "/jugadores";
    }
}