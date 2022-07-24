package com.dh.clinicaSmile.clinica.service;

import com.dh.clinicaSmile.clinica.model.Odontologo;
import com.dh.clinicaSmile.clinica.model.Paciente;
import com.dh.clinicaSmile.clinica.model.Turno;
import com.dh.clinicaSmile.clinica.repository.TurnoRepository;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno buscar(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isEmpty()){
            throw new ResourceNotFoundException("No existe ningun turno con el ID: " + id);
        }
        return turno.get();
    }

    public Turno guardar(Turno turno) throws ResourceNotFoundException, BadRequestException {
        if (turno.getPaciente().getId() == null || turno.getOdontologo().getId() == null) {
            throw new BadRequestException("Peticion invalida");
        }
        Odontologo odontologoEncontrado = odontologoService.buscar(turno.getOdontologo().getId());
        Paciente pacienteEncontrado = pacienteService.buscar(turno.getPaciente().getId());

        turno.setFecha(new Date());
        return turnoRepository.save(turno);

    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        buscar(id);
        turnoRepository.deleteById(id);
    }



    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }


    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }
}
