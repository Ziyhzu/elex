package com.soltel.elex.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soltel.elex.models.TiposExpedienteModel;

@Repository
public interface ITiposExpedienteRepository extends JpaRepository<TiposExpedienteModel, Integer> {

    List<TiposExpedienteModel> findAllByBorradoFalse();
    List<TiposExpedienteModel> findAllByBorradoTrue();

    Optional<TiposExpedienteModel> findByIdAndBorradoFalse(int id);
    Optional<TiposExpedienteModel> findByIdAndBorradoTrue(int id);

    List<TiposExpedienteModel> findByDescripcion(String descripcion);
    List<TiposExpedienteModel> findByTipo(String tipo);
    List<TiposExpedienteModel> findByTipoAndDescripcion(String descripcion, String tipo);
}
