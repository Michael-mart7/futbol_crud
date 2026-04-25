package com.futbol.app.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.futbol.app.entidades.Jugador;
import com.futbol.app.entidades.Club;
import java.util.List;

public interface JugadorRepositorio extends MongoRepository<Jugador, String> {
    List<Jugador> findByClub(Club club);
}