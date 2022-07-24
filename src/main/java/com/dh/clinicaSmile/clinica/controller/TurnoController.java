package com.dh.clinicaSmile.clinica.controller;

import com.dh.clinicaSmile.clinica.model.Turno;
import com.dh.clinicaSmile.clinica.service.TurnoService;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    @Autowired
    private TurnoService turnoService;


    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok().body("Fue eliminado Turno con ID: " + id);
    }

    @PutMapping
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) throws ResourceNotFoundException, BadRequestException {
            return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}