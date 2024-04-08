package com.soltel.elex.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actuaciones")
public class ActuacionesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "tasa", nullable = false)
    private float tasa;

	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false)
        private String estado;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    @ManyToOne
    @JoinColumn(name = "expediente_FK", nullable = false)
    private ExpedientesModel expediente;

	@JsonIgnore
    @OneToMany(mappedBy = "actuacion")
    private Set<DocumentosModel> documentos;
    
    // Constructores
    
    public ActuacionesModel() { }
    
	public ActuacionesModel(String descripcion, String responsable, float tasa, LocalDate fecha, String estado,
			ExpedientesModel expediente) {
		super();
		this.descripcion = descripcion;
		this.responsable = responsable;
		this.tasa = tasa;
		this.fecha = fecha;
		this.estado = estado;
		this.expediente = expediente;
	}

	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public float getTasa() {
		return tasa;
	}

	public void setTasa(float tasa) {
		this.tasa = tasa;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public ExpedientesModel getExpediente() {
		return expediente;
	}

	public void setExpediente(ExpedientesModel expediente) {
		this.expediente = expediente;
	}

	public Set<DocumentosModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentosModel> documentos) {
		this.documentos = documentos;
	}

}
