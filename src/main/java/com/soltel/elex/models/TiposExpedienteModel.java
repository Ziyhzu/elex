package com.soltel.elex.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipos_expediente")
public class TiposExpedienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;


	// Relaciones
	@JsonIgnore
    @OneToMany(mappedBy = "tipoExpediente")
    private Set<ExpedientesModel> expedientes;

    
	// Constructores
    public TiposExpedienteModel() {
    }

    public TiposExpedienteModel(String tipo, String descripcion) {
        super();
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    // Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public Set<ExpedientesModel> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(Set<ExpedientesModel> expedientes) {
		this.expedientes = expedientes;
	}

}
