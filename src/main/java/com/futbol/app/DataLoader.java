package com.futbol.app;

import com.futbol.app.entidades.*;
import com.futbol.app.repositorios.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final EntrenadorRepositorio entrenadorRepo;
    private final AsociacionRepositorio asociacionRepo;
    private final CompeticionRepositorio competicionRepo;
    private final ClubRepositorio clubRepo;
    private final JugadorRepositorio jugadorRepo;

    public DataLoader(EntrenadorRepositorio entrenadorRepo,
                      AsociacionRepositorio asociacionRepo,
                      CompeticionRepositorio competicionRepo,
                      ClubRepositorio clubRepo,
                      JugadorRepositorio jugadorRepo) {
        this.entrenadorRepo = entrenadorRepo;
        this.asociacionRepo = asociacionRepo;
        this.competicionRepo = competicionRepo;
        this.clubRepo = clubRepo;
        this.jugadorRepo = jugadorRepo;
    }

    @Override
    public void run(String... args) {
        if (clubRepo.count() == 0) {

            // ENTRENADORES
            Entrenador e1 = new Entrenador();
            e1.setNombre("Carlos"); e1.setApellido("Gómez");
            e1.setEdad(45); e1.setNacionalidad("Colombiano");
            entrenadorRepo.save(e1);

            Entrenador e2 = new Entrenador();
            e2.setNombre("Miguel"); e2.setApellido("Pérez");
            e2.setEdad(39); e2.setNacionalidad("Argentino");
            entrenadorRepo.save(e2);

            Entrenador e3 = new Entrenador();
            e3.setNombre("José"); e3.setApellido("Martínez");
            e3.setEdad(50); e3.setNacionalidad("Uruguayo");
            entrenadorRepo.save(e3);

            Entrenador e4 = new Entrenador();
            e4.setNombre("Andrés"); e4.setApellido("Ruiz");
            e4.setEdad(41); e4.setNacionalidad("Chileno");
            entrenadorRepo.save(e4);

            Entrenador e5 = new Entrenador();
            e5.setNombre("Luis"); e5.setApellido("Torres");
            e5.setEdad(44); e5.setNacionalidad("Mexicano");
            entrenadorRepo.save(e5);

            Entrenador e6 = new Entrenador();
            e6.setNombre("Jhon"); e6.setApellido("Moreno");
            e6.setEdad(32); e6.setNacionalidad("Colombiano");
            entrenadorRepo.save(e6);

            Entrenador e7 = new Entrenador();
            e7.setNombre("Hansi"); e7.setApellido("Flick");
            e7.setEdad(60); e7.setNacionalidad("Alemán");
            entrenadorRepo.save(e7);

            // ASOCIACIONES
            Asociacion a1 = new Asociacion();
            a1.setNombre("Liga Colombiana"); a1.setPais("Colombia");
            a1.setPresidente("Ramiro García");
            asociacionRepo.save(a1);

            Asociacion a2 = new Asociacion();
            a2.setNombre("Premier League"); a2.setPais("Inglaterra");
            a2.setPresidente("Mark Adams");
            asociacionRepo.save(a2);

            Asociacion a3 = new Asociacion();
            a3.setNombre("Liga MX"); a3.setPais("México");
            a3.setPresidente("Ernesto López");
            asociacionRepo.save(a3);

            // COMPETICIONES
            Competicion c1 = new Competicion();
            c1.setNombre("Copa Libertadores");
            c1.setMontoPremio(23000000);
            competicionRepo.save(c1);

            Competicion c2 = new Competicion();
            c2.setNombre("Champions League");
            c2.setMontoPremio(50000000);
            competicionRepo.save(c2);

            Competicion c3 = new Competicion();
            c3.setNombre("Copa Sudamericana");
            c3.setMontoPremio(10000000);
            competicionRepo.save(c3);

            Competicion c4 = new Competicion();
            c4.setNombre("Concachampions");
            c4.setMontoPremio(5000000);
            competicionRepo.save(c4);

            // CLUBES
            Club club1 = new Club();
            club1.setNombre("Atlético Nacional");
            club1.setCiudad("Medellín");
            club1.setEntrenador(e1);
            club1.setAsociacion(a1);
            club1.setCompeticiones(List.of(c1, c3));
            clubRepo.save(club1);

            Club club2 = new Club();
            club2.setNombre("Liverpool FC");
            club2.setCiudad("Liverpool");
            club2.setEntrenador(e2);
            club2.setAsociacion(a2);
            club2.setCompeticiones(List.of(c2));
            clubRepo.save(club2);

            Club club3 = new Club();
            club3.setNombre("América de Cali");
            club3.setCiudad("Cali");
            club3.setEntrenador(e3);
            club3.setAsociacion(a1);
            club3.setCompeticiones(List.of(c1));
            clubRepo.save(club3);

            Club club4 = new Club();
            club4.setNombre("Club América");
            club4.setCiudad("Ciudad de México");
            club4.setEntrenador(e5);
            club4.setAsociacion(a3);
            club4.setCompeticiones(List.of(c4));
            clubRepo.save(club4);

            Club club5 = new Club();
            club5.setNombre("Universidad de Chile");
            club5.setCiudad("Santiago");
            club5.setEntrenador(e4);
            club5.setAsociacion(a1);
            club5.setCompeticiones(List.of(c1, c3));
            clubRepo.save(club5);

            // JUGADORES
            Jugador j1 = new Jugador();
            j1.setNombre("Juan"); j1.setApellido("Pérez");
            j1.setNumero(10); j1.setPosicion("Delantero");
            j1.setClub(club1); jugadorRepo.save(j1);

            Jugador j2 = new Jugador();
            j2.setNombre("Carlos"); j2.setApellido("Ramírez");
            j2.setNumero(7); j2.setPosicion("Mediocampista");
            j2.setClub(club1); jugadorRepo.save(j2);

            Jugador j3 = new Jugador();
            j3.setNombre("David"); j3.setApellido("López");
            j3.setNumero(9); j3.setPosicion("Delantero");
            j3.setClub(club2); jugadorRepo.save(j3);

            Jugador j4 = new Jugador();
            j4.setNombre("Andrés"); j4.setApellido("Cortés");
            j4.setNumero(5); j4.setPosicion("Defensa");
            j4.setClub(club2); jugadorRepo.save(j4);

            Jugador j5 = new Jugador();
            j5.setNombre("Sebastián"); j5.setApellido("Moreno");
            j5.setNumero(8); j5.setPosicion("Mediocampista");
            j5.setClub(club3); jugadorRepo.save(j5);

            Jugador j6 = new Jugador();
            j6.setNombre("Felipe"); j6.setApellido("Sánchez");
            j6.setNumero(4); j6.setPosicion("Defensa");
            j6.setClub(club3); jugadorRepo.save(j6);

            Jugador j7 = new Jugador();
            j7.setNombre("Mario"); j7.setApellido("Vargas");
            j7.setNumero(11); j7.setPosicion("Delantero");
            j7.setClub(club4); jugadorRepo.save(j7);

            Jugador j8 = new Jugador();
            j8.setNombre("José"); j8.setApellido("Díaz");
            j8.setNumero(6); j8.setPosicion("Mediocampista");
            j8.setClub(club4); jugadorRepo.save(j8);

            Jugador j9 = new Jugador();
            j9.setNombre("Cristian"); j9.setApellido("Rojas");
            j9.setNumero(2); j9.setPosicion("Defensa");
            j9.setClub(club5); jugadorRepo.save(j9);

            Jugador j10 = new Jugador();
            j10.setNombre("Ricardo"); j10.setApellido("Pineda");
            j10.setNumero(1); j10.setPosicion("Portero");
            j10.setClub(club5); jugadorRepo.save(j10);

            System.out.println("✅ Datos de ejemplo insertados correctamente.");
        }
    }
}