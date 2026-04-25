package com.futbol.app.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.futbol.app.entidades.Entrenador;

public interface EntrenadorRepositorio extends MongoRepository<Entrenador, String> {
}