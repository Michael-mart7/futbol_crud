package com.futbol.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.futbol.app.entidades.Asociacion;

public interface AsociacionRepositorio extends JpaRepository<Asociacion, Long> {
}
