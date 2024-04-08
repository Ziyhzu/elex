package com.soltel.elex.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentos")
public class DocumentosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ruta", nullable = false)
    private String ruta;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    @ManyToOne
    @JoinColumn(name = "actuacion_FK", nullable = false)
    private ActuacionesModel actuacion;
    
 // Constructores

    public DocumentosModel() { }
    
	public DocumentosModel(String nombre, String ruta, ActuacionesModel actuacion) {
		super();
		this.nombre = nombre;
		this.ruta = ruta;
		this.actuacion = actuacion;
	}
	
	// Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public ActuacionesModel getActuacion() {
		return actuacion;
	}

	public void setActuacion(ActuacionesModel actuacion) {
		this.actuacion = actuacion;
	}
}
