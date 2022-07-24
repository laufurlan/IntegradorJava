package com.dh.clinicaSmile.serviceTest;
import com.dh.clinicaSmile.clinica.model.Domicilio;
import com.dh.clinicaSmile.clinica.model.Paciente;
import com.dh.clinicaSmile.clinica.service.DomicilioService;
import com.dh.clinicaSmile.clinica.service.PacienteService;
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

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DomicilioService domicilioService;

    private static final Logger logger = Logger.getLogger(PacienteServiceTest.class);


    //@BeforeClass
    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = domicilioService.guardar(new Domicilio("Mosconi", "456", "CABA", "Ciudad de Buenos Aires"));
        Paciente p = pacienteService.guardar(new Paciente("Camila", "Furlan", "38321987", new Date(), domicilio));
        Domicilio domicilio1 = domicilioService.guardar(new Domicilio("San juan", "523", "San Juan", "San juan"));
        Paciente p1 = pacienteService.guardar(new Paciente("Romualdo", "Rodriguez", "34455697", new Date(), domicilio1));
    }


    @Test
    public void agregarYBuscarPacienteTest() throws BadRequestException, ResourceNotFoundException {
        System.out.println("-------- Test agregar y buscar paciente --------\n");
        System.out.println("------------------------------------------------\n");
        this.cargarDataSet();
        Domicilio domicilio = domicilioService.guardar(new Domicilio("aranguren", "1333", "Flores", "Ciudad de Buenos Aires"));
        Paciente paciente = pacienteService.guardar(new Paciente("Carmen", "Hondura", "83545656", new Date(), domicilio));
        logger.info("Se guarda el paciente con el id: " + paciente.getId());
        Assert.assertNotNull(pacienteService.buscar(paciente.getId()));

    }

   /* @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException, BadRequestException {
        System.out.println("-------- Test eliminar paciente --------\n");
        System.out.println("----------------------------------------\n");
        Domicilio domicilio = domicilioService.guardar(new Domicilio("aranguren", "1333", "Flores", "Ciudad de Buenos Aires"));
        Paciente paciente = pacienteService.guardar(new Paciente("Carmen", "Honura", "83545656", new Date(), domicilio));
        logger.info("Se guarda el paciente con el id: " + paciente.getId());
        pacienteService.eliminar(paciente.getId());
        logger.info("Se elimina el paciente con el id: " + paciente.getId());
        Assert.assertTrue(pacienteService.buscar(paciente.getId()) == null);
    }*/

    @Test
    public void traerTodosTest() throws BadRequestException {
        System.out.println("-------- Test traer todos los paciente --------\n");
        System.out.println("-----------------------------------------------\n");
        Domicilio domicilio = new Domicilio("falsa", "123", "Carlos Paz", "Cordoba");
        Paciente paciente = pacienteService.guardar(new Paciente("lucas", "Ramirez", "878758789", new Date(), domicilio));
        Domicilio domicilio2 = new Domicilio("honduras", "836", "CABA", "Ciudad de Buenos Aires");
        Paciente paciente2 = pacienteService.guardar(new Paciente("Andres", "Furlan", "35515890", new Date(), domicilio2));

        List<Paciente> pacientes = pacienteService.buscarTodos();
        logger.info("Se buscan todos los pacientes en la base de datos.");
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }

   @Test
    public void actualizarPacienteTest() throws ResourceNotFoundException, BadRequestException {
        System.out.println("-------- Test actualizar paciente --------\n");
        System.out.println("------------------------------------------\n");
        Domicilio d = domicilioService.guardar(new Domicilio("Mojarrita","4560","Devoto", "Ciudad de Bueno Aires"));
        Paciente p = pacienteService.guardar(new Paciente("Argentina", "Argentino", "736465758", new Date(), d));
        logger.info("Se actualiza el paciente con el id:" + p.getId() + " Nombre: " + p.getNombre() + " Apellido: " + p.getApellido()+ " DNI: " + p.getDni());
        Paciente p_nuevo = new Paciente(p.getId(), "Hernan", "Hernandez", "87654433", new Date(), d);
        p_nuevo = pacienteService.actualizar(p_nuevo);
        logger.info("Se actualiza el paciente con el id:" + p_nuevo.getId() + " Nombre: " + p_nuevo.getNombre() + " Apellido: " + p_nuevo.getApellido()+ " DNI: " + p_nuevo.getDni());
        Assert.assertEquals(pacienteService.buscar(p_nuevo.getId()).getNombre(), "Jose");
        System.out.println(p_nuevo);

    }
}