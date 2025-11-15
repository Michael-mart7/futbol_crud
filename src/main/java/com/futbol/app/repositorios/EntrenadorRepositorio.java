package com.futbol.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.futbol.app.entidades.Entrenador;

public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> {
}
