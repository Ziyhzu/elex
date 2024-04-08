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
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "expedientes")
public class ExpedientesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "estado", nullable = false)
        private String estado;

    @Column(name = "opciones")
    private String opciones;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    @ManyToOne
    @JoinColumn(name = "tipo_expediente_FK", nullable = false)
    private TiposExpedienteModel tipoExpediente;

	@JsonIgnore
    @OneToMany(mappedBy = "expediente")
    private Set<ActuacionesModel> actuaciones;

    // Constructores
    
    public ExpedientesModel() { }
    
    
	public ExpedientesModel(String codigo, String responsable, String estado, String opciones,
			TiposExpedienteModel tipoExpediente) {
		super();
		this.codigo = codigo;
		this.responsable = responsable;
		this.estado = estado;
		this.opciones = opciones;
		this.tipoExpediente = tipoExpediente;
	}

	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getOpciones() {
		return opciones;
	}

	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public TiposExpedienteModel getTipoExpediente() {
		return tipoExpediente;
	}

	public void setTipoExpediente(TiposExpedienteModel tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	public Set<ActuacionesModel> getActuaciones() {
		return actuaciones;
	}

	public void setActuaciones(Set<ActuacionesModel> actuaciones) {
		this.actuaciones = actuaciones;
	}
}
