package com.soltel.elex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soltel.elex.models.ExpedientesModel;
import com.soltel.elex.models.TiposExpedienteModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IExpedientesRepository extends JpaRepository<ExpedientesModel, Integer> {

    List<ExpedientesModel> findAllByBorradoFalse();
    List<ExpedientesModel> findAllByBorradoTrue();

    Optional<ExpedientesModel> findByIdAndBorradoFalse(int id);
    Optional<ExpedientesModel> findByIdAndBorradoTrue(int id);

    List<ExpedientesModel> findByCodigo(String codigo);
    List<ExpedientesModel> findByResponsable(String responsable);
    List<ExpedientesModel> findByEstado(String estado);
    List<ExpedientesModel> findByCodigoAndResponsable(String codigo, String responsable);
    List<ExpedientesModel> findByCodigoAndEstado(String codigo, String estado);

    List<ExpedientesModel> findByTipoExpediente(TiposExpedienteModel tipoExpediente);



    // Nuevo método para buscar expedientes por responsable y estado
    List<ExpedientesModel> findByResponsableAndEstado(String responsable, String estado);

    // Métodos adicionales para buscar por cada campo individualmente

    List<ExpedientesModel> findByCodigoAndResponsableAndEstado(String codigo, String responsable, String estado);

}


