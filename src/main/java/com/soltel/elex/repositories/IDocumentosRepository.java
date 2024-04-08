package com.soltel.elex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soltel.elex.models.DocumentosModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDocumentosRepository extends JpaRepository<DocumentosModel, Integer> {

    List<DocumentosModel> findAllByBorradoFalse();
    List<DocumentosModel> findAllByBorradoTrue();

    Optional<DocumentosModel> findByIdAndBorradoFalse(int id);
    Optional<DocumentosModel> findByIdAndBorradoTrue(int id);

    List<DocumentosModel> findByNombre(String nombre);

}

