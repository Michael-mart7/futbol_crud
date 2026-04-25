package com.futbol.app.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.futbol.app.entidades.Competicion;

public interface CompeticionRepositorio extends MongoRepository<Competicion, String> {
}