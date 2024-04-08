package com.soltel.elex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soltel.elex.models.ActuacionesModel;
import com.soltel.elex.repositories.IActuacionesRepository;

import jakarta.transaction.Transactional;

@Service
public class ActuacionesService {

    @Autowired
    private IActuacionesRepository actuacionesRepository;

    public List<ActuacionesModel> selectActuaciones() {
        return actuacionesRepository.findAll();
    }

    public List<ActuacionesModel> selectActuaciones(boolean borrado) {
        if (borrado) {
            return actuacionesRepository.findAllByBorradoTrue();
        } else {
            return actuacionesRepository.findAllByBorradoFalse();
        }
    }

    public Optional<ActuacionesModel> selectIdActuacion(int id) {
        return actuacionesRepository.findById(id);
    }

    public Optional<ActuacionesModel> selectBorradoFalseActuacion(int id) {
        return actuacionesRepository.findByIdAndBorradoFalse(id);
    }

    public Optional<ActuacionesModel> selectBorradoTrueActuacion(int id) {
        return actuacionesRepository.findByIdAndBorradoTrue(id);
    }

    public ActuacionesModel insertActuacion(ActuacionesModel actuacion) {
        return actuacionesRepository.save(actuacion);
    }

    public ActuacionesModel updateActuacion(ActuacionesModel actuacion) {
        return actuacionesRepository.save(actuacion);
    }

    @Transactional
    public void deleteLogicoActuacion(int id) {
        ActuacionesModel actuacion = actuacionesRepository.findById(id).orElse(null);
        if (actuacion != null) {
            actuacion.setBorrado(true);
            actuacionesRepository.save(actuacion);
        }
    }

    @Transactional
    public void restaurarActuacion(int id) {
        ActuacionesModel actuacion = actuacionesRepository.findById(id).orElse(null);
        if (actuacion != null) {
            actuacion.setBorrado(false);
            actuacionesRepository.save(actuacion);
        }
    }


    public Optional<ActuacionesModel> obtenerActuacionPorId(int id) {
        return actuacionesRepository.findById(id);
    }

}
