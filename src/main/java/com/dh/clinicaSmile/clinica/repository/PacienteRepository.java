package com.dh.clinicaSmile.clinica.repository;

import com.dh.clinicaSmile.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Integer> {
}
