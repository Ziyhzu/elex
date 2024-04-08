package com.soltel.elex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soltel.elex.models.ActuacionesModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IActuacionesRepository extends JpaRepository<ActuacionesModel, Integer> {


    List<ActuacionesModel> findAllByBorradoFalse();
    List<ActuacionesModel> findAllByBorradoTrue();

    Optional<ActuacionesModel> findByIdAndBorradoFalse(int id);
    Optional<ActuacionesModel> findByIdAndBorradoTrue(int id);

    List<ActuacionesModel> findByDescripcion(String descripcion);
    List<ActuacionesModel> findByResponsable(String responsable);
    List<ActuacionesModel> findByEstado(String estado);
    List<ActuacionesModel> findByDescripcionAndResponsable(String descripcion, String responsable);
    List<ActuacionesModel> findByDescripcionAndEstado(String descripcion, String estado);

    List<ActuacionesModel> findByResponsableAndEstado(String responsable, String estado);


}
