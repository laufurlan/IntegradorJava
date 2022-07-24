package com.dh.clinicaSmile.clinica.service;


import com.dh.clinicaSmile.clinica.model.Paciente;
import com.dh.clinicaSmile.clinica.repository.PacienteRepository;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private DomicilioService domicilioService;

    private PacienteRepository pacienteRepository;
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente p) throws BadRequestException {
        p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public String eliminar(Integer id) throws ResourceNotFoundException {
            buscar(id);
            pacienteRepository.deleteById(id);
            return "Fue eliminado el Paciente con ID: " + id;

    }

     public Paciente buscar (Integer id) throws ResourceNotFoundException {
            Optional<Paciente> paciente = pacienteRepository.findById(id);
            if(paciente.isEmpty()){
                throw new ResourceNotFoundException("No existe ningun paciente con el ID: " + id);
            }
            return paciente.get();
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente actualizar(Paciente p) throws ResourceNotFoundException, BadRequestException {
        buscar(p.getId());
        domicilioService.buscar(p.getDomicilio().getId());
        return pacienteRepository.save(p);
    }
}
