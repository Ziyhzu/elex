package com.soltel.elex.controllers;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soltel.elex.models.ActuacionesModel;
import com.soltel.elex.models.ExpedientesModel;
import com.soltel.elex.services.ActuacionesService;
import com.soltel.elex.services.ExpedientesService;





@RestController
@RequestMapping("/actuaciones")
public class ActuacionesController {

    @Autowired
    private ActuacionesService actuacionesService;
    private ExpedientesService expedientesService;

    // Constructor
    public ActuacionesController(ExpedientesService expedientesService, 
    ActuacionesService actuacionesService) {
        this.expedientesService = expedientesService;
        this.actuacionesService = actuacionesService;
    }

    @GetMapping("/select_all")
    public ResponseEntity<List<ActuacionesModel>> selectAllActuaciones(){
        return ResponseEntity.ok(actuacionesService.selectActuaciones());
    }

    @GetMapping("/select_all_borrado=false")
    public ResponseEntity<List<ActuacionesModel>> selectAllBorradoFalseActuaciones(){
        return ResponseEntity.ok(actuacionesService.selectActuaciones(false));
    }

    @GetMapping("/select_all_borrado=true")
    public ResponseEntity<List<ActuacionesModel>> selectAllBorradoTrueActuaciones(){
        return ResponseEntity.ok(actuacionesService.selectActuaciones(true));
    }

    @GetMapping("/select_Id/{id}")
    public ResponseEntity<ActuacionesModel> selectIdActuacion(@PathVariable("id") int id) {
        Optional<ActuacionesModel> actuacion = actuacionesService.selectIdActuacion(id);
        return actuacion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/select_Id_borrado=false/{id}")
    public ResponseEntity<?> selectBorradoFalseActuacion(@PathVariable int id) {
        return actuacionesService.selectBorradoFalseActuacion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/select_Id_borrado=true/{id}")
    public ResponseEntity<?> selectBorradoTrueActuacion(@PathVariable int id) {
        return actuacionesService.selectBorradoTrueActuacion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/insert/{descripcion}/{responsable}/{estado}/{tasa}/{fecha}/{Expediente}")
    public ResponseEntity<?> insertActuacion (@PathVariable String descripcion,
                                @PathVariable String responsable,
                                @PathVariable String estado,
                                @PathVariable float tasa,
                                @PathVariable LocalDate fecha,
                                @PathVariable int Expediente) {
        
        
        // 2º Tengo que buscar el expediente a partir del id
        Optional<ExpedientesModel> expedienteBuscado = expedientesService.obtenerExpedientePorId(Expediente);

        if(expedienteBuscado.isPresent()) {


            ActuacionesModel nuevaActuacion = new ActuacionesModel();
            ExpedientesModel expedientebuscado = expedienteBuscado.get();

            // Hago la asignación de todos los atributos (incluido el objeto tipo)
            nuevaActuacion.setDescripcion(descripcion);
            nuevaActuacion.setResponsable(responsable);
            nuevaActuacion.setEstado(estado);
            nuevaActuacion.setTasa(tasa);
            nuevaActuacion.setFecha(fecha);
            nuevaActuacion.setExpediente(expedientebuscado);

            ActuacionesModel actuacioneGuardada = actuacionesService.insertActuacion(nuevaActuacion);
            return ResponseEntity.ok(actuacioneGuardada);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ese expediente no existe!");           
        } 
    }

    @PutMapping("/update/{id}/{descripcion}/{responsable}/{estado}/{tasa}/{fecha}")
    public ResponseEntity<?> actualizarExpediente(@PathVariable int id,
                                                   @PathVariable String descripcion,
                                                   @PathVariable String responsable,
                                                   @PathVariable String estado,
                                                   @PathVariable float tasa,
                                                   @PathVariable LocalDate fecha) {
    
        Optional<ActuacionesModel> actuacion = actuacionesService.obtenerActuacionPorId(id);
        if (actuacion.isPresent()) {
            ActuacionesModel actuacionActualizada = actuacion.get();
    
            actuacionActualizada.setDescripcion(descripcion);
            actuacionActualizada.setResponsable(responsable);
            actuacionActualizada.setEstado(estado);
            actuacionActualizada.setTasa(tasa);
            actuacionActualizada.setFecha(fecha);
    
            ActuacionesModel guardaActuacion = actuacionesService.updateActuacion(actuacionActualizada);
            return ResponseEntity.ok(guardaActuacion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actuacion no encontrado");
        }
    }
    
/*
    @PutMapping("/update/{id}/{descripcion}/{responsable}/{estado}/{tasa}/{fecha}/{Expediente}")
    public ResponseEntity<?> actualizarExpediente(@PathVariable int id,
    @PathVariable String descripcion,
    @PathVariable String responsable,
    @PathVariable String estado,
    @PathVariable float tasa,
    @PathVariable LocalDate fecha,
    @PathVariable int Expediente) {

        Optional<ActuacionesModel> actuacion = actuacionesService.obtenerActuacionPorId(id);
        Optional<ExpedientesModel> expediente = expedientesService.obtenerExpedientePorId(Expediente);
        if (actuacion.isPresent()) {

            ExpedientesModel expedientelisto = expediente.get();
            ActuacionesModel actuacionActualizada = actuacion.get();

            actuacionActualizada.setDescripcion(descripcion);
            actuacionActualizada.setResponsable(responsable);
            actuacionActualizada.setEstado(estado);
            actuacionActualizada.setTasa(tasa);
            actuacionActualizada.setFecha(fecha);
            actuacionActualizada.setExpediente(expedientelisto);
            ActuacionesModel guardaActuacion = actuacionesService.updateActuacion(actuacionActualizada);
            return ResponseEntity.ok(guardaActuacion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actuacion no encontrado");
        }
    }
     */

    @PutMapping("/delete_logico/{id}")
    public ResponseEntity<?> deleteLogico(@PathVariable int id) {
        actuacionesService.deleteLogicoActuacion(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/restaurar_delete_logico/{id}")
    public ResponseEntity<?> restaurar(@PathVariable int id) {
        actuacionesService.restaurarActuacion(id);
        return ResponseEntity.ok().build();
    }
 
}
