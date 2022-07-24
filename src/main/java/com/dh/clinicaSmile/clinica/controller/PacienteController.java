package com.dh.clinicaSmile.clinica.controller;

import com.dh.clinicaSmile.clinica.model.Paciente;
import com.dh.clinicaSmile.clinica.service.PacienteService;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("eliminado Paciente con ID: " + id);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }
}
