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

import com.soltel.elex.models.TiposExpedienteModel;
import com.soltel.elex.services.TiposExpedienteService;

@RestController
@RequestMapping("/tipos_expediente")
public class TiposExpedienteController {

    @Autowired
    private TiposExpedienteService tiposExpedienteService;

    // Constructor
    public TiposExpedienteController (TiposExpedienteService tiposExpedienteService) {
        this.tiposExpedienteService = tiposExpedienteService;
    }

    // SELECT general de tipos de expediente
    @GetMapping("/select_all")
    public ResponseEntity<List<TiposExpedienteModel>> selectAllTiposExpediente(){
    return ResponseEntity.ok(tiposExpedienteService.selectTiposExpediente());
    }

    // SELECT general de tipos de expediente con borrado en false
    @GetMapping("/select_all_borrado=false")
    public ResponseEntity<List<TiposExpedienteModel>> selectAllBorradoFalseTiposExpediente(){
        return ResponseEntity.ok(tiposExpedienteService.selectTiposExpediente(false));
    }

    // SELECT general de tipos de expediente con borrado en true
    @GetMapping("/select_all_borrado=true")
    public ResponseEntity<List<TiposExpedienteModel>> selectAllBorradoTrueTiposExpediente(){
        return ResponseEntity.ok(tiposExpedienteService.selectTiposExpediente(true));
    }

    // SELECT general de tipos de expediente por ID
    @GetMapping("/select_Id/{id}")
    public ResponseEntity<TiposExpedienteModel> selectIdTiposExpediente(@PathVariable("id") int id) {
        Optional<TiposExpedienteModel> tipoExpediente = tiposExpedienteService.selectIdTipoExpediente(id);
        return tipoExpediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // SELECT de tipos de expediente por ID (Con Borrado false)
    @GetMapping("/select_Id_borrado=false/{id}")
    public ResponseEntity<?> selectBorradoFalseTiposExpediente(@PathVariable int id) {
        return tiposExpedienteService.selectBorradoFalseTiposExpediente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SELECT de tipos de expediente por ID (Con Borrado true)
    @GetMapping("/select_Id_borrado=true/{id}")
    public ResponseEntity<?> selectBorradoTrueTiposExpediente(@PathVariable int id) {
        return tiposExpedienteService.selectBorradoTrueTiposExpediente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
     @PostMapping("/insert/{tipo}/{descripcion}")
     public ResponseEntity<?> insertTipoExpediente (@PathVariable String tipo, @PathVariable String descripcion) {
         
             TiposExpedienteModel nuevoTipoExpediente = new TiposExpedienteModel();
 
             // Hago la asignación de todos los atributos (incluido el objeto tipo)
             nuevoTipoExpediente.setTipo(tipo);
             nuevoTipoExpediente.setDescripcion(descripcion);
 
             TiposExpedienteModel TipoExpedienteGuardado = tiposExpedienteService.insertTipoExpediente(nuevoTipoExpediente);
             return ResponseEntity.ok(TipoExpedienteGuardado);
        } 
     
        
    

    // UPDATE de tipos de expediente
    @PutMapping("/update/{id}/{tipo}/{descripcion}")
    public ResponseEntity<?> updateTipoExpediente(@PathVariable int id, @PathVariable String tipo, @PathVariable String descripcion) {
        Optional<TiposExpedienteModel> tipoExpediente = tiposExpedienteService.obtenerTipoExpedientePorId(id);
        if (tipoExpediente.isPresent()) {
            TiposExpedienteModel tipoExpedienteActualizado = tipoExpediente.get();
            tipoExpedienteActualizado.setTipo(tipo);
            tipoExpedienteActualizado.setDescripcion(descripcion);
            TiposExpedienteModel guardarTipoExpediente = tiposExpedienteService.updateTipoExpediente(tipoExpedienteActualizado);
            return ResponseEntity.ok(guardarTipoExpediente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de expediente no encontrado");
        }
    }

    // DELETE LOGICO
    @PutMapping("/delete_logico/{id}")
    public ResponseEntity<?> deleteLogico(@PathVariable int id) {
        tiposExpedienteService.deleteLogicoTipoExpediente(id);
        return ResponseEntity.ok().build();
    }

    // DELETE LOGICO RESTAURACION
    @PutMapping("/restaurar_delete_logico/{id}")
    public ResponseEntity<?> restaurar(@PathVariable int id) {
        tiposExpedienteService.restaurarTipoExpediente(id);
        return ResponseEntity.ok().build();
    }
    
}
