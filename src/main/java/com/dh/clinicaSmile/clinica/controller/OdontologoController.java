package com.dh.clinicaSmile.clinica.controller;

import com.dh.clinicaSmile.clinica.model.Odontologo;
import com.dh.clinicaSmile.clinica.service.OdontologoService;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException, BadRequestException {
        odontologoService.eliminar(id);
        return ResponseEntity.ok().body("Se Elimino el odontologo con id = "+id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }


    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
            return ResponseEntity.ok(odontologoService.actualizar(odontologo));
    }
}
