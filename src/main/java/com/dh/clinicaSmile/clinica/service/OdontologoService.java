package com.dh.clinicaSmile.clinica.service;


import com.dh.clinicaSmile.clinica.model.Odontologo;
import com.dh.clinicaSmile.clinica.repository.OdontologoRepository;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {


    private OdontologoRepository odontologoRepository;
    private OdontologoService odontologoService;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public String eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {

            buscar(id);
            odontologoRepository.deleteById(id);
            return "Eliminado";
            }


    public Odontologo buscar(Integer id) throws ResourceNotFoundException {

        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isEmpty()){
            throw new ResourceNotFoundException("No existe un turno con el ID: " + id);
        }
        return odontologo.get();
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException {
        buscar(odontologo.getId());
        return odontologoRepository.save(odontologo);
    }

}
