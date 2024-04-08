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

import com.soltel.elex.models.ActuacionesModel;
import com.soltel.elex.models.DocumentosModel;

import com.soltel.elex.services.ActuacionesService;
import com.soltel.elex.services.DocumentosService;


@RestController
@RequestMapping("/documentos")
public class DocumentosController {

    @Autowired
    private DocumentosService documentosService;
    private ActuacionesService actuacionesService;

    // Constructor
    public DocumentosController(DocumentosService documentosService, 
    ActuacionesService actuacionesService) {
        this.documentosService = documentosService;
        this.actuacionesService = actuacionesService;
    }

    @GetMapping("/select_all")
    public ResponseEntity<List<DocumentosModel>> selectAllDocumentos(){
        return ResponseEntity.ok(documentosService.selectDocumentos());
    }

    @GetMapping("/select_all_borrado=false")
    public ResponseEntity<List<DocumentosModel>> selectAllBorradoFalseDocumentos(){
        return ResponseEntity.ok(documentosService.selectDocumentos(false));
    }

    @GetMapping("/select_all_borrado=true")
    public ResponseEntity<List<DocumentosModel>> selectAllBorradoTrueDocumentos(){
        return ResponseEntity.ok(documentosService.selectDocumentos(true));
    }

    @GetMapping("/select_Id/{id}")
    public ResponseEntity<DocumentosModel> selectIdDocumento(@PathVariable("id") int id) {
        Optional<DocumentosModel> documento = documentosService.selectIdDocumento(id);
        return documento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/select_Id_borrado=false/{id}")
    public ResponseEntity<?> selectBorradoFalseDocumento(@PathVariable int id) {
        return documentosService.selectBorradoFalseDocumento(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/select_Id_borrado=true/{id}")
    public ResponseEntity<?> selectBorradoTrueDocumento(@PathVariable int id) {
        return documentosService.selectBorradoTrueDocumento(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insert/{nombre}/{ruta}/{Actuacion}")
    public ResponseEntity<?> insertDocumento(@PathVariable String nombre,
                                             @PathVariable String ruta,
                                             @PathVariable int Actuacion) {

        // Buscar la actuación a partir del ID
        Optional<ActuacionesModel> actuacionBuscado = actuacionesService.obtenerActuacionPorId(Actuacion);

        if (actuacionBuscado.isPresent()) {
            
            DocumentosModel nuevoDocumento = new DocumentosModel();
            ActuacionesModel actuacionbuscado = actuacionBuscado.get();

            // Hago la asignación de todos los atributos (incluido el objeto tipo)
            nuevoDocumento.setNombre(nombre);
            nuevoDocumento.setRuta(ruta);
            nuevoDocumento.setActuacion(actuacionbuscado);

            // Guardar el nuevo documento en la base de datos
            DocumentosModel documentoGuardado = documentosService.insertDocumento(nuevoDocumento);
            
            return ResponseEntity.ok(documentoGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La actuación no existe");
        }
    }

    @PutMapping("/update/{id}/{nombre}/{ruta}")
public ResponseEntity<?> updateDocumento(@PathVariable int id,
                                         @PathVariable String nombre,
                                         @PathVariable String ruta) {

    // Buscar el documento por ID
    Optional<DocumentosModel> documento = documentosService.obtenerDocumentoPorId(id);

    if (documento.isPresent()) {
        DocumentosModel documentoActualizado = documento.get();

        // Actualizar los atributos del documento
        documentoActualizado.setNombre(nombre);
        documentoActualizado.setRuta(ruta);

        // Guardar el documento actualizado en la base de datos
        DocumentosModel guardarDocumento = documentosService.updateDocumento(documentoActualizado);
        
        return ResponseEntity.ok(guardarDocumento);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento no encontrado");
    }
}



/*
    @PutMapping("/update/{id}/{nombre}/{ruta}/{Actuacion}")
    public ResponseEntity<?> updateDocumento(@PathVariable int id,
                                             @PathVariable String nombre,
                                             @PathVariable String ruta,
                                             @PathVariable int Actuacion) {

        // Buscar el documento por ID
        Optional<DocumentosModel> documento = documentosService.obtenerDocumentoPorId(id);
        
        // Buscar la actuación por ID
        Optional<ActuacionesModel> actuacion = actuacionesService.obtenerActuacionPorId(Actuacion);

        if (actuacion.isPresent()) {
            DocumentosModel documentoActualizado = documento.get();
            ActuacionesModel actuacionEncontrada = actuacion.get();

            // Actualizar los atributos del documento
            documentoActualizado.setNombre(nombre);
            documentoActualizado.setRuta(ruta);
            documentoActualizado.setActuacion(actuacionEncontrada);

            // Guardar el documento actualizado en la base de datos
            DocumentosModel guardarDocumento = documentosService.updateDocumento(documentoActualizado);
            
            return ResponseEntity.ok(guardarDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento o actuación no encontrados");
        }
    }
    */

    @PutMapping("/delete_logico/{id}")
    public ResponseEntity<?> deleteLogico(@PathVariable int id) {
        documentosService.deleteLogicoDocumento(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/restaurar_delete_logico/{id}")
    public ResponseEntity<?> restaurar(@PathVariable int id) {
        documentosService.restaurarDocumento(id);
        return ResponseEntity.ok().build();
    }

}


