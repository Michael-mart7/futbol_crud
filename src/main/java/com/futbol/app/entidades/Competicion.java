package com.futbol.app.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "competiciones")
public class Competicion {

    @Id
    private String id;
    private String nombre;
    private int montoPremio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getMontoPremio() { return montoPremio; }
    public void setMontoPremio(int montoPremio) { this.montoPremio = montoPremio; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
}