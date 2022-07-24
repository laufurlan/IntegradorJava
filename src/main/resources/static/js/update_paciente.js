window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let pacienteId = document.querySelector('#paciente_id_update').value;


        const formData = {

            id: document.querySelector('#odontologo_id_update').value,

            nombre: document.querySelector('#nombre-update').value,

            apellido: document.querySelector('#apellido-update').value,

            dni: document.querySelector('#dni-update').value,
            fechaIngreso: document.querySelector('#fechaingreso-update').value,
            domicilio: {
                calle: document.querySelector('#calle-update').value,
                numero: document.querySelector('#numero-update').value,
                localidad: document.querySelector('#localidad-update').value,
                provincia: document.querySelector('#provincia-update').value,
            }
        };

        const url = '/pacientes';

        const settings = {

            method: 'PUT',

            headers: {

                'Content-Type': 'application/json',

            },

            body: JSON.stringify(formData)

        }

        fetch(url,settings)

            .then(response => response.json())

        location.reload()
    })

})

function findBy(id) {

    const url = '/pacientes'+"/"+id;

    const settings = {

        method: 'GET'

    }

    fetch(url,settings)

        .then(response => response.json())

        .then(data => {

            let paciente = data;

            document.querySelector('#paciente_id_update').value = paciente.id;

            document.querySelector('#nombre-update').value = paciente.nombre;

            document.querySelector('#apellido-update').value = paciente.apellido;

            document.querySelector('#dni-update').value = paciente.dni;

            document.querySelector('#fechaingreso-update').value = paciente.fechaIngreso;

            document.querySelector('#calle-update').value = paciente.domicilio.calle;

            document.querySelector('#numero-update').value = paciente.domicilio.numero;

            document.querySelector('#localidad-update').value = paciente.domicilio.localidad;

            document.querySelector('#provincia-update').value = paciente.domicilio.provincia;


            document.querySelector('#div_paciente_updating').style.display = "block";

        }).catch(error => {

        alert("Error: " + error);

    })

}
