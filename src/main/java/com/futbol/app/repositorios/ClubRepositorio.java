package com.futbol.app.repositorios;

import com.futbol.app.entidades.Club;
import com.futbol.app.entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepositorio extends JpaRepository<Club, Long> {

    Optional<Club> findByEntrenador(Entrenador entrenador);
}
