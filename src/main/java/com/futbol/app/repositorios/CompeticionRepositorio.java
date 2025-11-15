package com.futbol.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.futbol.app.entidades.Competicion;

public interface CompeticionRepositorio extends JpaRepository<Competicion, Long> {
}
