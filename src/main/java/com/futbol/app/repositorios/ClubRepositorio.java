package com.futbol.app.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.futbol.app.entidades.Club;
import com.futbol.app.entidades.Entrenador;
import com.futbol.app.entidades.Competicion;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepositorio extends MongoRepository<Club, String> {
    Optional<Club> findByEntrenador(Entrenador entrenador);
    List<Club> findByCompeticionesContaining(Competicion competicion);
}