package com.dh.clinicaSmile.clinica.repository;



import com.dh.clinicaSmile.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional<Usuario> findByEmail(String email);

}