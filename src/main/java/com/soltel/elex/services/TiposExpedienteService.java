package com.soltel.elex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soltel.elex.models.TiposExpedienteModel;
import com.soltel.elex.repositories.ITiposExpedienteRepository;

import jakarta.transaction.Transactional;


@Service
public class TiposExpedienteService {
    @Autowired
    private ITiposExpedienteRepository iTiposExpedienteRepository;

    // CONSULTAS

    // SELECT general de tipos de expediente
    public List<TiposExpedienteModel> selectTiposExpediente() {
        return iTiposExpedienteRepository.findAll();
    }

    // SELECT general de tipos de expediente (Con Borrado true) y (Con Borrado false)
    public List<TiposExpedienteModel> selectTiposExpediente(boolean borrado) {
        if (borrado) {
            return iTiposExpedienteRepository.findAllByBorradoTrue();
        } else {
            return iTiposExpedienteRepository.findAllByBorradoFalse();
        }
    }

    // SELECT general de tipos de expediente por ID
    public Optional<TiposExpedienteModel> selectIdTipoExpediente(int id) {
        return iTiposExpedienteRepository.findById(id);
    }

    // SELECT de tipos de expediente por ID (Con Borrado false)
    public Optional<TiposExpedienteModel> selectBorradoFalseTiposExpediente(int id) {
        return iTiposExpedienteRepository.findByIdAndBorradoFalse(id);
    }

    // SELECT de tipos de expediente por ID (Con Borrado true)
    public Optional<TiposExpedienteModel> selectBorradoTrueTiposExpediente(int id) {
        return iTiposExpedienteRepository.findByIdAndBorradoTrue(id);
    }


    // INSERCIONES

    //INSERT de tipos de expediente
    public TiposExpedienteModel insertTipoExpediente(TiposExpedienteModel tipoExpediente) {
        return iTiposExpedienteRepository.save(tipoExpediente);
    }

    // ACTUALIZACIONES

    public TiposExpedienteModel updateTipoExpediente(TiposExpedienteModel tipoexpediente) {
        return iTiposExpedienteRepository.save(tipoexpediente);
    }

    //DELETE LOGICO
    @Transactional
    public void deleteLogicoTipoExpediente(int id) {
        TiposExpedienteModel tipoExpediente = iTiposExpedienteRepository.findById(id).orElse(null);
        if (tipoExpediente != null) {
            tipoExpediente.setBorrado(true);
            iTiposExpedienteRepository.save(tipoExpediente);
        }
    }

    //DELETE LOGICO RESTAURACION
    @Transactional
    public void restaurarTipoExpediente(int id) {
        TiposExpedienteModel tipoExpediente = iTiposExpedienteRepository.findById(id).orElse(null);
        if (tipoExpediente != null) {
            tipoExpediente.setBorrado(false);
            iTiposExpedienteRepository.save(tipoExpediente);
        }
    }
    

    public Optional<TiposExpedienteModel> findByTipo(String tipo) {
        List<TiposExpedienteModel> tiposExpedientes = iTiposExpedienteRepository.findByTipo(tipo);
            return Optional.of(tiposExpedientes.get(0)); 
    }



    public Optional<TiposExpedienteModel> obtenerTipoExpedientePorId(int id) {
        return iTiposExpedienteRepository.findById(id);
    }
}
