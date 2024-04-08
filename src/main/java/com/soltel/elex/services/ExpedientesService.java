package com.soltel.elex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soltel.elex.models.ExpedientesModel;
import com.soltel.elex.repositories.IExpedientesRepository;

import jakarta.transaction.Transactional;

@Service
public class ExpedientesService {

    @Autowired
    private IExpedientesRepository expedientesRepository;

    // SELECT general de expedientes
    public List<ExpedientesModel> selectExpedientes() {
        return expedientesRepository.findAll();
    }

    // SELECT general de expedientes (Con Borrado true) y (Con Borrado false)
    public List<ExpedientesModel> selectExpedientes(boolean borrado) {
        if (borrado) {
            return expedientesRepository.findAllByBorradoTrue();
        } else {
            return expedientesRepository.findAllByBorradoFalse();
        }
    }

    // SELECT general de expedientes por ID
    public Optional<ExpedientesModel> selectIdExpediente(int id) {
        return expedientesRepository.findById(id);
    }

    // SELECT de expedientes por ID (Con Borrado false)
    public Optional<ExpedientesModel> selectBorradoFalseExpediente(int id) {
        return expedientesRepository.findByIdAndBorradoFalse(id);
    }

    // SELECT de expedientes por ID (Con Borrado true)
    public Optional<ExpedientesModel> selectBorradoTrueExpediente(int id) {
        return expedientesRepository.findByIdAndBorradoTrue(id);
    }

    // INSERT de expedientes
    public ExpedientesModel insertExpediente(ExpedientesModel expediente) {
        return expedientesRepository.save(expediente);
    }

    // UPDATE de expedientes
    public ExpedientesModel updateExpediente(ExpedientesModel expediente) {
        return expedientesRepository.save(expediente);
    }

    // DELETE LOGICO
    @Transactional
    public void deleteLogicoExpediente(int id) {
        ExpedientesModel expediente = expedientesRepository.findById(id).orElse(null);
        if (expediente != null) {
            expediente.setBorrado(true);
            expedientesRepository.save(expediente);
        }
    }

    // DELETE LOGICO RESTAURACION
    @Transactional
    public void restaurarExpediente(int id) {
        ExpedientesModel expediente = expedientesRepository.findById(id).orElse(null);
        if (expediente != null) {
            expediente.setBorrado(false);
            expedientesRepository.save(expediente);
        }
    }

    public Optional<ExpedientesModel> obtenerExpedientePorId(int id) {
        return expedientesRepository.findById(id);
    }

    
}
