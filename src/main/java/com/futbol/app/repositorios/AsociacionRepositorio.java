package com.futbol.app.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.futbol.app.entidades.Asociacion;

public interface AsociacionRepositorio extends MongoRepository<Asociacion, String> {
}