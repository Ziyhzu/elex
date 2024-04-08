package com.soltel.elex.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soltel.elex.models.DocumentosModel;
import com.soltel.elex.repositories.IDocumentosRepository;

import jakarta.transaction.Transactional;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import java.nio.file.Path;


@Service
public class DocumentosService {

    private static final String UPLOAD_DIR = "../../../../resources/uploads/";

    @Autowired
    private IDocumentosRepository documentosRepository;

    public List<DocumentosModel> selectDocumentos() {
        return documentosRepository.findAll();
    }

    public List<DocumentosModel> selectDocumentos(boolean borrado) {
        if (borrado) {
            return documentosRepository.findAllByBorradoTrue();
        } else {
            return documentosRepository.findAllByBorradoFalse();
        }
    }

    public Optional<DocumentosModel> selectIdDocumento(int id) {
        return documentosRepository.findById(id);
    }

    public Optional<DocumentosModel> selectBorradoFalseDocumento(int id) {
        return documentosRepository.findByIdAndBorradoFalse(id);
    }

    public Optional<DocumentosModel> selectBorradoTrueDocumento(int id) {
        return documentosRepository.findByIdAndBorradoTrue(id);
    }

    public DocumentosModel insertDocumento(DocumentosModel documento) {
        return documentosRepository.save(documento);
    }

    public DocumentosModel updateDocumento(DocumentosModel documento) {
        return documentosRepository.save(documento);
    }

    @Transactional
    public void deleteLogicoDocumento(int id) {
        DocumentosModel documento = documentosRepository.findById(id).orElse(null);
        if (documento != null) {
            documento.setBorrado(true);
            documentosRepository.save(documento);
        }
    }

    @Transactional
    public void restaurarDocumento(int id) {
        DocumentosModel documento = documentosRepository.findById(id).orElse(null);
        if (documento != null) {
            documento.setBorrado(false);
            documentosRepository.save(documento);
        }
    }


    public Optional<DocumentosModel> obtenerDocumentoPorId(int id) {
        return documentosRepository.findById(id);
    }

    public String guardarDocumento(MultipartFile archivo) throws IOException {
        // Generar un nombre único para el archivo
        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
        // Crear la ruta completa del archivo
        Path rutaCompleta = Paths.get(UPLOAD_DIR + nombreArchivo);
        // Guardar el archivo en el sistema de archivos
        Files.copy(archivo.getInputStream(), rutaCompleta);
        // Devolver la URL del archivo
        return rutaCompleta.toString();
    }

}

