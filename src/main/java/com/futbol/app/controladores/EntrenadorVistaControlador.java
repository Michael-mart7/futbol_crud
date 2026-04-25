package com.futbol.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.futbol.app.entidades.Entrenador;
import com.futbol.app.repositorios.EntrenadorRepositorio;

@Controller
public class EntrenadorVistaControlador {

    @Autowired
    private EntrenadorRepositorio entrenadorRepo;

    // Listar todos
    @GetMapping("/entrenadores")
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "entrenadores";
    }

    // Mostrar formulario nuevo
    @GetMapping("/entrenadores/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("accion", "Crear");
        return "form-entrenador";
    }

    // Mostrar formulario editar
    @GetMapping("/entrenadores/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Entrenador entrenador = entrenadorRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Entrenador no encontrado"));
        model.addAttribute("entrenador", entrenador);
        model.addAttribute("accion", "Editar");
        return "form-entrenador";
    }

    // Guardar (crear o editar)
    @PostMapping("/entrenadores/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador, RedirectAttributes ra) {
        entrenadorRepo.save(entrenador);
        ra.addFlashAttribute("success", "Entrenador guardado correctamente.");
        return "redirect:/entrenadores";
    }

    // Eliminar
    @GetMapping("/entrenadores/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes ra) {
        entrenadorRepo.deleteById(id);
        ra.addFlashAttribute("success", "Entrenador eliminado correctamente.");
        return "redirect:/entrenadores";
    }
}