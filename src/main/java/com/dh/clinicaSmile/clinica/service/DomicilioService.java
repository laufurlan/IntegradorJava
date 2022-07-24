package com.dh.clinicaSmile.clinica.service;

import com.dh.clinicaSmile.clinica.model.Domicilio;
import com.dh.clinicaSmile.clinica.repository.DomicilioRepository;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DomicilioService {

    private DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio d){
        return domicilioRepository.save(d);
    }

    public Domicilio buscar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if(id == null) throw new BadRequestException("Petición invalida - falta ID de Domicilio");
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if(domicilio.isEmpty()){
            throw new ResourceNotFoundException("No existe un domicilio con el ID: " + id);
        }
        return domicilio.get();
    }


    public List<Domicilio> buscarTodos(){
        return domicilioRepository.findAll();
    }

    public void eliminar(Integer id){
        domicilioRepository.deleteById(id);
    }

    public Domicilio actualizar(Domicilio domicilio) throws ResourceNotFoundException, BadRequestException {
        buscar(domicilio.getId());
        return domicilioRepository.save(domicilio);
    }


}