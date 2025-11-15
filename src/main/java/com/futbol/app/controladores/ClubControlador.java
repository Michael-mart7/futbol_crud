package com.futbol.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.futbol.app.entidades.*;
import com.futbol.app.repositorios.*;

import java.util.List;

@Controller
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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listar")
    public String listar(Model model, @ModelAttribute("success") String success) {
        List<Club> clubes = clubRepo.findAll();
        model.addAttribute("clubes", clubes);
        return "listar";
    }

    @GetMapping("/nuevo")
    public String nuevoClub(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);

        List<Entrenador> entrenadoresDisponibles = entrenadorRepo.findAll()
            .stream()
            .filter(e -> clubRepo.findByEntrenador(e).isEmpty()) // no está asignado
            .toList();

        model.addAttribute("entrenadores", entrenadoresDisponibles);
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());

        return "form";
    }


    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club, RedirectAttributes ra) {

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

        clubRepo.save(club);

        ra.addFlashAttribute("success", "Club guardado correctamente.");
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarClub(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));
        model.addAttribute("club", club);

        List<Entrenador> entrenadoresDisponibles = entrenadorRepo.findAll()
            .stream()
            .filter(e -> {
                return clubRepo.findByEntrenador(e).isEmpty() || club.getEntrenador().equals(e);
            })
            .toList();

        model.addAttribute("entrenadores", entrenadoresDisponibles);
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "form";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        clubRepo.deleteById(id);
        ra.addFlashAttribute("success", "Club eliminado correctamente.");
        return "redirect:/listar";
    }

}
