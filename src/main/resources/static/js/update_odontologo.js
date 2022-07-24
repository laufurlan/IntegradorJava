window.addEventListener('load', function () {

   const formulario = document.querySelector('#update_odontologo_form');

   formulario.addEventListener('submit', function (event) {
        event.preventDefault();

       let odontologoId = document.querySelector('#odontologo_id_update').value;


       const formData = {

           id: document.querySelector('#odontologo_id_update').value,

           nombre: document.querySelector('#nombre-update').value,

           apellido: document.querySelector('#apellido-update').value,

           matricula: document.querySelector('#matricula-update').value,
       };

       const url = '/odontologos';

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

        const url = '/odontologos'+"/"+id;

        const settings = {

            method: 'GET'

        }

        fetch(url,settings)

        .then(response => response.json())

        .then(data => {

            let odontologo = data;

            document.querySelector('#odontologo_id_update').value = odontologo.id;

            document.querySelector('#nombre-update').value = odontologo.nombre;

            document.querySelector('#apellido-update').value = odontologo.apellido;

            document.querySelector('#matricula-update').value = odontologo.matricula;

            document.querySelector('#div_odontologo_updating').style.display = "block";

        }).catch(error => {

            alert("Error: " + error);

 })

}
