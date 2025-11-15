package com.futbol.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.futbol.app.entidades.Jugador;

public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {
}
