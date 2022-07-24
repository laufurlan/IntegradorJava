package com.dh.clinicaSmile.serviceTest;

import com.dh.clinicaSmile.clinica.model.Odontologo;
import com.dh.clinicaSmile.clinica.model.Paciente;
import com.dh.clinicaSmile.clinica.model.Turno;
import com.dh.clinicaSmile.clinica.model.Domicilio;
import com.dh.clinicaSmile.clinica.service.DomicilioService;
import com.dh.clinicaSmile.clinica.service.OdontologoService;
import com.dh.clinicaSmile.clinica.service.PacienteService;
import com.dh.clinicaSmile.clinica.service.TurnoService;
import com.dh.clinicaSmile.exceptions.BadRequestException;
import com.dh.clinicaSmile.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    private static final Logger logger = Logger.getLogger(TurnoServiceTest.class);

    @Test
    public void agregarYBuscarTurnoTest() throws BadRequestException, ResourceNotFoundException {
        System.out.println("-------- Test agregar y buscar paciente --------\n");
        System.out.println("------------------------------------------------\n");
        Domicilio domicilio = new Domicilio("Mosconi", "456", "CABA", "Ciudad de Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Camila", "Furlan", "38321987", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("9808", "Cristian", "Frodo"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));
        logger.info("Se registra el turno con el id: " + turno.getId());
        Assert.assertNotNull(turnoService.buscar(turno.getId()));
    }


    @Test
    public void traerTodosLosTurnos() throws BadRequestException, ResourceNotFoundException {
        System.out.println("-------- Test traer turnos --------\n");
        System.out.println("-----------------------------------\n");
        Domicilio domicilio = new Domicilio("Mosconi", "456", "CABA", "Ciudad de Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Camila", "Furlan", "38321987", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("9808", "Cristian", "Frodo"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        Domicilio domicilio2 = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente2 = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio2));
        Odontologo odontologo2 = odontologoService.guardar(new Odontologo("154678", "Martin", "Rodriguez"));
        Turno turno2 = turnoService.guardar(new Turno(paciente2,odontologo2,new Date()));

        List<Turno> turnos = turnoService.buscarTodos();
        logger.info("Se buscan los turnos con en la base de datos");

        Assert.assertTrue(!turnos.isEmpty());
        Assert.assertTrue(turnos.size() > 0);
        System.out.println(turnos);
    }

    @Test
    public void actualizarTurnoTest() throws BadRequestException, ResourceNotFoundException {//error de detached entity --> par aque funcione se cambia el cascade type to merge
        System.out.println("\n---------------------------------------\n");
        System.out.println("-------- Test actualizar Turno --------\n");
        System.out.println("---------------------------------------\n");
        Domicilio d = domicilioService.guardar(new Domicilio("Av. Las Heras","1200","Capital", "Bs.As."));
        Paciente p = pacienteService.guardar(new Paciente("Belena", "Perez", "1234563", new Date(), d));
        Odontologo o = odontologoService.guardar(new Odontologo("235", "Blancas", "Perlas"));
        Turno t = turnoService.guardar(new Turno(p ,o, new Date()));
        Odontologo o2 = odontologoService.guardar(new Odontologo("100", "Odolito", "Dental"));
        Turno t2 = new Turno(t.getId(),p, o2, new Date());

        t2 = turnoService.actualizar(t2);
        logger.info("Se actualizo el turno.");
        Assert.assertEquals(turnoService.buscar(t2.getId()).getOdontologo().getNombre(), "Odolito");
        System.out.println(t2);
    }
}