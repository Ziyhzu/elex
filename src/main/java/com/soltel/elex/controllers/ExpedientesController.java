package com.soltel.elex.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soltel.elex.models.ExpedientesModel;
import com.soltel.elex.models.TiposExpedienteModel;
import com.soltel.elex.services.ExpedientesService;
import com.soltel.elex.services.TiposExpedienteService;



@RestController
@RequestMapping("/expedientes")
public class ExpedientesController {

    @Autowired
    private ExpedientesService expedientesService;
    private TiposExpedienteService tiposExpedienteService;


    // Constructor
    public ExpedientesController(ExpedientesService expedientesService, 
    TiposExpedienteService tiposExpedienteService) {
        this.expedientesService = expedientesService;
        this.tiposExpedienteService = tiposExpedienteService;
    }

    // SELECT general de expedientes
    @GetMapping("/select_all")
    public ResponseEntity<List<ExpedientesModel>> selectAllExpedientes(){
        return ResponseEntity.ok(expedientesService.selectExpedientes());
    }

    // SELECT general de expedientes con borrado en false
    @GetMapping("/select_all_borrado=false")
    public ResponseEntity<List<ExpedientesModel>> selectAllBorradoFalseExpedientes(){
        return ResponseEntity.ok(expedientesService.selectExpedientes(false));
    }

    // SELECT general de expedientes con borrado en true
    @GetMapping("/select_all_borrado=true")
    public ResponseEntity<List<ExpedientesModel>> selectAllBorradoTrueExpedientes(){
        return ResponseEntity.ok(expedientesService.selectExpedientes(true));
    }

    // SELECT general de expedientes por ID
    @GetMapping("/select_Id/{id}")
    public ResponseEntity<ExpedientesModel> selectIdExpediente(@PathVariable("id") int id) {
        Optional<ExpedientesModel> expediente = expedientesService.selectIdExpediente(id);
        return expediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // SELECT de expedientes por ID (Con Borrado false)
    @GetMapping("/select_Id_borrado=false/{id}")
    public ResponseEntity<?> selectBorradoFalseExpediente(@PathVariable int id) {
        return expedientesService.selectBorradoFalseExpediente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SELECT de expedientes por ID (Con Borrado true)
    @GetMapping("/select_Id_borrado=true/{id}")
    public ResponseEntity<?> selectBorradoTrueExpediente(@PathVariable int id) {
        return expedientesService.selectBorradoTrueExpediente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        // INSERT de expedientes
        @PostMapping("/insert/{codigo}/{responsable}/{estado}/{opciones}/{tipoExpediente}")
    public ResponseEntity<?> insertExpediente (@PathVariable String codigo,
                                               @PathVariable String responsable,
                                               @PathVariable String estado,
                                               @PathVariable String opciones,
                                               @PathVariable int tipoExpediente) {
        
        
        // 2º Tengo que buscar el tipo de expediente a partir del id
        Optional<TiposExpedienteModel> tipoBuscado = tiposExpedienteService.obtenerTipoExpedientePorId(tipoExpediente);

        if(tipoBuscado.isPresent()) {


            ExpedientesModel nuevoExpediente = new ExpedientesModel();
            TiposExpedienteModel tipobuscado = tipoBuscado.get();

            // Hago la asignación de todos los atributos (incluido el objeto tipo)
            nuevoExpediente.setCodigo(codigo);
            nuevoExpediente.setResponsable(responsable);
            nuevoExpediente.setEstado(estado);
            nuevoExpediente.setOpciones(opciones);
            nuevoExpediente.setTipoExpediente(tipobuscado);

            ExpedientesModel ExpedienteGuardado = expedientesService.insertExpediente(nuevoExpediente);
            return ResponseEntity.ok(ExpedienteGuardado);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ese tipo no existe!");           
        } 
    }

    // UPDATE dinamico que se actualiza en base a los valores que se le envie

    @PutMapping("/update/{id}/{codigo}/{responsable}/{estado}/{opciones}")
public ResponseEntity<?> actualizarExpediente(@PathVariable int id,
                                                @PathVariable String codigo,
                                                @PathVariable String responsable,
                                                @PathVariable String estado,
                                                @PathVariable String opciones) {

    Optional<ExpedientesModel> expediente = expedientesService.obtenerExpedientePorId(id);
    if (expediente.isPresent()) {
        ExpedientesModel expedienteActualizado = expediente.get();
        expedienteActualizado.setCodigo(codigo);
        expedienteActualizado.setResponsable(responsable);
        expedienteActualizado.setEstado(estado);
        expedienteActualizado.setOpciones(opciones);
        
        // No se actualiza el tipo de expediente en esta versión del método
        
        ExpedientesModel guardaExpediente = expedientesService.updateExpediente(expedienteActualizado);
        return ResponseEntity.ok(guardaExpediente);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expediente no encontrado");
    }
}

    /*
    @PutMapping("/update/{id}/{codigo}/{responsable}/{estado}/{opciones}/{TipoExpediente}")
    public ResponseEntity<?> actualizarExpediente(@PathVariable int id,
                                                @PathVariable String codigo,
    @PathVariable String responsable,
    @PathVariable String estado,
    @PathVariable String opciones,
    @PathVariable int TipoExpediente) {

        Optional<TiposExpedienteModel> tipo = tiposExpedienteService.obtenerTipoExpedientePorId(TipoExpediente);
        Optional<ExpedientesModel> expediente = expedientesService.obtenerExpedientePorId(id);
        if (expediente.isPresent()) {

            ExpedientesModel expedienteActualizado = expediente.get();
            TiposExpedienteModel tipoExpediente = tipo.get();

            expedienteActualizado.setCodigo(codigo);
            expedienteActualizado.setResponsable(responsable);
            expedienteActualizado.setEstado(estado);
            expedienteActualizado.setOpciones(opciones);
            expedienteActualizado.setTipoExpediente(tipoExpediente);
            ExpedientesModel guardaExpediente = expedientesService.updateExpediente(expedienteActualizado);
            return ResponseEntity.ok(guardaExpediente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expediente no encontrado");
        }
    }*/

    

// DELETE LOGICO
@PutMapping("/delete_logico/{id}")
public ResponseEntity<?> deleteLogico(@PathVariable int id) {
    expedientesService.deleteLogicoExpediente(id);
    return ResponseEntity.ok().build();
}

// DELETE LOGICO RESTAURACION
@PutMapping("/restaurar_delete_logico/{id}")
public ResponseEntity<?> restaurar(@PathVariable int id) {
    expedientesService.restaurarExpediente(id);
    return ResponseEntity.ok().build();
}
}
